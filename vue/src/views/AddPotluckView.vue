<template>
    <div class="create">
        <h1>Create New Potluck</h1>
        <PotluckForm v-bind:potluck="potluck" />
    </div>
</template>

<script>
import PotluckForm from '../components/PotluckForm.vue';

export default {
    components: {
        PotluckForm
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