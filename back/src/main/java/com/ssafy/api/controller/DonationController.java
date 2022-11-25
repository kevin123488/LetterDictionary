package com.ssafy.api.controller;


import com.ssafy.api.request.DonationRegisterPostReq;
import com.ssafy.api.response.DonationRes;
import com.ssafy.api.service.DonationService;
import com.ssafy.api.service.FoundationService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Donation;
import com.ssafy.db.entity.Foundation;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 기부 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "기부 API", tags = {"Donation"})
@RestController
@RequestMapping("/api/donations")
public class DonationController {
	
	@Autowired
	DonationService donationService;

	@Autowired
	FoundationService foundationService;

	@Autowired
	UserService userService;
	
	@PostMapping()
	@ApiOperation(value = "기부 등록", notes = "<strong>사용자가 기부를 하면</strong>기부 정보를 등록 한다.")
    @ApiResponses({
        @ApiResponse(code = 201, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> donation_register(
			@RequestBody @ApiParam(value="기부 정보", required = true) DonationRegisterPostReq registerInfo) {

		Donation donation = donationService.createDonation(registerInfo);
		
		return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
	}


	@GetMapping("/{donationSeq}")
	@ApiOperation(value = "기부 상세조회", notes = "<strong>donationSeq</strong>를 통해 기부상세정보를 검색한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 409, message = "이미 존재하는 사용자"),
			@ApiResponse(code = 500, message = "서버 오류")
	})

	public ResponseEntity<DonationRes> donationInfo(@PathVariable("donationSeq") @ApiParam(value="조회할 donationSeq", required = true) int donationSeq) {

		Donation donation = donationService.getDonationByDonationSeq(donationSeq);


		Foundation foundation = foundationService.selectFoundation(donation.getFoundationSeq());
		String foundationName = foundation.getFoundationName();

		User user = userService.selectUser(donation.getUserSeq());
		String userId = user.getUserId();

		return ResponseEntity.status(200).body(DonationRes.of(donation,foundationName, userId));
	}


	@GetMapping("/userList/{userSeq}")
	@ApiOperation(value = "기부 전체 조회(회원의 기부 목록)", notes = "<strong>userSeq</strong>를 통해 회원의 기부 내역을 검색한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 409, message = "이미 존재하는 사용자"),
			@ApiResponse(code = 500, message = "서버 오류")
	})

	public ResponseEntity<List<DonationRes>> donationInfoListByUser(@PathVariable("userSeq") @ApiParam(value="조회할 userSeq", required = true) int userSeq) {

		List<Donation> donations = donationService.getDonationByUserSeq(userSeq);
		List<DonationRes> res = new ArrayList<DonationRes>();


		User user = userService.selectUser(userSeq);
		String userId = user.getUserId();

		for(Donation donation : donations){

			Foundation foundation = foundationService.selectFoundation(donation.getFoundationSeq());
			String foundationName = foundation.getFoundationName();

			DonationRes donationRes = DonationRes.of(donation,foundationName,userId);
			res.add(donationRes);
		}

		return ResponseEntity.status(200).body(res);
	}

	@GetMapping("/foundationList/{foundationSeq}")
	@ApiOperation(value = "기부 전체 조회(재단이 받은 기부 목록)", notes = "<strong>foundationSeq</strong>를 통해 재단의 기부 내역을 검색한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 409, message = "이미 존재하는 사용자"),
			@ApiResponse(code = 500, message = "서버 오류")
	})


	public ResponseEntity<List<DonationRes>> donationInfoListByFoundation(@PathVariable("foundationSeq") @ApiParam(value="조회할 foundationSeq", required = true) int foundationSeq) {

		List<Donation> donations = donationService.getDonationByFoundationSeq(foundationSeq);
		List<DonationRes> res = new ArrayList<DonationRes>();

		Foundation foundation = foundationService.selectFoundation(foundationSeq);
		String foundationName = foundation.getFoundationName();

		for(Donation donation : donations){

			User user = userService.selectUser(donation.getUserSeq());
			String userId = user.getUserId();

			DonationRes donationRes = DonationRes.of(donation,foundationName,userId);
			res.add(donationRes);
		}

		return ResponseEntity.status(200).body(res);
	}

}
