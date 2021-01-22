package com.deviceomi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="borrow")
@Data
public class BorrowEntity extends AuditEntity{
    @Column(name="reson")
    private String reson;

    @Column(name="dateBorrow")
    private Date dateBorrow;

    @Column(name="dateReturn")
    private Date dateReturn;

    @ManyToOne
    @JoinColumn(name="device_id")
    private DeviceEntity deviceBorrow;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity userBorrow;

    @ManyToOne
    @JoinColumn(name="deparment_id")
    private DepartmentEntity deparmentBorrow;

    @Column(name = "status")
    private Integer status;
}
