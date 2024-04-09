<template>
<form>
    <!-- need v model above on category, name, servings, etc-->
    <div class="form-element">
        <label for="name">Name:</label>
        <input id="name" type="text"/>
    </div>
    <div class="form-element">
        <label for="description">Description:</label>
        <input id="description" type="text"/>
    </div>
    <div class="form-element">
        <label for="diet">Dietary Restrictions:</label>
        <input id="diet" type="text"/>
    </div>
    <div class="form-element">
        <label for="date">Date:</label>
        <input id="date" type="date"/>
    </div>
    <div class="form-element">
        <label for="time">Date:</label>
        <input id="time" type="time"/>
    </div>
    <input type="submit" value="Save" />
    <input type="cancel" value="Cancel"  />
</form>
</template>


<script>
export default {
    data () {
        return {
            newPotluck: {}
        };
    },
    methods: {
        addNewPotluck(){
            this.newPotluck.potluckId = this.potluckId
            this.$store.commit('ADD_POTLUCK', this.newPotluck)
        },
        resetForm(){
            this.newPotluck = {};
        }
    }
}
</script>


<style>
</style>