<template>
<form @submit.prevent="addNewPotluck">
    <div class="form-element">
        <label for="name">Name:</label>
        <input id="name" type="text" v-model="newPotluck.eventName"/>
    </div>
    <div class="form-element">
        <label for="description">Description:</label>
        <input id="description" type="text" v-model="newPotluck.description"/>
    </div>
    <div class="form-element">
        <label for="location">Location</label>
        <input id="location" type="text" v-model="newPotluck.location"/>
    </div>
    <div class="form-element">
        <label for="date">Date:</label>
        <input id="date" type="date" v-model="newPotluck.eventDate"/>
    </div>
    <div class="form-element">
        <label for="time">Time:</label>
        <input id="time" type="time" v-model="newPotluck.eventTime"/>
    </div>
 
<!--------------------------------------------------------------------------->    
    <!-- <div class="form-input-group">Food Restrictions:

        <label v-for="(restriction, index) in newPotluck.diet" :key="index" :for="restriction.id">
        {{ restriction.label }}
        <input type="checkbox" :id="restriction.id" v-model="newPotluck.diet[index].checked" />
      </label>
</div> -->
<!---------------------------------------------------------------------------->
<!-- <div class="form-element"> Food Categories: 

    <label v-for="(category, index) in newPotluck.categories" :key="index" :for="category.id">
        {{ category.label }}
        <input type="checkbox" :id="category.id" v-model="newPotluck.categories[index].checked" />
      </label>

</div> -->
<!---------------------------------------------------------------------------->
<div class="form-element">
        <label for="private">Private:</label>
        <input id="private" type="checkbox" v-model="newPotluck.private"/>
    </div>

    <div class="form-element">
        <label for="recurring">Recurring:</label>
        <input id="recurring" type="checkbox" v-model="newPotluck.recurring"/>
    </div>
  
    <div class="form-element" v-show="newPotluck.recurring">
        <label for="frequency">Every __ days:</label>
        <input id="frequency" type="number" v-model="newPotluck.frequencyDays"/>
    </div>

    <input type="submit" value="Save" />
    <input type="button" value="Clear" @click="resetForm"/>

</form>
</template>


<script>
import PotluckService from '../services/PotluckService';
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
                eventName: "",
                description: "", 
                eventDate: "",
                eventTime: "",
                // diet: [
                // { id: 'vegan', label: 'Vegan', checked: false },
                // { id: 'vegetarian', label: 'Vegetarian', checked: false},
                // { id: 'gluten', label: 'Gluten-Free', checked: false },
                // { id: 'sodium', label: 'Low-Sodium', checked: false },
                // { id: 'paleo', label: 'Paleo', checked: false },
                // { id: 'halal', label: 'Halal', checked: false },
                // { id: 'kosher', label: 'Kosher', checked: false },
                // { id: 'dairy', label: 'Dairy-Free', checked: false },
                // { id: 'nuts', label: 'No-Nuts', checked: false },
                // { id: 'sugar', label: 'Sugar-Free', checked: false}
                // ],
                recurring: false,
                frequencyDays: 0,
                location: "",
                private: false
            },
        };
    },
    methods: {
        addNewPotluck(){
            if(this.newPotluck.frequency <= 0){
                return;
            }
            PotluckService.addPotluck(this.newPotluck);
            this.resetForm();
        },
        
        resetForm() {
        this.newPotluck.eventName = "";
        this.newPotluck.description = "";
        this.newPotluck.eventDate = "";
        this.newPotluck.eventTime = "";
        // this.newPotluck.diet.forEach(restriction => restriction.checked = false);
        this.newPotluck.recurring = false;
        this.newPotluck.frequencyDays = 0;
        this.newPotluck.location = "";
        this.newPotluck.private = false;
        }
    }
}
</script>


