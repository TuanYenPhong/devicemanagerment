package com.deviceomi.service;

import com.deviceomi.model.BorrowEntity;
import com.deviceomi.payload.request.BorrowRequest;
import com.deviceomi.payload.response.BorrowResponse;
import com.deviceomi.payload.response.UserBorrowResponse;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BorrowService {
    List<BorrowEntity> findByDeviceId(Long deviceId);
    <T> T saveBorrow(T request);
    void updateBorrow(BorrowRequest request);
    List<BorrowResponse> findAllBorrow();
    List<BorrowEntity> findByDeparmentId(Long departmentId);

    Set<UserBorrowResponse> findAllUserBorrow();

    void deleteBorrow(Long id);

    //List<BorrowEntity> findAllUserBorrow();

    BorrowResponse getBorrowById(Long id);

    List<BorrowEntity> findBorrow();
}
