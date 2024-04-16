<template>
    <div>
        <h1>Add More Dish Needs</h1>
        <p>Use this form to add your dish needs to the potluck! You can indicate to your guests, what kinds of items they should bring, and how many servings.</p>
        <DishNeedsForm v-bind:dishNeeds="dishNeeds"/>
    </div>

</template>

<script>
import DishNeedsForm from '../components/DishNeedsForm.vue';
export default {
    components: {
    DishNeedsForm
},
data () {
    return {
        dishNeeds: {
            potluck_id: this.$route.params.potluckId,
            dish_category: "",
            dish_serving_count: 0,
            dish_serving_count_filled: 0
        }
    }
}
}
</script>

<style>

</style>