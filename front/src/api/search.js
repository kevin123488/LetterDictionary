import { apiInstance } from "./index.js";
const api = apiInstance();

// 검색부분 api docs 업데이트 되면 마저 작성
async function search(searchWord, success, fail) { // 검색
    console.log("검색어 뭐 들어가는지 확인해보자");
    console.log(JSON.stringify(searchWord));
    await api.get(`/api/search/`, { params: { searchWord: searchWord } }).then(success).catch(fail);
}

export {
    search,
}