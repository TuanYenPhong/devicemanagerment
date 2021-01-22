package com.deviceomi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@EqualsAndHashCode
public class PasswordReset {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pwd_reset_token")
    @SequenceGenerator(name = "pwd_reset_token", allocationSize = 1)
    private Long id;

    @Column(nullable = false,unique = true)
    private String token;

    @Column(nullable = false,unique = true)
    private Instant exprixyDate;

    @OneToOne
    @JoinColumn
    private UserEntity user;

    public PasswordReset setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
