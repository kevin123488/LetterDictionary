package com.ssafy.api.service;

import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.api.request.BoardUpdatePostReq;
import com.ssafy.db.entity.Board;

import com.ssafy.db.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *	게시판 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("BoardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
    BoardRepository boardRepository;

    @Override
    public Board createBoard(BoardRegisterPostReq boardRegisterInfo) {

        Board board = new Board();

        board.setBoardCode(boardRegisterInfo.getBoardCode());
        board.setUserSeq(boardRegisterInfo.getUserSeq());
        board.setBoardTitle(boardRegisterInfo.getBoardTitle());
        board.setBoardContent(boardRegisterInfo.getBoardContent());

        return boardRepository.save(board);
    }

    @Override
    public Board updateBoard(int boardSeq, BoardUpdatePostReq boardUpdateInfo) {

        Board board = getBoardByBoardSeq(boardSeq);

        board.setBoardTitle(boardUpdateInfo.getBoardTitle());
        board.setBoardContent(boardUpdateInfo.getBoardContent());
        boardRepository.save(board);

        return board;
    }

    @Override
    public Board getBoardByBoardSeq(int boardSeq) {

        Board board = boardRepository.findByBoardSeq(boardSeq);

        return board;
    }

    @Override
    public List<Board> getBoardByBoardCode(int boardCode) {

        List<Board> boards = boardRepository.findByBoardCode(boardCode);

        return boards;
    }

    @Override
    public void deleteBoard(int boardSeq) {
        boardRepository.delete(getBoardByBoardSeq(boardSeq));
    }

}
