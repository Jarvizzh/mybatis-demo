<!--共享目录/表-->
<template>
  <div class="panel">
    <panel-title :title="$route.meta.dir_title">
      <el-button @click.stop="on_refresh_dir" size="small">
        <i class="fa fa-refresh"></i> 
      </el-button>
      <router-link :to="{name: 'saveDir'}" tag="span">
        <el-button type="primary" icon="plus" size="small"> 添加目录 </el-button>
      </router-link>
    </panel-title>
    <div class="panel-body">
      <!-- 搜索目录 -->
      <el-input placeholder="请输入内容" v-model="str0" class="input-with-select" style="width:600px">
        <el-select v-model="condition_select0" slot="prepend" placeholder="请选择条件" style="width:130px;">
          <el-option label="应用组名" value="1"></el-option>
          <el-option label="逻辑地址" value="2"></el-option>
          <el-option label="真实地址" value="3"></el-option>
        </el-select>
        <el-button slot="append" @click="search_dir(condition_select0, str0)">搜索</el-button>
      </el-input>
      <el-table :data="dir_data" v-loading="load_dir" element-loading-text="拼命加载中"
        border @selection-change="on_batch_select_dir" style="width: 100%; margin-top:12px">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="app-table-expand">
              <el-form-item label="应用组名称">
                <span>{{ props.row.app.appName }}</span>
              </el-form-item>
              <el-form-item label="应用组编号">
                <span>{{ props.row.app.appCode }}</span>
              </el-form-item>
              <el-form-item label="应用组类型">
                <span>{{ props.row.app.appType }}</span>
              </el-form-item>
              <el-form-item label="BG">
                <span>{{ props.row.app.bg }}</span>
              </el-form-item>
              <el-form-item label="产品">
                <span>{{ props.row.app.product }}</span>
              </el-form-item>
              <el-form-item label="业务线">
                <span>{{ props.row.app.business }}</span>
              </el-form-item>
              <el-form-item label="管理员">
                <span>{{ props.row.app.owner }}</span>
              </el-form-item>
               <el-form-item label="创建时间">
                <span>{{ props.row.app.createTime }}</span>
              </el-form-item>
               <el-form-item label="修改时间">
                <span>{{ props.row.app.updateTime }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column type="selection"  width="55"> </el-table-column>
        <el-table-column prop="dirId" label="id"  width="80"> </el-table-column>
        <el-table-column prop="app.appName" label="属主应用组名" width="120"> </el-table-column>
        <el-table-column prop="logicPath" label="逻辑地址"></el-table-column>
        <el-table-column prop="realPath" label="真实地址"> </el-table-column>
        <el-table-column prop="type" label="目录类型" width="100"> </el-table-column>
        <!-- <el-table-column prop="shareType" label="共享类型" width="80"> </el-table-column> -->
        <el-table-column prop="nameNodeAddr" label="nameNode地址"></el-table-column>
        <el-table-column sortable prop="createTime" label="创建时间" width="120"></el-table-column>
        <el-table-column sortable prop="updateTime" label="修改时间" width="120"></el-table-column>
        <el-table-column prop="state" label="逻辑删除标记" width="90">
           <template scope="props">
            <span v-text="props.row.state == 1 ? '正常' : '删除'"></span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="170">
          <template scope="props">
            <router-link :to="{name: 'dirUpdate', params: {dirId: props.row.dirId}}" tag="span">
              <el-button type="info" size="small" icon="edit">修改</el-button>
            </router-link>
            <el-button type="danger" size="small" icon="delete" @click="delete_dir(props.row.dirId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <bottom-tool-bar>
        <el-button
          type="danger"
          icon="delete"
          size="small"
          :disabled="batch_select_dir.length === 0"
          @click="on_batch_del_dir"
          slot="handler">
          <span>批量删除</span>
        </el-button>
        <div slot="page">
          <el-pagination
            @current-change="handleDirCurrentChange"
            :current-page="dirCurrPage"
            :page-size="10"
            layout="total, prev, pager, next"
            :total="dirTotal">
          </el-pagination>
        </div>
      </bottom-tool-bar>
    </div>

    <panel-title :title="$route.meta.table_title">
      <el-button @click.stop="on_refresh_table" size="small">
        <i class="fa fa-refresh"></i> 
      </el-button>
      <router-link :to="{name: 'saveTable'}" tag="span">
        <el-button type="primary" icon="plus" size="small"> 添加表 </el-button>
      </router-link>
    </panel-title>
    <div class="panel-body">
      <el-input placeholder="请输入内容" v-model="str" class="input-with-select" style="width:600px">
        <el-select v-model="condition_select" slot="prepend" placeholder="请选择条件" style="width:130px;">
          <el-option label="应用组名" value="1"></el-option>
          <el-option label="逻辑地址" value="2"></el-option>
          <el-option label="真实地址" value="3"></el-option>
        </el-select>
        <el-button slot="append" @click="search_table(condition_select, str)" >搜索</el-button>
      </el-input>
      <el-table :data="table_data" v-loading="load_data" element-loading-text="拼命加载中"
        border @selection-change="on_batch_select" style="width: 100%; margin-top:12px">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="app-table-expand">
              <el-form-item label="逻辑地址">
                <span>{{ props.row.logicPath }}</span>
              </el-form-item>
              <el-form-item label="真实地址">
                <span>{{ props.row.realPath }}</span>
              </el-form-item>
              <el-form-item label="目录类型">
                <span>{{ props.row.type }}</span>
              </el-form-item>
              <el-form-item label="nameNode地址">
                <span>{{ props.row.nameNodeAddr }}</span>
              </el-form-item>
              <el-form-item label="应用组名称">
                <span>{{ props.row.app.appName }}</span>
              </el-form-item>
              <el-form-item label="应用组编号">
                <span>{{ props.row.app.appCode }}</span>
              </el-form-item>
              <el-form-item label="应用组类型">
                <span>{{ props.row.app.appType }}</span>
              </el-form-item>
              <el-form-item label="BG">
                <span>{{ props.row.app.bg }}</span>
              </el-form-item>
              <el-form-item label="产品">
                <span>{{ props.row.app.product }}</span>
              </el-form-item>
              <el-form-item label="业务线">
                <span>{{ props.row.app.business }}</span>
              </el-form-item>
              <el-form-item label="管理员">
                <span>{{ props.row.app.owner }}</span>
              </el-form-item>
               <el-form-item label="应用组创建时间">
                <span>{{ props.row.app.createTime }}</span>
              </el-form-item>
               <el-form-item label="应用组修改时间">
                <span>{{ props.row.app.updateTime }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column type="selection"  width="55"> </el-table-column>
        <el-table-column prop="dirId" label="id"  width="80"> </el-table-column>
        <el-table-column prop="sql" label="建表sql语句" > </el-table-column>
        <!-- <el-table-column prop="app.appName" label="属主应用组名" width="120"> </el-table-column>
        <el-table-column prop="logicPath" label="逻辑地址"></el-table-column>
        <el-table-column prop="realPath" label="真实地址"> </el-table-column>
        <el-table-column prop="type" label="目录类型" width="100"> </el-table-column>
            <el-table-column prop="shareType" label="共享类型" width="80"> </el-table-column>
        <el-table-column prop="nameNodeAddr" label="nameNode地址"></el-table-column> -->
        <el-table-column sortable prop="createTime" label="创建时间" width="120"></el-table-column>
        <el-table-column sortable prop="updateTime" label="修改时间" width="120"></el-table-column>
        <el-table-column prop="state" label="逻辑删除标记" width="90">
           <template scope="props">
            <span v-text="props.row.state == 1 ? '正常' : '删除'"></span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="170">
          <template scope="props">
            <router-link :to="{name: 'dirUpdate', params: {dirId: props.row.dirId}}" tag="span">
              <el-button type="info" size="small" icon="edit">修改</el-button>
            </router-link>
            <el-button type="danger" size="small" icon="delete" @click="delete_table(props.row.dirId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <bottom-tool-bar>
        <el-button
          type="danger"
          icon="delete"
          size="small"
          :disabled="batch_select.length === 0"
          @click="on_batch_del"
          slot="handler">
          <span>批量删除</span>
        </el-button>
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
      dir_data: [],
      table_data: [],
      //当前页码
      currentPage: 1,
      //数据总条目
      total: 0,
      //每页显示多少条数据
      length: 15,
      //请求时的loading效果
      load_dir: true,
      load_data: true,
      //批量选择数组
      batch_select_dir: [],
      batch_select: [],
      //搜索
      str0:'',
      str:'',
      condition_select0: [],
      condition_select: []
    };
  },
  components: {
    panelTitle,
    bottomToolBar
  },
  created() {
    this.get_dir_data();
    this.get_table_data();
  },
  methods: {
    //刷新
    on_refresh_dir() {
      this.get_dir_data();
    },
    on_refresh_table() {
      this.get_table_data();
    },
    //获取目录数据
    get_dir_data() {
      this.load_dir = true;
      this.dirCurrPage = 1;
      this.dirTotal = 150;
      this.dir_data = [
        {
          dirId: 1,
          app: {
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
          logicPath: "/mtt/profile/hive",
          realPath: "/user/mqq/mtt/profile/hive",
          type: "普通",
          nameNodeAddr: "10.191.128.11:9000",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 1
        },
        {
          dirId: 2,
          app: {
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
          logicPath: "/mtt/profile/hive",
          realPath: "/user/mqq/mtt/profile/hive",
          type: "普通",
          nameNodeAddr: "10.191.128.11:9000",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 2
        },
        {
          dirId: 3,
          app: {
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
          logicPath: "/mtt/profile/hive",
          realPath: "/user/mqq/mtt/profile/hive",
          type: "普通",
          nameNodeAddr: "10.191.128.11:9000",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 1
        },
        {
          dirId: 4,
          app: {
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
          logicPath: "/mtt/profile/hive",
          realPath: "/user/mqq/mtt/profile/hive",
          type: "普通",
          nameNodeAddr: "10.191.128.11:9000",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 1
        },
        {
          dirId: 5,
          app: {
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
          logicPath: "/mtt/profile/hive",
          realPath: "/user/mqq/mtt/profile/hive",
          type: "普通",
          nameNodeAddr: "10.191.128.11:9000",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 1
        },
        {
          dirId: 6,
          app: {
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
          logicPath: "/mtt/profile/hive",
          realPath: "/user/mqq/mtt/profile/hive",
          type: "普通",
          nameNodeAddr: "10.191.128.11:9000",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 1
        }
      ];
      this.load_dir = false;
    },
    //获取表数据
    get_table_data() {
      this.load_data = true;
      // this.$fetch.api_table
      //   .list({
      //     page: this.currentPage,
      //     length: this.length
      //   })
      //   .then(({ data: { result, page, total } }) => {
      //     this.table_data = result;
      //     this.currentPage = page;
      //     this.total = total;
      //     this.load_data = false;
      //   })
      //   .catch(() => {
      //     this.load_data = false;
      //   });
      this.currentPage = 1;
      this.total = 100;
      this.table_data = [
        {
          dirId: 1,
          sql:
            "CREATE TABLE `t_app_dir_relation` (`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',`app_id` int(11) NOT NULL COMMENT 'app_id',`dir_id` int(11) NOT NULL COMMENT 'dir_id',`type` tinyint(4) NOT NULL ,`create_time` datetime NOT NULL ,`update_time` datetime NOT NULL ,`state` tinyint(4) NOT NULL,PRIMARY KEY (`id`),UNIQUE KEY `app_dir_type` (`app_id`,`dir_id`,`type`)) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ",
          app: {
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
          logicPath: "/mtt/profile/hive",
          realPath: "/user/mqq/mtt/profile/hive",
          type: "普通",
          nameNodeAddr: "10.191.128.11:9000",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 1
        },
        {
          dirId: 2,
          sql:
            "CREATE TABLE `t_app_dir_relation` (`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',`app_id` int(11) NOT NULL COMMENT 'app_id',`dir_id` int(11) NOT NULL COMMENT 'dir_id',`type` tinyint(4) NOT NULL ,`create_time` datetime NOT NULL ,`update_time` datetime NOT NULL ,`state` tinyint(4) NOT NULL,PRIMARY KEY (`id`),UNIQUE KEY `app_dir_type` (`app_id`,`dir_id`,`type`)) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ",
          app: {
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
          logicPath: "/mtt/profile/hive",
          realPath: "/user/mqq/mtt/profile/hive",
          type: "普通",
          nameNodeAddr: "10.191.128.11:9000",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 2
        },
        {
          dirId: 3,
          sql:
            "CREATE TABLE `t_app_dir_relation` (`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',`app_id` int(11) NOT NULL COMMENT 'app_id',`dir_id` int(11) NOT NULL COMMENT 'dir_id',`type` tinyint(4) NOT NULL ,`create_time` datetime NOT NULL ,`update_time` datetime NOT NULL ,`state` tinyint(4) NOT NULL,PRIMARY KEY (`id`),UNIQUE KEY `app_dir_type` (`app_id`,`dir_id`,`type`)) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ",
          app: {
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
          logicPath: "/mtt/profile/hive",
          realPath: "/user/mqq/mtt/profile/hive",
          type: "普通",
          nameNodeAddr: "10.191.128.11:9000",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 1
        },
        {
          dirId: 4,
          sql:
            "CREATE TABLE `t_app_dir_relation` (`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',`app_id` int(11) NOT NULL COMMENT 'app_id',`dir_id` int(11) NOT NULL COMMENT 'dir_id',`type` tinyint(4) NOT NULL ,`create_time` datetime NOT NULL ,`update_time` datetime NOT NULL ,`state` tinyint(4) NOT NULL,PRIMARY KEY (`id`),UNIQUE KEY `app_dir_type` (`app_id`,`dir_id`,`type`)) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ",
          app: {
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
          logicPath: "/mtt/profile/hive",
          realPath: "/user/mqq/mtt/profile/hive",
          type: "普通",
          nameNodeAddr: "10.191.128.11:9000",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 1
        },
        {
          dirId: 5,
          sql:
            "CREATE TABLE `t_app_dir_relation` (`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',`app_id` int(11) NOT NULL COMMENT 'app_id',`dir_id` int(11) NOT NULL COMMENT 'dir_id',`type` tinyint(4) NOT NULL ,`create_time` datetime NOT NULL ,`update_time` datetime NOT NULL ,`state` tinyint(4) NOT NULL,PRIMARY KEY (`id`),UNIQUE KEY `app_dir_type` (`app_id`,`dir_id`,`type`)) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ",
          app: {
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
          logicPath: "/mtt/profile/hive",
          realPath: "/user/mqq/mtt/profile/hive",
          type: "普通",
          nameNodeAddr: "10.191.128.11:9000",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 1
        },
        {
          dirId: 6,
          sql:
            "CREATE TABLE `t_app_dir_relation` (`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',`app_id` int(11) NOT NULL COMMENT 'app_id',`dir_id` int(11) NOT NULL COMMENT 'dir_id',`type` tinyint(4) NOT NULL ,`create_time` datetime NOT NULL ,`update_time` datetime NOT NULL ,`state` tinyint(4) NOT NULL,PRIMARY KEY (`id`),UNIQUE KEY `app_dir_type` (`app_id`,`dir_id`,`type`)) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ",
          app: {
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
          logicPath: "/mtt/profile/hive",
          realPath: "/user/mqq/mtt/profile/hive",
          type: "普通",
          nameNodeAddr: "10.191.128.11:9000",
          createTime: "2018/6/11",
          updateTime: "2018/6/11",
          state: 1
        }
      ];
      this.load_data = false;
    },
    //单个删除
    delete_dir(id) {
      this.$confirm("此操作将删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          console.log('del dir: ' + id);
          // this.load_dir = true;
          this.$message.success("delete success");
          // this.$fetch.api_table
          //   .del(item)
          //   .then(({ msg }) => {
          //     this.get_table_data();
          //     this.$message.success(msg);
          //   })
          //   .catch(() => {});
        })
        .catch(() => {});
    },
    delete_table(id) {
      this.$confirm("此操作将删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          console.log('del tab: ' + id);
          // this.load_data = true;
          this.$message.success("delete success");
          // this.$fetch.api_table
          //   .del(item)
          //   .then(({ msg }) => {
          //     this.get_table_data();
          //     this.$message.success(msg);
          //   })
          //   .catch(() => {});
        })
        .catch(() => {});
    },

    //目录页码选择
    handleDirCurrentChange(val) {
      this.dirCurrPage = val;
      this.get_dir_data();
    },
    //表页码选择
    handleCurrentChange(val) {
      this.currentPage = val;
      this.get_table_data();
    },
    //批量选择
    on_batch_select_dir(val) {
      this.batch_select_dir = val;
    },
    on_batch_select(val) {
      this.batch_select = val;
    },
    //批量删除
    on_batch_del_dir() {
      this.$confirm("此操作将批量删除选择数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
      .then(() => {
          console.log(this.batch_select_dir)
          this.load_data = true;
          this.$fetch.api_table
            .batch_del(this.batch_select_dir)
            .then(({ msg }) => {
              this.get_table_data();
              this.$message.success(msg);
            })
            .catch(() => {});
        })
        .catch(() => {});
    },
    on_batch_del() {
      this.$confirm("此操作将批量删除选择数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.load_data = true;
          this.$fetch.api_table
            .batch_del(this.batch_select)
            .then(({ msg }) => {
              this.get_table_data();
              this.$message.success(msg);
            })
            .catch(() => {});
        })
        .catch(() => {});
    },
    search_dir(condition, str) {
      console.log(condition);
      console.log(str);
    },
    search_table(condition, str) {
      console.log(condition);
      console.log(str);
    }
  }
};
</script>

<style>
.app-table-expand {
  font-size: 0;
}
.app-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.app-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 25%;
}
</style>
