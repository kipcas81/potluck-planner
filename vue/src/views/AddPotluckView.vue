<template>
    <h1>Create New Potluck</h1>
    <AddPotluck v-bind:potluck="potluck" />
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
                diet: [],
                location: '',
                date: new Date(),
                
            },
        }
    }
 };
</script>

<style>

</style>