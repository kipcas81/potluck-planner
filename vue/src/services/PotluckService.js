import axios from "axios";


export default {

potluckList() {
    return axios.get('/potlucks');
  },
  addPotluck(potluck) {
    return axios.post('/potlucks', potluck);
  }
}