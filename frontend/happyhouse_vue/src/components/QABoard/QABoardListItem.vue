<template>
  <b-tr>
    <b-th class="text-left" v-if="board.recnt === 0">
      <b-badge pill variant="warning">답변대기</b-badge>
    </b-th>
    <b-th class="text-left" v-else>
      <b-badge pill variant="light">답변완료</b-badge>
    </b-th>
    <b-th>
      <router-link
        :to="{
          name: 'qaboardDetail',
          params: { boardNum: board.boardNum },
        }"
        >{{ board.title }}</router-link
      >
    </b-th>
    <b-td>{{ board.id }}</b-td>
    <b-td>{{ board.createTime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import { mapState } from "vuex";
import moment from "moment";
const userStore = "userStore";

export default {
  name: "QABoardListItem",
  props: {
    board: Object,
  },
  filters: {
    dateFormat(regtime) {
      let date = new Date(regtime);
      let utc = moment.utc(date).toDate();
      return moment(utc).format("YY.MM.DD");
    },
  },
  computed: {
    ...mapState(userStore, ["userInfo", "isLogin"]),
  },
};
</script>

<style></style>
