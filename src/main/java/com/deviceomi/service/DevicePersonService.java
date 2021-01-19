package com.deviceomi.service;

import com.deviceomi.model.DevicePersonEntity;
import com.deviceomi.payload.request.DevicePersonRequest;
import com.deviceomi.payload.response.DevicePersonResponse;
import com.deviceomi.search.DevicePersonSearch;

import java.util.List;
import java.util.Optional;

public interface DevicePersonService {
    List<DevicePersonResponse> findAll();

    List<DevicePersonResponse> create(DevicePersonRequest devicePersonRequest);

    boolean update(DevicePersonRequest devicePersonRequest);

    List<DevicePersonResponse> search(DevicePersonSearch devicePersonSearch);
}
