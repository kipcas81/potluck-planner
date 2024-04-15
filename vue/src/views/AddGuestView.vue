<template>
    <div>
        <h1>Add New Guest</h1>
        <GuestForm v-bind:guest="guest" />
    </div>
</template>

<script>
import GuestForm from '../components/GuestForm.vue';

export default {
    components: {
        GuestForm
    },
    data () {
        return {
            guest: {
                guest_first_name: "",
                guest_last_name: "",
                guest_email_address: ""
            },
        }
    }
};
</script>

<style>
</style>