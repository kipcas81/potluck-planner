<template>
  <div class="home">
    <h1>Potluck Planner</h1>
    <p>something something potluck</p>
    <button class="create-potluck" v-on:click="$router.push({path: '/add-potluck'})">Create New Potluck</button>
  
  <PotluckList />
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
