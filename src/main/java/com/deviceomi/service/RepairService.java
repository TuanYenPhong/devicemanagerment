package com.deviceomi.service;

import com.deviceomi.model.RepairEntity;
import com.deviceomi.payload.request.RepairRequest;
import com.deviceomi.payload.response.RepairResponse;
import com.deviceomi.search.RepairSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RepairService {
    void saveOrUpdate(RepairRequest repairRequest);
    void delete(Long id);
    Page<RepairEntity> paginationRepair(Pageable pageable);
    List<RepairResponse> findAll();
    List<RepairResponse> search(RepairSearch repairSearch);
    RepairResponse getRepairById(Long id);
}
