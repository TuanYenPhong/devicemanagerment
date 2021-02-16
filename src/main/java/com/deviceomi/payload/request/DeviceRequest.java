package com.deviceomi.payload.request;

import com.deviceomi.model.DeviceEntity;
import com.deviceomi.payload.DeviceBase;
import lombok.Data;

@Data
public class DeviceRequest extends DeviceBase {
    @Override
    public DeviceBase toResponse(DeviceEntity deviceEntity) {
        return super.toResponse(deviceEntity);
    }

    @Override
    public DeviceEntity toEntity() {
        return super.toEntity();
    }
}
