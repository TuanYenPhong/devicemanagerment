package com.deviceomi.service.impl;

import com.deviceomi.model.DeviceEntity;
import com.deviceomi.model.HistoryEntity;
import com.deviceomi.payload.request.*;
import com.deviceomi.payload.response.DeviceResponse;
import com.deviceomi.payload.response.DeviceResponse_;
import com.deviceomi.repository.DeviceRepository;
import com.deviceomi.repository.HistoryRepository;
import com.deviceomi.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    private HistoryRepository historyRepository;

    public HistoryEntity historyEntity(String content, String editObject){
        HistoryEntity historyEntity=new HistoryEntity();
        historyEntity.setContent(content);
        historyEntity.setPage("Quản lý nhân sự dùng device cá nhân");
        historyEntity.setEditObject(editObject);
        return historyEntity;
    }

    @Override
    public <T> T saveOrUpdate(T request) {
        if (request == null) return null;

        DeviceEntity deviceEntity = null;
        DeviceEntity saveDeviceEntity = null;
        HistoryEntity historyEntity=null;

        if (request instanceof DeviceWorkRequest){
            DeviceWorkRequest deviceWorkRequest = (DeviceWorkRequest) request;
            deviceEntity = deviceWorkRequest.toEntity();
        }else if(request instanceof DeviceTestRequest){
            DeviceTestRequest deviceTestRequest = (DeviceTestRequest) request;
            deviceEntity = deviceTestRequest.toEntity();
        }else if(request instanceof DeviceOtherRequest){
            DeviceOtherRequest deviceOtherRequest = (DeviceOtherRequest) request;
            deviceEntity = deviceOtherRequest.toEntity();
        }else {
            DeviceCustomerRequest deviceCustomerRequest = (DeviceCustomerRequest) request;
            deviceEntity = deviceCustomerRequest.toEntity();
        }

        if (deviceEntity.getId() != null){
            //update
//            System.out.println("tuan update");
            saveDeviceEntity = deviceRepository.findById(deviceEntity.getId()).orElse(new DeviceEntity());
            //saveDeviceEntity = saveDeviceEntity.toEntity(deviceEntity);
            saveDeviceEntity = deviceEntity;
            historyEntity=historyEntity("đã tạo mới thông tin thiết bị với mã thiết bị ",deviceEntity.getCodeDevice());
        }else {
            //create
//            System.out.println("tuan create = " + deviceEntity.getCodeDevice());
            //neu codeDevice da ton tai thi ko cho save
            List<DeviceEntity> checkDevice = deviceRepository.findByCodeDevice(deviceEntity.getCodeDevice());
            if (checkDevice.size() > 0)
                return null;
            saveDeviceEntity = deviceEntity;
            historyEntity=historyEntity("đã chỉnh sửa thông tin thiết bị với mã thiết bị ",deviceEntity.getCodeDevice());
        }

        deviceRepository.save(saveDeviceEntity);
        if (historyEntity!=null) historyRepository.save(historyEntity);
        return request;
    }

    @Override
    public DeviceRequest save(DeviceRequest deviceRequest) {
        if (deviceRequest == null)
            return null;
        HistoryEntity historyEntity=null;
        DeviceEntity deviceEntity = null;

        if (deviceRequest.getId() != null){
            //update
            System.out.println("tuan update");
            deviceEntity = deviceRepository.findById(deviceRequest.getId()).orElse(new DeviceEntity());
            if(deviceRequest.getCodeDevice() != null)
                historyEntity=historyEntity("đã chỉnh sửa thông tin thiết bị với mã thiết bị ",deviceRequest.getCodeDevice());
        }else {
            //create
            System.out.println("tuan create = " + deviceRequest.getCodeDevice());
            //neu codeDevice da ton tai thi ko cho save
            List<DeviceEntity> checkDevice = deviceRepository.findByCodeDevice(deviceRequest.getCodeDevice());
            if(deviceRequest.getCodeDevice() != null)
                historyEntity=historyEntity("đã tạo mới thông tin thiết bị với mã thiết bị ",deviceRequest.getCodeDevice());
            if (checkDevice.size() > 0)
                return null;
        }
        deviceEntity = deviceRequest.toEntity();
        deviceRepository.save(deviceEntity);
        if (historyEntity!=null) historyRepository.save(historyEntity);
        return deviceRequest;
    }

    @Override
    public DeviceResponse_ getDeviceById(Long id) {
        return new DeviceResponse_().toResponse(deviceRepository.findById(id).orElse(new DeviceEntity()));
    }

    @Override
    public void delete(Long id) {
        DeviceEntity deviceEntity = deviceRepository.findById(id).orElse(new DeviceEntity());
        deviceRepository.delete(deviceEntity);
        historyRepository.save(historyEntity("đã xóa thông tin thiết bị với mã thiết bị ",deviceEntity.getCodeDevice()));
    }

    @Override
    public List<DeviceResponse> getAllDevice() {
        return deviceRepository.findAll().stream().map(device -> new DeviceResponse(device)).collect(Collectors.toList());
    }

    @Override
    public List<DeviceResponse_> getDevices() {
        return deviceRepository.findAll().stream().map(device -> new DeviceResponse_().toResponse(device)).collect(Collectors.toList());
    }
}
