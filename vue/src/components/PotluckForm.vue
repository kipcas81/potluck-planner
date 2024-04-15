<template>
<form @submit.prevent="addNewPotluck" class="potluckForm">
    <div class="form-element">
        <label for="name">Name:</label>
        <input class="inputText" type="text" v-model="newPotluck.eventName"/>
    </div>
    <div class="form-element">
        <label for="description">Description:</label>
        <input class="inputText" id="description" type="text" v-model="newPotluck.description" placeholder="Please enter a description of your potluck."/>
    </div>
    <div class="form-element">
        <label for="location">Location</label>
        <input class="inputText"  type="text" v-model="newPotluck.location"/>
    </div>
    <div class="form-element">
        <label for="date">Date:</label>
        <input id="date" type="date" v-model="newPotluck.eventDate"/>
    </div>
    <div class="form-element">
        <label for="time">Time:</label>
        <input id="time" type="time" v-model="newPotluck.eventTime"/>
    </div>
    <div class="form-element">
        <label for="diet">Dietary Restrictions</label>
        <input class="inputText" id="diet" type="text" v-model="newPotluck.diet" placeholder="Please indicate the dietary restrictions of this event to the guests."/>
        <span v-if="!newPotluck.diet" >There are no Dietary Restrictions for this event</span>
    </div>
 
<div class="form-element">
        <label for="private">Is this a private event? (Private events can only be viewed by invited guests):</label>
        <input id="private" type="checkbox" v-model="newPotluck.private"/>
    </div>

    <div class="form-element">
        <label for="recurring">Recurring:</label>
        <input id="recurring" type="checkbox" v-model="newPotluck.recurring"/>
    </div>
  
    <div class="form-element" v-show="newPotluck.recurring">
        <label for="frequency">How often should this potluck occur? (This field cannot be less than 1):</label>
        <input id="frequency" type="number" v-model="newPotluck.frequencyDays"/>
    </div>

    <input class="submitbtn" type="submit" value="Save" />
    <input class="cancelbtn" type="button" value="Clear" @click="resetForm"/>

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
                diet: "",
                recurring: false,
                frequencyDays: 0,
                location: "",
                private: false,
            },
        };
    },
    methods: {
        addNewPotluck(){
            if (this.newPotluck.potluckId === undefined){
              if(this.newPotluck.frequency <= 0){
                return;
            }
            PotluckService.addPotluck(this.newPotluck);
            this.resetForm();
            this.$router.push('/');  
            } else {
                PotluckService.updatePotluck(this.newPotluck);
                this.$router.push('/'); 
            }


            
        },
        
        resetForm() {
        this.newPotluck.eventName = "";
        this.newPotluck.description = "";
        this.newPotluck.eventDate = "";
        this.newPotluck.eventTime = "";
        this.newPotluck.diet = "",
        this.newPotluck.recurring = false;
        this.newPotluck.frequencyDays = 0;
        this.newPotluck.location = "";
        this.newPotluck.private = false;
        }
    },
    created() {
        if(this.$route.params.potluckId != undefined){
            let potluckId = parseInt(this.$route.params.potluckId);
        if (potluckId != 0){
            PotluckService.getPotluck(potluckId)
            .then(response => {
                this.newPotluck = response.data;
            });
        }
    }
}
}
</script>


<style scoped>
.form-element {
display: block;
margin-bottom: 1%;
}

label {
    display: block;
    font-size: larger;
}

.inputText {
    
    width: 95%;
    box-sizing: border-box;
    vertical-align: top;
    padding-top: 0;
}

#description {
    height: 80px;
}

#diet{
    height: 80px;
}

.potluckForm {
    border: 4px solid red;
    padding: 5px;
    width: 80%;
    background-color: coral;
}


</style>
