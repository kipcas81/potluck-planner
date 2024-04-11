<template>
<div class="create">
    <h1>Add New Friend</h1>
    <FriendsForm v-bind:friend="friend" />
</div>
</template>

<script>
import FriendsForm from '../components/FriendsForm.vue';

export default {
    components: {
        FriendsForm
    },
    data (){
        return {
            friend: {
                friend_first_name: "",
                friend_last_name: "",
                friend_email_address: "",
            }
        }
    }
}
</script>