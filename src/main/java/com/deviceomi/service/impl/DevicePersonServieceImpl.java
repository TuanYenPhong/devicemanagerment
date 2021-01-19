package com.deviceomi.service.impl;

import com.deviceomi.model.DevicePersonEntity;
import com.deviceomi.model.UserEntity;
import com.deviceomi.payload.request.DevicePersonRequest;
import com.deviceomi.payload.response.DevicePersonResponse;
import com.deviceomi.repository.DevicePersonRepository;
import com.deviceomi.repository.UserRepository;
import com.deviceomi.search.DevicePersonSearch;
import com.deviceomi.service.DevicePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DevicePersonServieceImpl implements DevicePersonService {
    @Autowired
    private DevicePersonRepository devicePersonRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @return List<DevicePersonResponse>
     */
    @Override
    public List<DevicePersonResponse> findAll() {
        List<DevicePersonEntity> devicePersonEntities=devicePersonRepository.findAll();
        List<DevicePersonResponse> devicePersons=new ArrayList<>();
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
    public List<DevicePersonResponse> create(DevicePersonRequest devicePersonRequest) {
        List<DevicePersonResponse> devicePersonResponses=findAll();
        UserEntity userEntity=userRepository.findById(devicePersonRequest.getIdUser()).orElse(null);

        if (devicePersonRepository.findByIdDevice(devicePersonRequest.getIdDevice())==null && userEntity!=null){
            DevicePersonEntity devicePersonEntity=devicePersonRequest.toDeviceEntity(userEntity);
            devicePersonRepository.save(devicePersonEntity);
            devicePersonResponses.add(0,new DevicePersonResponse(devicePersonEntity));
            return devicePersonResponses;
        }
        return null;
    }

    /**
     * Update Device Person
     * @param devicePersonRequest
     */
    @Override
    public boolean update(DevicePersonRequest devicePersonRequest) {
//        DevicePersonEntity devicePersonEntity = devicePersonRepository.findByIdDevice(devicePersonRequest.getIdDevice());
//        UserEntity userEntity=userRepository.findById(devicePersonRequest.getIdUser()).orElse(new UserEntity());
//        if(devicePersonEntity!=null) {
//            devicePersonRepository.save(devicePersonRequest.toDeviceEntity(devicePersonEntity));
//            return true;
//        }
//        return false;
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
