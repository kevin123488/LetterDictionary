import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";
import mainpageStore from "./modules/mainpageStore";
import searchStore from "./modules/searchStore";
import accountStore from "./modules/accountStore";
import mypageStore from "./modules/mypageStore";
import organizationStore from "./modules/organizationStore";
import donationStore from "./modules/donationStore";
import boardStore from "./modules/boardStore";
import postcardStore from "./modules/postcardStore";

export default createStore({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    mainpageStore,
    searchStore,
    accountStore,
    organizationStore,
    donationStore,
    mypageStore,
    boardStore,
    postcardStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});
