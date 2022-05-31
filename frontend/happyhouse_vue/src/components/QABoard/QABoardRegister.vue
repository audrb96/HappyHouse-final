<template>
  <b-card no-body>
    <b-card-header class="border-0">
      <b-row>
        <b-col class="text-center">
          <br />
          <h3 class="mb-0">QA 작성</h3>
          <br /><br />
        </b-col>
      </b-row>
      <q-a-board-input-item type="register" />
    </b-card-header>
  </b-card>
</template>

<script>
import QABoardInputItem from "./QABoardInputItem.vue";

export default {
  name: "BoardWrite",
  components: {
    QABoardInputItem,
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
