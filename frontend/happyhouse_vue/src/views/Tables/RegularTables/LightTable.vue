<template>
  <b-card no-body>
    <b-card-header class="border-0">
      <b-col class="text-left">
        <h3 class="mb-0">공지사항</h3>
      </b-col>
      <b-col class="text-right">
        <b-button
          class="ml-3"
          type="button"
          @click="moveWrite()"
          style="width: 10%"
          v-if="userInfo && userInfo.id === 'hadmin'"
          >글쓰기</b-button
        >
      </b-col>
    </b-card-header>

    <b-table-simple hover responsive>
      <b-thead head-variant="light">
        <b-tr>
          <b-th>글번호</b-th>
          <b-th>제목</b-th>
          <b-th>작성자</b-th>
          <b-th>작성일</b-th>
          <b-th>조회수</b-th>
        </b-tr>
      </b-thead>
      <tbody>
        <!-- 하위 component인 ListRow에 데이터 전달(props) -->
        <notice-list-item
          v-for="(article, index) in articles"
          :key="index"
          :article="article"
          :idx="index"
        />
      </tbody>
    </b-table-simple>

    <b-card-footer class="py-4 d-flex justify-content-end">
      <base-pagination
        v-model="currentPage"
        :per-page="10"
        :total="50"
      ></base-pagination>
    </b-card-footer>
    <b-col class="text-right">
      <b-form style="display: flex; justify-content: center">
        <b-form-select
          v-model="selected"
          :options="options"
          style="width: 15%"
        />
        <b-input
          type="text"
          v-model="text"
          placeholder="검색어 입력..."
          style="width: 30%"
        />
        <b-button class="ml-3" type="button" @click="search" style="width: 10%"
          >검색</b-button
        >
      </b-form>
      <br />
    </b-col>
  </b-card>
</template>
<script>
import { listArticle } from "../../../api/notice.js";
import NoticeListItem from "../../../components/Notice/NoticeListItem";
import http from "../../../api/http";
import { mapState } from "vuex";
const userStore = "userStore";
//import projects from "./../projects";
//import { Table, TableColumn } from "element-ui";
export default {
  name: "NoticeList",
  components: {
    NoticeListItem,
    //[Table.name]: Table,
    //[TableColumn.name]: TableColumn,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  data() {
    return {
      articles: [],
      // projects,
      currentPage: 1,
      selected: null,
      options: [
        { value: null, text: "검색" },
        { value: "title", text: "제목" },
        { value: "text", text: "내용" },
        { value: "userid", text: "작성자" },
      ],
      text: "",
    };
  },
  created() {
    // let param = {
    //   pg: 1,
    //   spp: 20,
    //   key: null,
    //   word: null,
    // };
    listArticle(
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "noticeRegister" });
    },
    search() {
      console.log(this.selected);
      console.log(this.text);
      http.get(`notice/${this.selected}/${this.text}`).then(({ data }) => {
        this, (this.articles = data);
      });
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
