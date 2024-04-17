<template>
<div>
<div class="dishNeedsBox" v-for="dishNeeds in dishNeedsList" v-bind:key="dishNeeds.dish_category_id">
    <div>
        <p>{{ dishNeeds.dish_category }} | {{ dishNeeds.dish_serving_count_filled }} of {{ dishNeeds.dish_serving_count_needed }} servings filled</p>
    </div>
</div>
    <!-- <button class="btn-delete" v-on:click="(deleteDishNeeds())">Remove All Dish Needs</button> -->
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
        deleteDishNeeds(){
            if (
        confirm('Are you sure you want to delete all of the dish needs? This cannot be undone.')
      ) {
            PotluckService.deleteDishNeeds(this.$route.params.potluckId).then(()=>{
                this.getAllDishNeeds();
            });
            
        }
    }
    
},
    created(){
        this.getAllDishNeeds(this.$route.params.potluckId);
    }
}
</script>


<style>

</style>