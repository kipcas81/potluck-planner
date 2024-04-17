<template>
<div>
    <h1>Edit Potluck</h1>
    <PotluckForm v-bind:potluck="potluck"/>
</div>
</template>


<script>
import PotluckForm from '../components/PotluckForm.vue';
import PotluckService from '../services/PotluckService';

export default {
    components: {
        PotluckForm
    },
    data() {
        return {
            potluck: {
                eventName: "",
                description: "", 
                eventDate: "",
                eventTime: "",
                dietaryRestrictions: "",
                recurring: false,
                frequencyDays: 0,
                location: "",
                private: false
            }
        };
    },
    created() {
        let potluckId = parseInt(this.$route.params.potluckId);
        if (potluckId != 0){
            PotluckService.getPotluck(potluckId)
            .then(response => {
                this.potluck = response.data;
                this.potluck.potluckId = potluckId;
            })
        }
    }
}
</script>


<style>

</style>