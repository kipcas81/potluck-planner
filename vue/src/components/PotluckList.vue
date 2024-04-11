<template>
<div>
    <div class="potluckBox" v-for="potluck in potlucks" v-bind:key="potluck.id">
     <h2> {{ potluck.eventName }} </h2>
     <p>{{potluck.description}}</p>
      <P> {{ potluck.eventDate }}  starting at {{ potluck.eventTime }}</P>
      <P> Please bring </P>
    </div>
    
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
    //
    }
    //
  },
  created(){
    this.getPotlucks();
  }
}




</script>


<style>
</style>