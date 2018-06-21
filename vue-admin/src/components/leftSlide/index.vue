<!-- 侧边导航栏 -->
<template>
  <div class="left-side">
    <div class="left-side-inner">
      <router-link to="/" class="logo block">
        <img src="./images/logo.png" alt="AdminX">
      </router-link>
      <el-menu
        class="menu-box"
        theme="dark"
        router
        :default-active="$route.path">
        <div
          v-for="(item, index) in nav_menu_data"
          :key="index">
          <el-menu-item
            class="menu-list"
            v-if="typeof item.child === 'undefined'"
            :index="item.path">
            <i class="icon fa" :class="item.icon"></i>
            <span v-text="item.title" class="text"></span>
          </el-menu-item>
          <el-submenu
            :index="item.path"
            v-else>
            <template slot="title">
              <i class="icon fa" :class="item.icon"></i>
              <span v-text="item.title" class="text"></span>
            </template>
            <el-menu-item
              class="menu-list"
              v-for="(sub_item, sub_index) in item.child"
              :index="sub_item.path"
              :key="sub_index">
              <!--<i class="icon fa" :class="sub_item.icon"></i>-->
              <span v-text="sub_item.title" class="text"></span>
            </el-menu-item>
          </el-submenu>
        </div>
      </el-menu>
    </div>
  </div>
</template>
<script type="text/javascript">
export default {
  name: "slide",
  data() {
    return {
      nav_menu_data: [
        // {
        //   title: "主页",
        //   path: "/home",
        //   icon: "fa-home"
        // },
        {
          title: "我创建的目录/表",
          path: "/my",
          icon: "fa-home",
          child: [
            {
              title: "私有",
              path: "/table/private"
            },
            {
              title: "共享",
              path: "/table/share"
            }
          ]
        },
        {
          title: "有权限的目录/表",
          path: "/other",
          icon: "fa-table", //fa-bar-chart-o
          child: [
            {
              title:"读权限",
              path:"/table/read"
            },
            {
              title:"写权限",
              path:"/table/write"
            }
            // {
            //   title: "柱状图表",
            //   path: "/charts/bar"
            // }
          ]
        },
        {
          title: "应用组管理",
          path: "/home",
          icon: "fa-bar-chart-o", //el-icon-menu
          child: [
            {
              title: "应用组列表",
              path: "/table/appTable"
            },
            {
              title: "应用组录入",
              path: "/table/appSave"
            }
          ]
        }
      ]
    };
  }
};
</script>
