import axios from "axios";

export default {

    friendsList() {
        return axios.get('/friends');
    },
    addFriend(friend) {
        return axios.post('/friends', friend);
    },
    addGuest(guest, potluckId) {
        return axios.post(`/guests`, guest);
    },
    guestList(potluckId) {
        return axios.get(`/guests?potluckId=${potluckId}`);
    }
}