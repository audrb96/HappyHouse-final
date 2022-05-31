d
<template>
  <b-card no-body>
    <b-card-header class="border-0">
      <b-row>
        <b-col class="text-center">
          <br />
          <h3 class="mb-0">글 상세보기</h3>
        </b-col>
      </b-row>
    </b-card-header>
    <b-row class="mb-1">
      <b-col class="text-left" style="text-align: left; margin-left: 10%">
        <b-button size="m" @click="listArticle" variant="light">목록</b-button>
      </b-col>
      <b-col
        v-if="userInfo && userInfo.id == 'hadmin'"
        class="text-right"
        style="margin-right: 10%"
      >
        <b-button pill size="m" @click="moveModifyArticle" class="mr-2"
          >수정하기</b-button
        >
        <b-button pill size="m" variant="danger" @click="deleteArticle"
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
            <div style='display: flex; margin-left: 10%'><h1>${article.title} &nbsp </h1><div style='font-size: 15px'> [${article.views}]</div></div>
            <div style='text-align: right; margin-right: 10%'>${article.userId} 	
&nbsp&nbsp&nbsp&nbsp${article.createTime}</div>
            <hr>
            <div style='display: flex; margin-left: 10%; text-align: left'><h3><pre>${article.text}</pre></h3></div>`"
          class="mb-3"
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
    <div v-for="(comment, index) in comments" :key="index">
      <!-- <comment-item :comment="comment"></comment-item> -->
      <b-card style="margin-left: 10%; margin-right: 10%">
        <b-row class="mb-1">
          <b-col class="text-left">
            <b>{{ comment.userid }}</b>
          </b-col>
          <b-col class="text-right">{{ comment.regtime }}</b-col>
        </b-row>
        <b-row class="mb-1">
          <b-col class="text-left">
            {{ comment.content }}
          </b-col>
          <b-button
            pill
            size="sm"
            variant="outline-danger"
            @click="deleteComment(comment)"
            v-if="userInfo && userInfo.id == comment.userid"
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
          <label for="comment"><code>작성자</code> &nbsp;:</label>
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
// import moment from "moment";
import { getArticle, deleteArticle } from "../../api/notice";
import http from "../../api/http";
import { mapState } from "vuex";
import moment from "moment";

const userStore = "userStore";
// import CommentItem from "./item/CommentItem.vue";

export default {
  name: "NoticeDetail",
  props: {
    comment: Object,
  },
  data() {
    return {
      content: "",
      article: {},
      comments: [],
    };
  },
  filters: {},
  computed: {
    ...mapState(userStore, ["userInfo"]),
    message() {
      if (this.article.text) return this.article.text.split("\n").join("<br>");
      return "";
    },
  },
  async created() {
    await http
      .get(`/Comment/${this.$route.params.articleNo}`)
      .then(({ data }) => {
        this.comments = data;
      });

    await getArticle(
      this.$route.params.articleNo,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("게시글 삭제시 에러발생!!", error);
      }
    );
    setTimeout(() => {
      this.article.createTime = this.dateFormat(this.article.createTime);
    }, 30);
  },
  methods: {
    dateFormat(regtime) {
      //  console.log(regtime);
      return moment(new Date(regtime)).format("YY.MM.DD hh:mm");
    },
    listArticle() {
      this.$router.push({ name: "noticeList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "noticeModify",
        params: { articleNo: this.article.articleNo },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        deleteArticle(this.article.articleNo, () => {
          this.$router.push({ name: "noticeList" });
        });
      }
    },
    async onSubmit() {
      await http
        .post(
          "/Comment",
          JSON.stringify({
            userid: this.userInfo.id,
            content: this.content,
            articleNo: this.$route.params.articleNo,
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
        .get(`/Comment/${this.$route.params.articleNo}`)
        .then(({ data }) => {
          console.log(data);
          this.comments = data;
        });
    },
    async deleteComment(comment) {
      await http.delete(`/Comment/${comment.commentNum}`).then(() => {
        console.log("삭제 성공");
      });
      await http
        .get(`/Comment/${this.$route.params.articleNo}`)
        .then(({ data }) => {
          this.comments = data;
        });
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
