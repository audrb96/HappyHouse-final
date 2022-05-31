<template>
  <card>
    <b-row align-v="center" slot="header">
      <b-col cols="8">
        <h3 class="mb-0">프로필 변경</h3>
      </b-col>
      <b-col cols="4" class="text-right">
        <a href="#!" class="btn btn-sm btn-primary" @click="updateProfile"
          >Settings</a
        >
      </b-col>
    </b-row>

    <b-form @submit.prevent="updateProfile">
      <h6 class="heading-small text-muted mb-4">사용자 정보</h6>

      <div class="pl-lg-4">
        <b-row>
          <b-col lg="6">
            <base-input
              type="text"
              label="성명"
              placeholder="Username"
              v-model="user.username"
            >
            </base-input>
          </b-col>
          <b-col lg="6">
            <base-input
              type="email"
              label="Email"
              placeholder="mike@email.com"
              v-model="user.email"
            >
            </base-input>
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="6">
            <base-input
              type="text"
              label="나이"
              placeholder="Age"
              v-model="user.age"
            >
            </base-input>
          </b-col>
        </b-row>
      </div>
      <hr class="my-4" />

      <!-- Address -->
      <h6 class="heading-small text-muted mb-4">Contact information</h6>

      <div class="pl-lg-4">
        <b-row>
          <b-col md="12">
            <base-input
              type="text"
              label="주소"
              placeholder="Home Address"
              v-model="user.address"
            >
            </base-input>
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="4">
            <base-input
              type="text"
              label="시/도"
              placeholder="City"
              v-model="user.city"
            >
            </base-input>
          </b-col>
          <b-col lg="4">
            <base-input
              type="text"
              label="나라"
              placeholder="Country"
              v-model="user.country"
            >
            </base-input>
          </b-col>
          <b-col lg="4">
            <base-input
              label="우편번호"
              placeholder="ZIP Code"
              v-model="user.postalCode"
            >
            </base-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col md="5">
            <base-input
              type="text"
              label="전화번호"
              placeholder="phone-number"
              v-model="user.phone"
            >
            </base-input>
          </b-col>
        </b-row>
      </div>

      <hr class="my-4" />
      <!-- Description -->
      <h6 class="heading-small text-muted mb-4">About me</h6>
      <div class="pl-lg-4">
        <b-form-group
          label="About Me"
          label-class="form-control-label"
          class="mb-0"
          label-for="about-form-textaria"
        >
          <!--  <label class="form-control-label">About Me</label> -->
          <b-form-textarea
            rows="4"
            v-model="user.aboutMe"
            id="about-form-textaria"
            placeholder="A few words about you ..."
          ></b-form-textarea>
        </b-form-group>
        <div>
          <base-button type="danger" class="my-4" @click="deleteId"
            >회원 탈퇴</base-button
          >
        </div>
      </div>
    </b-form>
  </card>
</template>
<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  data() {
    return {
      user: {
        phone: "",
        username: "",
        email: "",
        address: "",
        city: "",
        country: "",
        postalCode: "",
        aboutMe: ``,
        age: "",
      },
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    this.user = this.userInfo;
  },
  methods: {
    ...mapActions(userStore, ["EditUserProfile", "getUserInfo", "DeleteId"]),
    async updateProfile() {
      await this.EditUserProfile(this.user);
    },
    deleteId() {
      const isDelete = confirm("정말 탈퇴하시겠습니까?");
      if (isDelete) {
        this.DeleteId(this.userInfo.id);
        this.$router.push("/dashboard");
      }
    },
  },
};
</script>
<style></style>
