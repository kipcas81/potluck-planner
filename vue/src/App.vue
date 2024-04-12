<template>
  <div id="capstone-app">
    <div id="nav">
      <!-- need pipe -->
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;|&nbsp;
      <!-- <router-link v-bind:to="{name: 'friends'}">Friends</router-link> -->
    </div>
    <router-view />
  </div>
</template>

<style>
body{
  font-family: Arial, Helvetica, sans-serif;
  background-color: rgb(255, 238, 170);
}

h1{
  color: rgb(52, 38, 7);
}

</style>