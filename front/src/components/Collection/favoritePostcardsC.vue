<template>



    <!-- 포스트카드 나오는 부분 -->
    <!-- 본인 창이면 -->
    <div v-if="this.isOwner" class="d-flex justify-content-center" data-aos="fade-up" data-aos-duration="500">
      <div class="glass">
      </div>
  <!-- 벽지 -->
  <div class="remindBackground d-flex justify-content-center">
    
    <!-- 유저가 정한 이미지 6개 -->


    <img v-if="ownerInfo.donations[0]" class="postcard postcard1" style="cursor: pointer;" @click="changeUserRemind(1)" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[0].donationImgUrl" alt="테스트" />
    <img v-else class="postcard postcard1" style="cursor: pointer;" @click="changeUserRemind(1)" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" alt="테스트" />
    
    <img v-if="ownerInfo.donations[1]" class="postcard postcard2" style="cursor: pointer;" @click="changeUserRemind(2)" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[1].donationImgUrl" alt="테스트" />
    <img v-else class="postcard postcard2" style="cursor: pointer;" @click="changeUserRemind(2)" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" alt="테스트" />
    
    <img v-if="ownerInfo.donations[2]" class="postcard postcard3" style="cursor: pointer;" @click="changeUserRemind(3)" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[2].donationImgUrl" alt="테스트" />
    <img v-else class="postcard postcard3" style="cursor: pointer;" @click="changeUserRemind(3)" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" alt="테스트" />
    
    <img v-if="ownerInfo.donations[3]" class="postcard postcard4" style="cursor: pointer;" @click="changeUserRemind(4)" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[3].donationImgUrl" alt="테스트" />
    <img v-else class="postcard postcard4" style="cursor: pointer;" @click="changeUserRemind(4)" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" alt="테스트" />
    
    <img v-if="ownerInfo.donations[4]" class="postcard postcard5" style="cursor: pointer;" @click="changeUserRemind(5)" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[4].donationImgUrl" alt="테스트" />
    <img v-else class="postcard postcard5" style="cursor: pointer;" @click="changeUserRemind(5)" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" alt="테스트" />
    
    <img v-if="ownerInfo.donations[5]" class="postcard postcard6" style="cursor: pointer;" @click="changeUserRemind(6)" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="ownerInfo.donations[5].donationImgUrl" alt="테스트" />
    <img v-else class="postcard postcard6" style="cursor: pointer;" @click="changeUserRemind(6)" data-bs-toggle="modal" data-bs-target="#choiceModal" :src="defaultImg.imageUrl" alt="테스트" />
    
    <!-- 폴라로이드 이미지 -->
    <div class="polaroids">          
    </div>
  
  </div>
</div>


<!-- 다른사람 페이지면 -->
<div v-else class="d-flex justify-content-center" style="position: fixed; top: 3vh; left: 50%; transform: translate(-50%, 0);" data-aos="fade-up" data-aos-duration="500" >
  <!-- 벽지 -->
  <div class="glass">
      </div>
  <div class="remindBackground d-flex justify-content-center">
    


    <img v-if="ownerInfo.donations[0]" class="postcard postcard1" :src="showImages[0].imageUrl" alt="테스트" />
    <img v-if="ownerInfo.donations[1]" class="postcard postcard2" :src="showImages[1].imageUrl" alt="테스트" />
    <img v-if="ownerInfo.donations[2]" class="postcard postcard3" :src="showImages[2].imageUrl" alt="테스트" />
    <img v-if="ownerInfo.donations[3]" class="postcard postcard4" :src="showImages[3].imageUrl" alt="테스트" />
    <img v-if="ownerInfo.donations[4]" class="postcard postcard5" :src="showImages[4].imageUrl" alt="테스트" />
    <img v-if="ownerInfo.donations[5]" class="postcard postcard6" :src="showImages[5].imageUrl" alt="테스트" />
    
    <!-- 폴라로이드 이미지 -->
    <div class="polaroids">          
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
    name: "favoritePostcardC",
    data() {
    return {
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

:root {
  --remind-bg-height: 90vh;
  --remind-bg-width: 105vh;
}

.remindButton {
  font-size: 3vw;
  font-family: 'Nanum Pen Script', cursive;;
  cursor : pointer;
}
.remindBackground {
  position: relative;
  height: calc(var(--remind-bg-height));
  width: calc(var(--remind-bg-width));
  background-image: url(../../../public/images/remind_wallpaper.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  border-radius: 10px;
}
.polaroids {
  height: calc(var(--remind-bg-height));
  width: calc(var(--remind-bg-width));
  background-image: url(../../../public/images/remind_polaroids.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  z-index: 99;
}
.postcard1 {
  position: absolute;
  height: 21vh;
  width: 22.2vh;
  top: 15vh;
  left: 8vh;
  transform:rotate(calc(6deg));
  background-repeat: no-repeat;
  background-size: 100% 100%;
  z-index: 100;
  overflow: hidden;
  transition: all 0.1s linear;
  border-radius: 5px;
}
.postcard2 {
  position: absolute;
  height: 21vh;
  width: 22.2vh;
  top: 15vh;
  left: 45vh;
  transform:rotate(calc(3deg));
  background-repeat: no-repeat;
  background-size: 100% 100%;
  z-index: 100;
  overflow: hidden;
  transition: all 0.1s linear;
  border-radius: 5px;
}
.postcard3 {
  position: absolute;
  height: 21vh;
  width: 22.2vh;
  top: 12vh;
  left: 78.3vh;
  transform:rotate(calc(9deg));
  background-repeat: no-repeat;
  background-size: 100% 100%;
  z-index: 100;
  overflow: hidden;
  transition: all 0.1s linear;
  border-radius: 5px;
}
.postcard4 {
  position: absolute;
  height: 21vh;
  width: 22.2vh;
  top: 60vh;
  left: 8vh;
  transform:rotate(calc(9deg));
  background-repeat: no-repeat;
  background-size: 100% 100%;
  z-index: 100;
  overflow: hidden;
  transition: all 0.1s linear;
  border-radius: 5px;
}
.postcard5 {
  position: absolute;
  height: 21vh;
  width: 22.2vh;
  top: 61vh;
  left: 41.5vh;
  transform:rotate(calc(-9.5deg));
  background-repeat: no-repeat;
  background-size: 100% 100%;
  z-index: 100;
  overflow: hidden;
  transition: all 0.1s linear;
  border-radius: 5px;
}
.postcard6 {
  position: absolute;
  height: 21vh;
  width: 22.2vh;
  top: 58vh;
  left: 75.5vh;
  transform:rotate(calc(-9.5deg));
  background-repeat: no-repeat;
  background-size: 100% 100%;
  z-index: 100;
  overflow: hidden;
  transition: all 0.1s linear;
  border-radius: 5px;
}
.postcard:hover {
  transform: scale(1.1);
}

.postcardBackground {
  position: relative;
  height: calc(var(--remind-bg-height));
  width: calc(var(--remind-bg-width));
  background-image: url(../../../public/images/test1.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  border-radius: 10px;
}
</style>