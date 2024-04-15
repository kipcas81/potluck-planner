import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import AddPotluckView from '../views/AddPotluckView.vue';
import FriendsView from '../views/FriendsView.vue';
import AddFriendsView from '../views/AddFriendsView.vue';
import PotluckView from '../views/PotluckView.vue';
import EditPotluckView from '../views/EditPotluckView.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/add-potluck",
    name: "add-potluck",
    component: AddPotluckView,
    meta: {
      requiresAuth: true
    }
  },
  {
  path: "/friends",
  name: "friends",
  component: FriendsView,
  meta: {
    requiresAuth: true
  }
  },
  {
    path: "/add-friend",
    name: "add-friend",
    component: AddFriendsView,
    meta: {
      requiresAuth: true
    }
    },
    {
      path: "/potluck/:potluckId",
      name: 'PotluckView',
      component: PotluckView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/potluck/:potluckId/edit",
      name: 'EditPotluckView',
      component: EditPotluckView,
      meta: {
        requiresAuth: true
      }
    }
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
