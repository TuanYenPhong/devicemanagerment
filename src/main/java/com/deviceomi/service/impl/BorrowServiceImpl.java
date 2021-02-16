package com.deviceomi.service.impl;

import com.deviceomi.model.*;
import com.deviceomi.payload.request.BorrowRequest;
import com.deviceomi.payload.response.BorrowResponse;
import com.deviceomi.payload.response.UserBorrowResponse;
import com.deviceomi.repository.*;
import com.deviceomi.search.SearchBorrow;
import com.deviceomi.service.BorrowService;
import com.deviceomi.util.FormatDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<BorrowEntity> findByDeviceId(Long deviceId) {
        return null;
    }

    @Override
    public void saveBorrow(BorrowRequest borrowRequest) {
        if(borrowRequest != null) {
            BorrowEntity borrowEntity = borrowRequest.toEntity();
            DeviceEntity deviceEntity = deviceRepository.findById(borrowRequest.getIdDevice()).orElse(new DeviceEntity());
            borrowEntity.setDeviceBorrow(deviceEntity);
            if (borrowRequest.getIdDepartment() != null) {
                DepartmentEntity departmentEntity = departmentRepository.findById(borrowRequest.getIdDepartment()).orElse(new DepartmentEntity());
                borrowEntity.setDeparmentBorrow(departmentEntity);
            }else{
                UserEntity userEntity=userRepository.findById(borrowRequest.getUserId()).orElse(new UserEntity());
                borrowEntity.setUserBorrow(userEntity);
            }
            if(deviceEntity != null && (borrowEntity.getDeparmentBorrow() != null||borrowEntity.getUserBorrow()!=null)) {
                borrowRepository.save(borrowEntity);
                historyRepository.save(new HistoryEntity("đã tạo mới thông tin mượn trả với mã thiết bị ",
                        borrowEntity.getDeviceBorrow().getCodeDevice(), "Quản lý mượn trả"));
            }
        }
    }

    @Override
    public void updateBorrow(BorrowRequest request) {
        if(request.getId() != null) {
            BorrowEntity borrowOld = borrowRepository.findById(request.getId()).orElse(new BorrowEntity());
            borrowOld = request.toEntity(borrowOld);
            if(request.getIdDepartment()!=null){
                borrowOld.setDeparmentBorrow(departmentRepository.findById(request.getIdDepartment()).orElse(borrowOld.getDeparmentBorrow()));
                borrowOld.setUserBorrow(null);
            }else{
                borrowOld.setUserBorrow(userRepository.findById(request.getUserId()).orElse(borrowOld.getUserBorrow()));
                borrowOld.setDeparmentBorrow(null);
            }
            if(request.getIdDevice() != null && request.getIdDevice() != borrowOld.getDeviceBorrow().getId())
                borrowOld.setDeviceBorrow(deviceRepository.findById(request.getIdDevice()).orElse(new DeviceEntity()));
            borrowRepository.save(borrowOld);
            historyRepository.save(new HistoryEntity("đã chỉnh sửa thông tin mượn trả với mã thiết bị ",
                    borrowOld.getDeviceBorrow().getCodeDevice(),"Quản lý mượn trả"));
        }
    }

    /**
     *
     * @return HashMap
     */
    @Override
    public HashMap<String,Object> findAllBorrow() {
        List<BorrowResponse> borrows = new ArrayList<>();
        HashMap hashMap=new HashMap();
        borrowRepository.findAll().stream().filter(borrow->borrow.getDateReturn()!=null)
                .map(borrow -> new BorrowResponse(borrow)).forEach(borrows::add);
        hashMap.put("borrows",borrows.stream().sorted((o1,o2)->o1.getDateReturn().compareTo(o2.getDateReturn()))
                .sorted((o1,o2)->o2.getStatus().compareTo(o1.getStatus())).collect(Collectors.toList()));
        hashMap.put("outOfDate",borrows.stream().filter(borrow->borrow.getStatus().equals(2))
                .filter(borrow->FormatDate.stringToDate(borrow.getDateReturn())
                .before(FormatDate.stringToDate(LocalDateTime.now().toString()))).count());
        hashMap.put("deadline",borrows.stream().filter(borrow->borrow.getStatus().equals(2))
               .filter(borrow->borrow.getDateReturn().equals(FormatDate.dateToString(Date.from(Instant.now())))).count());
        return hashMap;
    }

    @Override
    public List<BorrowEntity> findByDeparmentId(Long departmentId) {
        List<BorrowEntity> borrows = new ArrayList<>();
        borrowRepository.findByDepartmentId(departmentId).forEach(borrows::add);
        return borrows;
    }

    @Override
    public List<UserBorrowResponse> findAllUserBorrow() {
        Set<UserBorrowResponse> userBorrows=new HashSet<>();
        List<BorrowEntity> userBorrowList=borrowRepository.findByUserBorrowDate(Date.from(Instant.now()));
        userBorrowList.stream().map(a->new UserBorrowResponse(a)).forEach(userBorrows::add);
        for (UserBorrowResponse userBorrow:userBorrows){
            Set<BorrowResponse> borrows=new HashSet<>();
            userBorrowList.stream().filter(a->a.getUserBorrow().getId().equals(userBorrow.getIdUser()))
                    .map(a->new BorrowResponse(a)).forEach(borrows::add);
            userBorrow.setCountBorow(borrows.size());
            userBorrow.setBorrows(borrows);
            if(userBorrow.getCountBorow()<2) userBorrows.remove(userBorrow);
        }
        return userBorrows.stream().filter(userBorrow->userBorrow.getCountBorow()>1).collect(Collectors.toList());
    }

    @Override
    public void deleteBorrow(Long id) {
        BorrowEntity borrowEntity=borrowRepository.findById(id).orElse(new BorrowEntity());
        if(borrowEntity!=null){
            borrowRepository.deleteById(id);
            historyRepository.save(new HistoryEntity("đã xóa thông tin mượn trả với mã thiết bị ",
                    borrowEntity.getDeviceBorrow().getCodeDevice(),"Quản lý mượn trả"));
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

//    @Override
//    public List<BorrowResponse> searchBorrow(SearchBorrow searchBorrow) {
//        List<BorrowResponse> borrows=new ArrayList<>();
//        DepartmentEntity department=departmentRepository.findByNameDepartment(searchBorrow.getType());
//        borrowRepository.searchBorrow(searchBorrow.getType(),searchBorrow.getFromDateBorrow()
//                ,searchBorrow.getToDateBorrow(),searchBorrow.getFromDateReturn(),searchBorrow.getToDateReturn()).stream()
//                .map(borrow->new BorrowResponse(borrow)).forEach(borrows::add);
//        return borrows;
//    }
}
