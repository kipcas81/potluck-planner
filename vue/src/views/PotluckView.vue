<template>
    <div>
      <PotluckDetail v-bind:potluck="Potluck"/>
    </div>
    <GuestList :guests="guests"/>
</template>
<script>
import PotluckDetail from '../components/PotluckDetail.vue';
import PotluckService from '../services/PotluckService';
import GuestList from '../components/GuestList.vue';

export default {
    components: {
        PotluckDetail,
        GuestList
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
  font-family: cursive;
  display: flex;
  flex-direction: column; 
  border-radius: 15px;
  align-items: center; 
  margin-left: 40px;
  width: 45vw;
  height: 60vh;
  background-color: rgb(252, 191, 188);
  border: 4px solid rgb(255, 127, 80);
  padding: 5px;
  position: relative;
}
#actions {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  position: absolute;
  bottom: 10px;
  
  width: 85%;
  height: 5%

}
.btn-edit {
  margin-right: 10px;
  width: 150px;
  
  border-radius: 10px;
}
.btn-delete {
  margin-right: 10px;
  margin-left: 5px;
  width: 150px;
  border-radius: 10px;
}
.inv-guest {
  margin-right: 10px;
  margin-left: 5px;
  width: 150px;
  border-radius: 10px;
}
.update-dish-needs {
  margin-right: 10px;
  margin-left: 5px;
  width: 150px;
  border-radius: 10px;
}



</style>