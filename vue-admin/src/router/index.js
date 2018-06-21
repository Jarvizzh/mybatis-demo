/**
 * 
 * 路由Map
 *
 */

import Vue from 'vue'
import VueRouter from 'vue-router'
import store from 'store'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

//import components
//view page warp component
import viewPageComponent from 'pages/App'

//home
import homeComponent from 'pages/home'
//404
import noPageComponent from 'pages/error/404'
//login
import loginComponent from 'pages/user/login'
//app table
import appTableComponent from 'pages/table/appTable'
//app save
import saveAppComponent from 'pages/table/appSave'
//dir list
import dirListComponent from 'pages/table/dirList'
//dir save
import dirSaveComponent from 'pages/table/dirSave'
//table save
import tableSaveComponent from 'pages/table/tableSave'
//share dir
import shareDirComponent from 'pages/table/share'
//other r dir
import readDirComponent from 'pages/table/read'
//other w dir
import writeDirComponent from 'pages/table/write'

Vue.use(VueRouter)

//使用AMD方式加载
// component: resolve => require(['pages/home'], resolve),
const routes = [{
  path: '/404',
  name: 'notPage',
  component: noPageComponent
},
{
  path: '*',
  redirect: '/404'
},
{
  path: '/user/login',
  name: 'login',
  component: loginComponent
},
{
  path: '/',
  redirect: '/table/private',
  component: viewPageComponent,
  children: [

    {
      path: '/home',
      name: 'home',
      component: homeComponent,
      meta: {
        title: "主页",
        auth: true
      }
    },
    {
      path: '/table/share',
      name: 'shareDir',
      component: shareDirComponent,
      meta: {
        dir_title: "我的共享目录",
        table_title: "我的共享表",
        auth: true
      }
    },
    {
      path: '/table/appTable',
      name: 'appTable',
      component: appTableComponent,
      meta: {
        title: "应用组列表",
        auth: true
      }
    },
    {
      path: '/table/appSave',
      name: 'appSave',
      component: saveAppComponent,
      meta: {
        title: "应用组录入",
        auth: true
      }
    },
    {
      path: '/table/appUpdate/:id',
      name: 'appUpdate',
      component: saveAppComponent,
      meta: {
        title: "应用组数据修改",
        auth: true
      }
    },
    {
      path: '/table/private',
      name: 'privateDir',
      component: dirListComponent,
      meta: {
        dir_title: "我的私有目录",
        table_title: "我的私有表",
        auth: true
      }
    },
    {
      path: '/table/saveDir',
      name: 'saveDir',
      component: dirSaveComponent,
      meta: {
        title: "录入目录",
        auth: true
      }
    },
    {
      path: '/table/dirUpdate/:id',
      name: 'dirUpdate',
      component: dirSaveComponent,
      meta: {
        title: "目录数据修改",
        auth: true
      }
    },
    {
      path: '/table/saveTable',
      name: 'saveTable',
      component: tableSaveComponent,
      meta: {
        title: "录入表",
        auth: true
      }
    },
    {
      path: '/table/tableUpdate/:id',
      name: 'tableUpdate',
      component: tableSaveComponent,
      meta: {
        title: "表数据修改",
        auth: true
      }
    },
    {
      path: '/table/read',
      name: 'readDir',
      component: readDirComponent,
      meta: {
        dir_title: "读权限目录",
        table_title: "读权限表",
        auth: true
      }
    },
    {
      path: '/table/write',
      name: 'writeDir',
      component: writeDirComponent,
      meta: {
        dir_title: "写权限目录",
        table_title: "写权限表",
        auth: true
      }
    }]
}]

const router = new VueRouter({
  routes,
  mode: 'history', //default: hash ,history
  // base: '/projectName/',  //项目名
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { x: 0, y: 0 }
    }
  }
})

//全局路由配置
//路由开始之前的操作
router.beforeEach((to, from, next) => {
  NProgress.done().start()
  let toName = to.name
  // let fromName = from.name
  let is_login = store.state.user_info.login

  if (!is_login && toName !== 'login') {
    next({
      name: 'login'
    })
  } else {
    if (is_login && toName === 'login') {
      next({
        path: '/'
      })
    } else {
      next()
    }
  }
})

//路由完成之后的操作
router.afterEach(route => {
  NProgress.done()
})

export default router
