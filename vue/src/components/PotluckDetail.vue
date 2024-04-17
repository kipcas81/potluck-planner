<template>
    <header class="flex">

    <div class="container">
<<<<<<< HEAD
        <!-- <h1>{{ potluck.eventName }}</h1> -->
        <div class="description">
            <h3>Description: {{ potluck.description }}</h3>
        </div>
=======
        <h1>{{ potluck.eventName }}</h1>
        <h2>{{ potluck.description }}</h2>
>>>>>>> f332adaa50d6ee3510dadee0dfcaadd2b38e9e2d
        <p>Dietary Restrictions: {{ potluck.dietaryRestrictions }}</p>
        <p>Starting at: {{ potluck.eventTime }} on {{ potluck.eventDate }}</p>
        <p>Location: {{ potluck.location }}</p>
        <p v-if="potluck.private">This is a private event.</p>
        
        <div class="actions" v-if="isHost()">
            <button class="btn-edit" v-on:click="$router.push({name: 'EditPotluckView', params: {potluckId: potluckId}})">Edit</button>
            <button class="btn-delete" v-on:click="deletePotluck">Delete</button>
            <button class="inv-guest" v-on:click="$router.push({name: 'AddGuestView', params: {potluckId: potluckId}})" >Invite Guests</button>
            <button class="update-dish-needs" v-on:click="$router.push({name: 'AddDishNeedsView', params: {potluckId: potluckId}})">Dish Needs</button>
        </div>
        </div>
    </header>

</template>

<script>
import PotluckService from '../services/PotluckService';

export default {
    props: ['potluck'],
    methods: {
        deletePotluck() {
            if (
                confirm('Are you sure you want to delete this potluck? This cannot be undone.')
            ) {
                PotluckService.deletePotluck(this.potluck.potluckId)
                .then(response => {
                    if(response.status === 200){
                        this.$router.push({path: '/'});
                    }
                });
                
            }
        },
        isHost(){
            return this.$store.state.user.id === this.potluck.userId; 
        }
    }
}
</script>

<style>
.description {
    width: 75%;
    word-wrap: break-word;
}
</style>
