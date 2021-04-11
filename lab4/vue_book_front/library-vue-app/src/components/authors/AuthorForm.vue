<template>
  <div id="app" class="small-container">
    <h1>Add author</h1>
    <div id="author-form">
      <form @submit.prevent="addAuthor(author)">
        <label>Fullname</label>
        <input v-model="author.fullName" name="fullName" type="text" />
        <button>Add author</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "author-form",
  data() {
    return {
        author: {
          id: -1,
          fullName: "",
        },
      authors: [],
    };
  },
  methods: {
    async addAuthor(author) {
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
        this.$router.push({
          name: "Authors",
        });
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>

<style scoped>
</style>