package com.ssafy.api.response;

import com.ssafy.common.customObj.PostcardList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("PostcardListGetResponse")
public class PostcardListGetRes {

    @ApiModelProperty(name="엽서 리스트", example="엽서 리스트...")
    List<PostcardList> postcardList;

    public static PostcardListGetRes of(List<PostcardList> postcardLists) {
        PostcardListGetRes postcardListGetPos = new PostcardListGetRes();
        postcardListGetPos.setPostcardList(postcardLists);

        return postcardListGetPos;
    }
}
