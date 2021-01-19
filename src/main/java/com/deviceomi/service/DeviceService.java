package com.deviceomi.service;

import com.deviceomi.payload.request.DeviceRequest;
import com.deviceomi.payload.response.DeviceResponse;
import com.deviceomi.payload.response.DeviceResponse_;

import java.util.List;

public interface DeviceService {
    <T> T saveOrUpdate(T request);
    void delete(Long id);
    List<DeviceResponse> getAllDevice();
    List<DeviceResponse_> getDevices();
    DeviceRequest save(DeviceRequest deviceRequest);
    DeviceResponse_ getDeviceById(Long id);
}
