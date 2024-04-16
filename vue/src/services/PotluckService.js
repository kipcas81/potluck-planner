import axios from "axios";

export default {

  potluckList() {
    return axios.get('/potlucks');
  },
  addPotluck(potluck) {
    return axios.post('/potlucks', potluck);
  },
  updatePotluck(potluck) {
    return axios.put(`/potlucks/${potluck.potluckId}`, potluck);
  },
  deletePotluck(potluckId) {
    return axios.delete(`/potlucks/${potluckId}`);
  },
  getPotluck(potluckId){
    return axios.get(`/potlucks/${potluckId}`);
  },
  addDishNeeds(potluckDishNeeds, potluckId){
    return axios.post(`/dishNeeds/${potluckId}`, potluckDishNeeds)
  }
}