<template>
<div class="dishListBox" v-for="dish in dishes" v-bind:key="dish.dish_id">
    <div>
        <h3>{{ dish.dish_name }}</h3>
        <p>Dietary Restrictions: {{ dish.dish_dietary_restrictions }}</p>
        <p>Category: {{ dish.dish_category }} | {{ dish.dish_servings }} servings</p>
        <p>Recipe: {{ dish.dish_recipe }}</p>
    </div>
</div>
</template>


<script>
import PotluckService from '../services/PotluckService';
export default {
    data(){
        return {
            dishes: [],
        };
    },
    methods: {
        getAllDishes(){
            PotluckService.getAllDishes(this.$route.params.potluckId)
            .then(response => {
                this.dishes = response.data;
            })
        }
    },
    created(){
        this.getAllDishes(this.$route.params.potluckId);
    }
}
</script>


<style>
</style>