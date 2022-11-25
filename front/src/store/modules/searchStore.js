import { search } from "@/api/search.js";
import _ from "lodash";

const searchStore = {
  namespaced: true,
  state: {
    letterSearchResult: [],
    searchedWord: "",
  },
  getters: {
    letterSearchResult: (state) => {
      return state.letterSearchResult;
    },
    isSearchResult: (state) => {
      return !_.isEmpty(state.letterSearchResult);
    },
    searchedWord: (state) => {
      return state.searchedWord;
    },
  },
  mutations: {
    SET_SEARCHRESULT: (state, searchResult) => {
      state.letterSearchResult = searchResult;
    },
    SET_SEARCHEDWORD: (state, word) => {
      state.searchedWord = word;
    },
    SET_STATE: (state) => {
      const initialState = {
        letterSearchResult: [],
        searchedWord: "",
      };
      Object.assign(state, initialState);
    },
  },
  actions: {
    async getSearchResult({ commit }, searchWord) {
      await search(
        searchWord,
        (response) => {
          commit("SET_SEARCHRESULT", response.data.postcardLists);
          commit("SET_SEARCHEDWORD", response.config.params.searchWord);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    // state 리셋
    searchStoreReset({ commit }) {
      commit("SET_STATE");
    },
  },
};

export default searchStore;
