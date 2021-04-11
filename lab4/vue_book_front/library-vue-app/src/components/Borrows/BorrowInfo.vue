<template>
  <div id="app" class="small-container">
    <h1>Wypożyczenie</h1>
    <div id="borrow-info">
      <form>
        <label>Tytuł</label>
        <input type="text" />
        <label>Autor</label>
        <input type="text" />
        <label>Liczba stron</label>
        <input type="text" />
        <label>User</label>
        <input type="text" />
      </form>
    </div>
  </div>
</template>
<script>
export default {
  name: 'borrow-info',
  data() {
    return {
      borrow: {
        book: {
          title: "",
          pages: 0,
          author: "",
        },
        user: "",
      },
    }
  },
  methods: {
    async getBorrowInfo(borrow) {
      try {
        const response = await fetch('http://localhost:8081/books/book/' + borrow.book, {method:"GET",mode:"no-cors"})
        this.borrow.book.title = response.title
        this.borrow.book.pages = response.pages
        this.borrow.book.author = response.author
        this.borrow.user = borrow.user
      } catch (error) {
        console.error(error)
      }
    }
  }, mounted() {
    this.getBorrowInfo();
  },
}
</script>
<style scoped>
form {
  margin-bottom: 2rem;
}
</style>
