<template>
<form v-on:submit="addNewDishNeed">
    <div>
        <label for="dish_category">Required Dish</label>
        <input id="dish_category" type="text" v-model="newDishNeeds.dish_category">
    </div>
    <div>
        <label for="servings">How many servings is this dish?</label>
        <input id="servings" type="number" v-model="newDishNeeds.dish_serving_count_needed">
    </div>
    <input class="submitbtn" type="submit" value="Save" />
    <input class="cancelbtn" type="button" value="Cancel" @click="cancelForm"/>
</form>
<p>Done adding dish needs? <router-link :to="{name: 'PotluckView', params: {potluckId: $route.params.potluckId}}">Click Me!</router-link></p>
</template>

<script>
import PotluckService from '../services/PotluckService';
export default {
    props: {
        dishNeeds: {
            type: Object,
            required: true
        }
    },
    data(){
        return {
            newDishNeeds: {
                potluck_id: this.$route.params.potluckId,
                dish_category: "",
                dish_serving_count_needed: 0,
                dish_serving_count_filled: 0
            },

        };
    },
    methods: {
        addNewDishNeed(){
            if(this.newDishNeeds.dish_serving_count > 0){
              PotluckService.addDishNeeds(this.newDishNeeds, this.$route.params.potluckId);
            this.resetForm();  
            }
        },
        cancelForm(){
            this.$emit('cancel');
            this.resetForm();
        },
        resetForm(){
            this.newDishNeeds = {};
        }
    }
}
</script>