<template>
  <div id="register" class="text-center">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      
      <div class="form-input-group">
        <label for="first-name">First Name</label>
        <input type="text" id="first_name" v-model="user.first_name" required />
      </div>
      
      <div class="form-input-group">
        <label for="last-name">Last Name</label>
        <input type="text" id="last_name" v-model="user.last_name" required />
      </div>

      <div class="form-input-group">
        <label for="email">Email</label>
        <input type="text" id="email" v-model="user.email" required />
      </div>
      <!--make dietary restrictions input here?-->
      
      <div class="form-input-group">Food Restrictions:

        <label for="Vegan">Vegan</label>
        <input type="checkbox" id="dietary-vegan" v-model="user.diet" />

        <label for="Vegetarian">Vegetarian</label>
        <input type="checkbox" id="dietary_vegetarian" v-model=user.diet />

        <label for="Gluten-Free">Gluten-Free</label>
        <input type="checkbox" id="dietary_gluten" v-model="user.diet" />

        <label for="Low-Sodium">Low-Sodium</label>
        <input type="checkbox" id="dietary_sodium" v-model="user.diet" />

        <label for="Paleo">Paleo</label>
        <input type="checkbox" id="dietary_paleo" v-model="user.diet" />

        <!-- <label for="Halal">Halal</label>
        <input type="checkbox" id="dietary_halal" v-model="user.diet" /> -->

        <!-- <label for="Kosher">Kosher</label>
        <input type="checkbox" id="dietary_kosher" v-model="user.diet" /> -->

        <label for="Dairy-Free">Dairy-Free</label>
        <input type="checkbox" id="dietary_dairy" v-model="user.diet" />

        <label for="Nuts">Nuts</label>
        <input type="checkbox" id="dietary_nuts" v-model="user.diet" />

        <label for="Sugar-Free">Sugar-Free</label>
        <input type="checkbox" id="dietary_sugar" v-model="user.diet" />
      </div> 
      <!-------------------------------------------->
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button type="submit">Create Account</button>
      <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        first_name: '',
        last_name: '',
        email: '',
        diet: [],
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
</style>
