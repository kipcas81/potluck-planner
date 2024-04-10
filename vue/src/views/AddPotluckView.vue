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
                id: "",
                name: "",
                description: "", 
                date: "",
                time: "",
                creator: "",
                diet: [],
                categories: [],
                isRecurring: "",
                frequency: "",
                location: "",
                isPrivate: ""
      },
    }
  }
};
</script>

<style>

</style>