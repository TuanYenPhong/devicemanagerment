package com.deviceomi.service;

import com.deviceomi.model.DepartmentEntity;
import com.deviceomi.payload.request.DepartmentRequest;
import com.deviceomi.payload.response.DepartmentResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface DeparmentService{
     List<DepartmentEntity> findAllDeparment();
     DepartmentEntity createDepartment(DepartmentRequest departmentRequest);
     void deleteDepartment(Long id);
     Optional<DepartmentEntity> getDepartmentById(Long id);
     void updateDepartment(DepartmentRequest departmentRequest);

     List<DepartmentResponse> findRegionDepartment();
}
