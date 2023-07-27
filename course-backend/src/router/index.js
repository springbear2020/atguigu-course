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
    children: [
      {
        path: 'list',
        name: 'TeacherList',
        component: () => import('@/views/teacher/index'),
        meta: { title: '讲师列表', icon: 'table' }
      },
      {
        path: 'insert',
        name: 'TeacherInsert',
        component: () => import('@/views/teacher/form'),
        meta: { title: '讲师添加', icon: 'tree' }
      },
      {
        path: 'edit/:id',
        name: 'TeacherEdit',
        component: () => import('@/views/teacher/form'),
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
    children: [
      {
        path: 'list',
        name: 'CourseList',
        component: () => import('@/views/course/list'),
        meta: { title: '课程列表', icon: 'el-icon-s-help' }
      },
      {
        path: 'subject',
        name: 'SubjectList',
        component: () => import('@/views/course/subject'),
        meta: { title: '分类列表', icon: 'table' }
      },
      {
        path: 'info',
        name: 'CourseForm',
        component: () => import('@/views/course/form'),
        meta: { title: '发布课程' },
        hidden: true
      },
      {
        path: 'info/:id',
        name: 'CourseInfoEdit',
        component: () => import('@/views/course/form'),
        meta: { title: '编辑课程' },
        hidden: true
      },
      {
        path: 'chapter/:id',
        name: 'CourseChapterEdit',
        component: () => import('@/views/course/form'),
        meta: { title: '编辑大纲' },
        hidden: true
      },
      {
        path: 'chart/:id',
        name: 'CourseChart',
        component: () => import('@/views/course/chart'),
        meta: { title: '课程统计' },
        hidden: true
      },
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
