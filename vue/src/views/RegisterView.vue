<template>
  <div id="register" class="text-center">
    <div class="banner">
    <h1 class="title">Two Prawn Potluck Planner</h1>
    <h2 class="welcome-message">make your silly little account</h2>
    <img class="logo-left" src="../assets/2-Prawn-logo.png"/>
     <img class="logo-right" src="../assets/2-Prawn-logo.png"/>
    </div>
    <form class="reg-box" v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      
      <div class="form-input-group">
        <label for="firstName">First Name</label>
        <input type="text" id="firstName" v-model="user.firstName" required />
      </div>
      
      <div class="form-input-group">
        <label for="lastName">Last Name</label>
        <input type="text" id="lastName" v-model="user.lastName" required />
      </div>

      <div class="form-input-group">
        <label for="email">Email</label>
        <input type="text" id="email" v-model="user.email" required />
      </div>

      <div class="form-input-group">
        <label for="user_diet">Diet Restrictions</label>
        <input type="text" id="user_diet" v-model="user.dietaryRestrictions"  />
      </div>
      
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
        firstName: '',
        lastName: '',
        email: '',
        diet: '',
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
  margin-bottom: 2rem;
}
label {
  margin-right: 0.5rem;
}
#register{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh; 
  text-align: center;
}
.reg-box {
  font-family: cursive;
  border-radius: 15px;
  width: 300px;
  background-color: rgb(252,191,188);
  border: 4px solid rgb(255,127,80);
  margin-top: 15%;
  margin-bottom: 20px;
  padding: 5px;
  width: 25%;
  height: 75%;
  align-content: center;
}
.banner {
  font-family: cursive;
  width: 95vw;
  background-color: rgb(252,191,188);
  border: 4px solid rgb(255,127,80);
  padding: 10px 20px;
  height: 125px;
  border-radius: 15px;
  margin-top: 10px;
  margin-bottom: 50px;
  position: absolute; 
  top: 40px; 
  left: 50%; 
  transform: translateX(-50%); 
} 

.logo-left,
.logo-right {
  height: 90px;
  position: relative; 
  top: -120px; 
}

.logo-left {
  margin-right: auto;
  padding-left: 70%;
  transform: rotateY(180deg)
}

.logo-right {
  margin-left: auto;
}

</style>