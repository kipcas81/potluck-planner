<template>
  
  <div class="home">
      <div class="banner">
    <h1 id="title">Two Prawn Potluck Planner</h1>
    <p id="tagline">"You're telling me a shrimp fried this rice?"</p>
      </div>

  <div class="potlucks">
    <button class="create-potluck" v-on:click="$router.push({path: '/add-potluck'})">Create New Potluck</button>
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
.potlucks {
  margin-top: 10%;
  margin-left: 20px;
}
.banner {
  display: flex;
  flex-direction: column;
  font-family: cursive;
  align-items: center;
  justify-content: space-between;
  width: 96vw;
  background-color: rgb(252,191,188);
  border: 4px solid rgb(255,127,80);
  padding: 10px 20px;
  border-radius: 15px;
  margin-top: 10px;
  margin-bottom: 80px;
  height: 115px;
  position: absolute; 
  top: 40px;
  left: 50%; 
  transform: translateX(-50%);
  background-size: auto;
}




</style>
