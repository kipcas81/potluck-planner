import axios from "axios";

export default {

    friendsList() {
        return axios.get('/friends');
    },
    addFriend(friend) {
        return axios.post('/friends', friend);
    },
    addGuest(guest) {
        return axios.post(`/guests`, guest);
    },
    guestList(potluckId) {
        return axios.get(`/guests?potluckId=${potluckId}`);
    },
    deleteGuest(guests){
        return axios.delete('/guests', { data: guests })
    },
}