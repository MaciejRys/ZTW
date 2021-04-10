<template>
  <div id="persons-table">
    <table>
      <thead>
        <th>Id</th>
        <th>Title</th>
        <th>Author</th>
      </thead>
      <tbody>
        <tr v-for="book in books" :key="book.id">
          <td>{{ book.id }}</td>
          <td>{{ book.title }}</td>
          <td>{{ book.author.fullName }}</td>
          <td>
            <button
              @click="
                $router.push({
                  name: 'about-something',
                  params: { id: book.id },
                })
              "
            >
              Edit
            </button>
            <button
              @click="
                deleteBook(book.id)
              "
            >
              Delete
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "book-table",
  data() {
    return {
      books: Array,
    };
  },
  methods: {
    async getBooks() {
      try {
        const response = await fetch("http://localhost:8081/rents/freeBooks");
        const data = await response.json();
        for (var i = 0; i < data.length; i++) {
          this.books.push(data[i].title);
        }
      } catch (error) {
        console.error(error);
      }
    },

    deleteBook(bookId) {

    }
  },
  mounted() {
    this.getBooks();
  },
};
</script>

<style>
</style>