<template>
<div>
    <router-link v-for="potluck in potlucks" v-bind:key="potluck.id">{{ potluck.name }}</router-link>
</div> 
</template>

<script>
import PotluckService from '../services/PotluckService';
export default {
    
    data() {
    return {
      potlucks: [],
    };
  },
  methods: {
    getPotlucks() {
      PotluckService.potluckList()
      .then(response => {
        this.potlucks = response.data;
      })
    
    }
    
  },
  created(){
    this.getPotlucks();
  }
}




</script>


<style>
</style>