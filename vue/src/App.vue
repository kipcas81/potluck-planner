<template>
  <div id="capstone-app">
    <div id="nav">
      <!-- need pipe -->
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'friends'}">Friends</router-link>
    </div>
    <router-view />
  </div>
</template>

<style >
#capstone-app {
  background-image: url("./assets/2-prawn-pattern.png"); 
  background-size: contain;  
  height: 100%;
  background-position: center; 
  background-repeat: repeat; 
  font-family: cursive;
  
}

#nav {
  background-color: rgba(255, 255, 255, 0.7); 
  padding: 10px;
 
}

</style>