<template>
  <div class="panel">
    <panel-title :title="$route.meta.title"></panel-title>
    <div class="panel-body">
      {{msg}}
      <p v-if="seen">now u see me.</p>
      <p v-else>oh ho</p>
    </div>
    <div class="panel-body">
      <ol>
        <li v-for="todo in todos" :key="todo.id">
          {{todo.text}} - {{todo.name}}
        </li>
      </ol>
      <ol>
        <li v-for="(value, key) in todos" :key="key">
          {{key}} : {{value}}
        </li>
      </ol>
      <ol>
        <li v-for="n in evenNums" v-if="n != 2" :key="n">{{n}}</li>
      </ol>
    </div>
    <div class="panel-body" id="example">
       <el-button @click="cnt += 1" size="small">Add</el-button>
        <span>result : {{cnt}}</span>
    </div>
    <div class="panel-body">
        <span>id : {{this.$route.params.id}}</span>
        <el-button @click="$router.back()" size="small">返回</el-button>
        <span>result: {{result}}</span>
        <el-button @click="get_data()" size="small">send</el-button>
    </div>
  </div>
</template>

<script type="text/javascript">
import { panelTitle, bottomToolBar } from "components";
import Vue from "vue";

export default {
  data() {
    return {
      seen: false,
      msg: "home page 123",
      todos: [
        { id: "1", text: "study js", name: "hello" },
        { id: "2", text: "study vue", name: "jar" }
      ],
      numbers: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
      cnt: 1,
      result: ""
    };
  },

  components: {
    panelTitle,
    bottomToolBar
  },

  computed: {
    evenNums: function() {
      return this.numbers.filter(function(number) {
        return number % 2 === 0;
      });
    }
  },

  methods: {
    get_data() {
      var vm = this;
      this.$http({
        url: "/api/tt", 
        method: "get" 
      })
        .then(function(res) {
          if (res.data.result == "success") {
            vm.result = res.data.result;
          }
          console.log(res, "成功");
        })
        .catch(function(err) {
          console.log(err, "错误");
        });
    }
  }
};
</script>
