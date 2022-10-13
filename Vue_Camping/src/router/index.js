import { createRouter, createWebHistory } from "vue-router"

const routes = [
   {
    path: '/',
    name: 'Home',
    component: () => import('../views/Home.vue'),
   },
  {
    path: '/recru/detail',
    name: 'recruDetail',
    component: () => import('@/components/recruit/RecruDetail.vue'),
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
   }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
