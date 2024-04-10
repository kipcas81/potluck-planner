<template>
    <div>
      <router-link v-bind:to="{ name: 'HomeView' }">Back to Home</router-link>
    </div>
</template>


<script>
import PotluckService from '../services/PotluckService';

export default {
    components: {
        
    }
}
</script>


<style>
</style>