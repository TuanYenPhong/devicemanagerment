package com.deviceomi.payload.request;

import com.deviceomi.model.BorrowEntity;
import com.deviceomi.model.DepartmentEntity;
import lombok.Data;

@Data
public class DepartmentRequest {

    private Long id;

    private String nameDepartment;

    private Integer status;


    public DepartmentEntity toEntity(){
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setNameDepartment(getNameDepartment());
        departmentEntity.setStatus((getStatus()));
        return departmentEntity;
    }
}
