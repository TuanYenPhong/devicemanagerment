package com.deviceomi.repository;

import com.deviceomi.model.DeviceEntity;
import com.deviceomi.model.RepairEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
    List<DeviceEntity> findByCodeDevice(String codeDevice);
    //DeviceEntity findOneByCodeDevice(String codeDevice);
//    <T> Page<T> getAllDevice();
    @Query("SELECT rp FROM DeviceEntity rp WHERE 1 = 1 and ( ?1 is null or rp.codeDevice like %?1%) and ( ?2 is null or rp.typeDevice like %?2%)")
    List<DeviceEntity> getDeviceSearch(String codeDevice, String typeDevice);
}
