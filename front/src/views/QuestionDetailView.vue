<template>
<side-bar></side-bar>
<div class="boardQuestionClass">
  <div class="boardQuestionInside">
    <h2>{{ watchingDetail.data.boardTitle }}</h2>
    <div>
      <div class="boardQuesDetailWriter">
        작성자 ({{ watchingDetail.data.userId }})
        <hr>
      </div>
      <div class="boardQuesdetailContent">
        {{ watchingDetail.data.boardContent }}
      </div>
    </div>
    <div class="boardDetailBtns">
      <div v-if="itsMe" @click="goPatchBoard" style="cursor: pointer;">수정하기</div>
      <div v-if="itsMe" @click="doDeleteBoard(watchingDetail.data.boardSeq)" style="cursor: pointer;">삭제하기</div>
      <div @click="goBoard" style="cursor: pointer;">문의사항 목록으로</div>
    </div>
  </div>
</div>
</template>

<script>
import { mapState } from "vuex";
import SideBar from "@/components/Nav/SideBar.vue";
import { deleteBoard } from "@/api/board.js";
const boardStore = "boardStore";
const accountStore = "accountStore";

export default {
  name: "QuestionDetailView",
  components: {
    SideBar,
  },
  data() {
    return {
      watchingDetail: [],
      itsMe: false,
    }
  },
  computed: {
    ...mapState(boardStore, ["watchingBoard"]),
    ...mapState(accountStore, ["userInfo"]),
  },
  methods: {
    goBoard() {
      this.$router.push("/board");
    },
    goPatchBoard() {
      this.$router.push('/patchBoard');
    },
    async doDeleteBoard(boardSeq) {
      await deleteBoard(boardSeq);
      this.$router.push("/board");
    },
  },
  created() {
    // 생성되면 조회한 게시글의 상세 정보를 보여줘야 함
    this.watchingDetail = this.watchingBoard;
    if (this.watchingDetail.data.userId === this.userInfo.userId) {
      this.itsMe = true;
    } else {
      this.itsMe = false;
    }
  },
}
</script>

<style>
.boardQuesDetailWriter {
  position: absolute;
  top: 10%;
  left: 0%;
  height: 5vh;
  width: 80vw;
  text-align: left;
  font-size: 1.5vw;
}
.boardQuesdetailContent {
  position: absolute;
  top: 20%;
  left: 0%;
  font-size: 2vw;
  height: 55vh;
  width: 80vw;
  text-align: left;
  box-shadow: 0 1vh 2vh rgba(0, 0, 0, 0.15);
  overflow: auto;
}
.boardQuesdetailContent::-webkit-scrollbar {
  display: none;
}
.boardDetailBtns {
  position: absolute;
  top: 100%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>