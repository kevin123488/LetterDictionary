<template>
<div class="searchFoundation">
  <div>
    <!-- 재단 이미지 넣을거임 -->
    <img src="../../../public/images/foundation_dummy.jpg" style="height: 25vw; width: 35vw;" alt="">
  </div>
  <div class="searchFoundationName">
    {{ foundationItem.foundation_Name }}
  </div>
  <div class="searchFoundationContent">
    {{ foundationItem.foundation_Content }}
  </div>
</div>
</template>

<script>
export default {
  name: "SearchFoundation",
  props: {
    foundationItem: {
      type: Object,
    }
  },
}
</script>

<style>
.searchFoundation {
  position: relative;
  height: 40vw;
  width: 35vw;
  margin-bottom: 2vh;
  overflow: auto;
  border-radius: 20px;
  background-color: white;
}

.searchFoundation::-webkit-scrollbar {
  display: none;
}

.searchFoundationName {
  cursor: pointer;
  position: absolute;
  top: 65%;
  left: 0%;
  transform: translate(0%, -50%);
  height: 3vh;
  /* width: 5vw; */
  border: 1px solid black;
  overflow: auto;
}

.searchFoundationName::-webkit-scrollbar {
  display: none;
}

.searchFoundationContent {
  position: absolute;
  top: 70%;
  left: 0%;
  width: 35vw;
  height: 12vw;
  overflow: auto;
}

.searchFoundationContent::-webkit-scrollbar {
  display: none;
}
</style>