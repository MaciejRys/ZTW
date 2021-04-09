<template>
  <div id="app" class="small-container">
    <h1>Wypożyczone książki</h1>
    <borrow-form  @add:borrow="addBorrow"/>/>
    <borrow-table :borrowSource="borrows"/>
  </div>
</template>
<script>


import BorrowTable from '@/components/BorrowTable.vue'
import BorrowForm from '@/components/BorrowForm.vue'
export default {
  name: 'app',
  components: {
    BorrowTable,
    BorrowForm,
  },
  data() {
    return {
      borrows: [
        {
          id: 1,
          title: 'kamil Słodowy',
          user: '+48 787 774 664'
        },
        {
          id: 2,
          title: 'Michał Studencki',
          user: '+48 600 565 454'
        },
        {
          id: 3,
          title: 'Kamila Napokaz',
          user: '+48 609 554 987'
        },
      ]
    }

  }, methods: {
    addBorrow(borrow) {
      this.borrows = [...this.borrows, borrow]
    }
  },
}
</script>
<style>
button {
  background: #009435;
  border: 1px solid #009435;
}
.small-container {
  max-width: 680px;
}
</style>


<!--<template>-->
<!--  <div id="app">-->
<!--    <img alt="Vue logo" src="./assets/logo.png">-->
<!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import HelloWorld from './components/HelloWorld.vue'-->

<!--export default {-->
<!--  name: 'App',-->
<!--  components: {-->
<!--    HelloWorld-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style>-->
<!--#app {-->
<!--  font-family: Avenir, Helvetica, Arial, sans-serif;-->
<!--  -webkit-font-smoothing: antialiased;-->
<!--  -moz-osx-font-smoothing: grayscale;-->
<!--  text-align: center;-->
<!--  color: #2c3e50;-->
<!--  margin-top: 60px;-->
<!--}-->
<!--</style>-->
