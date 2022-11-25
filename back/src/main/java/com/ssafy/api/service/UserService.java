package com.ssafy.api.service;

import com.ssafy.api.request.*;
import com.ssafy.db.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    User createUser(UserRegisterPostReq userRegisterInfo);

    void deleteUser(int userSeq);

    User updateUser(int userSeq, UserUpdatePostReq userUpdateInfo);

    User updateUserProfile(int userSeq, MultipartFile profile) throws IOException;

    User updateTemplate(int userSeq, UserUpdateTemplatePostReq userUpdateTemplateInfo);

    User updateDonation(int userSeq, int userRemind, UserUpdateDonationPostReq donationSeq);

    User selectUser(int userSeq);

    User selectUser(String userId);
}
