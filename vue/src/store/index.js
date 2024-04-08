import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      nextDishId: 0,
      nextPotluckId: 0, 
      dishes: [
        {}
      ],
      potlucks: [
        {}
      ]
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      ADD_DISH(state, dish){
        const product = state.dishes.find(p => p.id == dish.id)
        dish.id = state.nextDishId++;
        product.dishes.unshift(dish);
      },
      ADD_POTLUCK(state, potluck){
        const product = state.potlucks.find(p => p.id == potluck.id)
        potluck.id = state.nextPotluckId++;
        product.potluck.unshift(potluck);
      }
    },
  });
  return store;
}
