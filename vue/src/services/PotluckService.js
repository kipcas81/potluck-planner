import axios from "axios";

export default {

  potluckList() {
    return axios.get('/potlucks');
  },
  addPotluck(potluck) {
    return axios.post('/potlucks', potluck);
  },
  updatePotluck(potluck) {
    return axios.put(`/potlucks/${potluck.id}`, potluck);
  },
  deletePotluck(potluckId) {
    return axios.delete(`/potlucks/${potluckId}`);
  },
<<<<<<< HEAD
  
=======
  getPotluck(potluckId){
    return axios.get(`/potlucks/${potluckId}`);
  }
>>>>>>> f5b17cf3ff1a52c4e7ebebeb4907ed3282a0e758
}