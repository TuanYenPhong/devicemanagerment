package com.deviceomi.repository;

import com.deviceomi.model.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface RepairRepository extends JpaRepository<RepairEntity, Long> {
    @Query("SELECT rp FROM RepairEntity rp WHERE rp.deviceRepair.codeDevice like %?1% and rp.deviceRepair.typeDevice like %?2% and " +
            "(?3 is null or rp.dateRepair > ?3) and (?4 is null or rp.dateRepair<?4) and (?5 is null or rp.dateFinish > ?5) and (?6 is null or rp.dateFinish<?6) and (?7 is null or rp.deviceRepair.status = ?7)")
    List<RepairEntity> getRepairSearch(String codeDevice, String typeDevice, Date fromDateRepair, Date toDateRepair, Date fromDateFinish, Date toDateFinish, Integer status);
}
