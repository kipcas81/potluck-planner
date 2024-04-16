<template>
<div>
<div class="dishNeedsBox" v-for="dishNeeds in dishNeedsList" v-bind:key="dishNeeds.dish_category_id">
    <ul>
        <li>{{ dishNeeds.dish_category }}</li>
    </ul>
</div>
</div>
</template>


<script>
import PotluckService from '../services/PotluckService';
export default {
    data () {
        return {
            dishNeedsList: [],
        };
    },
    methods: {
        getAllDishNeeds(){
            PotluckService.dishNeedsList(this.$route.params.potluckId)
            .then(response => {
                this.dishNeedsList = response.data;
            });
        },
    },
    created(){
        this.getAllDishNeeds(this.$route.params.potluckId);
    }
}
</script>


<style>

</style>