<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- Card stats -->
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
          @change="getDongList"
        ></b-form-select>
        <b-form-select
          class="ml-3"
          v-model="dong"
          :options="dongSelect"
          @change="getAptList"
        ></b-form-select>
      </b-form>
    </base-header>

    <b-container fluid class="mt--7">
      <b-row style="margin-bottom: 50px">
        <b-col>
          <b-card no-body class="border-0">
            <GmapMap
              :center="center"
              :zoom="zoom"
              style="width: 1600px; height: 700px"
            >
              <GmapMarker
                :key="index"
                v-for="(apt, index) in aptList"
                :position="{ lat: apt.lat, lng: apt.lng }"
                :clickable="true"
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
                      <base-button
                        type="primary"
                        class="my-0"
                        @click="storeClick(apt)"
                        >상권 보기</base-button
                      >
                      <img
                        @click="interestOn(apt)"
                        v-if="!getInterestAptCode.includes(apt.aptCode)"
                        src="https://img.icons8.com/carbon-copy/100/000000/like--v2.png"
                        style="width: 40px; margin-left: 30px; cursor: pointer"
                      />
                      <img
                        @click="interestOff(apt)"
                        src="https://img.icons8.com/plasticine/100/000000/like--v1.png"
                        v-if="getInterestAptCode.includes(apt.aptCode)"
                        style="width: 40px; margin-left: 30px; cursor: pointer"
                      />
                    </div>
                  </div>
                </gmap-info-window>
              </GmapMarker>
            </GmapMap>
          </b-card>
        </b-col>
      </b-row>
      <b-row>
        <b-col v-if="localStoreList">
          <b-form-select
            class="ml-3"
            v-model="largeKind"
            :options="getLargeKind"
            style="margin-bottom: 30px"
            @change="searchLargeKind"
          ></b-form-select>
          <div v-if="largeKind === ''" class="storeListContainer">
            <div
              v-for="(store, index) in storeList"
              :key="index"
              style="margin-bottom: 20px"
            >
              <div class="card">
                <div class="card-header">
                  {{ store.storeName }}
                </div>

                <div class="card-body">
                  <p class="card-text">주소 : {{ store.doroadadd }}</p>
                  <p class="card-text">
                    업종 대분류: {{ store.storeLargeKind }}
                  </p>
                  <p class="card-text">업종 소분류 : {{ store.storeSmKind }}</p>
                  <p class="card-text">거리 : {{ store.distance }}m</p>
                </div>
              </div>
            </div>
          </div>
          <div v-else class="storeListContainer">
            <div
              v-for="(store, index) in localStoreList"
              :key="index"
              style="margin-bottom: 20px"
            >
              <div class="card">
                <div class="card-header">
                  {{ store.storeName }}
                </div>

                <div class="card-body">
                  <p class="card-text">주소 : {{ store.doroadadd }}</p>
                  <p class="card-text">
                    업종 대분류: {{ store.storeLargeKind }}
                  </p>
                  <p class="card-text">업종 소분류 : {{ store.storeSmKind }}</p>
                  <p class="card-text">거리 : {{ store.distance }}m</p>
                </div>
              </div>
            </div>
          </div>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import { mapActions, mapGetters, mapState, mapMutations } from "vuex";

const aptStore = "aptStore";
const userStore = "userStore";
export default {
  name: "GoogleMap",
  data() {
    return {
      zoom: 12,
      sido: "",
      gugun: "",
      dong: "",
      keyword: "",
      center: {
        lat: 37.541,
        lng: 126.986,
      },
      openedMarkerID: "",
      infoWinOpen: false,
      storeLargeKindList: null,
      largeKind: "",
      localStoreList: [],
    };
  },
  methods: {
    ...mapMutations(aptStore, [
      "SET_STORE_LIST",
      "CLEAR_APT_LIST",
      "CLEAR_STORE_LIST",
      "CLEAR_GUGUN",
      "CLEAR_DONG",
    ]),
    ...mapActions(aptStore, [
      "sidoCall",
      "gugunCall",
      "dongCall",
      "aptListCall",
      "baseAddressCall",
      "storeListCall",
      "insertInterestCall",
      "deleteInterestAptCall",
    ]),
    location() {
      if (!navigator.geolocation) {
        const msg = "위치 정보가 지원되지 않습니다.";
        alert(msg);
        throw msg;
      }
      navigator.geolocation.getCurrentPosition((position) => {
        this.center = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        };
      });
    },
    getGugunList() {
      this.gugunCall(this.sido);
    },
    getDongList() {
      this.dongCall(this.gugun);
    },
    async getAptList() {
      await this.baseAddressCall(this.dong);

      setTimeout(() => {
        this.center = this.baseAddress;
      }, 30);
      this.aptListCall(this.dong);
      this.zoom = 15;
    },
    markerClick(apt) {
      this.center = { lat: apt.lat, lng: apt.lng };
      if (this.openedMarkerID == apt.aptCode) {
        this.infoWinOpen = !this.infoWinOpen;
      } else {
        this.infoWinOpen = true;
        this.openedMarkerID = apt.aptCode;
      }
    },
    storeClick(apt) {
      this.storeListCall(apt);
      this.largeKind = "";
    },
    searchLargeKind() {
      this.localStoreList = this.storeList.filter(
        (store) => store.storeLargeKind === this.largeKind
      );
    },
    interestOn(apt) {
      const interestApt = { aptCode: apt.aptCode, userid: this.userInfo.id };
      const info = { interest: interestApt, apt: apt };

      this.insertInterestCall(info);
    },
    interestOff(apt) {
      const interestApt = { aptCode: apt.aptCode, userid: this.userInfo.id };
      this.deleteInterestAptCall(interestApt);
    },
  },
  created() {
    this.sidoCall();
    this.CLEAR_APT_LIST();
    this.CLEAR_STORE_LIST();
    this.CLEAR_GUGUN();
    this.CLEAR_DONG();
  },
  computed: {
    ...mapGetters(aptStore, [
      "getSidoGetter",
      "getGugunGetter",
      "getDongGetter",
      "getAptListGetter",
      "getStoreList",
      "getLargeKind",
      "getInterestAptCode",
      "sidoSelect",
      "gugunSelect",
      "dongSelect",
    ]),
    ...mapState(aptStore, [
      "sidoList",
      "gugunList",
      "dongList",
      "aptList",
      "baseAddress",
      "storeList",
      "interestApt",
    ]),
    ...mapState(userStore, ["userInfo"]),
  },

  mounted() {
    this.location();
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");
.card {
  font-family: "Jua", sans-serif;
}
.card-header {
  font-size: 18px;
}
.card-text {
  font-size: 13px;
}
.storeListContainer {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}
</style>
