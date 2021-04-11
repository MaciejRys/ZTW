<template>
  <div id="book-form-view" class="small-container">
    <h1>Edit book</h1>
    <div id="book-form">
      <form @submit.prevent="updateBook(book)">
        <label>Title</label>
        <input v-model="book.title" name="title" type="text" />
        <label>Pages</label>
        <input v-model="book.pages" name="pages" type="number" />
        <label>Author</label>
        <select v-model="book.author" name="author" type="text">
          <option v-bind:value="author" v-for="author in authors" :key="author.id">
            {{ author.fullName }}
          </option>
        </select>
        <button>Edit book</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "book-update",
  data() {
    return {
      book: {
        id: -1,
        title: "",
        author: {
          id: -1,
          fullName: "",
        },
        pages: -1,
      },
      authors: [],
    };
  },
  methods: {
    async getAuthors() {
      try {
        this.authors = [];
        const response = await fetch("http://localhost:8081/authors");
        const data = await response.json();
        for (var i = 0; i < data.length; i++) {
          this.authors.push(data[i]);
        }
      } catch (error) {
        console.error(error);
      }
    },

    async getBook() {
      let bookId = this.$route.params.id;

      try {
        var url = new URL("http://localhost:8081/books/" + bookId);

        const response = await fetch(url, {
          method: "GET",
        });

        const data = await response.json();
        this.book = data;
      } catch (error) {
        console.error(error);
      }
    },

    async updateBook(book) {
      let bookToUpdateId = book.id;

      try {
        var url = new URL("http://localhost:8081/books/" + bookToUpdateId);

        fetch(url, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(book),
        });

        this.$router.push({
          name: "Books",
        });
      } catch (error) {
        console.error(error);
      }
    },
  },
  mounted() {
    this.getAuthors();
    this.getBook();
  },
};
</script>

<style>
</style>