<template>
<div>
    <router-link class="potluckBox" v-for="potluck in potlucks" v-bind:key="potluck.potluckId" v-bind:to="{name: 'PotluckView', params: {potluckId: potluck.potluckId}}">
     <p>{{potluck.eventName}}</p>
    </router-link>
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