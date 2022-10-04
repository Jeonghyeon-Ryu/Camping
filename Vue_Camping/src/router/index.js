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
    path : '/',
    name : 'snsMainView',
    component : () =>
    import("../components/snsMainView.vue"),
  },
  {
    path : '/',
    name : 'snsWriteView',
    component : () =>
    import("../components/snsWriteView.vue"),
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
