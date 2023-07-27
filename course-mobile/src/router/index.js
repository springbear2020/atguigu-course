import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
    },
    {
        path: '/course/:subjectId',
        name: 'Course',
        component: () => import('../views/course/Course'),
    },
    {
        path: '/course/detail/:courseId',
        name: 'CourseInfo',
        component: () =>
            import('../views/course/CourseDetail'),
    },
    {
        path: '/play/:courseId/:videoId',
        name: 'Play',
        component: () => import('../views/course/Play'),
    },
    {
        path: '/trade/:courseId',
        name: 'Trade',
        component: () => import('../views/order/Trade'),
    },
    {
        path: '/pay/:orderId',
        name: 'Pay',
        component: () => import('../views/order/Pay'),
    },
    // {
    //     path: '/liveOnline',
    //     name: 'liveOnline',
    //     component: () =>
    //         import('../views/liveOnline.vue'),
    // },
    // {
    //     path: '/live',
    //     name: 'Live',
    //     component: () =>
    //         import('../views/live.vue'),
    // },
    // {
    //     path: '/liveInfo/:liveCourseId',
    //     name: 'LiveInfo',
    //     component: () =>
    //         import('../views/liveInfo.vue'),
    // },
    // {
    //     path: '/order',
    //     name: 'Order',
    //     component: () =>
    //         import('../views/order.vue'),
    // },
    // {
    //     path: '/myCourse',
    //     name: 'MyCourse',
    //     component: () =>
    //         import('../views/myCourse.vue'),
    // },
    // {
    //     path: '/bindFirst',
    //     name: 'BindFirst',
    //     component: () =>
    //         import('../views/bindFirst.vue'),
    // },
    // {
    //     path: '/bindSecond',
    //     name: 'BindSecond',
    //     component: () =>
    //         import('../views/bindSecond.vue'),
    // },
    // {
    //     path: '/coupon',
    //     name: 'Coupon',
    //     component: () =>
    //         import('../views/coupon.vue'),
    // },
    // {
    //     path: '/clear',
    //     name: 'clear',
    //     component: () =>
    //         import('../views/clear.vue'),
    // }
];

const router = new VueRouter({
    routes,
});

export default router;
