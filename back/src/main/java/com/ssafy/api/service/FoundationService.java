package com.ssafy.api.service;

import com.ssafy.common.customObj.FoundationSearchList;
import com.ssafy.db.entity.Foundation;
import com.ssafy.db.entity.User;

import java.io.IOException;
import java.util.List;

public interface FoundationService {

    Foundation selectFoundation(int foundationSeq);

    List<Foundation> selectFoundationList();

    List<FoundationSearchList> selectFoundationSearchList(String searchWord) throws IOException;
}
