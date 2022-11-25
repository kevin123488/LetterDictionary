package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ApiModel("PostcardPostReq")
public class PostcardPostReq {
    @ApiParam(value = "엽서 파일")
    MultipartFile postcard;
    @ApiParam(value = "엽서 설명 태그")
    List<String> tag;
    @ApiParam(value = "엽서 제작자 ID")
    String userId;
}
