<template>
<side-bar></side-bar>
<div class="boardClass">
  <div class="boardTitle">문의사항</div>
  <div class="boardGoQuestion" @click="goQuestion">문의하기</div>
  <div class="boardGoHome" @click="goHome"></div>
  <div class="boardContents">
    <div class="boardItems" @click="goDetail(board.boardSeq)" v-for="(board, index) in questionList" :key="index">
      <!-- 작성자: {{ board.userId }}
      <br>
      제목: {{ board.boardTitle }}
      <br>
      내용: {{ board.boardContent }} -->
      <div class="boardItem">
        <div class="boardUserId">작성자: {{ board.userId }}</div>
        <div class="boardItemTitle">{{ board.boardTitle }}</div>
        <!-- <div></div> -->
      </div>
      <hr>
    </div>
  </div>
</div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import SideBar from "@/components/Nav/SideBar.vue"
import { postBoard, patchBoard, deleteBoard } from "@/api/board.js";
const boardStore = "boardStore";

export default {
  name: "BoardView",
  components: {
    SideBar,
  },
  data() {
    return {
      questionListView: [],
      noticeListView: [],
    }
  },
  computed: {
    ...mapState(boardStore, ["watchingBoard", "noticeList", "questionList"]),
  },
  methods: {
    ...mapActions(boardStore, ["detailBoardStore", "listBoardStore"]),
    async doPostBoard() {
      await postBoard();
    },
    async doPatchBoard() {
      await patchBoard();
    },
    async doDeleteBoard() {
      await deleteBoard();
    },
    goQuestion() {
      this.$router.push("/question");
    },
    async goDetail(boardSeq) {
      await this.detailBoardStore(boardSeq);
      this.$router.push("/questionDetail");
    },
    goHome() {
      this.$router.push("/main");
    }
  },
  async created() {
    await this.listBoardStore(201); // 공지사항 세팅
    await this.listBoardStore(202); // 문의사항 세팅
    this.questionListView = this.questionList;
    this.noticeListView = this.noticeList;
  }
}
</script>

<style>
.boardClass {
  position: absolute;
  left: 47%;
  transform: translate(-50%, 0%);
  width: 100vw;
  height: 100vh;
  background-color: #fcf4e0;
}

.boardTitle {
  position: absolute;
  top: 7%;
  left: 50%;
  transform: translate(-50%, -50%);
  height: 5vh;
  width: 10vw;
  font-size: 3vw;
}

.boardGoQuestion {
  cursor: pointer;
  position: absolute;
  top: 13%;
  left: 90%;
  transform: translate(-50%, -50%);
  height: 5vh;
  width: 10vw;
  font-size: 1.5vw;
}

.boardGoHome {
  cursor: pointer;
  position: absolute;
  top: 90%;
  left: 50%;
  transform: translate(-50%, -50%);
  height: 5vh;
  width: 5vh;
  background-image: url("../../public/images/homeicon.png");
  background-size: 5vh 5vh;
}

.boardContents {
  cursor: pointer;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  height: 70vh;
  width: 84vw;
  border-radius: 30px;
  background-color: white;
  overflow: auto;
  padding: 2vh 2vh 2vh 2vh;
}

.boardContents::-webkit-scrollbar {
  display: none;
}

.boardItems {
}

.boardItem {
  position: relative;
  display: flex;
  justify-content: space-between;
  border-radius: 10px;
  height: 10vh;
  font-size: 5vh;
  line-height: 10vh;
  vertical-align: middle;
}

.boardItem:hover {
  background-color: aliceblue;
}

.boardItemTitle {
  position: absolute;
  left: 50%;
  height: 10vh;
  overflow: auto;
}

.boardItemTitle::-webkit-scrollbar {
  display: none;
}
</style>