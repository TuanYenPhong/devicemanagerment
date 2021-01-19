package com.deviceomi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="role")
@Data

public class RoleEntity extends AuditEntity{

//    @Column(name="code")
//    private String code;
//    @Column(name="name")
//    private String name;
//
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

//    @ManyToMany(mappedBy = "roles")
//    private Set<UserEntity> users = new HashSet<>();
}
