import { apiInstance } from "./index.js";

const api = apiInstance();

function getQABoardlistadmin(success, fail) {
  api.get(`/QA`).then(success).catch(fail);
}

function getQABoardlistId(id, success, fail) {
  api.get(`/QA/${id}`).then(success).catch(fail);
}

function writeQAboard(qaboard, success, fail) {
  api.post(`/QA`, JSON.stringify(qaboard)).then(success).catch(fail);
}

// function getQAboard(boardNum, success, fail) {
//   api.get(`/QA/${boardNum}`).then(success).catch(fail);
// }

function getQAboardbyIdNo(id, boardNum, success, fail) {
  api.get(`/QA/${id}/${boardNum}`).then(success).catch(fail);
}

function modifyQAboard(board, success, fail) {
  api
    .put(`/QA/${board.boardNum}`, JSON.stringify(board))
    .then(success)
    .catch(fail);
}

function deleteQAboard(boardNum, success, fail) {
  api.delete(`/QA/${boardNum}`).then(success).catch(fail);
}

export {
  getQABoardlistadmin,
  getQABoardlistId,
  writeQAboard,
  // getQAboard,
  getQAboardbyIdNo,
  modifyQAboard,
  deleteQAboard,
};
