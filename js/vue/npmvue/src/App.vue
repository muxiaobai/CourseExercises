<template>
  <div id="app">
    <img class="logo" src="./assets/logo.png">
    <h1 v-text="title"></h1>
    <input v-model="newItem" v-on:keyup.enter="addNew">
    <div v-for="item in items">
       <div v-text="item.label" v-on:click="doThis(item)" v-bind:class="{finish:item.isFinished}">
      
        </div>
    </div>
     childWord: {{childWord}}
    <hello msgfather="father" v-on:child-tell-me="listenMySon"></hello>

  </div>
</template>

<script>
import Store from './Store'
import hello from './components/Hello'
export default {
  data: function () {
    return {
      title: ' <span>?</span> todo List',
      items: Store.fetch(),
      newItem: '',
      childWord: ''
    }
  },
  components: {
    hello
  },
  methods: {
    doThis: function (item) {
      item.isFinished = !item.isFinished
    },
    addNew: function () {
      this.items.push({
        label: this.newItem,
        isFinished: false
      })
      this.newItem = ''
    },
    listenMySon: function (msg) {
      this.childWord = msg
    }
  },
  watch: {
    items: {
      handler: function (item) {
        Store.save(item)
      },
      deep: true
    }
  }
}
</script>

<style>
.finish{
  text-decoration: underline;
}
html {
  height: 100%;
}

body {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

#app {
  color: #2c3e50;
  margin-top: -100px;
  max-width: 600px;
  font-family: Source Sans Pro, Helvetica, sans-serif;
  text-align: center;
}

#app a {
  color: #42b983;
  text-decoration: none;
}

.logo {
  width: 100px;
  height: 100px
}
</style>
