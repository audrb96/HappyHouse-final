<template>
  <base-nav
    container-classes="container-fluid"
    class="navbar-top navbar-expand"
    :class="{ 'navbar-dark': type === 'default' }"
  >
    <!-- <img
      v-b-toggle.sidebar-border
      src="https://img.icons8.com/external-others-colourcreatype/64/000000/external-interface-creatype-user-interface-filled-outline-others-colourcreatype-5.png"
      style="width: 30px"
    /> -->

    <a
      href="#"
      aria-current="page"
      class="h4 mb-0 text-white text-uppercase d-none d-lg-inline-block active router-link-active"
      style="font-family: Sunflower, sans-serif font-size:25px"
    >
      {{ $route.name }}
    </a>
    <!-- Navbar links -->
    <b-navbar-nav class="align-items-center ml-md-auto">
      <!-- This item dont have <b-nav-item> because item have data-action/data-target on tag <a>, wich we cant add -->
      <li class="nav-item d-sm-none">
        <a
          class="nav-link"
          href="#"
          data-action="search-show"
          data-target="#navbar-search-main"
        >
          <i class="ni ni-zoom-split-in"></i>
        </a>
      </li>
    </b-navbar-nav>
    <b-navbar-nav class="align-items-center ml-auto ml-md-0">
      <b-form
        class="navbar-search form-inline mr-sm-3"
        :class="{
          'navbar-search-dark': type === 'default',
          'navbar-search-light': type === 'light',
        }"
        id="navbar-search-main"
      >
      </b-form>
      <base-dropdown
        menu-on-right
        class="nav-item"
        tag="li"
        title-tag="a"
        title-classes="nav-link pr-0"
      >
        <a href="#" class="nav-link pr-0" @click.prevent slot="title-container">
          <b-media no-body class="align-items-center">
            <span class="avatar avatar-sm rounded-circle">
              <img src="img/theme/profile2.png" />
            </span>
            <b-media-body class="ml-2 d-none d-lg-block">
              <span class="mb-0 text-sm font-weight-bold" v-if="!isLogin"
                >Log in</span
              >
              <span class="mb-0 text-sm font-weight-bold" v-if="isLogin">{{
                userInfo.username
              }}</span>
            </b-media-body>
          </b-media>
        </a>

        <template>
          <b-dropdown-header class="noti-title">
            <h6 class="text-overflow m-0">Welcome!</h6>
          </b-dropdown-header>
          <b-dropdown-item href="#!" v-if="isLogin">
            <i class="ni ni-single-02"></i>
            <span>My profile</span>
          </b-dropdown-item>
          <b-dropdown-item href="#!">
            <i class="ni ni-settings-gear-65"></i>
            <span>Settings</span>
          </b-dropdown-item>
          <b-dropdown-item href="#!">
            <i class="ni ni-calendar-grid-58"></i>
            <span>Activity</span>
          </b-dropdown-item>
          <b-dropdown-item href="#!">
            <i class="ni ni-support-16"></i>
            <span>Support</span>
          </b-dropdown-item>
          <div class="dropdown-divider"></div>
          <b-dropdown-item @click.prevent="logout" v-if="isLogin">
            <i class="ni ni-user-run"></i>
            <span>Logout</span>
          </b-dropdown-item>
          <b-dropdown-item @click.prevent="login" v-if="!isLogin">
            <i class="ni ni-user-run"></i>
            <span>Log in</span>
          </b-dropdown-item>
        </template>
      </base-dropdown>
    </b-navbar-nav>
  </base-nav>
</template>
<script>
import { CollapseTransition } from "vue2-transitions";
import { BaseNav, Modal } from "@/components";
import { mapActions, mapState } from "vuex";
import NavbarToggleButton from "@/components/NavbarToggleButton";

const userStore = "userStore";

export default {
  components: {
    CollapseTransition,
    BaseNav,
    Modal,
    NavbarToggleButton,
  },
  props: {
    type: {
      type: String,
      default: "light", // default|light
      description:
        "Look of the dashboard navbar. Default (Green) or light (gray)",
    },
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    routeName() {
      const { name } = this.$route;
      return this.capitalizeFirstLetter(name);
    },
  },
  data() {
    return {
      activeNotifications: false,
      showMenu: false,
      searchModalVisible: false,
      searchQuery: "",
    };
  },
  methods: {
    ...mapActions(userStore, ["userLogout"]),

    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleNotificationDropDown() {
      this.activeNotifications = !this.activeNotifications;
    },
    closeDropDown() {
      this.activeNotifications = false;
    },
    logout() {
      this.userLogout();
      this.$router.push("/dashboard").catch(() => {});
    },
    login() {
      this.$router.push("/login");
    },
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Sunflower:wght@500&display=swap");
</style>
