package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("PostcardGetResponse")
public class PostcardGetRes {

    @ApiModelProperty(name="엽서 제작유저 ID", example="writerId")
    String userId;

    @ApiModelProperty(name="엽서 사진 url", example="s3주소")
    String postcardImgUrl;

    @ApiModelProperty(name="엽서의 태그리스트", example="ssafy, test, ..")
    List<String> tag;

    public static PostcardGetRes of(String userId, String postcardImgUrl, List<String> tag) {
        PostcardGetRes postcardGetPos = new PostcardGetRes();

        postcardGetPos.setUserId(userId);
        postcardGetPos.setPostcardImgUrl(postcardImgUrl);
        postcardGetPos.setTag(tag);

        return postcardGetPos;
    }
}
