package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CompanyLoginPostResponse")
public class UserLoginPostRes extends BaseResponseBody {
    @ApiModelProperty(name="JWT 인증 토큰", example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
    String accessToken;
    @ApiModelProperty(name="사용자 Sequence", example="1")
    int userSeq;
    @ApiModelProperty(name="사용자 ID", example="ssafy123")
    String userId;
    @ApiModelProperty(name="사용자 Password", example="user_password")
    String userPassword;
    @ApiModelProperty(name="사용자 Name", example="user_name")
    String userName;
    @ApiModelProperty(name="사용자 Phone", example="user_phone")
    String userPhone;
    @ApiModelProperty(name="사용자 Email", example="user_email")
    String userEmail;
    @ApiModelProperty(name="사용자 코드", example="101")
    int userCode;
    @ApiModelProperty(name="사용자 프로필", example="url")
    String userProfileUrl;

    public static UserLoginPostRes of(User user, Integer statusCode, String message, String accessToken) {
        UserLoginPostRes res = new UserLoginPostRes();
        res.setUserSeq(user.getUserSeq());
        res.setUserCode(user.getUserCode());
        res.setUserId(user.getUserId());
        res.setUserName(user.getUserName());
        res.setUserPassword(user.getUserPassword());
        res.setUserEmail(user.getUserEmail());
        res.setUserPhone(user.getUserPhone());
        res.setUserProfileUrl(user.getUserProfileUrl());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setAccessToken(accessToken);
        return res;
    }

}
