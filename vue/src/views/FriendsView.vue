<template>
<div>
    <h1>Friends List</h1>
    <button class="add-friend" v-on:click="toggleFriendsForm">Add a New Friend</button>
    <FriendsForm v-bind:friend="friend" v-if="showForm"/>
    <FriendsList :friends="friends"/>
</div>
</template>

<script>
import FriendService from '../services/FriendService';
import FriendsForm from '../components/FriendsForm.vue';
import FriendsList from '../components/FriendsList.vue';

export default {
    components: {
        FriendsForm,
        FriendsList
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