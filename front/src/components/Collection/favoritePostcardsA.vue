<template>
          
  <!-- TestMypageView의 좋아하는 엽서 목록 부분에 v-if  등으로 숨겨둘 것.
  사용자가 이걸 선택하면 보여주도록 로직 추가 필요 -->
  <!-- 내가보는거 -->
  <div v-if="this.isOwner" id="modalBox" data-aos="fade-up" data-aos-duration="500" class="favoritePics">
    <!-- <div class="glass">
    </div> -->
    

    
    <!-- 좌측 상단부 -->
    <div class="favoritePics1">
      <div class="favoritePics1_1">
        <img v-if="ownerInfo.donations[0]" @click="changeUserRemind(1)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[0].donationImgUrl" style="height: 22vh; width: 22vh; cursor: pointer;" alt="">
        <img v-else @click="changeUserRemind(1)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" style="height: 22vh; width: 22vh; cursor: pointer;" alt="">
      </div>
      <div class="favoritePics1_2">
        <img v-if="ownerInfo.donations[1]" @click="changeUserRemind(2)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[1].donationImgUrl" style="height: 22vh; width: 22vh; cursor: pointer;" alt="">
        <img v-else @click="changeUserRemind(2)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" style="height: 22vh; width: 22vh; cursor: pointer;" alt="">
      </div>
    </div>

    <!-- 중간측 상단부 -->
    <div class="favoritePics2">
      <img v-if="ownerInfo.donations[2]" @click="changeUserRemind(3)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[2].donationImgUrl" style="height: 27vh; width: 27vh; cursor: pointer;" alt="">
      <img v-else @click="changeUserRemind(3)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" style="height: 27vh; width: 27vh; cursor: pointer;" alt="">
      
    </div>

    <!-- 우측 -->
    <div class="favoritePics3">
      <div class="favoritePics3_1">
        <img v-if="ownerInfo.donations[3]" @click="changeUserRemind(4)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[3].donationImgUrl" style="height: 18vh; width: 18vh; cursor: pointer;" alt="">
        <img v-else @click="changeUserRemind(4)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" style="height: 18vh; width: 18vh; cursor: pointer;" alt="">
      
      </div>
      <div class="favoritePics3_2">
        <img v-if="ownerInfo.donations[4]" @click="changeUserRemind(5)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[4].donationImgUrl" style="height: 18vh; width: 18vh; cursor: pointer;" alt="">
        <img v-else @click="changeUserRemind(5)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" style="height: 18vh; width: 18vh; cursor: pointer;" alt="">
      
      </div>
      <div class="favoritePics3_3">
        <img v-if="ownerInfo.donations[5]" @click="changeUserRemind(6)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[5].donationImgUrl" style="height: 18vh; width: 18vh; cursor: pointer;" alt="">
        <img v-else @click="changeUserRemind(6)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" style="height: 18vh; width: 18vh; cursor: pointer;" alt="">
      
      </div>
      <div class="favoritePics3_4">
        <img v-if="ownerInfo.donations[6]" @click="changeUserRemind(7)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[6].donationImgUrl" style="height: 18vh; width: 18vh; cursor: pointer;" alt="">
        <img v-else @click="changeUserRemind(7)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" style="height: 18vh; width: 18vh; cursor: pointer;" alt="">
      
      </div>
    </div>

    <!-- 좌측 하단부 -->
    <div class="favoritePics4">
      <div class="favoritePics4_1">

        <img v-if="ownerInfo.donations[7]" @click="changeUserRemind(8)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[7].donationImgUrl" style="height: 22vh; width: 22vh; cursor: pointer;" alt="">
        <img v-else @click="changeUserRemind(8)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" style="height: 22vh; width: 22vh; cursor: pointer;" alt="">
      
      </div>
      <div class="favoritePics4_2">

        <img v-if="ownerInfo.donations[8]" @click="changeUserRemind(9)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[8].donationImgUrl" style="height: 22vh; width: 22vh; cursor: pointer;" alt="">
        <img v-else @click="changeUserRemind(9)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" style="height: 22vh; width: 22vh; cursor: pointer;" alt="">
      
      </div>
      <div class="favoritePics4_3">

        <img v-if="ownerInfo.donations[9]" @click="changeUserRemind(10)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[9].donationImgUrl" style="height: 22vh; width: 22vh; cursor: pointer;" alt="">
        <img v-else @click="changeUserRemind(10)" class="favoriteImg" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" style="height: 22vh; width: 22vh; cursor: pointer;" alt="">
      
      </div>
    </div>
  </div>



  <!-- 다른사람이 보는거 -->
  <div v-else id="modalBox" data-aos="fade-up" data-aos-duration="500" class="favoritePics">
    <!-- <div class="glass">
    </div> -->
    <!-- 좌측 상단부 -->
    <div class="favoritePics1">
      <div class="favoritePics1_1">
        <img v-if="ownerInfo.donations[0]" class="favoriteImg" :src="imgURL" style="height: 22vh; width: 22vh;" alt="">
      </div>
      <div class="favoritePics1_2">
        <img v-if="ownerInfo.donations[1]" class="favoriteImg" :src="imgURL" style="height: 22vh; width: 22vh;" alt="">
      </div>
    </div>

    <!-- 중간측 상단부 -->
    <div class="favoritePics2">
      <img v-if="ownerInfo.donations[2]" class="favoriteImg" :src="imgURL" style="height: 27vh; width: 27vh;" alt="">
    </div>

    <!-- 우측 -->
    <div class="favoritePics3">
      <div class="favoritePics3_1">
        <img v-if="ownerInfo.donations[3]" class="favoriteImg" :src="imgURL" style="height: 18vh; width: 18vh;" alt="">
      </div>
      <div class="favoritePics3_2">
        <img v-if="ownerInfo.donations[4]" class="favoriteImg" :src="imgURL" style="height: 18vh; width: 18vh;" alt="">
      </div>
      <div class="favoritePics3_3">
        <img v-if="ownerInfo.donations[5]" class="favoriteImg" :src="imgURL" style="height: 18vh; width: 18vh;" alt="">
      </div>
      <div class="favoritePics3_4">
        <img v-if="ownerInfo.donations[6]" class="favoriteImg" :src="imgURL" style="height: 18vh; width: 18vh;" alt="">
      </div>
    </div>

    <!-- 좌측 하단부 -->
    <div class="favoritePics4">
      <div class="favoritePics4_1">
        <img v-if="ownerInfo.donations[7]" class="favoriteImg" :src="imgURL" style="height: 22vh; width: 22vh;" alt="">
      </div>
      <div class="favoritePics4_2">
        <img v-if="ownerInfo.donations[8]" class="favoriteImg" :src="imgURL" style="height: 22vh; width: 22vh;" alt="">
      </div>
      <div class="favoritePics4_3">
        <img v-if="ownerInfo.donations[9]" class="favoriteImg" :src="imgURL" style="height: 22vh; width: 22vh;" alt="">
      </div>
    </div>
  </div>

