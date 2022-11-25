package com.ssafy.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {
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
    @ApiModelProperty(name="사용자 코드", example="user_code")
    int userCode;



}
