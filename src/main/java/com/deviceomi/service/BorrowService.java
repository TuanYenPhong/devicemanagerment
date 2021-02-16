package com.deviceomi.service;

import com.deviceomi.model.BorrowEntity;
import com.deviceomi.payload.request.BorrowRequest;
import com.deviceomi.payload.response.BorrowResponse;
import com.deviceomi.payload.response.UserBorrowResponse;
import com.deviceomi.search.SearchBorrow;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BorrowService {
    List<BorrowEntity> findByDeviceId(Long deviceId);
    void saveBorrow(BorrowRequest request);
    void updateBorrow(BorrowRequest request);
    HashMap<String,Object> findAllBorrow();
    List<BorrowEntity> findByDeparmentId(Long departmentId);

    List<UserBorrowResponse> findAllUserBorrow();

    void deleteBorrow(Long id);

    BorrowResponse getBorrowById(Long id);

    List<BorrowEntity> findBorrow();

//    List<BorrowResponse> searchBorrow(SearchBorrow searchBorrow);
}
