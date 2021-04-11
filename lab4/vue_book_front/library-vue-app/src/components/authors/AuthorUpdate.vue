<template>
  <div id="author-form-view" class="small-container">
    <h1>Edit author</h1>
    <div id="author-form">
      <form @submit.prevent="updateAuthor(author)">
        <label>Fullname</label>
        <input v-model="author.fullName" name="title" type="text" />
        <button>Edit book</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "author-update",
  data() {
    return {
      author: {
        id: -1,
        fullName: "",
      },
    };
  },
  methods: {
    async getAuthor() {
      let authorId = this.$route.params.id;

      try {
        var url = new URL("http://localhost:8081/authors/" + authorId);

        const response = await fetch(url, {
          method: "GET",
        });

        const data = await response.json();
        this.author = data;
      } catch (error) {
        console.error(error);
      }
    },

    async updateAuthor(author) {
      let authorToUpdateId = author.id;

      try {
        var url = new URL("http://localhost:8081/authors/" + authorToUpdateId);

        fetch(url, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(author),
        });

        this.$router.push({
          name: "Authors",
        });
      } catch (error) {
        console.error(error);
      }
    },
  },
  mounted() {
    this.getAuthor();
  },
};
</script>

<style>
</style>