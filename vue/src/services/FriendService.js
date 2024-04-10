import axios from "axios";

export default {

    friendsList() {
        return axios.get('/friends');
    },
    addFriend(friend) {
        return axios.post('/friends', friend)
    }
}