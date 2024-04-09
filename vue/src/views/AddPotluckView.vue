<template>
    <div class="create">
        <h1>Create New Potluck</h1>
        <AddPotluck v-bind:potluck="potluck" />
    </div>
</template>

<script>
import AddPotluck from '../components/AddPotluck.vue';

export default {
    components: {
        AddPotluck
    },
    data() {
        return {
            potluck: {
                name: '',
                description: '',
                categories: [],
                diet: [],
                location: '',
                date: new Date(),
                time: new TimeRanges,
      },
    }
  }
};
</script>

<style>

</style>