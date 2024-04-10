<template>
  <div class="home">
    <h1>Potluck Planner</h1>
    <p>something something potluck</p>
    <!-- <PotluckForm/> -->
    <!-- <AddPotluckView/> -->
    <button class="create-potluck" v-on:click="$router.push({name: 'addpotluck'})">Create New Potluck</button>
    
  </div>
</template>

<script>
import AddPotluckView from './AddPotluckView.vue';
import PotluckService from '../services/PotluckService.js';
import PotluckForm from '../components/PotluckForm.vue';

export default {
  components: {
        // PotluckForm,
        // AddPotluckView
    }
};
</script>
