<template>
    <form v-on:submit="addNewGuest">
        <div>
            <label for="firstname">First Name</label>
            <input id="firstname" type="text" v-model="newGuest.guest_first_name">  
        </div>
        <div>
            <label for="lastname">Last Name</label>
            <input id="lastname" type="text" v-model="newGuest.guest_last_name">  
        </div>
        <div>
            <label for="email">Email Address</label>
            <input id="email" type="email" v-model="newGuest.guest_email_address">   
        </div>
        <input class="submitbtn" type="submit" value="Save" />
        <input class="cancelbtn" type="button" value="Cancel" @click="cancelForm"/>
    </form>
    <div>
        <p>Done adding guests? <router-link :to="{name: 'PotluckView', params: {potluckId: $route.params.potluckId}}">Click Me!</router-link></p>
    </div>
    <div>
        <h1>Friends List</h1>
        <FriendsList :friends="friends"/>
    </div>
    </template>
    
    <script>
    import FriendService from '../services/FriendService';
    import FriendsList from './FriendsList.vue';
    export default {
        components: {
            FriendsList
        },
        props: {
            guest: {
              type: Object,
            required: true  
            }
        },
        data(){
            return {
                newGuest: {
                    guest_first_name: "",
                    guest_last_name: "",
                    guest_email_address: "",
                    potluck_id: this.$route.params.potluckId
            },
                guestsArray: [],
                friends: []
            };
        },
        methods: {
            addNewGuest(){
                this.guestsArray.push(this.newGuest)
                FriendService.addGuest(this.guestsArray);
                this.resetForm();
            },
            cancelForm(){
                this.$emit('cancel');
                this.resetForm();
            },
            resetForm(){
                this.newGuest = {};
            }
        }
    }
    </script>
    
    