package com.ssafy.api.controller;

import com.ssafy.api.service.FollowService;
import com.ssafy.db.entity.Follow;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 팔로우 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "팔로우 API", tags = {"Follow"})
@RestController
@RequestMapping("/api/follows")
public class FollowController {

    @Autowired
    FollowService followService;

    @PostMapping("/{myId}/{followId}")
    @ApiOperation(value = "팔로우 추가", notes = "myId가 FollowId를 팔로우 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "추가 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> insert(@PathVariable(value = "myId") @ApiParam(value = "내 아이디") String myId,
                                    @PathVariable(value = "followId") @ApiParam(value = "팔로우 아이디") String followId) throws IOException {
        String result = followService.saveFollow(myId, followId);
        if (result.equals("팔로우를 실패했습니다.")) {
            return ResponseEntity.status(401).body(result);
        }
        return ResponseEntity.status(200).body(result);
    }

    @DeleteMapping("/{myId}/{followId}")
    @ApiOperation(value = "팔로우 삭제", notes = "myId가 FollowId를 언팔로우 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "삭제 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> delete(@PathVariable(value = "myId") @ApiParam(value = "내 아이디") String myId,
                                    @PathVariable(value = "followId") @ApiParam(value = "팔로우 아이디") String followId) throws IOException {
        boolean result = followService.deleteFollow(myId, followId);
        if (result) {
            return ResponseEntity.status(200).body(myId + "가 " + followId + "를 언팔로우 했습니다.");
        } else {
            return ResponseEntity.status(401).body("팔로우 삭제에 실패했습니다.");
        }
    }

    @GetMapping("/{myId}")
    @ApiOperation(value = "팔로우 리스트 조회", notes = "myId의 팔로우 리스트를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "조회 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> selectList(@PathVariable(value = "myId") @ApiParam(value = "내 아이디") String myId) throws IOException {
        List<Follow> followList = followService.selectFollowList(myId);
        if (followList.size() == 0) {
            return ResponseEntity.status(401).body("팔로우한 사람이 없습니다.");
        } else {
            return ResponseEntity.status(200).body(followList);
        }
    }

}
