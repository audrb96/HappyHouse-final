import { apiInstance } from "./index.js";
import axios from "axios";
const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function deleteId(id, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.delete(`/user/${id}`).then(success).catch(fail);
}

async function join(user, success, fail) {
  await api.post(`/user/join`, JSON.stringify(user)).then(success).catch(fail);
}

async function pwFind(info, success, fail) {
  await api
    .post(`/user/pwFind`, JSON.stringify(info))
    .then(success)
    .catch(fail);
}

async function ModifyId(user, success, fail) {
  await api.put(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

export { login, findById, deleteId, join, pwFind, ModifyId };
