package com.ssafy.api.controller;

import com.ssafy.api.response.FoundationRes;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.FoundationService;
import com.ssafy.db.entity.Foundation;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "재단 API", tags = {"Foundation"})
@RestController
@RequestMapping("/api/foundations")
public class FoundationController {

    @Autowired
    FoundationService foundationService;

    @GetMapping("/{foundationSeq}")
    @ApiOperation(value = "재단 정보 조회", notes = "재단의 정보를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<FoundationRes> getFoundationInfo(
            @PathVariable("foundationSeq") @ApiParam(value="재단번호", required = true) int foundationSeq) {
        /**
         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
         */
        Foundation foundation = foundationService.selectFoundation(foundationSeq);

        return ResponseEntity.status(200).body(FoundationRes.of(foundation));
    }

    @GetMapping("/list")
    @ApiOperation(value = "재단 리스트 조회", notes = "재단의 리스트를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<FoundationRes>> getFoundationInfo( ){
        /**
         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
         */

        List<FoundationRes> res = new ArrayList<>();
        List<Foundation> list = foundationService.selectFoundationList();

        for (Foundation foundation: list) {
            FoundationRes foundationRes = FoundationRes.of(foundation);
            res.add(foundationRes);
        }

        return ResponseEntity.status(200).body(res);
    }
}
