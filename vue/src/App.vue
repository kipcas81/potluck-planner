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
  background-size: cover;  
  background-attachment: fixed; /* Background fixed so it won't scroll with content */
  background-position: center; 
  background-repeat: no-repeat; 
  font-family: cursive;
  min-height: 100vh; /* Set minimum height to fill the viewport */
  position: relative; /* Add position relative to enable z-index for other content */
}

#nav {
  background-color: rgba(255, 255, 255, 0.7); 
  padding: 10px;
  position:sticky;
  top: 0;
  z-index: 1;
}
/* @font-face {
  font-family: '';

  src: url('');
} */

</style>