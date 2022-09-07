package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserLoginPostRequest")
public class UserLoginPostReq {

    @ApiModelProperty(name="사용자 ID", example="ssafy123")
    String userId;
    @ApiModelProperty(name="사용자 Password", example="user_password")
    String userPassword;
}
