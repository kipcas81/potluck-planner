<template>
<div class="potluckBox" v-for="potluck in potlucks" v-bind:key="potluck.potluckId" :class="{completed: potluck.completed}">
  <div>
    <img class="potluckImage" :src="getRandomImage()"/>
  </div>
    <!-- add @click="saveSelectedPotluck(potluck)" -->
     <router-link v-bind:to="{name: 'PotluckView', params: {potluckId: potluck.potluckId}}" >{{potluck.eventName}}</router-link>
     <p> <i class="fa-solid fa-location-dot" style="color: rgb(0, 0, 0);"></i>  Location: {{ potluck.location }}</p>
    <p> <i class="fa-regular fa-calendar" style="color: rgb(0, 0, 0);"></i>  Date: {{ potluck.eventDate }}</p>
    <p> <i class="fa-regular fa-clock" style="color: rgb(0, 0, 0);"></i> Start Time: {{ potluck.eventTime }}</p>
    <input class="clonebtn" type="button" value="Duplicate Potluck" v-on:click="duplicatePotluck(potluck.potluckId)"/>
    </div>
</template>

<script>
import PotluckService from '../services/PotluckService';
// import { mapMutations } from 'vuex';
import potluck1 from "../assets/images/potluck1.jpg";
import potluck2 from "../assets/images/potluck2.jpg";
import potluck3 from "../assets/images/potluck3.jpg";
import potluck4 from "../assets/images/potluck4.jpg";
import potluck5 from "../assets/images/potluck5.jpg";
import potluck6 from "../assets/images/potluck6.jpg";
import potluck7 from "../assets/images/potluck7.jpg";
import potluck8 from  "../assets/images/potluck8.jpg";
import potluck9 from "../assets/images/potluck9.jpg";
import potluck10 from "../assets/images/potluck10.jpg";
import potluck11 from "../assets/images/potluck11.jpg";
import potluck12 from "../assets/images/potluck12.jpg";
import potluck13 from "../assets/images/potluck13.jpg";
import potluck14 from "../assets/images/potluck14.jpg";

export default {
    
    data() {
    return {
      potlucks: [],
      potluckImages: [
      potluck1, potluck2, potluck3, potluck4, potluck5, potluck6, potluck7, potluck8, potluck9, potluck10, potluck11, potluck12, potluck13, potluck14
      ]
    };
  },
  methods: {
    // ...mapMutations(['SET_SELECTED_POTLUCK']),
    // saveSelectedPotluck(potluck){
    //   this.SET_SELECTED_POTLUCK(potluck);
    // },
    getPotlucks() {
      PotluckService.potluckList()
      .then(response => {
        this.potlucks = response.data;
      })
    },
    getRandomImage(){
      const randomIndex = Math.floor(Math.random() * this.potluckImages.length);
      return this.potluckImages[randomIndex];
    },
    duplicatePotluck(potluckId){
            if (
                confirm('Are you sure you want to duplicate this potluck?')
            ) {
                PotluckService.clonePotluck(potluckId);
                this.$router.push({path: '/'})
        }
    }
    
  },
  created(){
    this.getPotlucks();
  }
}
</script>


<style>

.potluckImage{
  height: 150px;
  border-radius: 10px;
  border-color: rgb(0,0,0);
}
.potluckBox {
  display: flex;
  padding: 10px;
  flex-direction: column;
  margin-right: 25px;
  border-radius: 15px;
  align-items: center;
  background-color: rgb(252,191,188);
  border: 4px solid rgb(255,127,80);
  width: 15vw;
  position: relative;
  top: 40px;
  margin-bottom: 25px;
}

.completed {
  filter: grayscale(1);
}
</style>