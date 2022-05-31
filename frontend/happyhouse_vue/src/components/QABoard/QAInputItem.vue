<template>
  <b-row class="mb-1">
    <b-col style="text-align: left; margin-left: 15%">
      <b-form @submit="onSubmit" @reset="onReset" style="width: 80%">
        <b-form-group
          id="title-group"
          label="제목 :"
          class="bformcss"
          label-for="title"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="qaboard.title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="text-group"
          label="내용 :"
          label-for="text"
          class="bformcss"
        >
          <b-form-textarea
            id="text"
            v-model="qaboard.text"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>
        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>
<script>
import { writeQABoard, getArticle, modifyArticle } from "../../api/notice.js";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "QABoardInputItem",
  data() {
    return {
      qaboard: {
        boardNum: 0,
        ID: "",
        title: "",
        text: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  created() {
    console.log(this.type);
    if (this.type === "modify") {
      getArticle(
        this.$route.params.articleNo,
        ({ data }) => {
          // this.article.articleno = data.article.articleno;
          // this.article.userid = data.article.userid;
          // this.article.subject = data.article.subject;
          // this.article.content = data.article.content;
          this.qaboard = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    } else {
      console.log(this.userInfo);
      this.article.userId = this.userInfo.id;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      //   !this.article.userId &&
      //     ((msg = "작성자 입력해주세요"),
      //     (err = false),
      //     this.$refs.userId.focus());
      //   err &&
      !this.qaboard.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.qaboard.text &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs.text.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.qaboard.articleno = 0;
      this.qaboard.title = "";
      this.qaboard.text = "";
      this.$router.push({ name: "noticeList" });
    },
    registArticle() {
      writeQABoard(
        {
          ID: this.qaboard.ID,
          title: this.qaboard.title,
          text: this.qaboard.text,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyArticle() {
      modifyArticle(
        {
          boardNum: this.qaboard.boardNum,
          title: this.qaboard.title,
          text: this.qaboard.text,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "noticeList" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "noticeList" });
    },
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap");
.bformcss {
  font-family: "Sunflower", sans-serif;
  font-size: 19px;
  font-display: center;
}
.m-1 {
  font-family: "Sunflower", sans-serif;
  font-size: 15px;
}
</style>
