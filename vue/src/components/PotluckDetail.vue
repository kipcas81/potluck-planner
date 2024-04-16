<template>
    <header class="flex">

    <div class="container">
        <h1>{{ potluck.eventName }}</h1>
        <h2>{{ potluck.description }}</h2>
        <p>Dietary Restrictions: {{ potluck.diet }}</p>
        <p>Starting at: {{ potluck.eventTime }} on {{ potluck.eventDate }}</p>
        <p>Location: {{ potluck.location }}</p>
        <p v-if="potluck.private">This is a private event.</p>
        

    <div id="actions">
        <button class="btn-edit" v-on:click="$router.push({name: 'EditPotluckView', params: {potluckId: potluckId}})">Edit</button>
        <button class="btn-delete" v-on:click="deletePotluck">Delete</button>
        <button class="inv-guest" v-on:click="inviteGuest">Invite Guests</button>
        <button class="update-dish-needs" v-on:click="updateDish">Dish Needs</button>
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
        }
    }
}
</script>
