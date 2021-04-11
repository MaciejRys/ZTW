<template>
  <div id="app" class="small-container">
    <h1>Wypożyczone książki</h1>
  <div id="borrow-form">
    <form>
      <label>Tytuł - autor</label>
      <select v-model="borrow.book"  name="book" type="text" >
      <option v-for="book in books" :key="book">
        {{book}}
      </option>
      </select>
      <label>User</label>
      <select v-model="borrow.user"  name="user" type="text" >
        <option v-for="user in users" :key="user">
          {{user}}
        </option>
      </select>
      <button @click="addBorrow(borrow)" >Wypożycz książke</button>
    </form>
  </div>
  </div>
</template>
<script>
export default {
  name: 'borrow-form',
  props: {
    bookSource: Array,
    userSource: Array,
  },
  data() {
    return {
      books: [],
      users: [],
      borrow: {
        book: '',
        user: '',
      },
    }
  },
  methods: {
    addBorrow(borrow) {
      try {
        fetch('http://localhost:8081/rents/rent/' + borrow.book + '/' + borrow.user, {method:"POST",mode:"no-cors"})
      } catch (error) {
        console.error(error)
      }
    },
    async getUsers() {
      try {
        this.users = [];
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
        this.books = [];
        const response = await fetch('http://localhost:8081/rents/freeBooks')
        const data = await response.json()
        for(var i = 0; i < data.length; i++){
          this.books.push(data[i].title);
        }
      } catch (error) {
        console.error(error)
      }
    }
  }, mounted() {
    this.getBooks();
    this.getUsers();
  },
}
</script>
<style scoped>
form {
  margin-bottom: 2rem;
}
</style>
