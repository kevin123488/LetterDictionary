<template>
    <form @submit.prevent="login" class="logForm">
      <div class="row mb-3">
        <label for="username">Username</label>
        <input v-model.trim="loginCredentials.userId" type="text" class="inputButton " id="username" placeholder="ID를 입력해 주세요." required>
      </div>
      <div class="row ">
        <label for="password">Password</label>
        <input v-model.trim="loginCredentials.userPassword" type="password" class="inputButton " id="password" placeholder="비밀번호를 입력해 주세요." required>
      </div>
      <div class="row ">
        <button type="submit" class="">Login</button>    
      </div>
      <hr>
    </form>
</template>

<script>
  import { mapActions, mapGetters } from 'vuex'
  const accountStore = "accountStore";

  export default {
  name: "LoginView",
  data() { return { loginCredentials: { userId: null, userPassword: null}}},
  computed: {
    ...mapGetters(accountStore, ['isLogged'])
  },
  methods: {
    ...mapActions(accountStore, ['userLogin']),
    async login() {
      await this.userLogin(this.loginCredentials)
      // 로그인이 된 경우
      if (this.isLogged) {
        // 입력값 초기화
        this.loginCredentials = { userId: null, userPassword: null}
        // 메인페이지로 이동
        this.$router.push({ name: "MainView" });
      }
    }
  },
}
</script>
<style>
.logForm {
  /* align-self: center; */
  position: relative;
  width: 60vw;
  height: 50vh;
}
.loglabel {
  text-align: start;
}
.inputButton {
  width: 20vw;
  height: 10vh;
}
</style>