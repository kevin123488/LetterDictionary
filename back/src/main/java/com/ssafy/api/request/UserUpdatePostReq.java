package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserUpdatePostRequest")
public class UserUpdatePostReq {
    @ApiModelProperty(name="사용자 Password", example="user_password")
    String userPassword;
    @ApiModelProperty(name="사용자 Phone", example="user_phone")
    String userPhone;
    @ApiModelProperty(name="사용자 Email", example="user_email")
    String userEmail;
}
