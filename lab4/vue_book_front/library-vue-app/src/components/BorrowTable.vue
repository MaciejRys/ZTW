<template>
  <div id="borrow-table">
    <form  @submit.prevent="handleSubmit">
    <table>
      <thead>
      <tr>
        <th>Tytuł - autor</th>
        <th>Użytownik</th>
        <th>Usuń</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="borrow in borrowSource" :key="borrow.id">
        <td v-model="borrow.book">{{borrow.book}}</td>
        <td>{{borrow.user}}</td>
        <td><button>usuń wypożyczenie</button></td>
      </tr>
      </tbody>
    </table>
    </form>
  </div>
</template>
<script>
export default {
  name: 'borrow-table',
  props: {
    borrowSource: Array,
  },
  data() {
    return {
      borrow: {
        book: ''
      },
    }
  },
  methods: {
    handleSubmit() {
      this.$emit('delete:borrow', this.borrow)
    }
  }
}
</script>
<style scoped></style>