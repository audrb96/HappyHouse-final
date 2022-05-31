import {
  getSido,
  getGugun,
  getDong,
  getAptList,
  getBaseAddress,
  deleteInterestApt,
  aptListByInterest,
  insertInterest,
  top5ByInterest,
  avgByDong,
} from "@/api/apt.js";
import { getStoreByDistance } from "../../api/store.js";

const aptStore = {
  namespaced: true,
  state: {
    sidoList: [],
    gugunList: [],
    dongList: [],
    aptList: [],
    storeList: [],
    localStoreList: [],
    largeKindList: [],
    interestApt: [],
    top5Interest: [],
    avgByDong: [],
    baseAddress: null,
  },
  getters: {
    getSidoGetter: function (state) {
      return state.sidoList;
    },
    getGugunGetter: function (state) {
      return state.gugunList;
    },
    getDongGetter: function (state) {
      return state.dongList;
    },
    getAptListGetter: function (state) {
      return state.aptList;
    },
    getBaseAddress: function (state) {
      return state.baseAddress;
    },
    getStoreGetter: function (state) {
      return state.storeList;
    },
    dongSelect: function (state) {
      const list = [];
      list.push({ value: "", text: "동" });
      state.dongList.map(({ dongCode, dong }) => {
        list.push({ value: dongCode, text: dong });
      });
      return list;
    },
    gugunSelect: function (state) {
      const list = [];
      list.push({ value: "", text: "구/군" });
      state.gugunList.map(({ gugunCode, gugunName }) => {
        list.push({ value: gugunCode, text: gugunName });
      });
      return list;
    },
    sidoSelect: function (state) {
      const list = [];
      list.push({ value: "", text: "시/도" });
      state.sidoList.map(({ sidoCode, sidoName }) => {
        list.push({ value: sidoCode, text: sidoName });
      });
      return list;
    },
    getLargeKind: function (state) {
      const set = new Set();
      state.storeList.map((store) => {
        set.add({
          value: store.storeLargeKind.trim(),
          text: store.storeLargeKind.trim(),
        });
      });
      set.add({ value: "", text: "업종 대분류" });
      const list = [...set];
      const answer = list.filter((store, idx, arr) => {
        return (
          arr.findIndex(
            (item) => item.value === store.value && item.text === store.text
          ) === idx
        );
      });

      return answer;
    },
    getInterestAptCode: function (state) {
      const list = [];
      state.interestApt.map((apt) => {
        list.push(apt.aptCode);
      });
      return list;
    },
    getAvgChartData: function (state) {
      let data = [];
      let labels = [];
      state.avgByDong.map((avgByDong) => {
        labels.push(avgByDong.dong);
      });
      state.avgByDong.map((avgByDong) => {
        data.push(avgByDong.avg);
      });

      let chartData = {
        datasets: [
          {
            label: "평당 거래 금액 (단위 : 만원/평)",
            data: data,
          },
        ],
        labels: labels,
      };
      return chartData;
    },
  },
  mutations: {
    SET_SIDO: (state, sido) => {
      state.sidoList = sido;
    },
    SET_GUGUN: (state, gugun) => {
      state.gugunList = gugun;
    },
    SET_DONG: (state, dong) => {
      state.dongList = dong;
    },
    SET_APT_LIST: (state, aptList) => {
      state.aptList = aptList;
    },
    SET_BASEADDRESS: (state, baseAddress) => {
      state.baseAddress = baseAddress;
    },
    SET_STORE_LIST: (state, storeList) => {
      state.storeList = storeList;
    },
    SET_LARGE_KIND_LIST: (state, largeKindList) => {
      state.largeKindList = largeKindList;
    },
    SET_INTEREST_APT: (state, interestApt) => {
      state.interestApt = interestApt;
    },
    SET_TOP5_INTEREST: (state, top5) => {
      state.top5Interest = top5;
    },
    SET_AVG_BY_DONG: (state, avgByDong) => {
      state.avgByDong = avgByDong;
    },
    PUSH_INTEREST_APT: (state, apt) => {
      state.interestApt.push(apt);
    },
    POP_INTEREST_APT: (state, aptCode) => {
      const list = state.interestApt.filter((apt) => {
        apt.aptCode != aptCode;
      });
      console.log(list);
      // state.interestApt = list;
    },
    CLEAR_SIDO: (state, payload) => {
      state.sidoList = [];
    },
    CLEAR_GUGUN: (state, payload) => {
      state.gugunList = [];
    },
    CLEAR_DONG: (state, payload) => {
      state.dongList = [];
    },
    CLEAR_APT_LIST: (state, payload) => {
      state.aptList = [];
    },
    CLEAR_BASEADDRESS: (state, payload) => {
      state.baseAddress = null;
    },
    CLEAR_STORE_LIST: (state, payload) => {
      state.storeList = [];
    },
    CLEAR_INTEREST_APT: (state, payload) => {
      state.interestApt = [];
    },
    CLEAR_AVG_BY_DONG: (state, payload) => {
      state.avgByDong = [];
    },
  },
  actions: {
    sidoCall({ commit }) {
      commit("CLEAR_SIDO");
      getSido(
        (response) => {
          commit("SET_SIDO", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    gugunCall({ commit }, sido) {
      commit("CLEAR_GUGUN");
      getGugun(
        sido,
        (response) => {
          commit("SET_GUGUN", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    dongCall({ commit }, gugun) {
      commit("CLEAR_DONG");
      getDong(
        gugun,
        (response) => {
          commit("SET_DONG", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    aptListCall({ commit }, dong) {
      getAptList(
        dong,
        (response) => {
          commit("SET_APT_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    baseAddressCall({ commit }, dong) {
      getBaseAddress(dong, (response) => {
        commit("CLEAR_BASEADDRESS");
        commit("SET_BASEADDRESS", response.data);
      }),
        (error) => {
          console.log(error);
        };
    },
    storeListCall({ commit }, apt) {
      getStoreByDistance(apt.lat, apt.lng, (response) => {
        commit("CLEAR_STORE_LIST");
        commit("SET_STORE_LIST", response.data);
      }),
        (error) => {
          console.log(error);
        };
    },
    deleteInterestAptCall({ commit, dispatch }, interestApt) {
      deleteInterestApt(
        interestApt.userid,
        interestApt.aptCode,
        (response) => {
          if (response.data.message === "success") {
            dispatch("aptListByInterestCall", interestApt.userid);
          } else console.log("fail");
        },
        (error) => {
          console.log(error);
        }
      );
    },
    aptListByInterestCall({ commit }, userid) {
      aptListByInterest(
        userid,
        (response) => {
          commit("CLEAR_INTEREST_APT");
          commit("SET_INTEREST_APT", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    insertInterestCall({ commit }, info) {
      insertInterest(
        info.interest,
        (response) => {
          if (response.data.message === "success") {
            commit("PUSH_INTEREST_APT", info.apt);
          } else {
            console.log("fail");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    top5ByInterestCall({ commit }) {
      top5ByInterest(
        (response) => {
          commit("SET_TOP5_INTEREST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    avgByDongCall({ commit }, gugun) {
      avgByDong(
        gugun,
        (response) => {
          commit("CLEAR_AVG_BY_DONG");
          commit("SET_AVG_BY_DONG", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default aptStore;
