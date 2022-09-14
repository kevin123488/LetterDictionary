import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import mainpageStore from './modules/mainpageStore'

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    mainpageStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    })
  ],
})
