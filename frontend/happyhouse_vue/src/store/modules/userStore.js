import jwt_decode from "jwt-decode";
import { login, findById, join, pwFind, deleteId } from "@/api/user.js";
import { ModifyId } from "../../api/user";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    findedPassword: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    getFindedPassword: function (state) {
      return state.findedPassword;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    CLEAR_USER_INFO: (state) => {
      state.userInfo = null;
    },
    CLEAR_FIND_PASSWORD: (state) => {
      state.findedPassword = null;
    },
    SET_FIND_PASSWORD: (state, password) => {
      state.findedPassword = password;
    },
  },
  actions: {
    userLogout({ commit }) {
      commit("SET_IS_LOGIN", false);
      commit("SET_IS_LOGIN_ERROR", false);
      commit("CLEAR_USER_INFO");
      commit("aptStore/CLEAR_INTEREST_APT", "", { root: true });
      commit("aptStore/CLEAR_SIDO", "", { root: true });
      commit("aptStore/CLEAR_GUGUN", "", { root: true });
      commit("aptStore/CLEAR_DONG", "", { root: true });
      commit("aptStore/CLEAR_APT_LIST", "", { root: true });
      commit("aptStore/CLEAR_BASEADDRESS", "", { root: true });
      commit("aptStore/CLEAR_STORE_LIST", "", { root: true });
      sessionStorage.removeItem("access-token");
    },

    userJoin({ commit }, user) {
      !user.id && alert("아이디를 입력해주세요.");
      if (!user.id) return;
      !user.username && alert("이름을 입력해주세요.");
      if (!user.username) return;
      !user.password && alert("패스워드를 입력해주세요.");
      if (!user.password) return;
      !user.email && alert("이메일을 입력해주세요.");
      if (!user.email) return;
      !user.phone && alert("전화번호를 입력해주세요.");
      if (!user.phone) return;
      join(
        user,
        (response) => {
          console.log(response);
          if (response.data.message === "success") {
            alert("회원가입이 완료되었습니다.");
            return true;
          } else {
            alert("회원가입에 실패했습니다.");
            return false;
          }
        },
        (error) => {
          alert("회원가입에 실패했습니다.");
          console.log(error);
          return false;
        }
      );
    },

    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getUserInfo({ commit, dispatch }, token) {
      let decode_token = jwt_decode(token);
      console.log(decode_token);
      findById(
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
            dispatch(
              "aptStore/aptListByInterestCall",
              response.data.userInfo.id,
              { root: true }
            );
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    EditUserProfile({ commit }, user) {
      ModifyId(
        user,
        (response) => {
          if (response.data.message === "success") {
            alert("수정 완료");
          } else {
            alert("수정 실패 :  모든 값을 채워주세요.");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async FindUserPassword({ commit }, user) {
      await pwFind(
        user,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_FIND_PASSWORD", response.data.password);
          } else {
            alert("일치하는 정보의 계정이 없습니다.");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    DeleteId({ commit }, userId) {
      deleteId(
        userId,
        (response) => {
          if (response.data.message === "success") {
            alert("회원 탈퇴되었습니다.");
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("CLEAR_USER_INFO");
            commit("aptStore/CLEAR_INTEREST_APT", "", { root: true });
            sessionStorage.removeItem("access-token");
          } else {
            alert("회원 탈퇴 실패");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default userStore;
