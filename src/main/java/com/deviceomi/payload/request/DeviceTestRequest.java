package com.deviceomi.payload.request;

import com.deviceomi.model.DeviceEntity;
import com.deviceomi.payload.DeviceBase;

public class DeviceTestRequest  extends DeviceBase {

    @Override
    public DeviceEntity toEntity() {
        DeviceEntity deviceEntity = super.toEntity();
        //code them cac truong rieng

        return deviceEntity;
    }
}
