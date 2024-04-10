<template>
<div>
    <h1>Friends List</h1>
    <ul>
      <li v-for="friend in friends" :key="friend.email">
        {{ friend.firstName }} {{ friend.lastName }} - {{ friend.email }}
      </li>
    </ul>
</div>>
</template>

<script>
import FriendService from '../services/FriendService';

export default {
    data() {
        return {
            friends: []
        };
    },
    methods: {
        getFriendsList() {
            FriendService.friendsList()
            .then(response => {
                this.friends = response.data;
            })
        }
    }
}
</script>



<style>

</style>