<template>
<form @submit.prevent="addNewPotluck">
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
        <label for="date">Date:</label>
        <input id="date" type="date"/>
    </div>
    <div class="form-element">
        <label for="time">Time:</label>
        <input id="time" type="time"/>
    </div>
    <div class="form-element">
        <label for="diet">Dietary Restrictions:</label>
        <input id="diet" type="text"/>
    </div>
<div class="form-input-group">Food Restrictions:

    <label for="Vegan">Vegan</label>
    <input type="checkbox" id="dietary-vegan" v-model="user.diet" />

    <label for="Vegetarian">Vegetarian</label>
    <input type="checkbox" id="dietary_vegetarian" v-model=user.diet />

    <label for="Gluten-Free">Gluten-Free</label>
    <input type="checkbox" id="dietary_gluten" v-model="user.diet" />

    <label for="Low-Sodium">Low-Sodium</label>
    <input type="checkbox" id="dietary_sodium" v-model="user.diet" />

    <label for="Paleo">Paleo</label>
    <input type="checkbox" id="dietary_paleo" v-model="user.diet" />

    <label for="Halal">Halal</label>
    <input type="checkbox" id="dietary_halal" v-model="user.diet" />

    <label for="Kosher">Kosher</label>
    <input type="checkbox" id="dietary_kosher" v-model="user.diet" />

    <label for="Dairy-Free">Dairy-Free</label>
    <input type="checkbox" id="dietary_dairy" v-model="user.diet" />

    <label for="Nuts">Nuts</label>
    <input type="checkbox" id="dietary_nuts" v-model="user.diet" />

    <label for="Sugar-Free">Sugar-Free</label>
    <input type="checkbox" id="dietary_sugar" v-model="user.diet" />
</div>
<div class="form-element"> Food Categories: 
    <label for="Appetizers">Appetizers</label>
    <input type="checkbox" id="Appetizers" v-model="newPotluck.categories" />
    <label for="Entrees">Entrees</label>
    <input type="checkbox" id="Entrees" v-model="newPotluck.categories" />
    <label for="Sides">Sides</label>
    <input type="checkbox" id="Sides" v-model="newPotluck.categories" />
    <label for="Desserts">Desserts</label>
    <input type="checkbox" id="Desserts" v-model="newPotluck.categories" />
    <label for="Drinks">Drinks</label>
    <input type="checkbox" id="Drinks" v-model="newPotluck.categories" />
</div>
    <div class="form-element">
        <label for="recurring">Recurring:</label>
        <input id="recurring" type="checkbox"/>
    </div>
    <div class="form-element">
        <label for="frequency">Every __ days:</label>
        <input v-model.number="frequency" type="number"/>
    </div>
    <input type="submit" value="Save" />
    <input type="cancel" value="Cancel"  />
</form>
</template>


<script>
export default {
    data () {
        return {
            newPotluck: {
                id: "",
                name: "",
                description: "", 
                date: "",
                time: "",
                creator: "",
                diet: [],
                categories: [],
                isRecurring: "",
                frequency: "",
                location: "",
                isPrivate: ""
            }
        };
    },
    methods: {
        addNewPotluck(){
            this.newPotluck.potluckId = this.potluckId
            this.$store.commit('ADD_POTLUCK', this.newPotluck);
            this.resetForm();
        },
        resetForm(){
            this.newPotluck = {};
        }
    }
}
</script>


<style>
</style>