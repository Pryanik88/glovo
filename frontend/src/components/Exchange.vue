<template>
  <div class="hello">

    <h1>{{ hellomsg }}</h1>
    <h2>Choose your product:</h2>

    <v-select v-model="selected" :options="exchangeList"></v-select>
    <p><span v-if="loading">Loading...</span>&nbsp;</p>
    <span v-if="max !=null" class="green badge">{{max}}</span>
    <span v-if="notRated !=null" class="badge">{{notRated}}</span>
    <span v-if="min !=null" class="red badge">{{min}}</span>
  </div>
</template>

<script>
  import axios from 'axios';
  import vSelect from 'vue-select';


  export default {
    name: 'exchange',
    props: {hellomsg: {type: String, required: true}},
    components: {"v-select": vSelect},
    data() {
      return {
        loading: false,
        exchangeList: [],
        selected: null,
        max: null,
        min: null,
        notRated: null
      }
    },
    created() {
      this.loading = true;
      axios.get("http://localhost:8080/products").then(response => {
        this.exchangeList = response.data
        this.loading = false;
      }).catch(() => {
        this.loading = false;
      })
    },
    methods: {
      showPrices: function () {
        console.log("Bunda")
      }
    },
    watch: {
      selected() {
        this.loading = true;
        axios.get("http://localhost:8080/products/" + this.selected + "/prices").then((response) => {
          let data = {};
          data = response.data;
          let sortable = [];
          for (let exchange in data) {
            sortable.push([exchange, data[exchange]]);
          }
          sortable.sort(function(a, b) {
            return a[1] - b[1];
          });
          this.max = sortable[2][0]+":"+sortable[2][1];
          this.min = sortable[0][0]+":"+sortable[0][1];
          this.notRated = sortable[1][0]+":"+sortable[1][1];
          this.loading = false;
        }).catch(() => {
          this.loading = false;
          this.max = null;
          this.min = null;
          this.notRated = null;
        })
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

  .badge {
    display: inline-block;
    padding: 0.25em 0.4em;
    font-size: 75%;
    font-weight: 700;
    line-height: 1;
    text-align: center;
    white-space: nowrap;
    vertical-align: baseline;
    border-radius: 0.25rem;
  }
  .green {
    color: #fff;
    background-color: #28a745;
  }
  .red {
    color: #fff;
    background-color: #dc3545;
  }


</style>

