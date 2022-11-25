package com.ssafy.api.response;


import com.ssafy.db.entity.Donation;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("UserResponse")
public class UserRes {

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
    @ApiModelProperty(name="사용자 템플릿", example="0")
    int userTemplate;

    @ApiModelProperty(name="userRemind1~12")
    List<Donation> donations;



    public static UserRes of(User user, List<Donation> donations) {
        UserRes res = new UserRes();
        res.setUserSeq(user.getUserSeq());
        res.setUserCode(user.getUserCode());
        res.setUserId(user.getUserId());
        res.setUserName(user.getUserName());
        res.setUserPassword(user.getUserPassword());
        res.setUserEmail(user.getUserEmail());
        res.setUserPhone(user.getUserPhone());
        res.setUserProfileUrl(user.getUserProfileUrl());
        res.setUserTemplate(user.getUserTemplate());

        res.setDonations(donations);

        return res;
    }
}
