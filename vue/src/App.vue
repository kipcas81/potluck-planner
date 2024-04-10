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
