<template>
  
  <div class="home">
    <div class="banner">
    <h1>Potluck Planner</h1>
    <p>something something potluck</p>
    <button class="create-potluck" v-on:click="$router.push({path: '/add-potluck'})">Create New Potluck</button>
  </div>
  <div class="potlucks">
  <PotluckList :potlucks="potlucks"/>
  </div>
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
.banner {
  font-family: cursive;
  width: 96vw;
  background-color: rgb(252,191,188);
  border: 4px solid rgb(255,127,80);
  padding: 10px 20px;
  border-radius: 15px;
  margin-top: 10px;
  margin-bottom: 50px;
  position: absolute; 
  top: 40px;
  left: 50%; 
  transform: translateX(-50%);
  /* background-image: url("./src/assets/shrimp.gif") left center no-repeat, url("./src/assets/shrimp.gif") right center no-repeat;
  background-size: auto; */
}
.potlucks {
  margin-top: 10%;
  margin-left: 5%
}



</style>
