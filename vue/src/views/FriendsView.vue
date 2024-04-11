<template>
<div>
    <h1>Friends List</h1>
    <ul>
      <li v-for="friend in friends" :key="friend.email">
        {{ friend.firstName }} {{ friend.lastName }} - {{ friend.email }}
      </li>
    </ul>
    <button class="add-friend" v-on:click="toggleFriendsForm">Add a Friend</button>
    <FriendsForm v-bind:friend="friend" v-if="showForm"/>
</div>
</template>

<script>
import FriendService from '../services/FriendService';
import FriendsForm from '../components/FriendsForm.vue';

export default {
    components: {
        FriendsForm
    },
    data() {
        return {
            friends: [],
            friend: {
                friend_first_name: "",
                friend_last_name: "",
                friend_email_address: "",
            },
            showForm: false
        };
    },
    methods: {
        getFriendsList() {
            FriendService.friendsList()
            .then(response => {
                this.friends = response.data;
            })
        },
        toggleFriendsForm(){
            this.showForm = !this.showForm;
        }
    }
}
</script>



<style>

</style>