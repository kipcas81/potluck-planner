<template>
<div>
    <div class="guestsListBox" v-for="guest in guests" v-bind:key="guest.guest_email_address">
      <ul>
        <li>{{ guest.guest_first_name }} {{ guest.guest_last_name }} -- {{ guest.guest_email_address }}</li>
        <input type="checkbox" v-on:click="putGuestsInArray(guest)"/>
        <button class="btn-delete" v-on:click="(deleteGuest())">Remove Guest</button>
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
      guestsToRemove: [],
    };
  },
  methods: {
    getGuests() {
      FriendService.guestList(this.$route.params.potluckId)
      .then(response => {
        this.guests = response.data;
      })
    },
    deleteGuest(){
      if (
        confirm('Are you sure you want to delete this guest? This cannot be undone.')
      ) {
        FriendService.deleteGuest(this.guestsToRemove).then(()=>{
          this.getGuests();
        })
      }
    },
    putGuestsInArray(guest){
      const guestCopy = Object.assign({}, guest);
      const removableGuest = {
        guest_email_address: guestCopy.guest_email_address,
        potluck_id: guestCopy.potluck_id,
        guest_id: guestCopy.guest_id
      };
      const index = this.guestsToRemove.findIndex(item =>
    item.guest_email_address === removableGuest.guest_email_address &&
    item.potluck_id === removableGuest.potluck_id &&
    item.guest_id === removableGuest.guest_id
    );
    if (index !== -1) {
    this.guestsToRemove.splice(index, 1);
    console.log("take out");
  } else {
    this.guestsToRemove.push(removableGuest);
    console.log("PUT IN");
  }
    }
  },
  created(){
    this.getGuests(this.$route.params.potluckId);
  }
}
</script>

<style>


</style>