<template>
    <div class="banner">
      <img class="logo-left" src="../assets/2-Prawn-logo.png"/>
      <div class="headers">
        <h1 id="title">Two Prawn Potluck Planner</h1>
        <p id="tagline">"You're telling me a shrimp fried this rice?"</p>
        </div>
    <img class="logo-right" src="../assets/2-Prawn-logo.png"/>
    
   </div>
 
    
   <button class="create-potluck" v-on:click="$router.push({path: '/add-potluck'})">Create New Potluck</button>
<div class="potlucks">
  <PotluckList :potlucks="potlucks"/>
</div>
  
</template>
<script>
  import PotluckService from '../services/PotluckService.js';
  import PotluckList from '../components/PotluckList.vue';
export default {
  components: {
    
    PotluckList
  },
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


<style scoped>
.potlucks {
  margin-top: 10%;
  margin-left: 20px;
}

.headers {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 50vw
}
.banner {
  display: flex;
  flex-direction: row;
  font-family: cursive;
  align-items: center;
  justify-content: space-evenly;
  width: 95vw;
  background-color: rgb(252,191,188);
  border: 4px solid rgb(255,127,80);
  padding: 10px 20px;
  border-radius: 15px;
  margin-top: 10px;
  height: 115px;
  position: absolute; 
  top: 40px;
  left: 50%; 
  transform: translateX(-50%);
  background-size: auto;
}

.logo-left,
.logo-right {
  height: 90px;
  position: relative; 
}

.logo-left {
  transform: rotateY(180deg);
  position: relative; 
}
.logo-right {
  position: relative;
}

p {
  font-style: italic;
}
.create-potluck {
  font-family: cursive;
  font-size: large;
  height: 40px;
  width: 20vw;
  background-color: #bcf9fc;
  border-color: #09c7d1;
  border-radius: 10px;
  position: absolute;
  top: 210px; /* Adjust this value to move the button below the banner */
  left: 50%;
  transform: translateX(-50%);
}
.potlucks {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  margin-top: 12%;
}




</style>
