<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- Card stats -->
      <b-row> </b-row>
    </base-header>
    <b-container fluid class="mt--7">
      <b-row>
        <b-col>
          <router-view></router-view>
          <!-- <light-table /> -->
          <!--
          <b-card no-body>
            <b-card-header class="border-0">
              <b-col class="text-left">
                <h3 class="mb-0">QABoard</h3>
              </b-col>
              <b-col class="text-right">
                <b-button
                  class="ml-3"
                  type="button"
                  @click="moveWrite()"
                  style="width: 20%"
                  v-if="isLogin"
                  >작성하기</b-button
                >
              </b-col>
            </b-card-header>

            <b-table-simple hover responsive>
              <b-thead head-variant="light">
                <b-tr>
                  <b-th>제목</b-th>
                  <b-th>작성자</b-th>
                  <b-th>작성일</b-th>
                </b-tr>
              </b-thead>
              <tbody>
          -->
          <!-- 하위 component인 ListRow에 데이터 전달(props) -->
          <!--
                <q-a-board-list-item
                  v-for="board in boards"
                  :key="board.boardNum"
                  :board="board"
                />
              </tbody>
            </b-table-simple>
          </b-card>
          -->
        </b-col>
      </b-row>
      <div class="mt-5"></div>
    </b-container>
  </div>
</template>
<script>
import {
  Dropdown,
  DropdownItem,
  DropdownMenu,
  Table,
  TableColumn,
} from "element-ui";
// import projects from "./Tables/projects";
import users from "./Tables/users";

// import { getQABoardlistId } from "../api/qaboard.js";
// import QABoardListItem from "../components/QABoard/QABoardListItem.vue";
// import { mapState } from "vuex";
// const userStore = "userStore";

export default {
  components: {
    // QABoardListItem,
    [Dropdown.name]: Dropdown,
    [DropdownItem.name]: DropdownItem,
    [DropdownMenu.name]: DropdownMenu,
    [Table.name]: Table,
    [TableColumn.name]: TableColumn,
  },
  data() {
    return {
      users,
      // boards: [],
      // text: "",
    };
  },
  // computed: {
  //   ...mapState(userStore, ["userInfo", "isLogin"]),
  // },
  // created() {
  //   getQABoardlistId(
  //     this.userInfo.id,
  //     (response) => {
  //       this.boards = response.data;
  //       console.log(this.userInfo.id);
  //       console.log(this.boards);
  //     },
  //     (error) => {
  //       console.log(error);
  //     }
  //   );
  // },
  // methods: {
  //   dateFormat(regtime) {
  //     //  console.log(regtime);
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm");
  //   },
  //   moveWrite() {
  //     this.$router.push({ name: "boardRegister" });
  //   },
  // },
};
</script>
<style>
.el-table.table-dark {
  background-color: #172b4d;
  color: #f8f9fe;
}

.el-table.table-dark th,
.el-table.table-dark tr {
  background-color: #172b4d;
}

.el-table.table-dark td,
.el-table.table-dark th.is-leaf {
  border-bottom: none;
}
</style>
