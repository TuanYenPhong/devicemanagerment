package com.deviceomi.payload.response;

import com.deviceomi.model.DevicePersonEntity;
import com.deviceomi.model.UserEntity;
import com.deviceomi.payload.DeviceBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class DevicePersonResponse {
    /**
     *   Ma thiet bi
     */
    private String idDevice;

    /**
     *   Loai thiet bi
     */
    private String typeDevice;

    /**
     * Mo ta
     */
    private String description;

    /**
     * Thiet bi di kem
     */
    private String deviceAttach;

    /**
     * User
     */
    private String namePerson;
    private String nameDepartment;

    /**
     *Ngay cap nhat gan nhat
     */
    private Date modifiedDate;

    public DevicePersonResponse (DevicePersonEntity devicePersonEntity){
        this.setIdDevice(devicePersonEntity.getIdDevice());
        this.setTypeDevice(devicePersonEntity.getTypeDevice());
        this.setDescription(devicePersonEntity.getDescription());
        this.setDeviceAttach(devicePersonEntity.getDeviceAttach());
        this.setNamePerson(devicePersonEntity.getUserDevices().getFullname());
        this.setNameDepartment(devicePersonEntity.getUserDevices().getUserDepartment().getNameDepartment());
        this.setModifiedDate(devicePersonEntity.getModifiedDate());
    }

}
