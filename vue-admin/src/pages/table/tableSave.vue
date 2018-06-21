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
            <el-form-item label="建表SQL语句:" prop="logicPath">
              <el-input type="textarea" :rows="2" placeholder="请输入建表语句" v-model="form.sql" style="width: 380px;"> </el-input>
              <!-- <el-input v-model="form.sql" placeholder="请输入内容" style="width: 380px;"></el-input> -->
            </el-form-item>
            <el-form-item label="属主应用组:" prop="appId">
              <el-select v-model="form.appId" placeholder="请选择应用组(可搜索)" filterable style="width: 380px;"> 
                   <el-option
                    v-for="item in appOptions"
                    :key="item.appId"
                    :label="item.appName"
                    :value="item.appId">
                    <span style="float: left">{{ item.appName }} — {{item.appCode}} — {{item.appType}}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.appId }}</span>
                  </el-option>
               </el-select>
            </el-form-item>
            <el-form-item label="逻辑地址:" prop="logicPath">
              <el-input v-model="form.logicPath" placeholder="请输入内容" style="width: 380px;"></el-input>
            </el-form-item>
            <el-form-item label="真实地址:" prop="realPath">
              <el-input v-model="form.realPath"  placeholder="请输入内容" style="width: 380px;"></el-input>
            </el-form-item>
            <el-form-item label="目录类型:" prop="type">
               <el-radio-group v-model="form.type">
                <el-radio :label="1">普通</el-radio>
                <el-radio :label="2">个人</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="共享类型:" prop="shareType">
             <el-radio-group v-model="form.shareType">
                <el-radio :label="1">私有</el-radio>
                <el-radio :label="2">共享</el-radio>
              </el-radio-group> 
            </el-form-item>
            <el-form-item label="namenode地址:" prop="namenodeAddr">
              <el-input  v-model="form.namenodeAddr" placeholder="请输入内容" style="width: 380px;"></el-input>
            </el-form-item>
            <el-form-item label="逻辑删除标记:">
              <el-radio-group v-model="form.state">
                <el-radio :label="1">正常</el-radio>
                <el-radio :label="2">删除</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="权限分配:">
               <span>只读权限：</span>
               <el-select v-model="form.rapps" multiple filterable placeholder="请选择应用组用户" style="width: 380px;">
                  <el-option
                    v-for="item in appOptions"
                    :key="item.appId"
                    :label="item.appName"
                    :value="item.appId">
                    <span style="float: left">{{ item.appName }} — {{item.appCode}} — {{item.appType}}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.appId }}</span>
                  </el-option>
               </el-select>
               <span>读写权限：</span>
               <el-select v-model="form.rwapps" multiple filterable placeholder="请选择应用组(可搜索)" style="width: 380px;"> 
                   <el-option
                    v-for="item in appOptions"
                    :key="item.appId"
                    :label="item.appName"
                    :value="item.appId">
                    <span style="float: left">{{ item.appName }} — {{item.appCode}} — {{item.appType}}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.appId }}</span>
                  </el-option>
               </el-select>
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
        sql: "",
        appId: "",
        logicPath: "",
        realPath: "",
        type: 1,
        shareType: 1,
        namenodeAddr: "",
        // createTime: this.$dateFormat(new Date(), "yyyy-MM-dd"),
        // updateTime: this.$dateFormat(new Date(), "yyyy-MM-dd"),
        state: 1,
        rapps: [],
        rwapps: []
      },
      appOptions: [
        {
          appId: 1,
          appName: "svr01",
          appCode: "s0011",
          appType: "aaa",
          bg: "MIG",
          product: "p1",
          business: "b1",
          owner: "jar",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 1
        },
        {
          appId: 2,
          appName: "svr02",
          appCode: "s0011",
          appType: "aaa",
          bg: "MIG",
          product: "p1",
          business: "b1",
          owner: "jar",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 1
        },
        {
          appId: 3,
          appName: "asvr01",
          appCode: "s0011",
          appType: "aaa",
          bg: "MIG",
          product: "p1",
          business: "b1",
          owner: "jar",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 1
        },
        {
          appId: 4,
          appName: "dsvr01",
          appCode: "s0011",
          appType: "aaa",
          bg: "MIG",
          product: "p1",
          business: "b1",
          owner: "jar",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 0
        },
        {
          appId: 5,
          appName: "ssvr01",
          appCode: "s0011",
          appType: "aaa",
          bg: "MIG",
          product: "p1",
          business: "b1",
          owner: "jar",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 1
        },
        {
          appId: 12,
          appName: "srvr01",
          appCode: "s0011",
          appType: "aaa",
          bg: "MIG",
          product: "p1",
          business: "b1",
          owner: "jar",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 0
        },
        {
          appId: 122,
          appName: "svwr01",
          appCode: "s0011",
          appType: "aaa",
          bg: "MIG",
          product: "p1",
          business: "b1",
          owner: "jar",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 0
        },
        {
          appId: 212,
          appName: "svrq01",
          appCode: "s0011",
          appType: "aaa",
          bg: "MIG",
          product: "p1",
          business: "b1",
          owner: "jar",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 0
        }
      ],
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
        dirId: 1,
        appName: "svr01",
        logicPath: "s0011",
        realPath: "aaa",
        type: "普通",
        nameNodeAddr: "p1",
        createTime: "2018/6/11",
        updateTime: "2018/6/11",
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
        //
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
