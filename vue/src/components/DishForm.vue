<template>
<form v-on:submit="addNewDish">
    <div class="form-element">
        <label for="name">Dish Name:</label>
        <input id="name" type="text" v-model="newDish.dish_name"/>
    </div>
    <div class="form-element">
        <label for="category">Category:</label>
        <input id="category" list="dish_category" type="text" v-model="newDish.dish_category"/>
        <datalist id="dish_category">
                <option value="Dessert"></option>
                <option value="Appetizer"></option>
                <option value="Entre"></option>
                <option value="Sides"></option>
                <option value="Salad"></option>
                <option value="Soup"></option>
                <option value="BBQ"></option>
                <option value="Casseroles"></option>
                <option value="Chicken"></option>
                <option value="Fish"></option>
                <option value="Pasta"></option>
                <option value="Pizza"></option>
                <option value="Beef"></option>
                <option value="Breakfast"></option>
                <option value="Pork"></option>
                <option value="Pizza"></option>
                <option value="Rice"></option>
                <option value="Beans"></option>
                <option value="Seafood"></option>
                <option value="Vegetables"></option>
                <option value="Mac and Cheese"></option>
                <option value="Shrimp"></option>
                <option value="Prawns"></option>
                <option value="Bread"></option>
                <option value="Curry"></option>
                <option value="Sandwiches"></option>
                <option value="Chips"></option>
                <option value="Beer"></option>
                <option value="Wine"></option>
                <option value="Seltzers"></option>
                <option value="Non-Alcoholic Drinks"></option>
                <option value="Other"></option>
            </datalist>
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
    <input class="cancelbtn" type="button" value="Cancel" @click="this.$router.push('/')">
</form>
<div>
    <h1>Dish Needs</h1>
    <DishNeedsList :dishNeeds="dishNeeds"/>
</div>
</template>



<script>
import PotluckService from '../services/PotluckService';
import DishNeedsList from './DishNeedsList.vue';

export default {
    props: {
        dish: {
            type: Object,
            required: true
        }
    },
    components: {
        DishNeedsList
    },
    data(){
        return {
            newDish: {
                dish_name: "",
                dish_dietary_restrictions: "",
                dish_category: "",
                dish_servings: 0,
                dish_recipe: "",
                username: this.$store.state.user.username
            },
            dishes: []
        };
    },
    created(){
        PotluckService.dishNeedsList(this.$route.params.potluckId)
        .then(response => {
            this.dishNeeds = response.data
        })
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