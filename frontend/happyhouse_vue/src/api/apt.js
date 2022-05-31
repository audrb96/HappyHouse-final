import { apiInstance } from "./index.js";

const api = apiInstance();

function getSido(success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.get(`/apt/sido`).then(success).catch(fail);
}

function getGugun(sido, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.get(`/apt/gugun/${sido}`).then(success).catch(fail);
}

function getDong(gugun, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.get(`/apt/dong/${gugun}`).then(success).catch(fail);
}

function getAptList(dong, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.get(`/apt/aptlist/${dong}`).then(success).catch(fail);
}

function getBaseAddress(dong, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.get(`/apt/baseAddress/${dong}`).then(success).catch(fail);
}

function insertInterest(apt, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.post(`/apt/interest`, JSON.stringify(apt)).then(success).catch(fail);
}

function deleteInterestApt(userid, aptCode, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.delete(`/apt/interest/${userid}/${aptCode}`).then(success).catch(fail);
}

function aptListByInterest(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.get(`/apt/aptListByInterest/${userid}`).then(success).catch(fail);
}

function top5ByInterest(success, fail) {
  api.get(`apt/interest/top5Apt`).then(success).catch(fail);
}

function avgByDong(gugun, success, fail) {
  api.get(`apt/avg/${gugun}`).then(success).catch(fail);
}

export {
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
};
