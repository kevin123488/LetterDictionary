package com.ssafy.api.controller;


import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.api.request.BoardUpdatePostReq;
import com.ssafy.api.response.BoardRes;
import com.ssafy.api.service.BoardService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 게시판 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "게시판 API", tags = {"Board"})
@RestController
@RequestMapping("/api/boards")
public class BoardController {
	
	@Autowired
	BoardService boardService;

	@Autowired
	UserService userService;

	@PostMapping()
	@ApiOperation(value = "게시판 등록", notes = "<strong>회원이 게시판을 작성하면</strong>게시판 정보를 등록 한다.")
    @ApiResponses({
        @ApiResponse(code = 201, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> board_register(@RequestBody @ApiParam(value="게시판 정보", required = true) BoardRegisterPostReq registerInfo) {

		Board board = boardService.createBoard(registerInfo);

		return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
	}


	@GetMapping("/{boardSeq}")
	@ApiOperation(value = "게시판 상세조회", notes = "<strong>boardSeq</strong>를 통해 게시판 상세정보를 검색한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 409, message = "이미 존재하는 사용자"),
			@ApiResponse(code = 500, message = "서버 오류")
	})

	public ResponseEntity<BoardRes> boardInfo(@PathVariable("boardSeq") @ApiParam(value="조회할 boardSeq", required = true) int boardSeq) {

		Board board = boardService.getBoardByBoardSeq(boardSeq);

		User user = userService.selectUser(board.getUserSeq());
		String userId = user.getUserId();

		return ResponseEntity.status(200).body(BoardRes.of(board, userId));
	}


	@GetMapping("/list/{boardCode}")
	@ApiOperation(value = "게시판 목록 조회(201:공지사항, 202:문의사항)", notes = "<strong>boardCode</strong>를 통해 게시판 목록을 검색한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 409, message = "이미 존재하는 사용자"),
			@ApiResponse(code = 500, message = "서버 오류")
	})

	public ResponseEntity<List<BoardRes>> boardInfoListByBoardCode(@PathVariable("boardCode") @ApiParam(value="조회할 boardCode", required = true) int boardCode) {

		List<Board> boards = boardService.getBoardByBoardCode(boardCode);
		List<BoardRes> res = new ArrayList<BoardRes>();

		for(Board board : boards){

			User user = userService.selectUser(board.getUserSeq());
			String userId = user.getUserId();

			BoardRes boardRes = BoardRes.of(board,userId);
			res.add(boardRes);
		}

		return ResponseEntity.status(200).body(res);
	}

	@PatchMapping("/{boardSeq}")
	@ApiOperation(value = "게시판 수정", notes = "<strong>boardSeq</strong>를 통해 게시판을 수정 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> board_update(
			@PathVariable("boardSeq") @ApiParam(value="수정할 boardSeq") int boardSeq,
			@RequestBody @ApiParam(value="수정할 정보", required = true) BoardUpdatePostReq updateInfo) {

		Board board = boardService.updateBoard(boardSeq, updateInfo);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}


	@DeleteMapping("/{boardSeq}")
	@ApiOperation(value = "게시판 삭제", notes = "<strong>boardSeq </strong>을 통해 게시판을 삭제한다.")
	@ApiResponses({
			@ApiResponse(code = 204, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> board_delete(@PathVariable("boardSeq") @ApiParam(value="boardSeq", required = true) int boardSeq) {

		boardService.deleteBoard(boardSeq);

		return ResponseEntity.status(204).body(BaseResponseBody.of(204, "Success"));
	}

}
