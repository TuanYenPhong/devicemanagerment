package com.deviceomi.service;

import com.deviceomi.model.DevicePersonEntity;
import com.deviceomi.payload.request.DevicePersonRequest;
import com.deviceomi.payload.response.DevicePersonResponse;
import com.deviceomi.search.DevicePersonSearch;

import java.util.List;

public interface DevicePersonService {

    List<DevicePersonResponse> findAll();

    boolean create(DevicePersonRequest devicePersonRequest);

    boolean update(DevicePersonRequest devicePersonRequest);

    boolean delete(Long id);

    List<DevicePersonResponse> search(DevicePersonSearch devicePersonSearch);
}
