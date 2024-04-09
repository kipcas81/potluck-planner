<template>
  <div class="home">
    <h1>Potluck Planner</h1>
    <p>You must be authenticated to see this</p>
    <AddPotluck/>
  </div>
</template>

<script>
import AddPotluck from '../components/AddPotluck.vue';
export default {
  components: {
        AddPotluck
    }
};
</script>
