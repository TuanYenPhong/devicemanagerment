package com.deviceomi.service;

import com.deviceomi.payload.response.HistoryResponse;

import java.util.List;

public interface HistoryService{
    List<HistoryResponse> findAllHistory();
}
