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
    path: '/authors',
    name: 'Authors',
    component: () => import('@/components/authors/AuthorTable.vue')
  },
  {
    path: '/authors/update/:id',
    name: 'AuthorUpdate',
    component: () => import('@/components/authors/AuthorUpdate.vue')
  },
  {
    path: '/authors/add',
    name: 'AuthorForm',
    component: () => import('@/components/authors/AuthorForm.vue')
  },
  {
    path: '/borrows/add',
    name: 'BorrowForm',
    component: () => import('@/components/Borrows/BorrowForm.vue')
  },
  {
    path: '/borrows',
    name: 'BorrowTable',
    component: () => import('@/components/Borrows/BorrowTable.vue')
  },
  {
    path: '/borrows/info/:book/:user',
    name: 'BorrowInfo',
    component: () => import('@/components/Borrows/BorrowInfo.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router