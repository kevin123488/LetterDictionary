package com.ssafy.api.service;

import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.api.request.BoardUpdatePostReq;
import com.ssafy.db.entity.Board;

import java.util.List;

/**
 *	게시판 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface BoardService {

	Board createBoard(BoardRegisterPostReq boardRegisterInfo);
	Board updateBoard(int boardSeq, BoardUpdatePostReq boardUpdateInfo);

	Board getBoardByBoardSeq(int boardSeq);

	List<Board> getBoardByBoardCode(int boardCode);

	void deleteBoard(int boardSeq);

}
