package com.deviceomi.payload.response;

import com.deviceomi.model.DeviceEntity;
import lombok.Data;

@Data
public class DeviceResponse {
    private String codeDevice;
    private Long id;

    public DeviceResponse(DeviceEntity deviceEntity){
        if(deviceEntity != null){
            id = deviceEntity.getId();
            codeDevice = deviceEntity.getCodeDevice();
        }
    }
}
