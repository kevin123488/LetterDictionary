<template>
<side-bar></side-bar>
<div class="boardQuestionClass">
  <div class="boardQuestionInside">
    <h2>게시글 수정 페이지</h2>
    <div class="boardQuesWrap">
      <div class="boardQuesTitle">
        <input v-model="board.boardTitle" class="questionTitle" type="text" style="border:0 solid black" :placeholder="watchingDetail.data.boardTitle">
      </div>
      <div class="boardQuesContent">
        <textarea v-model="board.boardContent" class="questionContent" type="text" style="border:0 solid black" :placeholder="watchingDetail.data.boardContent"></textarea>
      </div>
    </div>
    <div class="boardpostBtn" @click="doPatchBoard(watchingDetail.data.boardSeq)">수정</div>
    <div class="boardGoBoardmain" @click="goBoard">목록으로</div>
  </div>
</div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import SideBar from "@/components/Nav/SideBar.vue";
import { patchBoard } from "@/api/board.js";
const boardStore = "boardStore";

export default {
  name: "PatchBoardView",
  components: {
    SideBar,
  },
  data() {
    return {
      board: {
        boardContent: "",
        boardTitle: "",
      },
      watchingDetail: [],
    }
  },
  computed: {
    ...mapState(boardStore, ["watchingBoard"]),
  },
  methods: {
    ...mapActions(boardStore, ["detailBoardStore"]),
    async doPatchBoard(boardSeq) {
      await patchBoard(this.board, boardSeq);
      await this.detailBoardStore(boardSeq);
      this.$router.push("/questionDetail");
    },
    goBoard() {
      this.$router.push("/board");
    }
  },
  created() {
    this.watchingDetail = this.watchingBoard;
    this.board.boardTitle = this.watchingDetail.data.boardTitle;
    this.board.boardContent = this.watchingDetail.data.boardContent;
  },
}
</script>

<style>

</style>