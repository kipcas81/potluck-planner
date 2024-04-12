<template>
    <header class="flex">
        <h1>{{ potluck.eventName }}</h1>
        <div class="actions">
            <button class="btn-edit" v-on:click="$router.push({name: 'EditPotluck', paras: {potluckId: potluckId}})">Edit</button>
            <button class="btn-delete" v-on:click="deletePotluck">Delete</button>
            <button class="inv-guest" v-on:click="inviteGuest">Invite Guests</button>
            <button class="update-dish-needs" v-on:click="updateDish">Update Dish Needs</button>
        </div>
    </header>

</template>

<script>
import PotluckService from '../services/PotluckService';

export default {
    props: {
        potluck: {
            type: Object,
            required: true
        }
    },
    computed: {

    },
    methods: {

    }
}
</script>

<style>

</style>