<template>
  <div id="app" class="small-container">
    <h1>Wypożyczenie</h1>
    <div id="borrow-info">
      <form>
        <label>Tytuł</label>
        <input type="text" disabled :value ="this.borrow.book.title" />
        <label>Autor</label>
        <input type="text" disabled :value = "this.borrow.book.author" />
        <label>Liczba stron</label>
        <input type="text" disabled :value = "this.borrow.book.pages" />
        <label>User</label>
        <input type="text" disabled :value = "this.borrow.user" />
        <button
            @click="
        $router.push({
          name: 'BorrowTable',
        })
      "
        >
          Powrót
        </button>
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
        const response = await fetch('http://localhost:8081/books/book/' + borrow.book)
        const data = await response.json()
        this.borrow.book.title = data.title
        this.borrow.book.pages = data.pages
        this.borrow.book.author = data.author.fullName
        this.borrow.user = borrow.user
      } catch (error) {
        console.error(error)
      }
    }
  }, mounted() {
    this.getBorrowInfo(this.$route.params);
  },
}
</script>
<style scoped>
form {
  margin-bottom: 2rem;
}
.small-container{
  margin-top: 4rem;
}
</style>
