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
    {
        path: '/login',
        component: () => import('@/views/login/index'),
        hidden: true
    },

    {
        path: '/404',
        component: () => import('@/views/404'),
        hidden: true
    },

    {
        path: '/',
        component: Layout,
        redirect: '/dashboard',
        children: [{
            path: 'dashboard',
            name: 'Dashboard',
            component: () => import('@/views/dashboard/index'),
            meta: {title: '仪表盘', icon: 'dashboard'}
        }]
    },

    {
        path: '/article',
        component: Layout,
        redirect: '/article/list',
        name: 'article',
        meta: {title: '文章管理', icon: 'el-icon-s-help'},
        children: [
            {
                path: 'save',
                name: 'save',
                component: () => import('@/views/article/save'),
                meta: {title: '新增文章', icon: 'table'},
                hidden: true
            },
            {
                path: 'update',
                name: 'update',
                component: () => import('@/views/article/update'),
                meta: {title: '修改文章', icon: 'table'},
                hidden: true
            },
            {
                path: 'list',
                name: 'list',
                component: () => import('@/views/article/list'),
                meta: {title: '文章列表', icon: 'table'}
            },
            {
                path: 'category',
                name: 'category',
                component: () => import('@/views/article/category'),
                meta: {title: '分类管理', icon: 'tree'}
            }
        ]
    },

    {
        path: '/page',
        component: Layout,
        redirect: '/page/list',
        name: 'page',
        meta: {title: '页面管理', icon: 'el-icon-s-help'},
        children: [
            {
                path: 'save',
                name: 'save',
                component: () => import('@/views/page/save'),
                meta: {title: '新增页面', icon: 'table'},
                hidden: true
            },
            {
                path: 'update',
                name: 'update',
                component: () => import('@/views/page/update'),
                meta: {title: '修改页面', icon: 'table'},
                hidden: true
            },
            {
                path: 'list',
                name: 'list',
                component: () => import('@/views/page/list'),
                meta: {title: '页面列表', icon: 'table'}
            },
            {
                path: 'category',
                name: 'category',
                component: () => import('@/views/page/menu'),
                meta: {title: '菜单管理', icon: 'tree'}
            }
        ]
    },

    {
        path: '/attachment',
        component: Layout,
        children: [
            {
                path: 'index',
                name: 'attachment',
                component: () => import('@/views/attachment/index'),
                meta: {title: '附件管理', icon: 'form'}
            }
        ]
    },

    {
        path: '/comment',
        component: Layout,
        children: [
            {
                path: 'index',
                name: 'comment',
                component: () => import('@/views/comment/index'),
                meta: {title: '评论管理', icon: 'form'}
            }
        ]
    },

    {
        path: '/setting',
        component: Layout,
        redirect: '/setting/author',
        name: 'setting',
        meta: {title: '设置', icon: 'nested'},
        children: [
            {
                path: 'author',
                name: 'author',
                component: () => import('@/views/setting/author'),
                meta: {title: '作者管理', icon: 'table'},
            },
            {
                path: 'config',
                name: 'config',
                component: () => import('@/views/setting/config'),
                meta: {title: '博客设置', icon: 'table'},
            },
            {
                path: 'transfer',
                name: 'transfer',
                component: () => import('@/views/setting/transfer'),
                meta: {title: '迁移', icon: 'table'},
            },
            {
                path: 'about',
                name: 'about',
                component: () => import('@/views/setting/about'),
                meta: {title: '关于', icon: 'table'},
            }
        ]
    },

    // 404 page must be placed at the end !!!
    {path: '*', redirect: '/404', hidden: true}
]

const createRouter = () => new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({y: 0}),
    routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
    const newRouter = createRouter()
    router.matcher = newRouter.matcher // reset router
}

export default router
