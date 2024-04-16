<template>
    <div>
      <PotluckDetail v-bind:potluck="Potluck"/>
    </div>
    <h2>Guest List</h2>
    <GuestList :guests="guests"/>
    <h2>Dish Needs</h2>
    <DishNeedsList :dishNeeds="dishNeeds"/>
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
</style>