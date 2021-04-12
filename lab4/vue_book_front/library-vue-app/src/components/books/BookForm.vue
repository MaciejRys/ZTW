<template>
  <div id="book-form-view" class="small-container">
    <h1>Add book</h1>
    <div id="book-form">
      <form @submit.prevent="addBook(book)">
        <label>Title</label>
        <input v-model="book.title" name="title" type="text"  :class="{ 'has-error': submitting && invalidTitle }"   @focus="clearStatus"
               @keypress="clearStatus"  />
        <label>Pages</label>
        <input v-model="book.pages" name="pages" type="number"  :class="{ 'has-error': submitting && invalidPages }"   @focus="clearStatus"
               @keypress="clearStatus"  />
        <label>Author</label>
        <select v-model="book.author" name="author" type="text" >
          <option v-bind:value="author" v-for="author in authors" :key="author.id">
            {{ author.fullName}}
          </option>
        </select>

        <p v-if="error && submitting" class="error-message">
          Proszę wypełnić wskazane pola formularza
        </p>
        <p v-if="success" class="success-message">
          Dane poprawnie zapisano
        </p>

        <button>Add book</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "book-form",
  computed: {
    invalidTitle() {
      return this.book.title === ''
    },
    invalidPages() {
      return this.book.pages < 1
    },
  },
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
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
      this.submitting = true
      this.clearStatus()
      //check form fields
      if (this.invalidTitle || this.invalidPages) {
        this.error = true
        return
      }
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
        this.book =  {
          title: "",
              author: {
            id: -1,
                fullName: "",
          },
          pages: -1,
        }
        this.error = false
        this.success = true
        this.submitting = false
        this.$router.push({
          name: "Books",
        });
      } catch (error) {
        console.error(error);
      }
    },
    clearStatus() {
      this.success = false
      this.error = false
    },
  },
  mounted() {
    this.getAuthors();
  },
};
</script>

<style scoped>
form {
  margin-bottom: 2rem;
}

[class*='-message'] {
  font-weight: 500;
}
.error-message {
  color: #d33c40;
}
.success-message {
  color: #32a95d;
}
.small-container {
  margin-top: 4rem;
}
</style>