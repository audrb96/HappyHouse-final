/*!

=========================================================
* BootstrapVue Argon Dashboard - v1.0.0
=========================================================

* Product Page: https://www.creative-tim.com/product/bootstrap-vue-argon-dashboard
* Copyright 2020 Creative Tim (https://www.creative-tim.com)

* Coded by www.creative-tim.com

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import Vue from "vue";
import DashboardPlugin from "./plugins/dashboard-plugin";
import App from "./App.vue";
import store from "./store";
// router setup
import router from "./routes/router";
import * as VueGoogleMaps from "vue2-google-maps";
import { API_KEY } from "./views/Maps/API_KEY.js";
import { IconsPlugin } from "bootstrap-vue";

// plugin setup
Vue.use(IconsPlugin);
Vue.use(DashboardPlugin);
Vue.use(VueGoogleMaps, {
  load: {
    key: API_KEY,
    libraries: "places",
  },
});

/* eslint-disable no-new */
new Vue({
  el: "#app",
  render: (h) => h(App),
  router,
  store,
});
