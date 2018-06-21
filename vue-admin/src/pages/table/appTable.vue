<template>
  <div class="panel">
    <panel-title :title="$route.meta.title">
      <el-button @click.stop="on_refresh" size="small">
        <i class="fa fa-refresh"></i>
      </el-button>
      <router-link :to="{name: 'appSave'}" tag="span">
        <el-button type="primary" icon="plus" size="small">应用组录入</el-button>
      </router-link>
    </panel-title>
    <!-- @selection-change="on_batch_select" -->
    <div class="panel-body">
      <el-table
        :data="table_data" 
        v-loading="load_data"
        element-loading-text="拼命加载中"
        border
        stripe
        style="width: 100%;">
        <!-- <el-table-column
          type="selection"
          width="55">
        </el-table-column> -->
        <el-table-column
          prop="appId"
          label="id"
          width="60">
        </el-table-column>
        <el-table-column
          prop="appName"
          label="应用组名"
          width="120">
        </el-table-column>
        <el-table-column
          prop="appCode"
          label="应用组编码"
          width="120">
        </el-table-column>
        <el-table-column
          prop="appType"
          label="应用组类型"
          width="110">
        </el-table-column>
        <el-table-column
          prop="bg"
          label="BG"
          width="80">
        </el-table-column>
        <el-table-column
          prop="product"
          label="产品"
          width="120">
        </el-table-column>
        <el-table-column
          prop="business"
          label="业务线">
        </el-table-column>
        <el-table-column
          prop="owner"
          label="管理员">
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="创建时间">
        </el-table-column>
        <el-table-column
          prop="updateTime"
          label="修改时间">
        </el-table-column>
        <el-table-column
          prop="state"
          label="逻辑删除标记"
          width="90">
           <template scope="props">
            <span v-text="props.row.state == 1 ? '正常' : '删除'"></span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          width="90">
          <template scope="props">
            <router-link :to="{name: 'appUpdate', params: {appId:props.row.appId}}" tag="span">
              <el-button type="info" size="small" icon="edit">修改</el-button>
            </router-link>
            <!-- <el-button type="danger" size="small" icon="delete" @click="delete_data(props.row)">删除</el-button> -->
          </template>
        </el-table-column>
      </el-table>
      <bottom-tool-bar>
        <div slot="page">
          <el-pagination
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="10"
            layout="total, prev, pager, next"
            :total="total">
          </el-pagination>
        </div>
      </bottom-tool-bar>
    </div>
  </div>
</template>

<script type="text/javascript">
import { panelTitle, bottomToolBar } from "components";

export default {
  data() {
    return {
      table_data: null,
      //当前页码
      currentPage: 1,
      //数据总条目
      total: 0,
      //每页显示多少条数据
      length: 15,
      //请求时的loading效果
      load_data: true,
      //批量选择数组
      batch_select: []
    };
  },
  components: {
    panelTitle,
    bottomToolBar
  },
  created() {
    this.get_table_data();
  },
  methods: {
    //刷新
    on_refresh() {
      this.get_table_data();
    },

    //获取数据
    get_table_data() {
      this.load_data = true;
      // this.$fetch.api_table.list({
      //   page: this.currentPage,
      //   length: this.length
      // })
      //   .then(({data: {result, page, total}}) => {
      //     this.table_data = result
      //     this.currentPage = page
      //     this.total = total
      //     this.load_data = false
      //   })
      //   .catch(() => {
      //     this.load_data = false
      //   })

      this.currentPage = 1;
      this.total = 100;
      this.table_data = [
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
      ];

      this.load_data = false;
    },

    //页码选择
    handleCurrentChange(val) {
      this.currentPage = val;
      this.get_table_data();
    }

    //   //单个删除
    //   delete_data(item){
    //     this.$confirm('此操作将删除该数据, 是否继续?', '提示', {
    //       confirmButtonText: '确定',
    //       cancelButtonText: '取消',
    //       type: 'warning'
    //     })
    //       .then(() => {
    //         this.load_data = true
    //         this.$fetch.api_table.del(item)
    //           .then(({msg}) => {
    //             this.get_table_data()
    //             this.$message.success(msg)
    //           })
    //           .catch(() => {
    //           })
    //       })
    //       .catch(() => {
    //       })
    //   },
    //   //批量选择
    //   on_batch_select(val){
    //     this.batch_select = val
    //   },
    //   //批量删除
    //   on_batch_del(){
    //     this.$confirm('此操作将批量删除选择数据, 是否继续?', '提示', {
    //       confirmButtonText: '确定',
    //       cancelButtonText: '取消',
    //       type: 'warning'
    //     })
    //       .then(() => {
    //         this.load_data = true
    //         this.$fetch.api_table.batch_del(this.batch_select)
    //           .then(({msg}) => {
    //             this.get_table_data()
    //             this.$message.success(msg)
    //           })
    //           .catch(() => {
    //           })
    //       })
    //       .catch(() => {
    //       })
    //   }
  }
};
</script>
