<template>
  <div id="app" class="small-container">
    <h1>Wypożyczone książki</h1>
    <borrow-form   @add:borrow="addBorrow" :bookSource="books" :userSource="users" />
    <borrow-table @add:borrow="deleteBorrow" :borrowSource="borrows"/>
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
      borrows: [],
      books: [],
      users: []
    }

  }, methods: {
    addBorrow(borrow) {
      try {
      this.borrows = [...this.borrows, borrow]
      fetch('http://localhost:8081/rents/rent/' + borrow.book + '/' + borrow.user, {method:"POST",mode:"no-cors"})
      } catch (error) {
        console.error(error)
      }
    },
   deleteBorrow(borrow) {
      try {
        console.log("borrow");
        fetch('http://localhost:8081/rents/free/' + borrow.book, {method:"POST",mode:"no-cors"})
        this.borrows = this.getBorrows();
      } catch (error) {
        console.error(error)
      }
    },
    async getBorrows() {
      try {
        const response = await fetch('http://localhost:8081/rents')
        const data = await response.json()
        for (var key in data) {
          for(var i = 0; i < data[key].length; i++){
            this.borrows.push({ book: data[key][i].title , user: key });
          }
        }
      } catch (error) {
        console.error(error)
      }
    },
    async getUsers() {
      try {
        const response = await fetch('http://localhost:8081/users')
        const data = await response.json()
        for(var i = 0; i < data.length; i++){
            this.users.push( data[i].fullName );
        }
      } catch (error) {
        console.error(error)
      }
    },
    async getBooks() {
      try {
        const response = await fetch('http://localhost:8081/rents/freeBooks')
        const data = await response.json()
        for(var i = 0; i < data.length; i++){
          this.books.push(data[i].title);
        }
      } catch (error) {
        console.error(error)
      }
    }
  },
  mounted() {
    this.getBorrows();
    this.getBooks();
    this.getUsers();
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

