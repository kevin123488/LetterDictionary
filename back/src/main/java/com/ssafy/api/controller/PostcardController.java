package com.ssafy.api.controller;

import com.ssafy.api.response.PostcardGetRes;
import com.ssafy.api.response.PostcardListGetRes;
import com.ssafy.api.service.PostcardService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.customObj.PostcardList;
import com.ssafy.db.entity.Postcard;
import com.ssafy.db.entity.Tag;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 엽서 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "엽서 API", tags = {"Postcard"})
@RestController
@RequestMapping("/api/postcards")
public class PostcardController {

    @Autowired
    PostcardService postcardService;

    @Autowired
    UserService userService;

    @PostMapping("/{userId}")
    @ApiOperation(value = "엽서 업로드", notes = "엽서 파일로 엽서를 등록한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "업로드 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> insert(@RequestPart @ApiParam(value = "엽서 사진 파일", required = true) MultipartFile postcard,
                                    @PathVariable @ApiParam(value = "유저 아이디", required = true) String userId) throws IOException {

        Postcard result = postcardService.savePostcard(postcard, userId);
        if(result == null){
            return ResponseEntity.status(401).body("업로드를 실패했습니다.");
        } else {
            return ResponseEntity.status(200).body(result);
        }

    }

    @PostMapping("/tag/{postcardSeq}")
    @ApiOperation(value = "엽서 태그 업로드", notes = "엽서에 태그를 등록한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "업로드 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> insertTag(@RequestBody @ApiParam(value = "태그 리스트") List<String> tag,
                                       @PathVariable @ApiParam(value = "엽서 seq") int postcardSeq) throws IOException {

        String result = postcardService.savePostcardTag(tag, postcardSeq);
        if(result.equals("DB에 저장을 실패했습니다.")){
            return ResponseEntity.status(401).body(result);
        } else {
            return ResponseEntity.status(200).body("엽서 업로드를 성공했습니다.");
        }

    }

    @ApiOperation(value = "엽서 삭제", notes = "엽서 seq로 엽서 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "삭제 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @DeleteMapping("/{postcardSeq}")
    public ResponseEntity<?> delete(@PathVariable @ApiParam("삭제할 엽서 seq") int postcardSeq) throws IOException {

        try {
            postcardService.deletePostcard(postcardSeq);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(401).body("삭제에 실패했습니다.");
        }

        return ResponseEntity.status(200).body(postcardSeq + "번째 엽서를 삭제했습니다.");
    }

    @ApiOperation(value = "엽서 좋아요 추가", notes = "엽서seq와 유저seq로 좋아요 추가")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "좋아요 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PostMapping("/{postcardSeq}/{userSeq}")
    public ResponseEntity<?> insertLike(@PathVariable(value = "postcardSeq") @ApiParam("엽서seq") int postcardSeq,
                                        @PathVariable(value = "userSeq") @ApiParam("유저Seq") int userSeq) throws IOException {
        boolean result = postcardService.savePostcardLike(postcardSeq, userSeq);
        if(!result) {
            return ResponseEntity.status(401).body("좋아요를 할 수 없습니다.");
        } else {
            return ResponseEntity.status(200).body(userSeq + "번째 유저가 " + postcardSeq + "번째 엽서를 좋아요 눌렀습니다.");
        }
    }

    @ApiOperation(value = "엽서 좋아요 삭제", notes = "엽서seq와 유저seq로 좋아요 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "좋아요 삭제 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @DeleteMapping("/{postcardSeq}/{userSeq}")
    public ResponseEntity<?> deleteLike(@PathVariable(value = "postcardSeq") @ApiParam("엽서seq") int postcardSeq,
                                        @PathVariable(value = "userSeq") @ApiParam("유저Seq") int userSeq) throws IOException {
        boolean result = postcardService.deletePostcardLike(postcardSeq, userSeq);
        if(!result) {
            return ResponseEntity.status(401).body("좋아요 삭제를 할 수 없습니다.");
        } else {
            return ResponseEntity.status(200).body(userSeq + "번째 유저가 " + postcardSeq + "번째 엽서 좋아요를 삭제했습니다.");
        }
    }

    @ApiOperation(value = "엽서 상세정보 조회", notes = "엽서seq로 엽서 상세정보 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "엽서 정보 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/{postcardSeq}")
    public ResponseEntity<?> select(@PathVariable(value = "postcardSeq") @ApiParam("엽서seq") int postcardSeq) throws IOException {
        try {
            Postcard postcard = postcardService.selectPostcard(postcardSeq);
            User user = userService.selectUser(postcard.getUserSeq());
            List<Tag> tags = postcardService.selectTag(postcardSeq);

            List<String> tagContents = new ArrayList<>();
            for (Tag tag : tags) {
                tagContents.add(tag.getTagContent());
            }

            return ResponseEntity.status(200).body(PostcardGetRes.of(user.getUserId(), postcard.getPostcardImgUrl(), tagContents));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body(PostcardGetRes.of(null, null, null));
        }

    }

    @ApiOperation(value = "엽서 리스트 조회", notes = "유저seq로 유저가 가지고 있는 엽서 리스트 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "유저 정보 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/list/{userSeq}")
    public ResponseEntity<?> selectPostcardList(@PathVariable(value = "userSeq") @ApiParam("유저seq") int userSeq) throws IOException {
        try {
            List<PostcardList> postcardList = postcardService.selectPostcardList(userSeq);
            return ResponseEntity.status(200).body(PostcardListGetRes.of(postcardList));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body("엽서 리스트가 없습니다.");
        }

    }

    @ApiOperation(value = "인기 엽서 리스트 조회", notes = "인기 엽서 탑10 리스트 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "엽서 리스트 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/top-list")
    public ResponseEntity<?> selectTopPostcardList() throws IOException {
        try {
            List<PostcardList> topPostcardLists = postcardService.selectPostcardTopList();
            return ResponseEntity.status(200).body(PostcardListGetRes.of(topPostcardLists));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body("인기 엽서 리스트가 없습니다.");
        }

    }

    @ApiOperation(value = "좋아요 누른 엽서 리스트 조회", notes = "유저seq로 좋아요 누른 엽서 리스트 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "엽서 리스트 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/like-list/{userSeq}")
    public ResponseEntity<?> selectLikePostcardList(@PathVariable(value = "userSeq") @ApiParam(value = "유저Seq") int userSeq) throws IOException {
        try {
            List<PostcardList> postcardList = postcardService.selectPostcardLikeList(userSeq);
            return ResponseEntity.status(200).body(PostcardListGetRes.of(postcardList));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body("엽서 리스트가 없습니다.");
        }

    }
}
