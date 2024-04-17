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

<style>
/* .friendsListBox {
  margin-top: 25%;
  margin-left: 15%;
  font-family: url(src\assets\fonts\hug_me_tight\Hug Me Tight - TTF.ttf);
   
  border-radius: 15px;
  align-items: center;
  background-color: rgb(252, 191, 188);
  border: 4px solid rgb(255, 127, 80);
  padding-bottom: 5px;
  position: relative;
} */

</style>
