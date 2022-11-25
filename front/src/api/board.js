import { apiInstance } from "./index.js";
const api = apiInstance();

// 게시판 페이지
// 게시판 등록, 수정, 삭제, 상세조회, 리스트 조회

async function postBoard(board, success, fail) { // 게시판 등록
    await api.post(`/api/boards`, JSON.stringify(board)).then(success).catch(fail);
}

async function patchBoard(board, boardSeq, success, fail) { // 게시판 수정
    await api.patch(`/api/boards/${boardSeq}`, JSON.stringify(board)).then(success).catch(fail);
}

async function deleteBoard(boardSeq, success, fail) { // 게시판 삭제
    await api.delete(`/api/boards/${boardSeq}`).then(success).catch(fail);
}

async function detailBoard(boardSeq, success, fail) { // 게시판 상세조회
    await api.get(`/api/boards/${boardSeq}`).then(success).catch(fail);
}

async function listBoard(boardCode, success, fail) { // 게시판 리스트 조회(boardCode 201: 공지사항, 202: 문의사항)
    await api.get(`/api/boards/list/${boardCode}`).then(success).catch(fail);
}

export {
    postBoard,
    patchBoard,
    deleteBoard,
    detailBoard,
    listBoard,
};