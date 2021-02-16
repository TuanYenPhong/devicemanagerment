package com.deviceomi.service;

import com.deviceomi.payload.response.RepairResponse;
import com.deviceomi.search.RepairSearch;

import java.util.List;

public interface StaticsService {
    List<RepairResponse> searchRepair(RepairSearch repairSearch);
    //exportRepair();
}
