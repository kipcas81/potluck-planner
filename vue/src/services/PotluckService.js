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
  addDishNeeds(potluckId, potluckDishNeeds){
    return axios.post(`/dishNeeds?potluckId=${potluckId}`, potluckDishNeeds);
  },
  dishNeedsList(potluckId){
    return axios.get(`/dishNeeds?potluckId=${potluckId}`);
  },
  deleteDishNeeds(potluckId){
    return axios.delete(`dishNeeds?potluckId=${potluckId}`)
  }
}