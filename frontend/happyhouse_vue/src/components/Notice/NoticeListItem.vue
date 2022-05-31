<template>
  <b-tr>
    <b-td>{{ idx + 1 }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'noticeDetail', params: { articleNo: article.articleNo } }"
        >{{ article.title }}</router-link
      >
    </b-th>
    <b-td>{{ article.userId }}</b-td>
    <b-td>{{ article.createTime | dateFormat }}</b-td>
    <b-td>{{ article.views }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "NoticeListItem",
  props: {
    article: Object,
    idx: Number,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
