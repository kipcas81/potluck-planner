<template>
<form @submit.prevent="addNewFriend">
    <div>
        <label for="firstname">First Name</label>
        <input id="firstname" type="text" v-model="newFriend.friend_first_name">  
    </div>
    <div>
        <label for="lastname">Last Name</label>
        <input id="lastname" type="text" v-model="newFriend.friend_last_name">  
    </div>
    <div>
        <label for="email">email address</label>
        <input id="email" type="email" v-model="newFriend.friend_email_address">   
    </div>
    <input type="submit" value="Save" />
    <input type="button" value="Cancel" @click="resetForm"/>
</form>
</template>

<script>
import FriendService from '../services/FriendService';
export default {
    props: {
        friends: {
          type: Object,
        required: true  
        }
    },
    data(){
        return {
            newFriend: {
                friend_first_name: "",
                friend_last_name: "",
                friend_email_address: "",
            }
        }
    },
    methods: {
        addNewFriend(){
            this.newFriend.id = this.id;
            this.$store.commit('ADD_FRIEND', this.newFriend);
            this.resetForm();
        },
        resetForm(){
            this.newFriend = {};
        }
    }
}
</script>

