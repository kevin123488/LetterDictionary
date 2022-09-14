package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserUpdateProfilePostRequest")
public class UserUpdateProfilePostReq {
    @ApiModelProperty(name="사용자 Profile", example="user_profile")
    String userProfileUrl;
}
