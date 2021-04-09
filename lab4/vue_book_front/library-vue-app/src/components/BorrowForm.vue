<template>
  <div id="borrow-form">
    <form  @submit.prevent="handleSubmit">>
      <label>Tytuł - autor</label>
      <input v-model="borrow.title" type="text" />
      <label>User</label>
      <input v-model="borrow.user" type="text" />
      <button>Wypożycz książke</button>
    </form>
  </div>
</template>
<script>
export default {
  name: 'borrow-form',
  data() {
    return {
      borrow: {
        book: '',
        user: '',
      },
    }
  },
  methods: {
    handleSubmit() {
      this.$emit('add:borrow', this.borrow)
    },
  }
}
</script>
<style scoped>
form {
  margin-bottom: 2rem;
}
</style>
