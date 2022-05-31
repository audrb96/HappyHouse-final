import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import userStore from "@/store/modules/userStore.js";
import aptStore from "@/store/modules/aptStore.js";

Vue.use(Vuex);
const store = new Vuex.Store({
  modules: {
    userStore,
    aptStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

export default store;
