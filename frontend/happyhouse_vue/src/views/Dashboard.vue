<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- Card stats -->
      <b-row class="text-left mt-5 ml-2">
        <div
          style="
            color: white;
            font-size: 55px;
            font-style: italic;
            font-weight: bold;
          "
        >
          Welcome, Happy House
        </div>
      </b-row>
      <b-row class="text-left ml-3 mt-1">
        <div style="color: white; font-size: 25px; font-weight: bold">
          Happy House에 오신 것을 환영합니다.
        </div>
      </b-row>
    </base-header>

    <!--Charts-->

    <b-container fluid class="mt-4">
      <!--start front board -->

      <b-row class="mt-7" align-v="stretch">
        <b-col cols="6">
          <div style="margin-left: 30px; margin-top: 10px">
            <h1 style="font-weight: bolder; color: black">
              당신의 멋진 집을 찾아 드리겠습니다 !
            </h1>
            <br />
            <!-- <h2>저희가 제공하는 서비스</h2> -->
            <h3>합리적인 선택이 가능합니다.</h3>
            <h4>
              정확한 정보와 고객의 요구사항을 만족하는 집을 찾아드리겠습니다.
            </h4>
            <h4>
              홈페이지 내의 제공되는 다양한 정보와 기능을 활용하여 자신의 집을
              찾아보세요!
            </h4>
          </div>
        </b-col>
        <b-col class="bg-light" style="height: 235px">
          <div style="text-align: center; margin-top: 50px">
            <img
              src="https://img.icons8.com/ios/50/000000/city-buildings.png"
              style="width: 100px"
            />
          </div>
          <h2 class="text-center mt-2">다양한 매물 정보</h2>
          <!-- <h5 class="text-center">합리적인 선택이 가능합니다.</h5> -->
        </b-col>
        <b-col>
          <div style="text-align: center; margin-top: 50px">
            <img
              src="https://img.icons8.com/pastel-glyph/64/000000/groups--v3.png"
              style="width: 100px"
            />
          </div>
          <h2 class="text-center mt-2">고객 맞춤 서비스</h2>
        </b-col>
      </b-row>
      <b-row align-v="stretch">
        <b-col
          cols="3"
          style="height: 235px; text-align: center; verticle-align: center"
        >
          <div style="text-align: center; margin-top: 70px">
            <img
              src="https://img.icons8.com/external-ddara-lineal-ddara/64/000000/external-arrow-real-estate-ddara-lineal-ddara-11.png"
              style="width: 80px"
            />
          </div>
        </b-col>
        <b-col style="background-color: gainsboro">
          <div style="text-align: center; margin-top: 50px">
            <img
              src="https://img.icons8.com/external-flatart-icons-outline-flatarticons/64/000000/external-location-map-location-flatart-icons-outline-flatarticons-1.png"
              style="width: 100px"
            />
          </div>
          <h2 class="text-center mt-2">지역별 정보 제공</h2>
        </b-col>
        <b-col>
          <div style="text-align: center; margin-top: 50px">
            <img
              src="https://img.icons8.com/external-wanicon-lineal-wanicon/64/000000/external-community-friendship-wanicon-lineal-wanicon.png"
              style="width: 100px"
            />
          </div>
          <h2 class="text-center mt-2">커뮤니티</h2>
        </b-col>
        <b-col style="background-color: gainsboro">
          <div style="text-align: center; margin-top: 50px">
            <img
              src="https://img.icons8.com/ios/50/000000/search-in-list.png"
              style="width: 100px"
            />
          </div>
          <h2 class="text-center mt-2">검색 기능</h2>
        </b-col>
      </b-row>
      <!--end front board -->

      <!-- <b-row class="mt-5">
        <b-col class="mb-xl-0">
          <card type="default" header-classess="bg-transparent"> </card>
        </b-col>
      </b-row> -->

      <!-- Start News Crawling -->
      <b-row class="mt-9">
        <b-col xl="8" class="mb-5 mb-xl-0">
          <div>
            <b-carousel
              id="carousel-1"
              v-model="slide"
              :interval="4000"
              controls
              indicators
              background="#ababab"
              img-width="930"
              img-height="440"
              style="text-shadow: 1px 1px 2px #333"
              @sliding-start="onSlideStart"
              @sliding-end="onSlideEnd"
            >
              <!-- Text slides with image -->
              <b-carousel-slide v-for="(news, index) in Newslist" :key="index">
                <h1 style="color: gold">{{ news.title }}</h1>
                <template #img>
                  <a :href="news.link" target="_blank">
                    <img
                      width="930"
                      height="440"
                      :src="news.img"
                      alt="image slot"
                      @click="t(1)"
                      style="width: 100%"
                    />
                  </a>
                </template>
              </b-carousel-slide>
            </b-carousel>
          </div>
        </b-col>

        <b-col xl="4" class="mb-5 mb-xl-0">
          <div style="margin-left: 30px; margin-top: 50px">
            <h1 style="font-weight: bolder; color: black">Today's News ~</h1>
            <br />
            <!-- <h2>저희가 제공하는 서비스</h2> -->
            <h3>최신 기사들을 접해보세요 !</h3>
            <h4>최신 기사를 통해 현재 동향을 알아보아요.</h4>
            <h4>사진을 클릭하여 자세한 내용을 읽어보세요.</h4>
          </div>
        </b-col>
      </b-row>
      <!-- End News Crawling -->

      <!-- Start Top5 Apt list -->
      <b-row class="mt-9">
        <b-col xl="4" class="mb-5 mb-xl-0">
          <div style="margin-left: 30px; margin-top: 30px; font-weight: bold">
            <div style="font-size: 25px; margin-bottom: 15px; color: black">
              가장 인기 있는 매물
            </div>
            <div style="font-size: 25px; color: black">
              Top 5를 지금 만나보세요~!
            </div>
          </div></b-col
        >
        <b-col xl="8" class="mb-5 mb-xl-0">
          <div class="accordion" role="tablist">
            <div v-for="(apt, index) in top5Interest" :key="index">
              <b-row>
                <b-col xl="2">
                  <div
                    style="
                      text-align: center;
                      font-size: 25px;
                      font-weight: bold;
                      color: darkblue;
                    "
                  >
                    Top {{ index + 1 }}.
                  </div></b-col
                >
                <b-col xl="10">
                  <b-card no-body class="mb-1">
                    <b-card-header header-tag="header" class="p-0" role="tab">
                      <b-button
                        block
                        v-b-toggle="'accordion-' + index"
                        style="background-color: lightblue; text-align: left"
                      >
                        {{ apt.aptName }}</b-button
                      >
                    </b-card-header>
                    <b-collapse
                      :id="'accordion-' + index"
                      visible
                      accordion="my-accordion"
                      role="tabpanel"
                    >
                      <b-card-body>
                        <b-card-text style="font-weight: bold">
                          주소 : {{ apt.sidoName }} &nbsp;
                          {{ apt.gugunName }} &nbsp; {{ apt.dongName }} &nbsp;
                          {{ apt.jubun }}</b-card-text
                        >
                        <b-card-text>면적 : {{ apt.area }}</b-card-text>
                        <b-card-text>가격대 : {{ apt.dealAmount }}</b-card-text>
                        <b-card-text>설립연도 : {{ apt.date }}년</b-card-text>
                        <b-card-text
                          >관심 수 : {{ apt.interestCount }}</b-card-text
                        >
                      </b-card-body>
                    </b-collapse>
                  </b-card>
                </b-col>
              </b-row>
            </div>
          </div>
        </b-col>
      </b-row>
      <!-- End Top5 Apt list -->

      <!-- Start Avg price -->
      <b-row class="mt-9 justify-content-md-center"
        ><div style="font-size: 30px; font-weight: bold; color: black">
          지역별 평당 거래가를 알아보세요.
        </div></b-row
      >
      <b-row class="mt-4">
        <b-col xl="12" class="mb-5 mb-xl-0">
          <card type="default" header-classes="bg-transparent">
            <b-row align-v="center" slot="header">
              <b-col>
                <h2 class="h3 text-white mb-3 ml-3 mt-2">
                  지역별 평당 거래가 [단위 : 만 원/평]
                </h2>
                <b-form style="display: flex; justify-content: center">
                  <b-form-select
                    class="ml-3"
                    v-model="sido"
                    :options="sidoSelect"
                    @change="getGugunList"
                  ></b-form-select>
                  <b-form-select
                    class="ml-3"
                    v-model="gugun"
                    :options="gugunSelect"
                    @change="getChartData"
                  ></b-form-select>
                </b-form>
              </b-col>
              <b-col>
                <b-nav class="nav-pills justify-content-end"> </b-nav>
              </b-col>
            </b-row>
            <line-chart
              :height="350"
              ref="bigChart"
              :chart-data="getAvgChartData"
              :extra-options="bigLineChart.extraOptions"
            >
            </line-chart>
          </card>
        </b-col>
      </b-row>
      <!-- End Avg price -->

      <!-- Start Why Us -->
      <!--
      <b-row class="text-left mt-7 ml-2">
        <div>-- Why us</div>
      </b-row>
      <b-row class="text-left ml-2">
        <div style="color: black; font-size: 30px; font-style: italic">
          Our Happy House is ~?
        </div>
      </b-row>

      <b-row class="mt-2">
        <b-col xl="4" md="8">
          <stats-card
            title="1st"
            type="gradient-red"
            sub-title="정확한 정보"
            icon="ni ni-active-40"
            class="mb-4"
          >
            <template slot="footer">
              <span class="text-nowrap"
                ><div>빠르고 정확한 정보로</div>
                <div>합리적인 선택이 가능합니다.</div></span
              >
            </template>
          </stats-card>
        </b-col>
        <b-col xl="4" md="8">
          <stats-card
            title="2nd"
            type="gradient-orange"
            sub-title="다양한 기능"
            icon="ni ni-chart-pie-35"
            class="mb-4"
          >
            <template slot="footer">
              <span class="text-nowrap"
                ><div>로</div>
                <div>다양한 부가기능을 이용할 수 있습니다.</div></span
              >
            </template>
          </stats-card>
        </b-col>
        <b-col xl="4" md="8">
          <stats-card
            title="3rd"
            type="gradient-green"
            sub-title="편리한 UI"
            icon="ni ni-money-coins"
            class="mb-4"
          >
            <template slot="footer">
              <span class="text-nowrap"
                ><div>사용자의 관점에서 생각한 UI로</div>
                <div>직관적이고 편리하게 이용할 수 있습니다.</div></span
              >
            </template>
          </stats-card>
        </b-col>
      </b-row>
