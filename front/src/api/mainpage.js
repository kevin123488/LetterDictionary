import { apiInstance } from "./index.js";
const api = apiInstance();

// 메인페이지에서 필요한 요청 사항은?
// 인기 엽서 목록 대략 10개
// 인기 재단 목록 대략 10개
// 인기 엽서 클릭하면 팝업으로 해당 엽서를 좋아하는 인원과 작성자 등의 정보를 받아야 함

async function getFamousLetter(success, fail) { // 인기 엽서 목록
    await api.get(`/api/postcards/top-list`).then(success).catch(fail);
}

async function getFamousFoundation(success, fail) { // 인기 재단 목록 -> 그냥 재단 목록
    await api.get(`/api/foundations/list`).then(success).catch(fail);
}

async function likeLetter(postcardSeq, userSeq, success, fail) {
    await api.post(`/api/postcards/${postcardSeq}/${userSeq}`).then(success).catch(fail);
}

async function dislikeLetter(postcardSeq, userSeq, success, fail) {
    await api.delete(`/api/postcards/${postcardSeq}/${userSeq}`).then(success).catch(fail);
}

async function letterDetail(postcardSeq, success, fail) {
    await api.get(`/api/postcards/${postcardSeq}`).then(success).catch(fail);
}

async function foundationDetail(foundationSeq, success, fail) {
    await api.get(`/api/foundations/${foundationSeq}`).then(success).catch(fail);
}

export {
    getFamousLetter,
    getFamousFoundation,
    likeLetter,
    dislikeLetter,
    letterDetail,
    foundationDetail,
}