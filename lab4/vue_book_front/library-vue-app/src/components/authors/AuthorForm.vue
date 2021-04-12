<template>
  <div id="app" class="small-container">
    <h1>Add author</h1>
    <div id="author-form">
      <form @submit.prevent="addAuthor(author)">
        <label>Fullname</label>
        <input v-model="author.fullName" name="fullName" type="text" :class="{ 'has-error': submitting && invalidName }"   @focus="clearStatus"
               @keypress="clearStatus"  />
        <p v-if="error && submitting" class="error-message">
          Proszę wypełnić wskazane pola formularza
        </p>
        <p v-if="success" class="success-message">
          Dane poprawnie zapisano
        </p>

        <button>Add author</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "author-form",
  computed: {
    invalidName() {
      return this.author.fullName === ''
    },
  },
    data() {
      return {
        submitting: false,
        error: false,
        success: false,
        author: {
          id: -1,
          fullName: "",
        },
        authors: [],
      };
    },
    methods: {
      async addAuthor(author) {
        this.submitting = true
        this.clearStatus()
        //check form fields
        if (this.invalidName) {
          this.error = true
          return
        }
        try {
          var url = new URL("http://localhost:8081/authors"),
              params = {
                author: author.fullName,
              };
          Object.keys(params).forEach((key) =>
              url.searchParams.append(key, params[key])
          );
          fetch(url, {
            method: "POST",
            mode: "no-cors",
          });
          this.author.fullName = ""
          this.error = false
          this.success = true
          this.submitting = false

          this.$router.push({
            name: "Authors",
          });
        } catch (error) {
          console.error(error);
        }
      },
      clearStatus() {
        this.success = false
        this.error = false
      },
    }
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