package com.deviceomi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="device_person")
@Data
public class DevicePersonEntity extends AuditEntity{
    @Column(name = "idDevice")
    private String idDevice;

    @Column(name = "typeDevice")
    private String typeDevice;

    @Column(name = "description")
    private String description;

    @Column(name = "deviceAttach")
    private String deviceAttach;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity userDevices;
}
