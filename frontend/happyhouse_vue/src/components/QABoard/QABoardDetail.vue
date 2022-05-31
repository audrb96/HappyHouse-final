<template>
  <b-card no-body>
    <b-card-header class="border-0">
      <b-row>
        <b-col class="text-center">
          <br />
          <h3 class="mb-0">Q&amp;A 상세보기</h3>
        </b-col>
      </b-row>
    </b-card-header>
    <b-row class="mb-1">
      <b-col class="text-left" style="text-align: left; margin-left: 10%">
        <b-button size="m" @click="listQAboard" variant="light">목록</b-button>
      </b-col>
      <b-col v-if="userInfo" class="text-right" style="margin-right: 10%">
        <b-button
          pill
          size="m"
          @click="moveModifyQABoard"
          class="mr-2"
          v-if="userInfo.id != 'hadmin'"
          >수정하기</b-button
        >
        <b-button pill size="m" variant="danger" @click="deleteQAboard"
          >삭제</b-button
        >
      </b-col>
    </b-row>
    <br />
    <b-row class="mb-1" style="coloc: blue">
      <b-col style="text-align: left; margin-left: 10%; margin-right: 10%">
        <b-card
          header=" "
          :header-html="`
          <br/>
            <div style='display: flex; margin-left: 10%'>
              <h1>${board.title} &nbsp </h1></div>
            <div style='text-align: right; margin-right: 10%'>${board.id} 	
            &nbsp&nbsp&nbsp&nbsp${board.createTime}</div>
            <hr>
            <div style='display: flex; margin-left: 10%; text-align: left'><h3><pre>${board.content}</pre></h3></div>`"
          class="mb-2"
          border-variant="light"
          no-body
          style="text-align: center"
        >
          <!-- <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body> -->
        </b-card>
      </b-col>
      <br />
    </b-row>
    <br />
    <div v-for="(qacomment, index) in qacomments" :key="index">
      <!-- <comment-item :comment="comment"></comment-item> -->
      <b-card style="margin-left: 10%; margin-right: 10%">
        <b-row class="mb-1">
          <b-col class="text-left">
            <b>{{ qacomment.id }}</b>
          </b-col>
          <b-col class="text-right">{{ qacomment.regtime }}</b-col>
        </b-row>
        <b-row class="mb-1">
          <b-col class="text-left">
            {{ qacomment.content }}
          </b-col>
          <b-button
            pill
            size="sm"
            variant="outline-danger"
            @click="deleteQAComment(qacomment)"
            v-if="userInfo && userInfo.id == qacomment.id"
            >삭제</b-button
          >
        </b-row>
      </b-card>
    </div>
    <br />

    <b-card
      class="no-body"
      v-if="userInfo"
      style="margin-left: 10%; margin-right: 10%; text-align: center"
      border-variant="none"
    >
      <b-row class="my-1">
        <b-col sm="2">
          <label for="qacomment"><code>작성자</code> &nbsp;:</label>
        </b-col>
        <b-col sm="2">
          <b>
            {{ userInfo.id }}
          </b>
        </b-col>
        <b-col sm="6">
          <b-form-input
            id="comment"
            type="text"
            v-model="content"
          ></b-form-input>
        </b-col>
        <b-col>
          <b-button variant="primary" @click.prevent="onSubmit">작성</b-button>
        </b-col>
      </b-row>
    </b-card>
    <br />
  </b-card>
</template>
<script>
import { getQAboard, getQAboardbyIdNo, deleteQAboard } from "../../api/qaboard";
import http from "../../api/http";
import { mapState } from "vuex";
// import moment from "moment";

const userStore = "userStore";

export default {
  name: "BoardDetail",
  props: {
    qacomment: Object,
  },
  data() {
    return {
      content: "",
      board: {},
      qacomments: [],
    };
  },
  filters: {},
  computed: {
    ...mapState(userStore, ["userInfo"]),
    message() {
      if (this.board.content)
        return this.board.content.split("\n").join("<br>");
      return "";
    },
  },
  async created() {
    await http
      .get(`/QAComment/${this.$route.params.boardNum}`)
      .then(({ data }) => {
        console.log(data);
        this.qacomments = data;
      });

    await getQAboardbyIdNo(
      this.userInfo.id,
      this.$route.params.boardNum,
      (response) => {
        this.board = response.data;
      },
      (error) => {
        console.log("게시글 삭제시 에러발생!!", error);
      }
    );
  },
  methods: {
    listQAboard() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyQABoard() {
      this.$router.replace({
        name: "boardModify",
        params: { boardNum: this.board.boardNum },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteQAboard() {
      if (confirm("정말로 삭제?")) {
        deleteQAboard(this.board.boardNum, () => {
          this.$router.push({ name: "boardList" });
        });
      }
    },
    async onSubmit() {
      console.log(this.qacomment);
      await http
        .post(
          "/QAComment",
          JSON.stringify({
            id: this.userInfo.id,
            content: this.content,
            boardNum: this.$route.params.boardNum,
          })
        )
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
        });

      this.content = ""; //댓글란 초기화
      await http
        .get(`/QAComment/${this.$route.params.boardNum}`)
        .then(({ data }) => {
          console.log("가져오기 " + data);
          this.qacomments = data;
        });
      await http
        .put(`/QAComment/${this.$route.params.boardNum}`)
        .then(({ data }) => {
          console.log("답변 업데이트" + data);
          this.board = data;
        });
      await getQAboardbyIdNo(
        this.userInfo.id,
        this.$route.params.boardNum,
        (response) => {
          this.board = response.data;
        },
        (error) => {
          console.log("게시글 삭제시 에러발생!!", error);
        }
      );
    },
    async deleteQAComment(qacomment) {
      await http.delete(`/QAComment/${qacomment.qacommentNum}`).then(() => {
        console.log("삭제 성공");
      });
      await http
        .get(`/QAComment/${this.$route.params.boardNum}`)
        .then(({ data }) => {
          this.qacomments = data;
        });
      await http
        .put(`/QAComment/${this.$route.params.boardNum}`)
        .then(({ data }) => {
          console.log("업데이트" + data);
          this.board = data;
        });
      await getQAboardbyIdNo(
        this.userInfo.id,
        this.$route.params.boardNum,
        (response) => {
          this.board = response.data;
        },
        (error) => {
          console.log("게시글 삭제시 에러발생!!", error);
        }
      );
    },
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Sunflower:wght@500&display=swap");
.mb-0 {
  font-family: "Sunflower", sans-serif;
  font-size: 25px;
  font-display: center;
}
</style>
