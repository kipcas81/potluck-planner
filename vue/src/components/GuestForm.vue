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
    </template>
    
    <script>
    import FriendService from '../services/FriendService';
    export default {
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
                    potluckId: this.$route.params.potluckId
            },
                guestsArray: [],
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
    
    