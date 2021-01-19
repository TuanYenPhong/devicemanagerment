package com.deviceomi.service.impl;

import com.deviceomi.model.DepartmentEntity;
import com.deviceomi.payload.request.DepartmentRequest;
import com.deviceomi.payload.response.BorrowResponse;
import com.deviceomi.payload.response.DepartmentResponse;
import com.deviceomi.repository.DepartmentRepository;
import com.deviceomi.service.DeparmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DeparmentService {
    @Autowired
    private final DepartmentRepository departmentRepository;

    /**
    *Find all department to database
    * */
    public List<DepartmentEntity> findAllDeparment(){
        List<DepartmentEntity> deparments = new ArrayList<>();
        departmentRepository.findAll().forEach(deparments::add);
        return deparments;
    }
    /**
     * Create department to database
     * */
    public DepartmentEntity createDepartment(DepartmentRequest departmentRequest){
        if(departmentRequest != null){
            DepartmentEntity departmentEntity = departmentRequest.toEntity();
            return departmentRepository.save(departmentEntity);
        }
        return null;
    }

    /**
     * Delete department to database
     * */
    public void deleteDepartment(Long id){
        departmentRepository.deleteById(id);
    }

    @Override
    public Optional<DepartmentEntity> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public void updateDepartment(DepartmentRequest request) {
        if(request.getId() != null){
            DepartmentEntity oldEntity = departmentRepository.findById(request.getId()).orElse(new DepartmentEntity());
            oldEntity.setStatus(request.getStatus());
            oldEntity.setNameDepartment(request.getNameDepartment());
            departmentRepository.save(oldEntity);
        }
    }

    @Override
    public List<DepartmentResponse> findRegionDepartment() {
        List<DepartmentResponse> dpList = new ArrayList<>();
        departmentRepository.findAll().stream().map(dp -> new DepartmentResponse(dp)).forEach(dpList::add);
        return dpList;
    }

}
