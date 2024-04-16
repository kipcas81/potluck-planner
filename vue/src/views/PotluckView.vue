<template>
    <div>
      <PotluckDetail v-bind:potluck="Potluck"/>
    </div>
    <div class="guest-head">
    <h2>Guest List</h2>
    <GuestList :guests="guests"/>
    </div>
    <div class="dish-head">
    <h2>Dish Needs</h2>
    <DishNeedsList :dishNeeds="dishNeeds"/>
    </div>
</template>
<script>
import PotluckDetail from '../components/PotluckDetail.vue';
import PotluckService from '../services/PotluckService';
import GuestList from '../components/GuestList.vue';
import DishNeedsList from '../components/DishNeedsList.vue';

export default {
    components: {
        PotluckDetail,
        GuestList,
        DishNeedsList
    },
    data(){
      return {
        Potluck: {
                eventName: "",
                description: "", 
                eventDate: "",
                eventTime: "",
                diet: "",
                recurring: false,
                frequencyDays: 0,
                location: "",
                private: false
            },
            guests: [],
            guest: {
              guest_first_name: "",
              guest_last_name: "",
              guest_email_address: ""
            },
            allDishNeeds: [],
            dishNeeds: {
                dish_category: "",
                dish_serving_count_needed: 0,
                dish_serving_count_filled: 0
            }
      };
    },
    created(){
      PotluckService.getPotluck(this.$route.params.potluckId)
      .then(response => {
        this.Potluck = response.data;
      });
    }
}
</script>
<style>
.container {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-top: 10px;
  align-items: center;
  font-family: cursive;
  border-radius: 15px;
  margin-left: 40px;
  width: 40%;
  height: 80vh;
  background-color: rgb(252, 191, 188);
  border: 4px solid rgb(255, 127, 80);
  padding: 5px;
  position: relative;
}
.actions {
  display: grid;
  align-items: center;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  column-gap: 50px;
  row-gap: 20px;
  grid-template-areas: 
  "btn-edit btn-delete"
  "inv-guest update-dish-needs";
}
.btn-edit {
  margin-right: 10px;
  width: 15vw;
  background-color: whitesmoke;
  border-radius: 10px;
}
.btn-delete {
  margin-right: 10px;
  
  width: 15vw;
  border-radius: 10px;
  background-color: whitesmoke;
}
.inv-guest {
  margin-right: 10px;
  width: 15vw;
  border-radius: 10px;
  background-color: whitesmoke;
}
.update-dish-needs {
  margin-right: 10px;
  width: 15vw;
  border-radius: 10px;
  background-color: whitesmoke;
}
.guest-head {
  margin-top: 15px;
  font-family: cursive;
  display: flex;
  flex-direction: column; 
  border-radius: 15px;
  align-items: center; 
  margin-left: 40px;
  width: 40%;
  height: 30vh;
  background-color: rgb(252, 191, 188);
  border: 4px solid rgb(255, 127, 80);
  padding: 5px;
  position: relative;
}




</style>