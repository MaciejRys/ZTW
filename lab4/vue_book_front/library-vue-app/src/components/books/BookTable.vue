<template>
  <div id="persons-table-component">
    <button
      @click="
        $router.push({
          name: 'BookForm',
        })
      "
    >
      Add book
    </button>
    <div id="persons-table">
      <table>
        <thead>
          <th>Id</th>
          <th>Title</th>
          <th>Author</th>
          <th>Pages</th>
        </thead>
        <tbody>
          <tr v-for="book in books" :key="book.id">
            <td>{{ book.id }}</td>
            <td>{{ book.title }}</td>
            <td>{{ book.author.fullName }}</td>
            <td>{{ book.pages }}</td>
            <td>
              <button
                @click="
                  $router.push({
                    name: 'BookUpdate',
                    params: { id: book.id },
                  })
                "
              >
                Edit
              </button>
              <button @click="deleteBook(book.id)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "book-table",
  data() {
    return {
      books: [],
    };
  },
  methods: {
    async getBooks() {
      try {
        const response = await fetch("http://localhost:8081/books");
        const data = await response.json();
        for (var i = 0; i < data.length; i++) {
          this.books.push(data[i]);
        }
      } catch (error) {
        console.error(error);
      }
    },

    async deleteBook(bookId) {
      fetch("http://localhost:8081/books/" + bookId, {
        method: "DELETE",
      })
        .then((respFulfilled) => {
          this.books = this.books.filter((book) => {
            return book.id != bookId;
          });
        })
        .catch((respRejected) => {
          console.log(respRejected);
        });
    },
  },
  mounted() {
    this.getBooks();
  },

  activated() {
    this.getBooks();
  },
};
</script>

<style>
</style>