<template>
  <div id="borrow-table">
    <button
        @click="
        $router.push({
          name: 'BorrowForm',
        })
      "
    >
      Dodaj wypożyczenie
    </button>
    <form>
    <table>
      <thead>
      <tr>
        <th>Tytuł - autor</th>
        <th>Użytownik</th>
        <th>Usuń</th>
        <th>Info</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="borrow in borrows" :key="borrow.id">
        <td>{{borrow.book}}</td>
        <td>{{borrow.user}}</td>
        <td><button  @click="deleteBorrow(borrow.book)" >usuń wypożyczenie</button></td>
        <td><button  @click="$router.push({
                  name: 'BorrowInfo',
                  params: { book: borrow.book, user: borrow.user }
                })"> pokaż informację </button></td>
      </tr>
      </tbody>
    </table>
    </form>
    <button
        @click="
                  $router.push({
                    name: 'BorrowForm'
                  })
                "
    >
      Dodaj Wypożyczenie
    </button>
  </div>
</template>
<script>
export default {
  name: 'borrow-table',
  data() {
    return {
      borrows: [],
    }
  },
  methods: {
    deleteBorrow(book) {
      try {
        fetch('http://localhost:8081/rents/free/' + book, {method:"POST",mode:"no-cors"})
        this.getBorrows();
      } catch (error) {
        console.error(error)
      }
    },
    async getBorrows() {
      try {
        this.borrows = [];
        const response = await fetch('http://localhost:8081/rents')
        const data = await response.json()
        for (var key in data) {
          for(var i = 0; i < data[key].length; i++){
            this.borrows.push({ book: data[key][i].title , user: key });
          }
        }
      } catch (error) {
        console.error(error)
      }
    }
  },
  mounted() {
    this.getBorrows();
  },
}
</script>
<style scoped>

</style>