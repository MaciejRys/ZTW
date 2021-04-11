<template>
  <header>
    <nav class="navbar">
      <router-link to="/">Home</router-link>
      <router-link to="/books">Books</router-link>
      <router-link to="/authors">Authors</router-link>
      <router-link to="/BorrowTable">Borrows</router-link>
    </nav>
  </header>
</template>

<script>
export default {
  name: "header-comp",
};
</script>

<style scoped>
/* The navigation bar */
.navbar {
  overflow: hidden;
  background-color: #333;
  position: fixed; /* Set the navbar to fixed position */
  top: 0; /* Position the navbar at the top of the page */
  width: 100%; /* Full width */
  left: 0;
}

/* Links inside the navbar */
.navbar a {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

/* Change background on mouse-over */
.navbar a:hover {
  background: #ddd;
  color: black;
}
</style>