import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:9000"
});

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
  }
}