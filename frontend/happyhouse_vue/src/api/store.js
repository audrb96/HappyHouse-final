import { apiInstance } from "./index.js";

const api = apiInstance();

function getStoreByDistance(lat, lng, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.get(`/store/getStoreByDistance/${lat}/${lng}`).then(success).catch(fail);
}

export { getStoreByDistance };
