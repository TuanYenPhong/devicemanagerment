package com.deviceomi.repository;

import com.deviceomi.model.DevicePersonEntity;
import com.deviceomi.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface DevicePersonRepository extends JpaRepository<DevicePersonEntity,Long> {

    @Query("SELECT d FROM DevicePersonEntity d WHERE d.idDevice like %?1% and d.typeDevice like %?2% and d.deviceAttach like %?3% and" +
            "(?4 is null or d.userDevices.id = ?4) and (?5 is null or d.modifiedDate>?5) and (?6 is null or d.modifiedDate<?6)")
    List<DevicePersonEntity> sreachByAll(String idDevice, String typeDevice, String deviceAttach, Long idUser,Date fromDate,Date toDate);

    DevicePersonEntity findByIdDevice(String idDevice);

}
