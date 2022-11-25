package com.ssafy.api.service;

import com.ssafy.common.customObj.FoundationSearchList;
import com.ssafy.db.entity.Foundation;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.FoundationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("FoundationService")
public class FoundationServiceImpl implements FoundationService{

    @Autowired
    FoundationRepository foundationRepository;

    @Override
    public Foundation selectFoundation(int foundationSeq) {
        Foundation foundation = foundationRepository.findByFoundationSeq(foundationSeq);
        return foundation;
    }

    @Override
    public List<Foundation> selectFoundationList() {
        List<Foundation> list = foundationRepository.findAll();
        return list;
    }

    @Override
    public List<FoundationSearchList> selectFoundationSearchList(String searchWord) throws IOException {
        return foundationRepository.selectFoundationSearchList(searchWord);
    }
}
