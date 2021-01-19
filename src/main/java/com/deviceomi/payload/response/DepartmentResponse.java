package com.deviceomi.payload.response;

import com.deviceomi.model.DepartmentEntity;
import lombok.Data;

@Data
public class DepartmentResponse {
    private Long id;
    private String name;
    private String region;

    public DepartmentResponse(DepartmentEntity departmentEntity){
        setId(departmentEntity.getId());
        setName(departmentEntity.getNameDepartment());
        setRegion("Phòng ban");
    }
}
