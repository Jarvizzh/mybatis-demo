<!-- 应用组录入 -->
<template>
  <div class="panel">
    <panel-title :title="$route.meta.title"></panel-title>
    <div class="panel-body"
         v-loading="load_data"
         element-loading-text="拼命加载中">
      <el-row>
        <el-col :span="8">
          <el-form ref="form" :model="form" :rules="rules" label-width="150px">
            <el-form-item label="应用组名:" prop="appName">
              <el-input v-model="form.appName" placeholder="请输入内容" style="width: 350px;"></el-input>
            </el-form-item>
            <el-form-item label="应用组编码:" prop="appCode">
              <el-input v-model="form.appCode" placeholder="请输入内容" style="width: 350px;"></el-input>
            </el-form-item>
            <el-form-item label="应用组类型:" prop="appType">
              <el-input v-model="form.appType"  placeholder="请输入内容" style="width: 350px;"></el-input>
            </el-form-item>
            <el-form-item label="bg:" prop="bg">
              <el-input v-model="form.bg"  placeholder="请输入内容" style="width: 350px;"></el-input>
            </el-form-item>
            <el-form-item label="产品:" prop="product">
              <el-input v-model="form.product"  placeholder="请输入内容" style="width: 350px;"></el-input>
            </el-form-item>
            <el-form-item label="业务线:" prop="business">
              <el-input  v-model="form.business" placeholder="请输入内容" style="width: 350px;"></el-input>
            </el-form-item>
            <el-form-item label="管理员:" prop="owner">
              <el-input v-model="form.owner" style="width: 350px;" disabled></el-input>
            </el-form-item>
            <el-form-item label="创建时间:" prop="createTime">
              <el-date-picker
                v-model="form.createTime"
                type="date"
                format="yyyy-MM-dd"
                :editable="false"
                @change="on_change_date"
                placeholder="选择时间"
                style="width: 350px;">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="修改时间:" prop="updateTime">
              <el-date-picker
                v-model="form.updateTime"
                type="date"
                format="yyyy-MM-dd"
                :editable="false"
                @change="on_change_date1"
                placeholder="选择时间"
                style="width: 350px;">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="应用组用户:" prop="users" >
              <el-input v-model="form.users" placeholder="请输入应用组用户英文ID，以英文分号;隔开" style="width: 350px;"></el-input>
                <!-- <el-select v-model="form.users" multiple filterable placeholder="请选择应用组用户" style="width: 350px;">
                    <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    </el-option>
                </el-select> -->
             </el-form-item>
            <el-form-item label="逻辑删除标记:">
              <el-radio-group v-model="form.state">
                <el-radio :label="1">正常</el-radio>
                <el-radio :label="2">删除</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="on_submit_form" :loading="on_submit_loading">提交</el-button>
              <el-button @click="$router.back()">取消</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script type="text/javascript">
import { panelTitle } from "components";

export default {
  data() {
    return {
      form: {
        appName: "",
        appCode: "",
        appCode: "",
        bg: "",
        product: "",
        business: "",
        owner: "jarvizzhang",
        createTime: this.$dateFormat(new Date(), "yyyy-MM-dd"),
        updateTime: this.$dateFormat(new Date(), "yyyy-MM-dd"),
        // users: [],
        users: "",
        state: 1
      },
      // options: [
      //   {
      //     value: "选项1",
      //     label: "aa"
      //   },
      //   {
      //     value: "选项2",
      //     label: "vv"
      //   },
      //   {
      //     value: "选项3",
      //     label: "cc"
      //   },
      //   {
      //     value: "选项4",
      //     label: "dd"
      //   },
      //   {
      //     value: "选项5",
      //     label: "ee"
      //   }
      // ],
      route_id: this.$route.params.appId,
      load_data: false,
      on_submit_loading: false,
      rules: {
        appName: [
          { required: true, message: "应用组名不能为空", trigger: "blur" }
        ],
        appCode: [
          { required: true, message: "应用组编码不能为空", trigger: "blur" }
        ],
        appType: [
          { required: true, message: "应用组类型不能为空", trigger: "blur" }
        ],
        createDate: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    console.log("appId = " + this.route_id);
    this.route_id && this.get_form_data();
  },
  methods: {
    //根据appId获取数据
    get_form_data() {
      console.log("==================");
      this.load_data = true;
      // this.$fetch.api_table
      //   .get({
      //     id: this.route_id
      //   })
      //   .then(({ data }) => {
      //     this.form = data;
      //     this.load_data = false;
      //   })
      //   .catch(() => {
      //     this.load_data = false;
      //   });
      this.form = {
        appName: "svr01",
        appCode: "s0011",
        appType: "aaa",
        bg: "MIG",
        product: "p1",
        business: "b1",
        owner: "jar",
        createTime: "2018/6/11",
        updateTime: "2018/6/11",
        users: [],
        state: 1
      };

      this.load_data = false;
    },

    //时间选择改变时
    on_change_date(val) {
      this.$set(this.form, "createTime", val);
    },
    on_change_date1(val) {
      this.$set(this.form, "updateTime", val);
    },

    //提交
    on_submit_form() {
      var formData = JSON.stringify(this.form);
      console.log(formData);

      this.$refs.form.validate(valid => {
        if (!valid) {
          console.log("必填项未填写完整");
          return false;
        }
        this.on_submit_loading = true;

        // todo 此处发请求
        this.$message.success("success");
        setTimeout(this.$router.back(), 3000);
        // // ？？？
        // this.$fetch.api_table
        //   .save(this.form)
        //   .then(({ msg }) => {
        //     this.$message.success(msg);
        //     // setTimeout(this.$router.back(), 1000);
        //   })
        //   .catch(() => {
        //     this.on_submit_loading = false;
        //   });
      });
    }
  },

  components: {
    panelTitle
  }
};
</script>
