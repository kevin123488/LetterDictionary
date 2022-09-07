package com.ssafy.api.service;

import com.ssafy.db.entity.Foundation;
import com.ssafy.db.entity.User;

import java.util.List;

public interface FoundationService {

    Foundation selectFoundation(int foundationSeq);

    List<Foundation> selectFoundationList();

}
