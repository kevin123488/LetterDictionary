package com.ssafy.common.customObj;

import com.ssafy.db.entity.Postcard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 태그 정보를 가지는 엽서 리스트를 저장하는 객체 정의
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostcardList {
    String userId;
    Postcard postcard;
    List<String> tag;
}
