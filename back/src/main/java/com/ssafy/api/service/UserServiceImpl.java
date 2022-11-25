package com.ssafy.api.service;

import com.ssafy.api.request.*;
import com.ssafy.common.util.S3Uploader;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service("UserService")
public class UserServiceImpl implements UserService{

    @Autowired
    S3Uploader s3Uploader;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserRegisterPostReq userRegisterInfo) {
        User user = new User();
        user.setUserId(userRegisterInfo.getUserId());
        user.setUserCode(userRegisterInfo.getUserCode());
        user.setUserEmail(userRegisterInfo.getUserEmail());
        user.setUserName(userRegisterInfo.getUserName());
        user.setUserPassword(passwordEncoder.encode(userRegisterInfo.getUserPassword()));
        user.setUserPhone(userRegisterInfo.getUserPhone());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int userSeq) {
        userRepository.delete(selectUser(userSeq));
        return;
    }

    @Override
    public User updateUser(int userSeq, UserUpdatePostReq userUpdateInfo) {
        User user = selectUser(userSeq);
        user.setUserEmail(userUpdateInfo.getUserEmail());
        user.setUserPassword(userUpdateInfo.getUserPassword());
        user.setUserPhone(userUpdateInfo.getUserPhone());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUserProfile(int userSeq, MultipartFile profile) throws IOException {

        User user = selectUser(userSeq);

        String result;
        try{
            // S3에 저장
            LocalDateTime today = LocalDateTime.now();
            result = s3Uploader.upload(profile, "profile_imgages_folder/" + user.getUserId() + "/" + today.toString().substring(0,10));
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }

        // DB 업데이트
        user.setUserProfileUrl(result);
        return userRepository.save(user);
    }

    @Override
    public User updateTemplate(int userSeq, UserUpdateTemplatePostReq userUpdateTemplateInfo) {
        User user = selectUser(userSeq);
        user.setUserTemplate(userUpdateTemplateInfo.getUserTemplate());
        return userRepository.save(user);
    }

    @Override
    public User updateDonation(int userSeq, int userRemind, UserUpdateDonationPostReq donationSeq) {
        User user = selectUser(userSeq);

        switch (userRemind){
            case 1 : user.setUserRemind1(donationSeq.getDonationSeq());
                break;
            case 2 : user.setUserRemind2(donationSeq.getDonationSeq());
                break;
            case 3 : user.setUserRemind3(donationSeq.getDonationSeq());
                break;
            case 4 : user.setUserRemind4(donationSeq.getDonationSeq());
                break;
            case 5 : user.setUserRemind5(donationSeq.getDonationSeq());
                break;
            case 6 : user.setUserRemind6(donationSeq.getDonationSeq());
                break;
            case 7 : user.setUserRemind7(donationSeq.getDonationSeq());
                break;
            case 8 : user.setUserRemind8(donationSeq.getDonationSeq());
                break;
            case 9 : user.setUserRemind9(donationSeq.getDonationSeq());
                break;
            case 10 : user.setUserRemind10(donationSeq.getDonationSeq());
                break;
            case 11 : user.setUserRemind11(donationSeq.getDonationSeq());
                break;
            case 12 : user.setUserRemind12(donationSeq.getDonationSeq());
                break;
        }

        return userRepository.save(user);
    }

    @Override
    public User selectUser(int userSeq) {
        User user = userRepository.findByUserSeq(userSeq);
        return user;
    }

    @Override
    public User selectUser(String userId) {
        User user = userRepository.findByUserId(userId);
        return user;
    }
}
