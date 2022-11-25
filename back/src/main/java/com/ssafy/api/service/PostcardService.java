package com.ssafy.api.service;

import com.ssafy.common.customObj.PostcardList;
import com.ssafy.db.entity.Postcard;
import com.ssafy.db.entity.Tag;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 *	엽서 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface PostcardService {
    Postcard savePostcard(MultipartFile multipartFile, String userId) throws IOException;
    String savePostcardTag(List<String> tag, int postcardSeq);
    void deletePostcard(int postcardSeq) throws IOException;

    boolean savePostcardLike(int postcardSeq, int userSeq) throws IOException;
    boolean deletePostcardLike(int postcardSeq, int userSeq) throws IOException;

    Postcard selectPostcard(int postcardSeq) throws IOException;
    List<PostcardList> selectPostcardList(int userSeq) throws IOException;
    List<PostcardList> selectPostcardTopList() throws IOException;
    List<PostcardList> selectPostcardSearchList(String searchWord) throws IOException;
    List<PostcardList> selectPostcardLikeList(int userSeq) throws IOException;

    List<Tag> selectTag(int postcardSeq) throws IOException;

}