</template>

<script>
import { mapActions, mapGetters } from "vuex";
const mypageStore = "mypageStore";
const accountStore = "accountStore";
const postcardStore = "postcardStore";

export default {
  name: "favoritePostcardsA",
  data() {
    return {
      imgURL: require("../../../public/images/test1.jpg"),
      isOwner: 0,
      showTemp: false,
      changeSeq: 0,
      changeDonationSeq: 0,
      defaultImg:{ imageUrl: require("../../../public/images/addImg.png") },
    }
  },

  computed: {
    ...mapGetters(mypageStore, [
      "donationList",
      "ownerInfo",
    ]),
    ...mapGetters(accountStore, [
      "userInfo",
    ]),
    ...mapGetters(postcardStore, [
      "postcardList",
      "userLikedPostcard",
      "likedPostcards",
    ]),
  },

  methods: {
    // data의 imgURL을 변경해주는 로직이 필요
    // 현 시점에선 사용자가 좋아하는 엽서 목록을 받지 못해
    // 해당하는 imgURL을 세팅할 수 없다
    ...mapActions(mypageStore, [
      "userSecession",
      "updateUserInfo",
      "getDonationList",
      "changeTemplate",
      "changeRemind",
      "changeUserRemind",
    ]),
  },

  created() {
    if (this.ownerInfo.userSeq === this.userInfo.userSeq) {
      this.isOwner = true
    }
  },
}
</script>

<style>
.glass {
  content: "";
  position: fixed;
  width: 100vw;
  height: 100vh;
  left: 50%; 
  transform: translate(-50%, 0);
  /* background: inherit; 배경 속성을 상속 받음 */
  /* top: -25px; 반투명 효과 영역을 25px 왼쪽으로 이동 */
  /* left: -25px; 반투명효과 영역을 25px 위로 이동 */
  box-shadow: inset 0 0 0 1000px rgba(255,255,255,0.5); /* 반투명 흰색 그림자 효과를 영역 안쪽에 아주 크게 생성 */
  filter: blur(15px); /* 블러 효과로 배경 이미지를 흐리게 만듬 */
}

/* 좋아하는 엽서 모음 전체 */
.favoritePics {
  position: relative;
  height: 50vw;
  width: 70vw;
  background-image: url("../../../public/images/deskBack.png");
  background-repeat: no-repeat;
  background-size: cover;
  border-radius: 10px;
  transition: 0.4s;
}
.favoritePics:hover {
  /* transform: rotate(360deg); */
  width: 75vw;
  transition: 0.4s;
}

