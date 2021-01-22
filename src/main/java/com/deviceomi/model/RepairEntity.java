package com.deviceomi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="repair")
@Data
public class RepairEntity extends AuditEntity{

    @Column(name="description")
    private String description;

    @Column(name="dateRepair")
    private Date dateRepair;

    @Column(name="dateFinish")
    private Date dateFinish;

    @ManyToOne
    @JoinColumn(name="device_id")
    private DeviceEntity deviceRepair;

    @Column(name = "status")
    private Integer status;
}
