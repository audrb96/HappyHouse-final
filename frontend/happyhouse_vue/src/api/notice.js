import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(success, fail) {
  api.get(`/notice`).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/notice`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(articleno, success, fail) {
  api.get(`/notice/${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api
    .put(`/notice/${article.articleno}`, JSON.stringify(article))
    .then(success)
    .catch(fail);
}

function deleteArticle(articleno, success, fail) {
  api.delete(`/notice/${articleno}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
