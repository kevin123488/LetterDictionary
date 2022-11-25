package com.ssafy.api.service;

import com.ssafy.common.customObj.PostcardList;
import com.ssafy.common.customObj.PostcardSearchList;
import com.ssafy.common.customObj.TopPostcardList;
import com.ssafy.common.util.S3Uploader;
import com.ssafy.db.entity.Postcard;
import com.ssafy.db.entity.PostcardLike;
import com.ssafy.db.entity.Tag;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.PostcardLikeRepository;
import com.ssafy.db.repository.PostcardRepository;
import com.ssafy.db.repository.TagRepository;
import com.ssafy.db.repository.UserRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 엽서 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("postcardService")
public class PostcardServiceImpl implements PostcardService{

    @Autowired
    S3Uploader s3Uploader;

    @Autowired
    PostcardRepository postcardRepository;

    @Autowired
    PostcardLikeRepository postcardLikeRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * 엽서 업로드 메서드
     */
    @Override
    @Transactional
    public Postcard savePostcard(MultipartFile multipartFile, String userId) throws IOException {
        String result;
        try{
            // S3에 저장
            LocalDateTime today = LocalDateTime.now();
            result = s3Uploader.upload(multipartFile, userId + "/" + today.toString().substring(0,10));
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }

        try{
            Postcard postcard = new Postcard();
            int userSeq = userRepository.findByUserId(userId).getUserSeq();
            postcard.setUserSeq(userSeq);
            postcard.setPostcardImgUrl(result);
            return postcardRepository.save(postcard);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 엽서 태그 업로드 메서드
     */
    @Override
    @Transactional
    public String savePostcardTag(List<String> tag, int postcardSeq) {

        try {
            for(String t : tag) {
                Tag oneTag = new Tag();
                oneTag.setPostcardSeq(postcardSeq);
                oneTag.setTagContent(t);
                tagRepository.save(oneTag);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "DB에 저장을 실패했습니다.";
        }
        return "저장 성공";
    }

    /**
     * 엽서 삭제 메서드
     */
    @Transactional
    public void deletePostcard(int postcardSeq) throws IOException {
        // 태그들 삭제
        tagRepository.deleteTagsByPostcardSeq(postcardSeq);
        // 좋아요 삭제
        postcardLikeRepository.deletePostcardLikesByPostcardSeq(postcardSeq);
        // 엽서 삭제
        postcardRepository.deleteById(postcardSeq);
    }

    /**
     * 엽서 좋아요 추가 메서드
     */
    public boolean savePostcardLike(int postcardSeq, int userSeq) throws IOException {
        if(postcardLikeRepository.findPostcardLikeByPostcardSeqAndUserSeq(postcardSeq, userSeq) != null) {
            return false;
        }

        try {
            PostcardLike postcardLike = new PostcardLike();
            postcardLike.setPostcardSeq(postcardSeq);
            postcardLike.setUserSeq(userSeq);
            postcardLikeRepository.save(postcardLike);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 엽서 좋아요 삭제 메서드
     */
    @Transactional
    public boolean deletePostcardLike(int postcardSeq, int userSeq) throws IOException {
        PostcardLike postcardLike = postcardLikeRepository.findPostcardLikeByPostcardSeqAndUserSeq(postcardSeq, userSeq);
        if(postcardLike == null){
            return false;
        }

        try {
            postcardLikeRepository.deletePostcardLikeByPostcardSeqAndUserSeq(postcardSeq, userSeq);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 엽서 상세정보 조회
     */
    public Postcard selectPostcard(int postcardSeq) throws IOException {
        return postcardRepository.findById(postcardSeq).get();
    }

    /**
     * 엽서 리스트 조회
     */
    @Override
    public List<PostcardList> selectPostcardList(int userSeq) throws IOException {
        List<PostcardList> postcardList = new ArrayList<>();
        String userId = userRepository.findByUserSeq(userSeq).getUserId();

        List<Postcard> postcards = postcardRepository.findAllByUserSeq(userSeq);
        for (Postcard postcard : postcards) {
            List<Tag> tags = tagRepository.getTagsByPostcardSeq(postcard.getPostcardSeq());

            List<String> tagContents = new ArrayList<>();
            for(Tag tag : tags) {
                tagContents.add(tag.getTagContent());
            }
            postcardList.add(new PostcardList(userId, postcard, tagContents));
        }

        return postcardList;
    }

    /**
     * 엽서 상위 리스트 조회
     */
    @Override
    public List<PostcardList> selectPostcardTopList() throws IOException {
        List<PostcardList> postcardList = new ArrayList<>();

        List<TopPostcardList> topPostcardLists = postcardRepository.getPostcardTopTen();
        for(TopPostcardList topPostcardList : topPostcardLists){
            List<Tag> tags = tagRepository.getTagsByPostcardSeq(topPostcardList.getPostcard_Seq());
            Postcard postcard = postcardRepository.findById(topPostcardList.getPostcard_Seq()).get();
            String userId = userRepository.findByUserSeq(postcard.getUserSeq()).getUserId();

            List<String> tagContents = new ArrayList<>();
            for(Tag tag : tags) {
                tagContents.add(tag.getTagContent());
            }

            postcardList.add(new PostcardList(userId, postcard, tagContents));
        }

        return postcardList;
    }

    /**
     * 검색어가 포함된 태그를 가진 엽서 리스트 조회
     */
    @Override
    public List<PostcardList> selectPostcardSearchList(String searchWord) throws IOException {
        List<PostcardList> postcardList = new ArrayList<>();

        List<PostcardSearchList> postcardSearchLists = postcardRepository.getPostcardSearchList(searchWord);
        for(PostcardSearchList postcardSearchList : postcardSearchLists) {
            Postcard postcard = postcardRepository.findById(postcardSearchList.getPostcard_Seq()).get();
            String userId = userRepository.findByUserSeq(postcard.getUserSeq()).getUserId();

            List<Tag> tags = tagRepository.getTagsByPostcardSeq(postcard.getPostcardSeq());

            List<String> tagContents = new ArrayList<>();
            for(Tag tag : tags) {
                tagContents.add(tag.getTagContent());
            }
            postcardList.add(new PostcardList(userId, postcard, tagContents));
        }

        return postcardList;
    }

    /**
     * 좋아요 누른 엽서 리스트 조회
     */
    @Override
    public List<PostcardList> selectPostcardLikeList(int userSeq) throws IOException {
        List<PostcardList> postcardList = new ArrayList<>();

        List<PostcardLike> postcardLikes = postcardLikeRepository.findAllByUserSeq(userSeq);
        for(PostcardLike postcardLike : postcardLikes) {
            Postcard postcard = postcardRepository.findById(postcardLike.getPostcardSeq()).get();
            String userId = userRepository.findByUserSeq(postcard.getUserSeq()).getUserId();
            List<Tag> tags = tagRepository.getTagsByPostcardSeq(postcard.getPostcardSeq());

            List<String> tagContents = new ArrayList<>();
            for(Tag tag : tags) {
                tagContents.add(tag.getTagContent());
            }
            postcardList.add(new PostcardList(userId, postcard, tagContents));
        }

        return postcardList;
    }

    /**
     * 태그 리스트 조회
     */
    @Override
    public List<Tag> selectTag(int postcardSeq) throws IOException {
        return tagRepository.getTagsByPostcardSeq(postcardSeq);
    }


}
