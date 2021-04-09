<template>
  <div id="borrow-table">
    <table>
      <thead>
      <tr>
        <th>Tytuł - autor</th>
        <th>Użytownik</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="borrow in borrowSource" :key="borrow.id">
        <td>{{borrow.title}}</td>
        <td>{{borrow.user}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
export default {
  name: 'borrow-table',
  props: {
    borrowSource: Array,
  },
}
</script>
<style scoped></style>