package com.ssafy.api.controller;

import com.ssafy.api.request.*;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.DonationService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Donation;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

@Api(value = "사용자 API", tags = {"User"})
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    DonationService donationService;

    @PostMapping()
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> register(
            @RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq registerInfo) {

        //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
        User user = userService.createUser(registerInfo);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    @DeleteMapping("/{userSeq}")
    @ApiOperation(value = "회원 탈퇴", notes = "<strong>아이디</strong>를 통해 회원탈퇴 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> delete(
            @PathVariable("userSeq") @ApiParam(value="회원탈퇴 정보", required = true) int userSeq) {

        userService.deleteUser(userSeq);

        return ResponseEntity.status(204).body(BaseResponseBody.of(204, "Success"));
    }

    @PatchMapping("/{userSeq}")
    @ApiOperation(value = "회원정보 수정", notes = "<strong>아이디</strong>를 통해 회원정보 수정 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> update(
            @PathVariable("userSeq") @ApiParam(value="회원정보 수정", required = true) int userSeq
            , @RequestBody @ApiParam(value="회원정보 수정", required = true) UserUpdatePostReq updateInfo) {

        userService.updateUser(userSeq, updateInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @PatchMapping("/template/{userSeq}")
    @ApiOperation(value = "회원 템플릿 수정", notes = "<strong>아이디</strong>를 통해 회원템플릿을 수정 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> updateTemplate(
            @PathVariable("userSeq") @ApiParam(value="회원정보 수정", required = true) int userSeq
            , @RequestBody @ApiParam(value="회원정보 수정", required = true) UserUpdateTemplatePostReq updateTemplateInfo) {

        userService.updateTemplate(userSeq, updateTemplateInfo);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
    @PatchMapping("/template/{userSeq}/{userRemind}")
    @ApiOperation(value = "회원 템플릿에 보여줄 기부정보 값 수정", notes = "<strong>유저조회</strong>를 통해 템플릿 기부정보를 수정 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> updateDonation(
            @PathVariable("userSeq") @ApiParam(value="회원정보 수정", required = true) int userSeq,
            @PathVariable("userRemind") @ApiParam(value="회원정보 수정", required = true) int userRemind
            , @RequestBody @ApiParam(value="템플릿 수정", required = true) UserUpdateDonationPostReq donationSeq) {


        userService.updateDonation(userSeq, userRemind, donationSeq);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }


    @PatchMapping("/profile/{userSeq}")
    @ApiOperation(value = "회원프로필사진 수정", notes = "<strong>아이디</strong>를 통해 회원프로필 수정 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> updateProfile(
            @PathVariable("userSeq") @ApiParam(value="회원정보 수정", required = true) int userSeq,
            @RequestPart @ApiParam(value="프로필 사진 파일", required = true) MultipartFile profile) throws IOException {

        try {
            User user = userService.updateUserProfile(userSeq, profile);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Fail"));
        }

    }

    @GetMapping("/check/{userId}")
    @ApiOperation(value = "ID 중복 체크", notes = "<strong>아이디</strong>를 통해 중복체크 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> duplicate(
            @PathVariable("userId") @ApiParam(value="찾을 아이디", required = true) String userId) {
        User user = userService.selectUser(userId);
        return user != null ? ResponseEntity.status(409).body(BaseResponseBody.of(409, "이미 존재하는 사용자 ID 입니다.")) :
                ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }


    @GetMapping("/{userSeq}")
    @ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<UserRes> getUserInfo(
            @PathVariable("userSeq") @ApiParam(value="userSeq", required = true) int userSeq) {
        /**
         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
         */
        User user = userService.selectUser(userSeq);

        List<Donation> donations = new ArrayList<>();

        //사용자가 템플릿에 저장한 기부내역들
        if(user.getUserRemind1() != 0){
            donations.add(donationService.getDonationByDonationSeq(user.getUserRemind1()));
        }else {
            donations.add(null);
        }

        if(user.getUserRemind2() != 0){
            donations.add(donationService.getDonationByDonationSeq(user.getUserRemind2()));
        }else {
            donations.add(null);
        }

        if(user.getUserRemind3() != 0){
            donations.add(donationService.getDonationByDonationSeq(user.getUserRemind3()));
        }else {
            donations.add(null);
        }

        if(user.getUserRemind4() != 0){
            donations.add(donationService.getDonationByDonationSeq(user.getUserRemind4()));
        }else {
            donations.add(null);
        }

        if(user.getUserRemind5() != 0){
            donations.add(donationService.getDonationByDonationSeq(user.getUserRemind5()));
        }else {
            donations.add(null);
        }

        if(user.getUserRemind6() != 0){
            donations.add(donationService.getDonationByDonationSeq(user.getUserRemind6()));
        }else {
            donations.add(null);
        }

        if(user.getUserRemind7() != 0){
            donations.add(donationService.getDonationByDonationSeq(user.getUserRemind7()));
        }else {
            donations.add(null);
        }

        if(user.getUserRemind8() != 0){
            donations.add(donationService.getDonationByDonationSeq(user.getUserRemind8()));
        }else {
            donations.add(null);
        }

        if(user.getUserRemind9() != 0){
            donations.add(donationService.getDonationByDonationSeq(user.getUserRemind9()));
        }else {
            donations.add(null);
        }

        if(user.getUserRemind10() != 0){
            donations.add(donationService.getDonationByDonationSeq(user.getUserRemind10()));
        }else {
            donations.add(null);
        }

        if(user.getUserRemind11() != 0){
            donations.add(donationService.getDonationByDonationSeq(user.getUserRemind11()));
        }else {
            donations.add(null);
        }

        if(user.getUserRemind12() != 0){
            donations.add(donationService.getDonationByDonationSeq(user.getUserRemind12()));
        }else {
            donations.add(null);
        }


        return ResponseEntity.status(200).body(UserRes.of(user, donations));
    }
}
