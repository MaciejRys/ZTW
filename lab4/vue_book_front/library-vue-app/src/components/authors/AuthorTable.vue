<template>
  <div id="persons-table-component">
    <button
      @click="
        $router.push({
          name: 'AuthorForm',
        })
      "
    >
      Dodaj autora
    </button>
    <div id="persons-table">
      <table>
        <thead>
          <th>Id</th>
          <th>Imię i nazwisko</th>
        </thead>
        <tbody>
          <tr v-for="author in authors" :key="author.id">
            <td>{{ author.id }}</td>
            <td>{{ author.fullName }}</td>
            <td>
              <button
                @click="
                  $router.push({
                    name: 'AuthorUpdate',
                    params: { id: author.id },
                  })
                "
              >
                Edit
              </button>
              <button @click="deleteAuthor(author.id)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
      <button
          @click="
                  $router.push({
                    name: 'AuthorForm'
                  })
                "
      >
        Dodaj Autora
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "authors-table",
  data() {
    return {
      authors: [],
    };
  },  watch: {
    // call again the method if the route changes
    '$route': 'getAuthors'
  },
  methods: {
    async getAuthors() {
      await new Promise(resolve => setTimeout(resolve, 100))
      this.authors = []
      try {
        const response = await fetch("http://localhost:8081/authors");
        const data = await response.json();
        for (var i = 0; i < data.length; i++) {
          this.authors.push(data[i]);
        }
      } catch (error) {
        console.error(error);
      }
    },

    async deleteAuthor(authorId) {
      fetch("http://localhost:8081/authors/" + authorId, {
        method: "DELETE",
      }).then((respFulfilled) => {
        this.authors = this.authors.filter((author) => {
          return author.id != authorId;
        });
      }).catch(respRejected => {
        console.log(respRejected)
      });
    },
  },
  mounted() {
    this.getAuthors();
  },
  activated() {
    this.getAuthors();
  },
};
</script>

<style>
button {
  margin-right: 10px;
}
</style>