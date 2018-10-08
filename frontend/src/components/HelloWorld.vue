<template>
  <div class="hello">

    <h1>{{ hellomsg }}</h1>


    <v-select v-model="selected" :options="exchangeList"></v-select>
    <card :className="max" :price="123"></card>
  </div>
</template>

<script>
  import axios from 'axios';
  import vSelect from 'vue-select';
  import Card from '@/components/Card';

  export default {
    name: 'hello',
    props: {hellomsg: {type: String, required: true}},
    components: {"v-select": vSelect, "card": Card},
    data() {
      return {
        exchangeList: [],
        selected: null
      }
    },
    created() {
      axios.get("http://localhost:8080/products").then(response => {
        console.log(response.data);
        this.exchangeList = response.data
      })
    },
    methods: {
      showPrices: function () {
        console.log("Bunda")
      }
    }
  }

</script>


<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }

  .hello {
    max-width: 30em;
    margin: 1em auto;
  }
</style>

