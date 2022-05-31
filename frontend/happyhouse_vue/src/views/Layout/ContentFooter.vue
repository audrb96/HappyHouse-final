<template>
  <footer class="footer px-4">
    <b-row align-v="center" class="justify-content-lg-between">
      <b-col lg="6">
        <div class="copyright text-center text-lg-left text-muted">
          © {{ year }}
          <a
            href="https://www.ssafy.com/ksp/jsp/swp/swpMain.jsp"
            class="font-weight-bold ml-1"
            target="_blank"
            >SSAFY 7th</a
          >
        </div>
      </b-col>
      <b-col lg="6">
        <b-nav align="center" class="nav-footer justify-content-lg-end">
          <b-nav-item href="https://www.creative-tim.com" target="_blank">
            Creative Tim
          </b-nav-item>
          <b-nav-item
            href="https://github.com/audrb96?tab=repositories"
            target="_blank"
          >
            About Us
          </b-nav-item>
          <b-nav-item
            href="https://irradiated-cesium-6a5.notion.site/portfolio-0e9a861fe66b4865847fd40ed5ac693e"
            target="_blank"
          >
            Blog
          </b-nav-item>
          <b-nav-item
            href="https://www.creative-tim.com/license"
            target="_blank"
          >
            License
          </b-nav-item>
        </b-nav>
      </b-col>
    </b-row>
  </footer>
</template>
<script>
export default {
  data() {
    return {
      year: new Date().getFullYear(),
    };
  },
};
</script>
<style></style>
