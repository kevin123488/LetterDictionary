package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserUpdateDonationPostRequest")
public class UserUpdateDonationPostReq {
    @ApiModelProperty(name="donationSeq", example="0")
    int donationSeq;
}
