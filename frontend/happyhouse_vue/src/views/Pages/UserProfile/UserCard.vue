<template>
  <b-card no-body class="card-profile" alt="Image placeholder" img-top>
    <b-row class="justify-content-center">
      <b-col lg="3" class="order-lg-2">
        <div class="card-profile-image">
          <a href="#">
            <b-img src="img/theme/profile2.png" rounded="circle" />
          </a>
        </div>
      </b-col>
    </b-row>

    <b-card-header class="text-center border-0 pt-8 pt-md-5 pb-0 pb-md-4">
    </b-card-header>

    <b-card-body class="pt-0">
      <b-row>
        <b-col>
          <div class="card-profile-stats d-flex justify-content-center mt-md-5">
            <div>
              <span class="heading"></span>
              <span class="description"></span>
            </div>
            <div>
              <span class="heading"></span>
              <span class="description"></span>
            </div>
            <div>
              <span class="heading"></span>
              <span class="description"></span>
            </div>
          </div>
        </b-col>
      </b-row>
      <div class="text-center">
        <h5 class="h3">
          {{ userInfo.username
          }}<span class="font-weight-light">, {{ userInfo.age }}</span>
        </h5>
        <div class="h5 font-weight-300">
          <i class="ni location_pin mr-2"></i>{{ userInfo.country }},
          {{ userInfo.city }}
        </div>
        <hr class="my-4" />
        <p>
          {{ userInfo.aboutMe }}
        </p>
      </div>
    </b-card-body>
  </b-card>
</template>
<script>
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>
<style></style>
