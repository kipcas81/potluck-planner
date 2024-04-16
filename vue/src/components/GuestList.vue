<template>
<div>
    <div class="guestsListBox" v-for="guest in guests" v-bind:key="guest.user_id">
      <ul id="guestlist">
        <li>{{ guest.guest_first_name }} {{ guest.guest_last_name }} -- {{ guest.guest_email_address }}</li>
      </ul>
    </div>
</div>
</template>

<script>
import FriendService from '../services/FriendService';
export default {

    data() {
    return {
      guests: [],
    };
  },
  methods: {
    getGuests() {
      FriendService.guestList(this.$route.params.potluckId)
      .then(response => {
        this.guests = response.data;
      })
    }
  },
  created(){
    this.getGuests(this.$route.params.potluckId);
  }
}
</script>

<style>


</style>