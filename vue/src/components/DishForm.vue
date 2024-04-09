<template>
<form>
    <!-- need v model above on category, name, servings, etc-->
    <div class="form-element">
        <label for="name">Name:</label>
        <input id="name" type="text"/>
    </div>
    <div class="form-element">
        <label for="category">Category:</label>
        <input id="category" type="text"/>
    </div>
    <div class="form-element">
        <label for="servings">Servings:</label>
        <input id="servings" type="text"/>
    </div>
    <div class="form-element">
        <label for="diet">Dietary Restrictions:</label>
        <input id="diet" type="text"/>
    </div>
    <div class="form-element">
        <label for="recipe">Recipe:</label>
        <input id="recipe" type="text"/>
    </div>
    <input type="submit" value="Save" />
    <input type="cancel" value="Cancel" v-on:click="resetForm" />
</form>
</template>



<script>
export default {
    props: ['dishId'],
    data(){
        return {
            newDish: {}
        };
    },
    methods: {
        addNewDish(){
            this.newDish.dishId = this.dishId
            this.$store.commit('ADD_DISH', this.newDish)
        },
        resetForm(){
            this.newDish = {};
        }
    }
}
</script>



<style>
</style>