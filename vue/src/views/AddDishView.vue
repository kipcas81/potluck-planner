<template>
<div>
<h1>Add A Dish</h1>
<p>Dishes can only be added to potlucks that have dish needs of the same category</p>
<DishForm v-bind:dish="dish"/>
</div>
</template>


<script>
import DishForm from '../components/DishForm.vue';
export default {
    components: {
        DishForm
    },
    data(){
        return {
            dish: {
                dish_name: "",
                dish_dietary_restrictions: "",
                dish_category: "",
                dish_servings: 0,
                dish_recipe: "",
                username: this.$store.state.user.username
            }
        }
    }
}
</script>

<style>
</style>