<template>
<form v-on:submit="addNewDish">
    <div class="form-element">
        <label for="name">Name:</label>
        <input id="name" type="text" v-model="newDish.dish_name"/>
    </div>
    <div class="form-element">
        <label for="category">Category:</label>
        <input id="category" type="text" v-model="newDish.dish_category"/>
    </div>
    <div class="form-element">
        <label for="servings">Servings:</label>
        <input id="servings" type="number" v-model="newDish.dish_servings"/>
    </div>
    <div class="form-element">
        <label for="diet">Dietary Restrictions:</label>
        <input id="diet" type="text" v-model="newDish.dish_dietary_restrictions"/>
    </div>
    <div class="form-element">
        <label for="recipe">Recipe:</label>
        <input id="recipe" type="text" v-model="newDish.dish_recipe"/>
    </div>
    <input class="submitbtn" type="submit" value="Save" />
    <input class="cancelbtn" type="button" value="Clear" @click="cancelForm" />
</form>
</template>



<script>
import PotluckService from '../services/PotluckService';

export default {
    props: {
        dish: {
            type: Object,
            required: true
        }
    },
    data(){
        return {
            newDish: {
                dish_name: "",
                dish_dietary_restrictions: "",
                dish_category: "",
                dish_servings: 0,
                dish_recipe: ""
            }
        };
    },
    methods: {
        addNewDish(){
            PotluckService.bringDish(this.$route.params.potluckId, this.newDish);
            this.resetForm();
            this.$router.push({name: 'PotluckView', params: {potluckId: this.$route.params.potluckId}});
        },
        cancelForm(){
            this.$emit('cancel');
            this.resetForm();
        },
        resetForm(){
            this.newDish = {};
        }
    }
}
</script>



<style>


</style>