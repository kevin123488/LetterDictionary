package com.ssafy.api.response;

import com.ssafy.common.customObj.FoundationSearchList;
import com.ssafy.common.customObj.PostcardList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("SearchGetResponse")
public class SearchGetRes {

    @ApiModelProperty(name="엽서 검색결과")
    List<PostcardList> postcardLists;

    @ApiModelProperty(name="재단 검색결과")
    List<FoundationSearchList> foundationSearchLists;


    public static SearchGetRes of(List<PostcardList> postcardLists, List<FoundationSearchList> foundationSearchLists) {
        SearchGetRes searchGetPos = new SearchGetRes();

        searchGetPos.setPostcardLists(postcardLists);
        searchGetPos.setFoundationSearchLists(foundationSearchLists);

        return searchGetPos;
    }
}
