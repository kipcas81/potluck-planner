<template>
  <div class="potluck-view">
    <div class="banner">
      <img class="logo-left" src="../assets/2-Prawn-logo.png"/>
      <h1>{{ Potluck.eventName }}</h1>
     <img class="logo-right" src="../assets/2-Prawn-logo.png"/>
      <!-- <h2>Hosted By: {{  }}</h2> -->
    </div>
    <div>
      <PotluckDetail v-bind:potluck="Potluck"/>
     </div>
     <div class="guest-head">
      <h2>Guest List</h2>
      <GuestList :guests="guests"/>
     </div>
      <div class="dish-needs">
     <h2>Dish Needs</h2>
      <DishNeedsList :dishNeeds="dishNeeds"/>

      </div>
      <div class="dishes-head">
     <h2>Dishes</h2>
     <button class="dishList" v-on:click="$router.push({name: 'AddDishView', params: {potluckId: potluckId}})">Add Dish</button>
      <DishList dishes="dishes"/>
      </div>
    </div>
</template>
<script>
import PotluckDetail from '../components/PotluckDetail.vue';
import PotluckService from '../services/PotluckService';
import GuestList from '../components/GuestList.vue';
import DishNeedsList from '../components/DishNeedsList.vue';
import DishList from '../components/DishList.vue';
import FriendService from '../services/FriendService';

export default {
    components: {
        PotluckDetail,
        GuestList,
        DishNeedsList,
        DishList
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
            },
            dishes: [],
            dish: {
              dish_name: "",
                dish_dietary_restrictions: "",
                dish_category: "",
                dish_servings: 0,
                dish_recipe: ""
            }
      };
    },
    created(){
      PotluckService.getPotluck(this.$route.params.potluckId)
      .then(response => {
        this.Potluck = response.data;
      });
      FriendService.guestList(this.$route.params.potluckId)
      .then(response => {
        this.guests = response.data;
      });
      PotluckService.dishNeedsList(this.$route.params.potluckId)
      .then(response => {
        this.allDishNeeds = response.data;
      });
      PotluckService.getAllDishes(this.$route.params.potluckId)
      .then(response => {
        this.dishes = response.data;
      });
    }
}
</script>
<style>
.container {
  margin-top: 25%;
  margin-left: 15%;
  font-family: cursive;
  display: flex;
  overflow-wrap: break-word;
  flex-direction: column;
  border-radius: 15px;
  align-items: center;
  background-color: rgb(252, 191, 188);
  border: 4px solid rgb(255, 127, 80);
  padding-bottom: 5px;
  position: relative;
}
.actions {
  display: grid;
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
.dish-needs {
  margin-bottom: 5%;
  margin-left: 15%;
  font-family: cursive;
  display: flex;
  flex-direction: column;
  border-radius: 15px;
  align-items: center;
  background-color: rgb(252, 191, 188);
  border: 4px solid rgb(255, 127, 80);
  /* margin-left: 10px; */
  padding-bottom: 5px;
  position: relative;
}
.guest-head {
  margin-top: 25%;
  margin-right: 15%;
  font-family: cursive;
  display: flex;
  flex-direction: column;
  border-radius: 15px;
  align-items: center;
  background-color: rgb(252, 191, 188);
  border: 4px solid rgb(255, 127, 80);
  /* margin-right: 10px; */
  padding-bottom: 5px;
  position: relative;
}
.potluck-view {
  display: grid;
  grid-template-columns: repeat(2, 1fr); 
  grid-template-rows: auto auto;
  column-gap: 30px;
  row-gap: 30px;
}
.dishes-head {
  margin-bottom: 5%;
  font-family: cursive;
  display: flex;
  flex-direction: column;
  border-radius: 15px;
  align-items: center;
  background-color: rgb(252, 191, 188);
  border: 4px solid rgb(255, 127, 80);
  margin-right: 15%;
  padding-bottom: 5px;
  position: relative;
}
.banner {
  display: flex;
  flex-direction: row;
  font-family: cursive;
  align-items: center;
  justify-content: space-evenly;
  width: 95vw;
  background-color: rgb(252,191,188);
  border: 4px solid rgb(255,127,80);
  padding: 10px 20px;
  border-radius: 15px;
  margin-top: 10px;
  height: 115px;
  position: absolute; 
  top: 40px;
  left: 50%; 
  transform: translateX(-50%);
  background-size: auto;
}
.logo-left,
.logo-right {
  height: 90px;
  position: relative; 
   
}

.logo-left {
  margin-right: auto;
  margin-left: 5%;
  transform: rotateY(180deg)
}

.logo-right {
  margin-left: auto;
  margin-right: 5%;
}


</style>