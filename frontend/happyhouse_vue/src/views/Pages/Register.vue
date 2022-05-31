<template>
  <div>
    <!-- Header -->
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
      <b-container class="container">
        <div class="header-body text-center mb-7">
          <b-row class="justify-content-center">
            <b-col xl="5" lg="6" md="8" class="px-5">
              <h1 class="text-white" style="font-size: 30px">
                새로운 계정을 등록해보세요!
              </h1>
              <p class="text-lead text-white" style="font-size: 19px">
                회원가입을 통해 자신만의 정보를 만들어보아요.
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
      <!-- Table -->
      <b-row class="justify-content-center">
        <b-col lg="6" md="8">
          <b-card no-body class="bg-secondary border-0">
            <b-card-body class="px-lg-5 py-lg-5">
              <div class="text-center text-muted mb-4">
                <small>Or sign up with credentials</small>
              </div>
              <validation-observer
                v-slot="{ handleSubmit }"
                ref="formValidator"
              >
                <b-form role="form" @submit.prevent="handleSubmit(onSubmit)">
                  <base-input
                    alternative
                    class="mb-3"
                    prepend-icon="ni ni-hat-3"
                    placeholder="Name"
                    name="Name"
                    :rules="{ required: true }"
                    v-model="model.username"
                  >
                  </base-input>
                  <base-input
                    alternative
                    class="mb-3"
                    prepend-icon="ni ni-mobile-button"
                    placeholder="phone-number"
                    name="phoneNumber"
                    :rules="{ required: true }"
                    v-model="model.phone"
                  >
                  </base-input>
                  <base-input
                    alternative
                    class="mb-3"
                    prepend-icon="ni ni-email-83"
                    placeholder="email"
                    name="email"
                    :rules="{ required: true, email: true }"
                    v-model="model.email"
                  >
                  </base-input>
                  <base-input
                    alternative
                    class="mb-3"
                    prepend-icon="ni ni-circle-08"
                    placeholder="ID"
                    name="ID"
                    :rules="{ required: true, min: 6 }"
                    v-model="model.id"
                  >
                  </base-input>

                  <base-input
                    alternative
                    class="mb-3"
                    prepend-icon="ni ni-lock-circle-open"
                    placeholder="password"
                    type="password"
                    name="Password"
                    :rules="{ required: true, min: 6 }"
                    v-model="model.password"
                  >
                  </base-input>
                  <div class="text-muted font-italic">
                    <small
                      >password strength:
                      <span class="text-success font-weight-700"
                        >strong</span
                      ></small
                    >
                  </div>
                  <b-row class="my-4">
                    <b-col cols="12">
                      <base-input
                        :rules="{ required: { allowFalse: false } }"
                        name="Privacy"
                        Policy
                      >
                        <b-form-checkbox v-model="model.agree">
                          <span class="text-muted"
                            >I agree with the
                            <a href="#!">Privacy Policy</a></span
                          >
                        </b-form-checkbox>
                      </base-input>
                    </b-col>
                  </b-row>
                  <div class="text-center">
                    <b-button type="submit" variant="primary" class="mt-4"
                      >Create account</b-button
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
import { mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "register",
  data() {
    return {
      model: {
        username: "",
        id: "",
        password: "",
        email: "",
        phone: "",
        agree: false,
      },
    };
  },
  methods: {
    ...mapActions(userStore, ["userJoin"]),
    onSubmit() {
      const isSuccess = this.userJoin(this.model);
      if (isSuccess) {
        this.$router.push({ name: "login" });
      }
    },
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");
.px-5 {
  font-family: "Sunflower", sans-serif;
}
</style>
