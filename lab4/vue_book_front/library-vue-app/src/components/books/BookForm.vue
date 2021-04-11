<template>
  <div id="app" class="small-container">
    <h1>Add book</h1>
    <div id="book-form">
      <form @submit.prevent="addBook(book)">
        <label>Title</label>
        <input v-model="book.title" name="title" type="text" />
        <label>Pages</label>
        <input v-model="book.pages" name="pages" type="number" />
        <label>Author</label>
        <select v-model="book.author" name="author" type="text">
          <option v-bind:value="author" v-for="author in authors" :key="author">
            {{ author.fullName}}
          </option>
        </select>
        <button>Add book</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "book-form",
  data() {
    return {
      book: {
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

    async addBook(book) {
      console.log(book);
      try {
        var url = new URL("http://localhost:8081/books"),
          params = {
            title: book.title,
            authorId: book.author.id,
            pages: book.pages,
          };
        Object.keys(params).forEach((key) =>
          url.searchParams.append(key, params[key])
        );
        fetch(url, {
          method: "POST",
          mode: "no-cors",
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
  },
};
</script>

<style scoped>
</style>