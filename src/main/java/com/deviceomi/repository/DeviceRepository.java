package com.deviceomi.repository;

import com.deviceomi.model.DeviceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
    List<DeviceEntity> findByCodeDevice(String codeDevice);
    //DeviceEntity findOneByCodeDevice(String codeDevice);
//    <T> Page<T> getAllDevice();
}
