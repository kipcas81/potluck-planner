<template>
  <div class="home">
    <h1>Potluck Planner</h1>
    <p>something something potluck</p>
    <!-- <PotluckForm/> -->
    <button class="create-potluck" v-on:click="$router.push({path: '/add-potluck'})">Create New Potluck</button>
  
  <PotluckList :potlucks="potlucks"/>
  </div>
</template>

<script>
  import PotluckList from '../components/PotluckList.vue';

export default {
  components: {
    PotluckList
  },
  created(){
    
  }
}

  // import PotluckService from '../services/PotluckService';
// import PotluckForm from '../components/PotluckForm.vue';
// export default {
//   components: {
//         PotluckForm
//     }
// };
</script>
