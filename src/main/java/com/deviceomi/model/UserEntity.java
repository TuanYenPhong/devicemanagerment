package com.deviceomi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "user",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "userName")
        })
@Data
@NoArgsConstructor
public class UserEntity extends AuditEntity{

    @Column(name="userName")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="fullname")
    private String fullname;

    public UserEntity(String username, String fullname, String password) {
        this.userName = username;
        this.fullname = fullname;
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles= new HashSet<>();

    @ManyToOne
    @JoinColumn(name="department_id")
    private DepartmentEntity userDepartment;

    @JsonIgnore
    @OneToMany(mappedBy = "userDevices")
    private Set<DevicePersonEntity> devicePersons = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "userBorrow")
    private Set<BorrowEntity> userBorrow = new HashSet<>();

    @Column
    private int status;
}
