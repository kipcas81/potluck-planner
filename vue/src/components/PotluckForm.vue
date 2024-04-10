<template>
<form @submit.prevent="addNewPotluck">
    <div class="form-element">
        <label for="name">Name:</label>
        <input id="name" type="text" v-model="newPotluck.name"/>
    </div>
    <div class="form-element">
        <label for="description">Description:</label>
        <input id="description" type="text" v-model="newPotluck.description"/>
    </div>
    <div class="form-element">
        <label for="date">Date:</label>
        <input id="date" type="date" v-model="newPotluck.date"/>
    </div>
    <div class="form-element">
        <label for="time">Time:</label>
        <input id="time" type="time" v-model="newPotluck.time"/>
    </div>
    <!-- <div class="form-element">
        <label for="diet">Dietary Restrictions:</label>
        <input id="diet" type="text" v-model="newPotluck.diet"/>
    </div> -->
<div class="form-input-group">Food Restrictions:

    <label for="Vegan">Vegan</label>
    <input type="checkbox" id="dietary-vegan" v-model="newPotluck.diet" />

    <label for="Vegetarian">Vegetarian</label>
    <input type="checkbox" id="dietary_vegetarian" v-model=newPotluck.diet />

    <label for="Gluten-Free">Gluten-Free</label>
    <input type="checkbox" id="dietary_gluten" v-model="newPotluck.diet" />

    <label for="Low-Sodium">Low-Sodium</label>
    <input type="checkbox" id="dietary_sodium" v-model="newPotluck.diet" />

    <label for="Paleo">Paleo</label>
    <input type="checkbox" id="dietary_paleo" v-model="newPotluck.diet" />

    <label for="Halal">Halal</label>
    <input type="checkbox" id="dietary_halal" v-model="newPotluck.diet" />

    <label for="Kosher">Kosher</label>
    <input type="checkbox" id="dietary_kosher" v-model="newPotluck.diet" />

    <label for="Dairy-Free">Dairy-Free</label>
    <input type="checkbox" id="dietary_dairy" v-model="newPotluck.diet" />

    <label for="Nuts">Nuts</label>
    <input type="checkbox" id="dietary_nuts" v-model="newPotluck.diet" />

    <label for="Sugar-Free">Sugar-Free</label>
    <input type="checkbox" id="dietary_sugar" v-model="newPotluck.diet" />
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
        <input id="recurring" type="checkbox" v-model="newPotluck.isRecurring"/>
    </div>
    <div class="form-element" v-show="newPotluck.isRecurring">
        <label for="frequency">Every __ days:</label>
        <input id="frequency" type="number" v-model="newPotluck.frequency"/>
    </div>
    <input type="submit" value="Save" />
    <input type="button" value="Cancel" @click="resetForm"/>
</form>
</template>


<script>
// import PotluckService from '../services/PotluckService';
export default {
    props: {
        potluck: {
            type: Object,
            required: true
        }
    },
    data () {
        return {
            //create potluck
            newPotluck: {
                id: "",
                name: "",
                description: "", 
                date: "",
                time: "",
                creator: "",
                diet: [],
                categories: [],
                isRecurring: false,
                frequency: 0,
                location: "",
                isPrivate: true
            },
            frequencyError: ""
        };
    },
    methods: {
        addNewPotluck(){
            if(this.newPotluck.frequency <= 0){
                this.frequencyError = "Cannot have negative frequency";
                return;
            }
            this.frequencyError = '';
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