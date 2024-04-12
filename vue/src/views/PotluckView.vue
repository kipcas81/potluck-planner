<template>
    <div>
      <!-- this is where the potluck-detail will go -->
    </div>
</template>


<script>
// import potluck detail
import PotluckService from '../services/PotluckService';

export default {
    components: {
        // potluck detail
    },
    data(){
      return {
        Potluck: {
                name: "",
                description: "", 
                date: "",
                time: "",
                diet: [
                { id: 'vegan', label: 'Vegan', checked: false },
                { id: 'vegetarian', label: 'Vegetarian', checked: false},
                { id: 'gluten', label: 'Gluten-Free', checked: false },
                { id: 'sodium', label: 'Low-Sodium', checked: false },
                { id: 'paleo', label: 'Paleo', checked: false },
                { id: 'halal', label: 'Halal', checked: false },
                { id: 'kosher', label: 'Kosher', checked: false },
                { id: 'dairy', label: 'Dairy-Free', checked: false },
                { id: 'nuts', label: 'No-Nuts', checked: false },
                { id: 'sugar', label: 'Sugar-Free', checked: false}
                ],
                isRecurring: false,
                frequency: 0,
                location: "",
                isPrivate: false
            },
      };
    },
    created(){
      PotluckService.getPotluck(this.$route.params.potluckId)
      .then(response => {
        this.potluck = response.data;
      });
    }
}
</script>


<style>
</style>