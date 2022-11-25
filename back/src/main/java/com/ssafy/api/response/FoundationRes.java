package com.ssafy.api.response;

import com.ssafy.db.entity.Foundation;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FoundationResponse")
public class FoundationRes {

    @ApiModelProperty(name="재단 Sequence", example="1")
    int foundationSeq;
    @ApiModelProperty(name="재단 name", example="foundation_name")
    String foundationName;
    @ApiModelProperty(name="재단 content", example="foundation_content")
    String foundationContent;
    @ApiModelProperty(name="재단 로고 URL", example="url")
    String foundationLogoUrl;

    public static FoundationRes of(Foundation foundation) {
        FoundationRes res =  new FoundationRes();
        res.setFoundationSeq(foundation.getFoundationSeq());
        res.setFoundationName(foundation.getFoundationName());
        res.setFoundationContent(foundation.getFoundationContent());
        res.setFoundationLogoUrl(foundation.getFoundationLogoUrl());
        return res;
    }
}
