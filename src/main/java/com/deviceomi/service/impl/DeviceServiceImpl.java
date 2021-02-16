package com.deviceomi.service.impl;

import com.deviceomi.model.DeviceEntity;
import com.deviceomi.model.HistoryEntity;
import com.deviceomi.payload.request.*;
import com.deviceomi.payload.response.DeviceResponse;
import com.deviceomi.payload.response.DeviceResponse_;
import com.deviceomi.repository.DeviceRepository;
import com.deviceomi.repository.HistoryRepository;
import com.deviceomi.search.DeviceWorkSearch;
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

//    @Override
//    public <T> T saveOrUpdate(T request) {
//        if (request == null) return null;
//
//        DeviceEntity deviceEntity = null;
//        DeviceEntity saveDeviceEntity = null;
//        HistoryEntity historyEntity=null;
//
//        if (request instanceof DeviceWorkRequest){
//            DeviceWorkRequest deviceWorkRequest = (DeviceWorkRequest) request;
//            deviceEntity = deviceWorkRequest.toEntity();
//        }else if(request instanceof DeviceTestRequest){
//            DeviceTestRequest deviceTestRequest = (DeviceTestRequest) request;
//            deviceEntity = deviceTestRequest.toEntity();
//        }else if(request instanceof DeviceOtherRequest){
//            DeviceOtherRequest deviceOtherRequest = (DeviceOtherRequest) request;
//            deviceEntity = deviceOtherRequest.toEntity();
//        }else {
//            DeviceCustomerRequest deviceCustomerRequest = (DeviceCustomerRequest) request;
//            deviceEntity = deviceCustomerRequest.toEntity();
//        }
//
//        if (deviceEntity.getId() != null){
//            //update
////            System.out.println("tuan update");
//            saveDeviceEntity = deviceRepository.findById(deviceEntity.getId()).orElse(new DeviceEntity());
//            //saveDeviceEntity = saveDeviceEntity.toEntity(deviceEntity);
//            saveDeviceEntity = deviceEntity;
//            historyEntity=historyEntity("đã tạo mới thông tin thiết bị với mã thiết bị ",deviceEntity.getCodeDevice());
//        }else {
//            //create
////            System.out.println("tuan create = " + deviceEntity.getCodeDevice());
//            //neu codeDevice da ton tai thi ko cho save
//            List<DeviceEntity> checkDevice = deviceRepository.findByCodeDevice(deviceEntity.getCodeDevice());
//            if (checkDevice.size() > 0)
//                return null;
//            saveDeviceEntity = deviceEntity;
//            historyEntity=historyEntity("đã chỉnh sửa thông tin thiết bị với mã thiết bị ",deviceEntity.getCodeDevice());
//        }
//
//        deviceRepository.save(saveDeviceEntity);
//        if (historyEntity!=null) historyRepository.save(historyEntity);
//        return request;
//    }

    @Override
    public DeviceRequest save(DeviceRequest deviceRequest) {
        if (deviceRequest == null)
            return null;
        HistoryEntity historyEntity=null;
        DeviceEntity deviceEntity = null;
        List<DeviceEntity> checkDevice = null;

        if (deviceRequest.getId() != null){
            //update
            deviceEntity = deviceRepository.findById(deviceRequest.getId()).orElse(new DeviceEntity());

            if (!deviceEntity.getCodeDevice().equals(deviceEntity.getCodeDevice().trim())) {
                checkDevice = deviceRepository.findByCodeDevice(deviceRequest.getCodeDevice().trim());
                if (checkDevice.size() > 0)
                    return null;
            }

            if(deviceRequest.getCodeDevice() != null)
                historyEntity=new HistoryEntity("đã tạo mới thông tin thiết bị với mã thiết bị ",
                        deviceRequest.getCodeDevice(),"Quản lý nhân sự dùng device cá nhân");

        }else {
            //create
            //neu codeDevice da ton tai thi ko cho save
            checkDevice = deviceRepository.findByCodeDevice(deviceRequest.getCodeDevice());
            if (checkDevice.size() > 0){
                for (var item : checkDevice) {
                    if (item.getIsLive() == 1){
                        return null;
                    }
                }
            }
            if(deviceRequest.getCodeDevice() != null)
                historyEntity=new HistoryEntity("đã chỉnh sửa thông tin thiết bị với mã thiết bị ",
                        deviceRequest.getCodeDevice(),"Quản lý nhân sự dùng device cá nhân");
        }
        deviceEntity = deviceRequest.toEntity();
        deviceRepository.save(deviceEntity);
        if (historyEntity!=null) historyRepository.save(historyEntity);
        return deviceRequest;
    }

    @Override
    public DeviceResponse_ getDeviceById(Long id) {
        return (DeviceResponse_) new DeviceResponse_().toResponse(deviceRepository.findById(id).orElse(new DeviceEntity()));
    }

    @Override
    public List<DeviceResponse_> searchDevice(DeviceWorkSearch deviceWorkSearch) {
        return deviceRepository.getDeviceSearch(deviceWorkSearch.getCodeDevice(), deviceWorkSearch.getTypeDevice()).stream().map(dv -> (DeviceResponse_) new DeviceResponse_().toResponse(dv)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        DeviceEntity deviceEntity = deviceRepository.findById(id).orElse(new DeviceEntity());
//        deviceRepository.delete(deviceEntity);
        deviceEntity.setIsLive(0);
        historyRepository.save(new HistoryEntity("đã xóa thông tin thiết bị với mã thiết bị ",
                deviceEntity.getCodeDevice(),"Quản lý nhân sự dùng device cá nhân"));

    }

    @Override
    public List<DeviceResponse> getAllDevice() {
        return deviceRepository.findAll().stream().filter(device -> device.getIsLive() != 0).map(device -> new DeviceResponse(device)).collect(Collectors.toList());
    }

    @Override
    public List<DeviceResponse_> getDevices() {
        return deviceRepository.findAll().stream().filter(device -> device.getIsLive() != 0).map(device -> (DeviceResponse_) new DeviceResponse_().toResponse(device)).collect(Collectors.toList());
    }
}
