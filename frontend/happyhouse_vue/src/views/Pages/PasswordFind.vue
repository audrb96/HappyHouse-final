<template>
  <div class="login-font">
    <!-- Header -->
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
      <b-container>
        <div class="header-body text-center mb-7">
          <b-row class="justify-content-center">
            <b-col xl="5" lg="6" md="8" class="px-5">
              <h1 class="text-white">비밀번호를 잃어버리셨나요?</h1>
              <p class="text-lead text-white">
                정보를 입력하고 비밀번호를 찾으세요!
              </p>
            </b-col>
          </b-row>
        </div>
      </b-container>
      <div class="separator separator-bottom separator-skew zindex-100">
        <svg
          x="0"
          y="0"
          viewBox="0 0 2560 100"
          preserveAspectRatio="none"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
        >
          <polygon
            class="fill-default"
            points="2560 0 2560 100 0 100"
          ></polygon>
        </svg>
      </div>
    </div>
    <!-- Page content -->
    <b-container class="mt--8 pb-5">
      <b-row class="justify-content-center">
        <b-col lg="5" md="7">
          <b-card no-body class="bg-secondary border-0 mb-0">
            <b-card-body class="px-lg-5 py-lg-5">
              <div class="text-center text-muted mb-4">
                <small>Find your password</small>
              </div>
              <validation-observer
                v-slot="{ handleSubmit }"
                ref="formValidator"
              >
                <b-form role="form" @submit.prevent="handleSubmit(onSubmit)">
                  <base-input
                    alternative
                    class="mb-3"
                    name="ID"
                    :rules="{ required: true, min: 6 }"
                    prepend-icon="ni ni-circle-08"
                    placeholder="ID"
                    v-model="model.id"
                  >
                  </base-input>

                  <base-input
                    alternative
                    class="mb-3"
                    name="email"
                    :rules="{ required: true, email: true }"
                    prepend-icon="ni ni-email-83"
                    type="email"
                    placeholder="email"
                    v-model="model.email"
                  >
                  </base-input>

                  <base-input
                    alternative
                    class="mb-3"
                    name="phonenumber"
                    :rules="{ required: true, min: 11 }"
                    prepend-icon="ni ni-lock-circle-open"
                    type="phone"
                    placeholder="phone"
                    v-model="model.phone"
                  >
                  </base-input>

                  <b-form-checkbox v-model="model.rememberMe"
                    >Remember me</b-form-checkbox
                  >
                  <div class="text-center">
                    <base-button
                      type="primary"
                      native-type="submit"
                      class="my-4"
                      >Find</base-button
                    >
                  </div>
                </b-form>
              </validation-observer>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import { mapState, mapActions, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  name: "passwordFind",
  data() {
    return {
      model: {
        email: "",
        id: "",
        phone: "",
      },
    };
  },
  computed: {
    ...mapState(userStore, ["findedPassword"]),
  },
  methods: {
    ...mapActions(userStore, ["FindUserPassword"]),
    ...mapMutations(userStore, ["CLEAR_FIND_PASSWORD"]),
    async onSubmit() {
      await this.FindUserPassword(this.model);
      if (this.findedPassword === null) {
        alert("일치하는 정보의 계정이 없습니다.");
      } else {
        alert(`비밀번호는 ${this.findedPassword}입니다.`);
      }

      this.CLEAR_FIND_PASSWORD();
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");
.px-5 {
  font-family: "Jua", sans-serif;
}
</style>
