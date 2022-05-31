<template>
  <div class="wrapper">
    <notifications></notifications>
    <side-bar>
      <template slot="links">
        <sidebar-item
          :link="{
            name: 'Main',
            path: '/dashboard',
            icon: 'ni ni-tv-2 text-primary',
          }"
        >
        </sidebar-item>
        <sidebar-item
          v-if="isLogin"
          :link="{
            name: '관심 매물',
            path: '/interest',
            icon: 'ni ni-favourite-28 text-pink',
          }"
        >
        </sidebar-item>

        <sidebar-item
          v-if="isLogin"
          :link="{
            name: 'Maps',
            path: '/maps',
            icon: 'ni ni-pin-3 text-orange',
          }"
        >
        </sidebar-item>

        <sidebar-item
          v-if="isLogin"
          :link="{
            name: 'User Profile',
            path: '/profile',
            icon: 'ni ni-single-02 text-yellow',
          }"
        >
        </sidebar-item>

        <sidebar-item
          :link="{
            name: '공지사항',
            path: '/notice',
            icon: 'ni ni-bullet-list-67 text-black',
          }"
        >
        </sidebar-item>

        <sidebar-item
          v-if="isLogin"
          :link="{
            name: 'Q&A',
            path: '/QA',
            icon: 'ni ni-chat-round text-blue',
          }"
        >
        </sidebar-item>

        <sidebar-item
          :link="{
            name: 'Login',
            path: '/login',
            icon: 'ni ni-key-25 text-info',
          }"
          v-if="!isLogin"
        >
        </sidebar-item>
        <sidebar-item
          :link="{
            name: 'Register',
            path: '/register',
            icon: 'ni ni-circle-08 text-pink',
          }"
          v-if="!isLogin"
        >
        </sidebar-item>
      </template>
    </side-bar>

    <div class="main-content">
      <div>
        <dashboard-navbar :type="$route.meta.navbarType"></dashboard-navbar>
      </div>

      <div @click="$sidebar.displaySidebar(false)">
        <fade-transition :duration="200" origin="center top" mode="out-in">
          <!-- your content here -->
          <router-view></router-view>
        </fade-transition>
      </div>
      <content-footer v-if="!$route.meta.hideFooter"></content-footer>
    </div>
  </div>
</template>
<script>
/* eslint-disable no-new */
import PerfectScrollbar from "perfect-scrollbar";
import "perfect-scrollbar/css/perfect-scrollbar.css";
function hasElement(className) {
  return document.getElementsByClassName(className).length > 0;
}

function initScrollbar(className) {
  if (hasElement(className)) {
    new PerfectScrollbar(`.${className}`);
  } else {
    // try to init it later in case this component is loaded async
    setTimeout(() => {
      initScrollbar(className);
    }, 100);
  }
}

import DashboardNavbar from "./DashboardNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import { FadeTransition } from "vue2-transitions";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  components: {
    DashboardNavbar,
    ContentFooter,
    FadeTransition,
  },
  methods: {
    initScrollbar() {
      let isWindows = navigator.platform.startsWith("Win");
      if (isWindows) {
        initScrollbar("sidenav");
      }
    },
  },
  computed: {
    ...mapState(userStore, ["userInfo", "isLogin", "isLoginError"]),
  },
  mounted() {
    this.initScrollbar();
  },
};
</script>
<style lang="scss"></style>
