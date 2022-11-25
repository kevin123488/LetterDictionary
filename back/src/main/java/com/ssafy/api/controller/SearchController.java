package com.ssafy.api.controller;

import com.ssafy.api.response.SearchGetRes;
import com.ssafy.api.service.FoundationService;
import com.ssafy.api.service.PostcardService;
import com.ssafy.common.customObj.FoundationSearchList;
import com.ssafy.common.customObj.PostcardList;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 검색 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "검색 API", tags = {"Search"})
@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    PostcardService postcardService;

    @Autowired
    FoundationService foundationService;

    @GetMapping("")
    @ApiOperation(value = "통합 검색", notes = "searchWord가 포함된 태그를 가진 엽서리스트와 재단리스트를 불러온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> search(@ApiParam(value = "검색 단어") String searchWord) throws IOException {
        List<PostcardList> postcardLists = postcardService.selectPostcardSearchList(searchWord);
        List<FoundationSearchList> foundationSearchLists = foundationService.selectFoundationSearchList(searchWord);

        return ResponseEntity.status(200).body(SearchGetRes.of(postcardLists, foundationSearchLists));
    }
}
