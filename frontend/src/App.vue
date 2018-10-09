<template>
  <div id="app">
    <router-view :hellomsg="msg" />
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      msg: 'Wellcome to Crypto Currency Exchange Informer!'
    }
  }
};
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
