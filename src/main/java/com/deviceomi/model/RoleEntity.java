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
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
