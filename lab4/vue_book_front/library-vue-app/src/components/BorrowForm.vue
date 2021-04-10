<template>
  <div id="borrow-form">
    <form  @submit.prevent="handleSubmit">
      <label>Tytuł - autor</label>
      <select v-model="borrow.book"  name="book" type="text" >
      <option v-for="book in bookSource" :key="book">
        {{book}}
      </option>
      </select>
      <label>User</label>
      <select v-model="borrow.user"  name="user" type="text" >
        <option v-for="user in userSource" :key="user">
          {{user}}
        </option>
      </select>
      <button>Wypożycz książke</button>
    </form>
  </div>
</template>
<script>
export default {
  name: 'borrow-form',
  props: {
    bookSource: Array,
    userSource: Array,
  },
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
