import DashboardLayout from "@/views/Layout/DashboardLayout.vue";
import AuthLayout from "@/views/Pages/AuthLayout.vue";

import NotFound from "@/views/NotFoundPage.vue";

const routes = [
  {
    path: "/",
    redirect: "dashboard",
    component: DashboardLayout,
    children: [
      {
        path: "/dashboard",
        name: "dashboard",
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/Dashboard.vue"),
      },
      {
        path: "/interest",
        name: "interest",
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/interest.vue"),
      },
      {
        path: "/profile",
        name: "profile",
        component: () =>
          import(
            /* webpackChunkName: "demo" */ "../views/Pages/UserProfile.vue"
          ),
      },
      {
        path: "/maps",
        name: "maps",
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/GoogleMaps.vue"),
      },
      {
        path: "/notice",
        name: "notices",
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/RegularTables.vue"),
        redirect: "/notice/list",
        children: [
          {
            path: "list",
            name: "noticeList",
            component: () =>
              import("../views/Tables/RegularTables/LightTable.vue"),
          },
          {
            path: "write",
            name: "noticeRegister",
            component: () => import("../components/Notice/NoticeRegister.vue"),
          },
          {
            path: "detail",
            name: "noticeDetail",
            component: () => import("../components/Notice/NoticeDetail.vue"),
          },
          {
            path: "modify",
            name: "noticeModify",
            component: () => import("../components/Notice/NoticeModify.vue"),
          },
        ],
      },
      {
        path: "/QA",
        name: "QAs",
        component: () => import("../views/QABoardTables.vue"),
        redirect: "/QA/list",
        children: [
          {
            path: "list",
            name: "boardList",
            component: () =>
              import("../views/Tables/RegularTables/QABoardTable.vue"),
          },
          {
            path: "write",
            name: "boardRegister",
            component: () =>
              import("../components/QABoard/QABoardRegister.vue"),
          },
          {
            path: "detail",
            name: "qaboardDetail",
            component: () => import("../components/QABoard/QABoardDetail.vue"),
          },
          {
            path: "modify",
            name: "boardModify",
            component: () => import("../components/QABoard/QABoardModify.vue"),
          },
        ],
      },
    ],
  },
  {
    path: "/",
    redirect: "login",
    component: AuthLayout,
    children: [
      {
        path: "/login",
        name: "login",
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/Pages/LoginPage.vue"),
      },
      {
        path: "/register",
        name: "register",
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/Pages/Register.vue"),
      },
      {
        path: "/passwordFind",
        name: "passwordFind",
        component: () => import("../views/Pages/PasswordFind.vue"),
      },
    ],
  },
];

export default routes;
