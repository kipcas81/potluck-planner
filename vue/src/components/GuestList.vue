<template>
  <div>
    <div class="guestsListBox" v-for="guest in guests" :key="guest.guest_email_address">
      <div>
        <p v-if="showCheckboxForGuest(guest)">
          <input 
            type="checkbox" 
            v-on:click="putGuestsInArray(guest)"
          /> 
          {{ guest.guest_first_name }} {{ guest.guest_last_name }} | {{ guest.guest_email_address }} 
        </p>
        <p v-else>
          {{ guest.guest_first_name }} {{ guest.guest_last_name }} | {{ guest.guest_email_address }} 
        </p>
        <button class="btn-delete" v-on:click="(deleteGuest())" v-if="isCurrentUserGuest(guest)">Leave Potluck</button>
      </div>
    </div>
    <button class="btn-delete" v-on:click="(deleteGuest())" v-if="showRemoveGuestsButton">Remove Selected Guests</button>
  </div>
</template>

<script>
import FriendService from '../services/FriendService';
export default {

  data() {
    return {
      guests: [],
      guestsToRemove: [],
      selfRemoval: [],
    };
  },
  computed: {
    currentUserInGuestList() {
      return this.guests.some(guest => guest.guest_email_address === this.$store.state.user.email);
    },
    showRemoveGuestsButton() {
      return !this.guests.some(guest => this.isCurrentUserGuest(guest));
    }
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
        confirm('Are you sure you want to delete these guests? This cannot be undone.')
      ) {
        FriendService.deleteGuest(this.guestsToRemove).then(()=>{
          this.getGuests();
        });
      }
    },
    isCurrentUserGuest(guest){
      return this.$store.state.user.email === guest.guest_email_address;
    },
    showCheckboxForGuest(guest) {
      return !this.currentUserInGuestList || this.isCurrentUserGuest(guest);
    },
  //   removeSelf(){
  //     if (
  //       confirm('Are you sure you want to remove yourself from this potluck? This cannot be undone.')
  //     ) {
  //         const email = this.$store.state.user.email;
  //         const email2 = 
  //   }
  // },
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
  } else {
    this.guestsToRemove.push(removableGuest);
  }
    },
    // isHost(){
    //     console.log(this.$store.state.selectedPotluck);
    //     console.log(this.$store.state.selectedPotluck.userId);
    //         return this.$store.state.user.id === this.$store.state.selectedPotluck; 
    //     }
  },
  created(){
    this.getGuests(this.$route.params.potluckId);
  }
}
</script>

<style>


</style>