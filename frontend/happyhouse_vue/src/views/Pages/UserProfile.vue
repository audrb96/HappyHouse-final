<template>
  <div>
    <div
      class="header pb-8 pt-5 pt-lg-8 d-flex align-items-center profile-header"
      style="
        min-height: 600px;
        background-size: cover;
        background-position: center top;
      "
    >
      <b-container fluid>
        <!-- Mask -->
        <span class="mask bg-gradient-success opacity-8"></span>
        <!-- Header container -->
        <b-container fluid class="d-flex align-items-center">
          <b-row>
            <b-col lg="12" md="10" class="changeFont">
              <h1 class="display-1 text-white">
                안녕하세요! {{ userInfo.username }}
              </h1>
              <p class="text-white mt-0 mb-0">
                프로필 변경 페이지입니다. <br />
                변경 후에 Setting 클릭! 잊지마세요.
              </p>
            </b-col>
          </b-row>
        </b-container>
      </b-container>
    </div>

    <b-container fluid class="mt--6">
      <b-row>
        <b-col xl="4" class="order-xl-2 mb-5">
          <user-card></user-card>
        </b-col>
        <b-col xl="8" class="order-xl-1">
          <edit-profile-form></edit-profile-form>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import EditProfileForm from "./UserProfile/EditProfileForm.vue";
import UserCard from "./UserProfile/UserCard.vue";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  components: {
    EditProfileForm,
    UserCard,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");

.changeFont {
  font-family: "Jua", sans-serif;
}
</style>
