import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  // user
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  // 404
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  // home page
  {
    path: '/',
    component: Layout,
    redirect: '/index',
    children: [{
      path: '/index',
      name: 'Home',
      component: () => import('@/views/index'),
      meta: { title: '课堂首页', icon: 'dashboard' }
    }]
  },

  // teacher
  {
    path: '/teacher',
    component: Layout,
    redirect: '/teacher/list',
    name: 'Teacher',
    meta: { title: '讲师管理', icon: 'el-icon-s-help' },
    alwaysShow: true,
    children: [
      {
        path: 'list',
        name: 'TeacherList',
        component: () => import('@/views/vod/teacher/TeacherList'),
        meta: { title: '讲师列表', icon: 'table' }
      },
      {
        path: 'insert',
        name: 'TeacherInsert',
        component: () => import('@/views/vod/teacher/TeacherForm.vue'),
        meta: { title: '讲师添加', icon: 'tree' }
      },
      {
        path: 'edit/:id',
        name: 'TeacherEdit',
        component: () => import('@/views/vod/teacher/TeacherForm.vue'),
        meta: { title: '编辑讲师', icon: 'tree' },
        // 隐式路由，组件不在页面中展示
        hidden: true
      }
    ]
  },

  // course
  {
    path: '/course',
    component: Layout,
    redirect: '/course/list',
    name: 'Course',
    meta: { title: '课程管理', icon: 'el-icon-s-help' },
    alwaysShow: true,
    children: [
      {
        path: 'subject',
        name: 'SubjectList',
        component: () => import('@/views/vod/course/CourseSubject'),
        meta: { title: '分类列表', icon: 'table' }
      },
      {
        path: 'list',
        name: 'CourseList',
        component: () => import('@/views/vod/course/CourseList'),
        meta: { title: '课程列表', icon: 'el-icon-s-help' }
      },
      {
        path: 'info',
        name: 'CourseForm',
        component: () => import('@/views/vod/course/CourseInsert'),
        meta: { title: '发布课程' },
        hidden: true
      },
      {
        path: 'info/:id',
        name: 'CourseInfoEdit',
        component: () => import('@/views/vod/course/CourseInsert'),
        meta: { title: '编辑课程' },
        hidden: true
      },
      {
        path: 'chapter/:id',
        name: 'CourseChapterEdit',
        component: () => import('@/views/vod/course/CourseInsert'),
        meta: { title: '编辑大纲' },
        hidden: true
      },
      {
        path: 'chart/:id',
        name: 'CourseChart',
        component: () => import('@/views/vod/course/CourseChart'),
        meta: { title: '课程统计' },
        hidden: true
      }
    ]
  },

  // order
  {
    path: '/order',
    component: Layout,
    redirect: '/order/list',
    name: 'Order',
    meta: { title: '订单管理', icon: 'el-icon-truck' },
    alwaysShow: true,
    children: [
      {
        path: 'order/list',
        name: 'OrderList',
        component: () => import('@/views/order/OrderList'),
        meta: { title: '订单列表', icon: 'el-icon-s-help' }
      }
    ]
  },

  // activity
  {
    path: '/activity',
    component: Layout,
    redirect: '/activity/coupon/list',
    name: 'Activity',
    meta: { title: '营销管理', icon: 'el-icon-football' },
    alwaysShow: true,
    children: [
      {
        path: 'coupon/list',
        name: 'CouponInfo',
        component: () => import('@/views/activity/CouponList'),
        meta: { title: '优惠券列表', icon: 'el-icon-football' }
      },
      {
        path: 'coupon/add',
        name: 'CouponInfoAdd',
        component: () => import('@/views/activity/CouponForm'),
        meta: { title: '添加' },
        hidden: true
      },
      {
        path: 'coupon/edit/:id',
        name: 'CouponInfoEdit',
        component: () => import('@/views/activity/CouponForm'),
        meta: { title: '编辑', noCache: true },
        hidden: true
      },
      {
        path: 'coupon/show/:id',
        name: 'CouponInfoShow',
        component: () => import('@/views/activity/CouponShow'),
        meta: { title: '详情', noCache: true },
        hidden: true
      }
    ]
  },

  // wechat
  {
    path: '/wechat',
    component: Layout,
    redirect: '/wechat/menu/list',
    name: 'Wechat',
    meta: {
      title: '菜单管理',
      icon: 'el-icon-refrigerator'
    },
    alwaysShow: true,
    children: [
      {
        path: 'menu/list',
        name: 'Menu',
        component: () => import('@/views/wechat/MenuList'),
        meta: { title: '菜单列表', icon: 'el-icon-s-help' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
