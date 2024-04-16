<template>
<div>
<div class="dishNeedsBox" v-for="dishNeeds in dishNeedsList" v-bind:key="dishNeeds.dish_category_id">
    <div>
        <p>{{ dishNeeds.dish_category }}</p>
        <p> {{ dishNeeds.dish_serving_count_filled }} of {{ dishNeeds.dish_serving_count_needed }} servings filled</p>
</div>
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