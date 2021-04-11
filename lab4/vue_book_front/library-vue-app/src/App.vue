<template>
  <div id="app" class="small-container">
    <header-comp/>
    <router-view></router-view>
    <footer-comp/>
  </div>
</template>
<script>
import FooterComp from './components/FooterComp.vue'
import HeaderComp from './components/HeaderComp.vue'


export default {
  name: 'app',
  components: {
    HeaderComp,
    FooterComp
  }
}
</script>
<style>
button {
  background: #009435;
  border: 1px solid #009435;
}
.small-container {
  max-width: 680px;
}
</style>

