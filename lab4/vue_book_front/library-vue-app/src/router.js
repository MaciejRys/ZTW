import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('@/components/Home.vue')
  },
  {
    path: '/books',
    name: 'Books',
    component: () => import('@/components/books/BookTable.vue')
  },
  {
    path: '/books/update/:id',
    name: 'BookUpdate',
    component: () => import('@/components/books/BookUpdate.vue')
  },
  {
    path: '/books/add',
    name: 'BookForm',
    component: () => import('@/components/books/BookForm.vue')
  },
  {
    path: '/BorrowForm',
    name: 'BorrowForm',
    component: () => import('@/components/Borrows/BorrowForm.vue')
  },
  {
    path: '/BorrowTable',
    name: 'BorrowTable',
    component: () => import('@/components/Borrows/BorrowTable.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.replace({ path: '*', redirect: '/' })

export default router