-->
      <!-- End Why Us -->

      <!--Tables-->
      <!--
      <b-row class="text-left mt-7 ml-2">
        <div>-- preview</div>
      </b-row>
      <b-row class="text-left ml-2">
        <div style="color: black; font-size: 25px; font-style: italic">
          Notice
        </div>
      </b-row>
      <b-row class="mt-2">
        <b-col xl="8" class="mb-5 mb-xl-0">
          <page-visits-table></page-visits-table>
        </b-col>
        <b-col xl="4" class="mb-5 mb-xl-0">
          <social-traffic-table></social-traffic-table>
        </b-col>
      </b-row>
-->
      <!--End tables-->

      <!-- Start Contact Us -->
      <!--      
      <b-row class="text-left mt-7 ml-2">
        <div>-- Contact</div>
      </b-row>
      <b-row class="text-left ml-2">
        <div style="color: black; font-size: 25px; font-style: italic">
          Contact Us
        </div>
      </b-row>
      <b-row class="mt-2">
        <b-col class="mb-xl-0">
          <stats-card type="default" header-classess="bg-transparent">
            <span class="text-nowrap">SSAFY 7기 18반 2조</span>
          </stats-card>
        </b-col>
      </b-row>
-->
      <!-- End Contact Us-->
      <br /><br /><br /><br />
    </b-container>
  </div>
