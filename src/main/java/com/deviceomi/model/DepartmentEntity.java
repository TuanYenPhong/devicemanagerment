package com.deviceomi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="department")
@Data
@Api(value = "Department model")
public class DepartmentEntity extends AuditEntity{

    @Column(name="nameDepartment")
    private String nameDepartment;

    @Column(name="status")
    private Integer status;
    @JsonIgnore
    @OneToMany(mappedBy = "userDepartment")
    private Set<UserEntity> userDepartment = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "deparmentBorrow")
    private Set<BorrowEntity> deparmentBorrow = new HashSet<>();
}
