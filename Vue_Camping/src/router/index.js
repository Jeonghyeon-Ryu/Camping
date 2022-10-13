import { createRouter, createWebHistory } from "vue-router"

const routes = [
   {
    path: '/',
    name: 'Home',
    component: () => import('../views/Home.vue'),
   },
  // {
  // path: '/about',
  // name: 'About',
  // component: () => import ('../views/About.vue'),
  // },

  {
    path: '/recru/detail/:recruId',
    name: 'recruDetail',
    component: () => import('@/components/recruit/RecruDetail.vue'),
    props : true
   }

]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
