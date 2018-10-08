<template>
<p :class='className'>{{price}}</p>
</template>
<script>

  export default {
    name: 'Card',
    props: {className: {type: String, required: true}, price:{type:String, required:true}},
  }
</script>
<style scoped>


</style>
