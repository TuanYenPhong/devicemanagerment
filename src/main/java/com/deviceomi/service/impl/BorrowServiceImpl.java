package com.deviceomi.service.impl;

import com.deviceomi.model.*;
import com.deviceomi.payload.request.BorrowRequest;
import com.deviceomi.payload.response.BorrowResponse;
import com.deviceomi.payload.response.UserBorrowResponse;
import com.deviceomi.repository.*;
import com.deviceomi.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    DeviceRepository deviceRepository;

//    @Autowired
//    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    HistoryRepository historyRepository;

    public HistoryEntity historyEntity(String context,String editObject){
        HistoryEntity historyEntity=new HistoryEntity();
        historyEntity.setContent(context);
        historyEntity.setPage("Quản lý mươn trả");
        historyEntity.setEditObject(editObject);
        return historyEntity;
    }

    @Override
    public List<BorrowEntity> findByDeviceId(Long deviceId) {
        return null;
    }

    @Override
    public <T> T saveBorrow(T request) {
        if(request == null){
            return null;
        }

        BorrowEntity borrowEntity = null;
        BorrowRequest borrowRequest = (BorrowRequest) request;
        borrowEntity = borrowRequest.toEntity();

        if(borrowRequest.getIdDevice() != null){
            DeviceEntity deviceEntity = deviceRepository.findById(borrowRequest.getIdDevice()).orElse(new DeviceEntity());
            borrowEntity.setDeviceBorrow(deviceEntity);
        }

//        UserEntity userEntity = userRepository.findById(borrowCustomerRequest.getUserId()).orElse(new UserEntity());
//        borrowEntity.setDeviceBorrow(userEntity);

        if(borrowRequest.getIdDepartment() != null){
            DepartmentEntity departmentEntity = departmentRepository.findById(borrowRequest.getIdDepartment()).orElse(new DepartmentEntity());
            borrowEntity.setDeparmentBorrow(departmentEntity);
        }
        borrowRepository.save(borrowEntity);
        historyRepository.save(historyEntity("đã tạo mới thông tin mượn trả với mã thiết bị ",borrowEntity.getDeviceBorrow().getCodeDevice()));
        return null;
    }

    @Override
    public void updateBorrow(BorrowRequest request) {
        if(request.getId() != null) {
            BorrowEntity borrowOld = borrowRepository.findById(request.getId()).orElse(new BorrowEntity());
            borrowOld = request.toEntity(borrowOld, request);

            /**
             * N?u m� ph�ng ban kh�c th� check
             * */
            if(request.getIdDepartment() != null && request.getIdDepartment() != borrowOld.getDeparmentBorrow().getId()){
                borrowOld.setDeparmentBorrow(departmentRepository.findById(request.getIdDepartment()).orElse(new DepartmentEntity()));
            }
          /**
           * N?u m� thi?t b?  kh�c th� check
           * */
            if(request.getIdDevice() != null && request.getIdDevice() != borrowOld.getDeviceBorrow().getId()){
                borrowOld.setDeviceBorrow(deviceRepository.findById(request.getIdDevice()).orElse(new DeviceEntity()));
            }
            borrowRepository.save(borrowOld);
            historyRepository.save(historyEntity("đã chỉnh sửa thông tin mượn trả với mã thiết bị ",borrowOld.getDeviceBorrow().getCodeDevice()));
        }
    }

    @Override
    public List<BorrowResponse> findAllBorrow() {
        List<BorrowResponse> borrows = new ArrayList<>();
        borrowRepository.findAll().stream().map(borrow -> new BorrowResponse(borrow)).forEach(borrows::add);
        return borrows;
    }

    @Override
    public List<BorrowEntity> findByDeparmentId(Long departmentId) {
        List<BorrowEntity> borrows = new ArrayList<>();
        borrowRepository.findByDepartmentId(departmentId).forEach(borrows::add);
        return borrows;
    }

    @Override
    public Set<UserBorrowResponse> findAllUserBorrow() {
        UserBorrowResponse userBorrowResponse=new UserBorrowResponse();
        Set<UserBorrowResponse> userBorrows=new HashSet<>();
        borrowRepository.findAllBorrowIfCount().stream().map(a->userBorrowResponse.toBorrowResponse(a)).forEach(userBorrows::add);
        for (UserBorrowResponse userBorrow:userBorrows){
            Set<BorrowResponse> borrows=new HashSet<>();
            borrowRepository.findByUserBorrow_Id(userBorrow.getIdUser()).stream()
                    .map(a->new BorrowResponse(a)).forEach(borrows::add);
            userBorrow.setBorrows(borrows);
            userBorrow.setCountBorow(borrowRepository.findByUserBorrow_Id(userBorrow.getIdUser()).size());
        }
        return userBorrows;
    }

    @Override
    public void deleteBorrow(Long id) {
        BorrowEntity borrowEntity=borrowRepository.findById(id).orElse(new BorrowEntity());
        if(borrowEntity!=null){
            borrowRepository.deleteById(id);
            historyRepository.save(historyEntity("đã xóa thông tin mượn trả với mã thiết bị ",borrowEntity.getDeviceBorrow().getCodeDevice()));
        }
    }

    @Override
    public BorrowResponse getBorrowById(Long id) {
        BorrowResponse borrow = new BorrowResponse(borrowRepository.findById(id).orElse(new BorrowEntity()));
        return borrow;
    }

    @Override
    public List<BorrowEntity> findBorrow() {
        List<BorrowEntity> borrowEntities= new ArrayList<>();
        borrowRepository.findAll().forEach(borrowEntities::add);
        return borrowEntities;
    }
}