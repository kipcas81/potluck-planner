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
 
<!--------------------------------------------------------------------------->    
    <div class="form-input-group">Food Restrictions:

<<<<<<< HEAD
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

    <label for="No-Nuts">Nuts</label>
    <input type="checkbox" id="dietary_nuts" v-model="newPotluck.diet" />

    <label for="Sugar-Free">Sugar-Free</label>
    <input type="checkbox" id="dietary_sugar" v-model="newPotluck.diet" />
=======
        <label v-for="(restriction, index) in newPotluck.diet" :key="index" :for="restriction.id">
        {{ restriction.label }}
        <input type="checkbox" :id="restriction.id" v-model="newPotluck.diet[index].checked" />
      </label>
>>>>>>> a0ebb31ed30ab202cfeec786357b052823b6b4bc
</div>
<!---------------------------------------------------------------------------->
<div class="form-element"> Food Categories: 

    <label v-for="(category, index) in newPotluck.categories" :key="index" :for="category.id">
        {{ category.label }}
        <input type="checkbox" :id="category.id" v-model="newPotluck.categories[index].checked" />
      </label>

</div>
<!---------------------------------------------------------------------------->
    <div class="form-element">
        <label for="recurring">Recurring:</label>
        <input id="recurring" type="checkbox" v-model="newPotluck.isRecurring"/>
    </div>
<!---------------------------------------------------------------------------->    
    <div class="form-element" v-show="newPotluck.isRecurring">
        <label for="frequency">Every __ days:</label>
        <input id="frequency" type="number" v-model="newPotluck.frequency"/>
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
                name: "",
                description: "", 
                date: "",
                time: "",
                creator: "",
                diet: [
                { id: 'vegan', label: 'Vegan', checked: false },
                { id: 'vegetarian', label: 'Vegetarian', checked: false},
                { id: 'gluten', label: 'Gluten-Free', checked: false },
                { id: 'sodium', label: 'Low-Sodium', checked: false },
                { id: 'paleo', label: 'Paleo', checked: false },
                { id: 'halal', label: 'Halal', checked: false },
                { id: 'kosher', label: 'Kosher', checked: false },
                { id: 'dairy', label: 'Dairy-Free', checked: false },
                { id: 'nuts', label: 'No-Nuts', checked: false },
                { id: 'sugar', label: 'Sugar-Free', checked: false}
                ],
                categories: [
                { id: 'appetizers', label: 'Appetizers', checked: false },
                { id: 'entrees', label: 'Entrees', checked: false },
                { id: 'sides', label: 'Sides', checked: false },
                { id: 'desserts', label: 'Desserts', checked: false },
                { id: 'drinks', label: 'Drinks', checked: false }   
                ],
                isRecurring: false,
                frequency: 0,
                location: "",
                isPrivate: true
            },
        };
    },
    methods: {
        addNewPotluck(){
            if(this.newPotluck.frequency <= 0){
                this.frequencyError = "Cannot have negative frequency";
                return;
            }
            this.frequencyError = '';
            this.newPotluck.potluckId = this.potluckId;
            this.$store.commit('ADD_POTLUCK', this.newPotluck);
            this.resetForm();
        },
        
        resetForm(){
            this.newPotluck = {};
        }
    }
}
</script>


