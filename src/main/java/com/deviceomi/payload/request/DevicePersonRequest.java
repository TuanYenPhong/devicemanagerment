package com.deviceomi.payload.request;

import com.deviceomi.model.DevicePersonEntity;
import com.deviceomi.model.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Component
public class DevicePersonRequest {

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
        private Long idUser;

    /**
     *Ngay cap nhat gan nhat
     */
    private Date modifiedDate;

    public DevicePersonEntity toDeviceEntity(UserEntity userEntity){
        DevicePersonEntity devicePersonEntity=new DevicePersonEntity();
        devicePersonEntity.setIdDevice(getIdDevice());
        devicePersonEntity.setTypeDevice(getTypeDevice());
        devicePersonEntity.setDeviceAttach(getDeviceAttach());
        devicePersonEntity.setDescription(getDescription());
        devicePersonEntity.setModifiedDate(getModifiedDate());
        devicePersonEntity.setUserDevices(userEntity);
        return devicePersonEntity;
    }

    public DevicePersonEntity toDeviceEntity(UserEntity userEntity,DevicePersonEntity devicePersonEntity){
        devicePersonEntity.setIdDevice(getIdDevice());
        devicePersonEntity.setTypeDevice(getTypeDevice());
        devicePersonEntity.setDeviceAttach(getDeviceAttach());
        devicePersonEntity.setDescription(getDescription());
        devicePersonEntity.setUserDevices(userEntity);
        return devicePersonEntity;
    }
}
