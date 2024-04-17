<template>
<form v-on:submit="addNewFriend">
    <div>
        <label for="firstname">First Name</label>
        <input id="firstname" type="text" v-model="newFriend.friend_first_name">  
    </div>
    <div>
        <label for="lastname">Last Name</label>
        <input id="lastname" type="text" v-model="newFriend.friend_last_name">  
    </div>
    <div>
        <label for="email">Email address</label>
        <input id="email" type="email" v-model="newFriend.friend_email_address">   
    </div>
    <input class="submitbtn" type="submit" value="Save" />
    <input class="cancelbtn" type="button" value="Clear" @click="cancelForm"/>
</form>
<p>Done adding friends? <router-link :to="{path: '/'}">Click Me!</router-link></p>
</template>

<script>
import FriendService from '../services/FriendService';
export default {
    props: {
        friend: {
          type: Object,
        required: true  
        }
    },
    data(){
        return {
            newFriend: {
                friend_first_name: "",
                friend_last_name: "",
                friend_email_address: ""
        },
            friendsArray: [],
        };
    },
    methods: {
        addNewFriend(){
            this.friendsArray.push(this.newFriend)
            FriendService.addFriend(this.friendsArray);
            this.resetForm();
        },
        cancelForm(){
            this.$emit('cancel');
            this.resetForm();
        },
        resetForm(){
            this.newFriend = {};
        }
    }
}
</script>

