<template>
  <b-row class="mb-1">
    <b-col style="text-align: left; margin-left: 15%">
      <b-form @submit="onSubmit" @reset="onReset" style="width: 80%">
        <b-form-group
          id="title-group"
          label="질문명 :"
          class="bformcss"
          label-for="title"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="board.title"
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
            v-model="board.content"
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
import {
  writeQAboard,
  getQAboardbyIdNo,
  modifyQAboard,
} from "../../api/qaboard";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardInputItem",
  data() {
    return {
      board: {
        boardNum: 0,
        id: "",
        title: "",
        content: "",
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
      getQAboardbyIdNo(
        this.userInfo.id,
        this.$route.params.boardNum,
        ({ data }) => {
          // this.article.articleno = data.article.articleno;
          // this.article.userid = data.article.userid;
          // this.article.subject = data.article.subject;
          // this.article.content = data.article.content;
          this.board = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    } else {
      console.log(this.userInfo);
      this.board.id = this.userInfo.id;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.board.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.board.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registQAboard() : this.modifyQAboard();
    },
    onReset(event) {
      event.preventDefault();
      this.board.boardNum = 0;
      this.board.title = "";
      this.board.content = "";
      this.$router.push({ name: "boardList" });
    },
    registQAboard() {
      writeQAboard(
        {
          id: this.board.id,
          title: this.board.title,
          content: this.board.content,
        },
        ({ data }) => {
          let msg = "Q&A 등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "Q&A 등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyQAboard() {
      modifyQAboard(
        {
          boardNum: this.board.boardNum,
          id: this.board.id,
          title: this.board.title,
          content: this.board.content,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "boardList" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "boardList" });
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
