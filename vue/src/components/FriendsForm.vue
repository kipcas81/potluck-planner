<template>
<form @submit.prevent="addNewFriend">
    <div>
        <label for="email">email address</label>
        <input id="email" type="email" v-model="newFriend.email">   
    </div>
    <div>
        <label for="firstname">First Name</label>
        <input id="firstname" type="text" v-model="newFriend.firstname">  
    </div>
    <div>
        <label for="lastname">Last Name</label>
        <input id="lastname" type="text" v-model="newFriend.lastname">  
    </div>
    <input type="submit" value="Save" />
    <input type="button" value="Cancel" @click="resetForm"/>
</form>
</template>

<script>
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
                email: "",
                firstname: "",
                lastname: ""
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