/* 좋아하는 엽서 좌측 상단부 */
.favoritePics1 {

}
.favoritePics1_1 {
  position: absolute;
  z-index: 0;
  height: 25vh;
  width: 25vh;
  line-height: 25vh;
  vertical-align: middle;
  background-color: white;
  box-shadow: 0 1vh 2vh rgba(0, 0, 0, 0.15);
  top: 12.5%;
  left: 0%;
  /* transform: translate(-50%, -50%); */
  transform: rotate(-6deg);
  transition: 0.4s;
}
.favoritePics1_1:hover {
  z-index: 10;
  transform: rotate(0deg);
}
.favoritePics1_2 {
  position: absolute;
  z-index: 1;
  height: 25vh;
  width: 25vh;
  line-height: 25vh;
  vertical-align: middle;
  background-color: white;
  box-shadow: 0 1vh 2vh rgba(0, 0, 0, 0.15);
  top: 12.5%;
  left: 7.5%;
  /* transform: translate(-50%, -50%); */
  transform: rotate(6deg);
  transition: 0.4s;
}
.favoritePics1_2:hover {
  transform: rotate(0deg);
}

/* 좋아하는 엽서 중반측 상단부 */
.favoritePics2 {
  position: absolute;
  height: 30vh;
  width: 30vh;
  line-height: 30vh;
  vertical-align: middle;
  background-color: white;
  box-shadow: 0 1vh 2vh rgba(0, 0, 0, 0.15);
  top: 15%;
  left: 54%;
  /* transform: translate(-50%, -50%); */
  transform: rotate(-4deg);
  transition: 0.4s;
}
.favoritePics2:hover {
  transform: rotate(0deg);
}

/* 좋아하는 엽서 우측 */
.favoritePics3 {
  
}
.favoritePics3_1 {
  position: absolute;
  z-index: 3;
  height: 20vh;
  width: 20vh;
  line-height: 20vh;
  vertical-align: middle;
  background-color: white;
  box-shadow: 0 1vh 2vh rgba(0, 0, 0, 0.15);
  top: 15%;
  left: 80%;
  /* transform: translate(-50%, -50%); */
  transform: rotate(4deg);
  transition: 0.4s;
}
.favoritePics3_1:hover {
  transform: rotate(0deg);
}
.favoritePics3_2 {
  position: absolute;
  z-index: 2;
  height: 20vh;
  width: 20vh;
  line-height: 20vh;
  vertical-align: middle;
  background-color: white;
  box-shadow: 0 1vh 2vh rgba(0, 0, 0, 0.15);
  top: 35%;
  left: 80%;
  /* transform: translate(-50%, -50%); */
  transform: rotate(-4deg);
  transition: 0.4s;
}
.favoritePics3_2:hover {
  z-index: 10;
  transform: rotate(0deg);
}
.favoritePics3_3 {
  position: absolute;
  z-index: 1;
  height: 20vh;
  width: 20vh;
  line-height: 20vh;
  vertical-align: middle;
  background-color: white;
  box-shadow: 0 1vh 2vh rgba(0, 0, 0, 0.15);
  top: 55%;
  left: 80%;
  /* transform: translate(-50%, -50%); */
  transform: rotate(4deg);
  transition: 0.4s;
}
.favoritePics3_3:hover {
  z-index: 10;
  transform: rotate(0deg);
}
.favoritePics3_4 {
  position: absolute;
  z-index: 0;
  height: 20vh;
  width: 20vh;
  line-height: 20vh;
  vertical-align: middle;
  background-color: white;
  box-shadow: 0 1vh 2vh rgba(0, 0, 0, 0.15);
  top: 75%;
  left: 80%;
  /* transform: translate(-50%, -50%); */
  transform: rotate(-4deg);
  transition: 0.4s;
}
.favoritePics3_4:hover {
  z-index: 10;
  transform: rotate(0deg);
}

/* 좋아하는 엽서 좌측 하단부 */
.favoritePics4 {
  
}
.favoritePics4_1 {
  position: absolute;
  z-index: 0;
  height: 25vh;
  width: 25vh;
  line-height: 25vh;
  vertical-align: middle;
  background-color: white;
  box-shadow: 0 1vh 2vh rgba(0, 0, 0, 0.15);
  top: 62.5%;
  left: 7.5%;
  /* transform: translate(-50%, -50%); */
  transform: rotate(-4deg);
  transition: 0.4s;
}
.favoritePics4_1:hover {
  z-index: 10;
  transform: rotate(0deg);
}
.favoritePics4_2 {
  position: absolute;
  z-index: 1;
  height: 25vh;
  width: 25vh;
  line-height: 25vh;
  vertical-align: middle;
  background-color: white;
  box-shadow: 0 1vh 2vh rgba(0, 0, 0, 0.15);
  top: 62.5%;
  left: 17.5%;
  /* transform: translate(-50%, -50%); */
  transition: 0.4s;
}
.favoritePics4_2:hover {
  z-index: 10;
  transform: rotate(0deg);
}
.favoritePics4_3 {
  position: absolute;
  z-index: 2;
  height: 25vh;
  width: 25vh;
  line-height: 25vh;
  vertical-align: middle;
  background-color: white;
  box-shadow: 0 1vh 2vh rgba(0, 0, 0, 0.15);
  top: 62.5%;
  left: 27.5%;
  /* transform: translate(-50%, -50%); */
  transform: rotate(4deg);
  transition: 0.4s;
}
.favoritePics4_3:hover {
  z-index: 10;
  transform: rotate(0deg);
}

/* 이미지 정렬 */
.favoriteImg {
}
</style>