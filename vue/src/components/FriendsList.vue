<template>
<div>
    <div class="friendsListBox" v-for="friend in friends" v-bind:key="friend.user_id">
      <ul>
        <li>{{ friend.friend_first_name }} {{ friend.friend_last_name }} -- {{ friend.friend_email_address }}</li>
      </ul>
    </div>
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
        this.friends = response.data;
      })
    }
  },
  created(){
    this.getFriends();
  }
}
</script>

