<template>
  <b-card no-body>
    <b-card-header class="border-0">
      <b-col class="text-left">
        <h3 class="mb-0">Q&amp;A</h3>
      </b-col>
      <b-col class="text-right">
        <b-button
          class="ml-3"
          type="button"
          @click="moveWrite()"
          style="width: 20%"
          v-if="isLogin && userInfo.id != 'hadmin'"
          >작성하기</b-button
        >
      </b-col>
    </b-card-header>

    <b-table-simple hover responsive>
      <b-thead head-variant="light">
        <b-tr>
          <b-th>구분</b-th>
          <b-th>제목</b-th>
          <b-th>작성자</b-th>
          <b-th>작성일</b-th>
        </b-tr>
      </b-thead>
      <tbody>
        <!-- 하위 component인 ListRow에 데이터 전달(props) -->
        <q-a-board-list-item
          v-for="board in boards"
          :key="board.boardNum"
          :board="board"
        />
      </tbody>
    </b-table-simple>
  </b-card>
</template>
<script>
import { getQABoardlistId, getQABoardlistadmin } from "../../../api/qaboard";
import QABoardListItem from "../../../components/QABoard/QABoardListItem.vue";
import { mapState } from "vuex";
const userStore = "userStore";
//import projects from "./../projects";
//import { Table, TableColumn } from "element-ui";
export default {
  name: "BoardList",
  components: {
    QABoardListItem,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  data() {
    return {
      boards: [],
      text: "",
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo", "isLogin"]),
  },
  created() {
    if (this.userInfo.id == "hadmin") {
      getQABoardlistadmin(
        (response) => {
          this.boards = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    } else {
      getQABoardlistId(
        this.userInfo.id,
        (response) => {
          this.boards = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    }
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Sunflower:wght@500&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");
.mb-0 {
  font-family: "Sunflower", sans-serif;
  font-size: 25px;
}
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
