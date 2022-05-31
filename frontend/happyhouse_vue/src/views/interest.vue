<template>
  <div>
    <!-- BootstrapVue Done -->
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- Card stats -->
    </base-header>

    <b-container fluid class="mt--7">
      <b-row class="justify-content-center">
        <b-col lg="12">
          <card header-classes="bg-transparent">
            <h3 slot="header" class="mb-0 ml-3">관심 매물</h3>
            <b-row
              class="interest-card"
              style="
                display: flex;
                flex-wrap: wrap;
                justify-content: space-around;
              "
            >
              <div
                class="card"
                v-for="(apt, index) in interestApt"
                :key="index"
                style="margin-bottom: 15px"
                @click="cardClick(apt)"
              >
                <div class="card-header">
                  {{ apt.aptName }}
                  &nbsp;&nbsp;&nbsp;
                  <b-button variant="danger" @click="deleteClick(apt)"
                    >삭제</b-button
                  >
                </div>
                <div class="card-body">
                  <p class="card-text">
                    주소 : {{ apt.sidoName }} &nbsp; {{ apt.gugunName }} &nbsp;
                    {{ apt.dongName }} &nbsp;
                    {{ apt.jubun }}
                  </p>
                  <p class="card-text">면적 : {{ apt.area }}</p>
                  <p class="card-text">가격대 : {{ apt.dealAmount }}</p>
                  <p class="card-text">설립연도 : {{ apt.date }}년</p>
                </div>
              </div>
            </b-row>
          </card>
        </b-col>
      </b-row>
      <b-row style="justify-content: center; margin-top: 20px">
        <GmapMap
          :center="center"
          :zoom="zoom"
          style="width: 1000px; height: 500px"
        >
          <GMapCluster>
            <GmapMarker
              :key="index"
              v-for="(apt, index) in interestApt"
              :position="{ lat: apt.lat, lng: apt.lng }"
              :clickabe="true"
              :draggable="true"
              @click="markerClick(apt)"
            >
              <gmap-info-window
                :opened="openedMarkerID === apt.aptCode && infoWinOpen"
                @closeclick="infoWinOpen = false"
              >
                <div class="card">
                  <div class="card-header">
                    {{ apt.aptName }}
                  </div>
                  <div class="card-body">
                    <p class="card-text">
                      주소 : {{ apt.sidoName }} &nbsp;
                      {{ apt.gugunName }} &nbsp; {{ apt.dongName }} &nbsp;
                      {{ apt.jubun }}
                    </p>
                    <p class="card-text">면적 : {{ apt.area }}</p>
                    <p class="card-text">가격대 : {{ apt.dealAmount }}</p>
                    <p class="card-text">설립연도 : {{ apt.date }}년</p>
                  </div>
                </div>
              </gmap-info-window>
            </GmapMarker>
          </GMapCluster>
        </GmapMap>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import Vue from "vue";
import VueClipboard from "vue-clipboard2";
import BaseHeader from "@/components/BaseHeader";
import { mapState, mapActions } from "vuex";

Vue.use(VueClipboard);
const aptStore = "aptStore";
const userStore = "userStore";

export default {
  name: "icons",
  components: {
    BaseHeader,
  },
  data() {
    return {
      center: {
        lat: 37.541,
        lng: 126.986,
      },
      zoom: 6,
      openedMarkerID: "",
      infoWinOpen: false,
    };
  },
  methods: {
    ...mapActions(aptStore, ["deleteInterestAptCall", "aptListByInterestCall"]),
    onCopy() {
      this.$notify({
        type: "info",
        message: "Copied to clipboard",
      });
    },
    deleteClick(apt) {
      const interestApt = { aptCode: apt.aptCode, userid: this.userInfo.id };
      this.deleteInterestAptCall(interestApt);
    },
    markerClick(apt) {
      console.log("AAAA");
      this.center = { lat: apt.lat, lng: apt.lng };
      if (this.openedMarkerID == apt.aptCode) {
        this.infoWinOpen = !this.infoWinOpen;
      } else {
        this.infoWinOpen = true;
        this.openedMarkerID = apt.aptCode;
      }
      this.zoom = 15;
    },
    cardClick(apt) {
      this.center = { lat: apt.lat, lng: apt.lng };
      this.zoom = 15;
    },
  },
  created() {
    this.aptListByInterestCall(this.userInfo.id);
  },
  computed: {
    ...mapState(aptStore, ["interestApt"]),
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Sunflower:wght@500&display=swap");

.interest-card {
  font-family: "Jua", sans-serif;
}

.mb-0 {
  font-family: "Sunflower", sans-serif;
  font-size: 25px;
}
</style>
