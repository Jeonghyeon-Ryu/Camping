import {
  createRouter,
  createWebHistory
} from "vue-router"

const routes = [{
    name: 'Home',
    path: '/',
    component: () => import('../views/Home.vue'),
  },
  {
    name: 'SnsMain',
    path: '/sns',
    component: () => import('../components/SnS/SnsMain.vue'),
  },
  {
    name: 'SnsDetail',
    path: '/sns/detail/:writeNo',
    component: () => import('../components/SnS/SnsDetailFeed.vue'),
  },
  {
    name: 'SnsWrite',
    path: '/sns/write',
    component: () => import('../components/SnS/SnsWrite.vue'),
  },
  {
    name: 'SnsRewrite',
    path: '/sns/rewrite/:writeNo',
    component: () => import('../components/SnS/SnsRewrite.vue'),
  },
  {
    path: '/recru/detail/:recruId',
    name: 'recruDetail',
    component: () => import('@/components/recruit/RecruDetail.vue'),
    props: true
  },
  {
    path: '/used/usedInsert',
    name: 'usedInsert',
    component: () => import('@/components/UsedPractice/UsedInsert.vue'),
  },
  {
    path: '/used/usedDetail/:usedId',
    name: 'usedDetail',
    component: () => import('@/components/UsedPractice/UsedDetail.vue'),
    props: true
  },
  {
    path: '/used/usedUpdate/:usedId',
    name: 'usedUpdate',
    component: () => import('@/components/UsedPractice/UsedUpdate.vue'),
    props: true
  },
  {
    name: 'LoginSignup',
    path: '/Login',
    component: () => import('@/components/User/LoginSignup.vue'),
  },
  {
    name: 'UserInfo',
    path: '/MyInfo',
    component: () => import('@/components/User/UserInfo.vue'),
  },
  {
    name: 'CampList',
    path: '/CampList',
    component: () => import('@/components/Camping/CampList.vue'),
  },
  {
    name: 'CampDetail',
    path: '/Camp/:campId',
    component: () => import('@/components/Camping/CampDetail.vue'),
  },
  {
    name: 'CampRegister',
    path: '/CampRegister',
    component: () => import('@/components/Camping/CampRegister.vue'),
  },
  {
    name: 'CampModify',
    path: '/CampModify/:campId',
    component: () => import('@/components/Camping/CampModify.vue'),
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router