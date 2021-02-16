package com.deviceomi.payload.response;

import com.deviceomi.model.DeviceEntity;
import com.deviceomi.payload.DeviceBase;
import lombok.Data;

@Data
public class DeviceResponse_ extends DeviceBase {
    @Override
    public DeviceBase toResponse(DeviceEntity deviceEntity) {
        return super.toResponse(deviceEntity);
    }

    @Override
    public DeviceEntity toEntity() {
        return super.toEntity();
    }
}