</template>
<script>
// Charts
import * as chartConfigs from "@/components/Charts/config";
import LineChart from "@/components/Charts/LineChart";
import BarChart from "@/components/Charts/BarChart";

// Components
import BaseProgress from "@/components/BaseProgress";
import StatsCard from "@/components/Cards/StatsCard";

// Tables
import SocialTrafficTable from "./Dashboard/SocialTrafficTable";
import PageVisitsTable from "./Dashboard/PageVisitsTable";

// News
import { getNews } from "../api/news";
import { mapActions, mapGetters, mapState } from "vuex";

const aptStore = "aptStore";

export default {
  components: {
    LineChart,
    BarChart,
    BaseProgress,
    StatsCard,
    PageVisitsTable,
    SocialTrafficTable,
  },
  data() {
    return {
      sido: "",
      gugun: "",
      slide: 0,
      sliding: null,
      Newslist: [],
      bigLineChart: {
        allData: [
          [0, 20, 10, 30, 15, 40, 20, 60, 60],
          [0, 20, 5, 25, 10, 30, 15, 40, 40],
        ],
        activeIndex: 0,
        chartData: {
          datasets: [
            {
              label: "Performance",
              data: [0, 20, 10, 30, 15, 40, 20, 60, 60],
            },
          ],
          labels: ["May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
        },
        extraOptions: chartConfigs.blueChartOptions,
      },
      redBarChart: {
        chartData: {
          labels: ["Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
          datasets: [
            {
              label: "Sales",
              data: [25, 20, 30, 22, 17, 29],
            },
          ],
        },
        extraOptions: chartConfigs.blueChartOptions,
      },
    };
  },
  created() {
    getNews(
      (response) => {
        this.Newslist = response.data.news;
      },
      (error) => {
        console.log(error);
      }
    );
    this.top5ByInterestCall();
    this.sidoCall();
    this.avgByDongCall();
  },
  methods: {
    ...mapActions(aptStore, [
      "top5ByInterestCall",
      "avgByDongCall",
      "sidoCall",
      "gugunCall",
    ]),
    onSlideStart(slide) {
      this.sliding = true;
    },
    onSlideEnd(slide) {
      this.sliding = false;
    },
    getGugunList() {
      this.gugunCall(this.sido);
    },
    getChartData() {
      this.avgByDongCall(this.gugun);
    },
  },
  computed: {
    ...mapState(aptStore, ["top5Interest", "avgByDong"]),
    ...mapGetters(aptStore, ["sidoSelect", "gugunSelect", "getAvgChartData"]),
  },
  mounted() {},
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");

.el-table .cell {
  padding-left: 0px;
  padding-right: 0px;
}
</style>
