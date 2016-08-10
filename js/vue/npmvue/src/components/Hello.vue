<template>
  <div class="hello">
    <h1 >{{ msg }}{{msgfather}}</h1>
    <button v-on:click="doThis">doThis</button>
   fatherWord: {{fatherWord}}
  </div>
</template>

<script>
export default {
  data: function () {
    return {
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
      msg: 'Hello World!',
      fatherWord: ''
    }
  },
  props: [ 'msgfather' ],
  methods: {
    doThis: function () {
      console.log(this.msgfather)
      this.fatherWord = this.msgfather
      this.$emit('child-tell-me', this.msg)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1 {
  color: #42b983;
}
</style>
