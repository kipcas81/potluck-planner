<template>
<div>
    <router-link v-for="potluck in potlucks" v-bind:key="potluck.id">{{ potluck.name }}</router-link>
</div>
</template>

<script>
export default {
    props: {
        potlucks: {
            type: Array,
            required: true
        }
    },
//     created () {
//     this.potlucks = this.$store.state.potlucks;
// }
};


</script>


<style>
</style>