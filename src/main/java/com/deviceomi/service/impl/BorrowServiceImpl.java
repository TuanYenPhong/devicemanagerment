package com.deviceomi.service.impl;

import com.deviceomi.model.BorrowEntity;
import com.deviceomi.model.DepartmentEntity;
import com.deviceomi.model.DeviceEntity;
import com.deviceomi.payload.request.BorrowRequest;
import com.deviceomi.payload.response.BorrowResponse;
import com.deviceomi.payload.response.UserBorrowResponse;
import com.deviceomi.repository.BorrowRepository;
import com.deviceomi.repository.DepartmentRepository;
import com.deviceomi.repository.DeviceRepository;
import com.deviceomi.repository.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

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

        return (T) borrowRepository.save(borrowEntity);
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
        borrowRepository.deleteById(id);
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