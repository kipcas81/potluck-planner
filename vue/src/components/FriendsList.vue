<template>
<div>
    <!-- <router-link v-for="friend in friends" v-bind:key="friend.id">{{ friend.name }}</router-link> -->
</div>
</template>

<script>
import FriendService from '../services/FriendService';
export default {

    data() {
    return {
      friends: [],
    };
  },
  methods: {
    getFriends() {
      FriendService.friendsList()
      .then(response => {
        this.potlucks = response.data;
      })
    }
  },
  created(){
    this.getFriends();
  }
}
</script>

