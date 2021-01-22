package com.deviceomi.service.impl;

import com.deviceomi.model.DevicePersonEntity;
import com.deviceomi.model.HistoryEntity;
import com.deviceomi.model.UserEntity;
import com.deviceomi.payload.request.DevicePersonRequest;
import com.deviceomi.payload.response.DevicePersonResponse;
import com.deviceomi.repository.DevicePersonRepository;
import com.deviceomi.repository.HistoryRepository;
import com.deviceomi.repository.UserRepository;
import com.deviceomi.search.DevicePersonSearch;
import com.deviceomi.service.DevicePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DevicePersonServieceImpl implements DevicePersonService {
    @Autowired
    private DevicePersonRepository devicePersonRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryRepository historyRepository;

    public HistoryEntity historyEntity(String content,String editObject){
        HistoryEntity historyEntity=new HistoryEntity();
        historyEntity.setContent(content);
        historyEntity.setPage("Quản lý nhân sự dùng device cá nhân");
        historyEntity.setEditObject(editObject);
        return historyEntity;
    }

    /**
     * Show DevicePerson
     * @return List<DevicePersonResponse>
     */
    @Override
    public List<DevicePersonResponse> findAll() {
        List<DevicePersonResponse> devicePersons=new ArrayList<>();
        List<DevicePersonEntity> devicePersonEntities=devicePersonRepository.findAll();
        devicePersonEntities.stream().map(a ->new DevicePersonResponse(a))
                .forEach(devicePersons::add);
        return devicePersons;
    }

    /**
     * @param devicePersonRequest
     *
     * if Check User!=null  && Id device =null
     *
     * @return list<Device>
     */
    @Override
    public boolean create(DevicePersonRequest devicePersonRequest) {
        UserEntity userEntity=userRepository.findById(devicePersonRequest.getIdUser()).orElse(null);
        if (devicePersonRepository.findByIdDevice(devicePersonRequest.getIdDevice())==null && userEntity!=null && userEntity.getStatus()==1 ){
            DevicePersonEntity devicePersonEntity=devicePersonRequest.toDeviceEntity(userEntity);
            devicePersonRepository.save(devicePersonEntity);
            historyRepository.save(historyEntity("đã tạo mới thông tin thiết bị cá nhân với mã thiết bị ",devicePersonEntity.getIdDevice()));
            return true;
        }
        return false;
    }

    /**
     * Update Device Person
     * @param devicePersonRequest
     */
    @Override
    public boolean update(DevicePersonRequest devicePersonRequest) {
        DevicePersonEntity devicePersonEntity = devicePersonRepository.findByIdDevice(devicePersonRequest.getIdDevice());
        System.out.println(devicePersonEntity.getTypeDevice());
        UserEntity userEntity=userRepository.findById(devicePersonRequest.getIdUser()).orElse(new UserEntity());
        if(devicePersonEntity!=null&&userEntity!=null&&userEntity.getStatus()==1) {
            devicePersonRepository.save(devicePersonRequest.toDeviceEntity(userEntity,devicePersonEntity));
            historyRepository.save(historyEntity("đã chỉnh sửa thông tin thiết bị cá nhân với mã thiết bị ",devicePersonRequest.getIdDevice()));
            return true;
        }
        return false;
    }


//    @Override
//    public boolean delete(DevicePersonRequest devicePersonRequest) {
//        DevicePersonEntity devicePersonEntity=devicePersonRepository.findByIdDevice(devicePersonRequest.getIdDevice());
//        if(devicePersonEntity!=null) {
//            devicePersonRepository.delete(devicePersonEntity);
//            return true;
//        }
//        return false;
//    }

    /**
     * Delete DevicePerson
     * @param
     */
    @Override
    public boolean delete(Long id) {
        if(id==null) return false;
        DevicePersonEntity devicePersonEntity=devicePersonRepository.findById(id).orElse(new DevicePersonEntity());
        if(devicePersonEntity!=null) {
            devicePersonRepository.delete(devicePersonEntity);
            historyRepository.save(historyEntity("đã xóa thông tin thiết bị cá nhân với mã thiết bị ",devicePersonEntity.getIdDevice()));
            return true;
        }
        return false;
    }

    /**
     *
     * @param devicePersonSearch
     * @return result search is List<devicePerson>
     */
    @Override
    public List<DevicePersonResponse> search(DevicePersonSearch devicePersonSearch) {
        List<DevicePersonResponse> devicePersonResponses=new ArrayList<>();
        List<DevicePersonEntity> devicePersonEntities=devicePersonRepository.sreachByAll(
                        devicePersonSearch.getIdDevice(), devicePersonSearch.getTypeDevice(),
                        devicePersonSearch.getDeviceAttach(),devicePersonSearch.getIdUser(),
                        devicePersonSearch.getFromModifiedDate(),devicePersonSearch.getToModifiedDate());
        devicePersonEntities.stream().map(a ->new DevicePersonResponse(a)).forEach(devicePersonResponses::add);
        return devicePersonResponses;
    }
}
