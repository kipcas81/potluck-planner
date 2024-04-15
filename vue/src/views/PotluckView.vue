<template>
    <div>
      <PotluckDetail v-bind:potluck="Potluck"/>
    </div>
</template>
<script>
import PotluckDetail from '../components/PotluckDetail.vue';
import PotluckService from '../services/PotluckService';

export default {
    components: {
        PotluckDetail
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
  width: 30%;
}


</style>