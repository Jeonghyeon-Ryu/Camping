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
    name: 'SnsDetail',
    path: '/sns/detail/:writeNo',
    component: () => import('../components/SnS/SnsDetailFeed.vue'),
  },
  {
    name: 'SnsRewrite',
    path: '/sns/rewrite/:writeNo',
    component: () => import('../components/SnS/SnsRewrite.vue'),
  },
  {
    path: '/recru/detail',
    name: 'recruDetail',
    component: () => import('@/components/recruit/RecruDetail.vue'),
  }

]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router