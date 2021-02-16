package com.deviceomi.service.impl;

import com.deviceomi.model.HistoryEntity;
import com.deviceomi.payload.response.HistoryResponse;
import com.deviceomi.repository.HistoryRepository;
import com.deviceomi.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public List<HistoryResponse> findAllHistory() {
        List<HistoryResponse> historyResponse=new ArrayList<>();
        List<HistoryEntity> historyEntities=historyRepository.findAll();
        historyEntities.stream().map(history-> new HistoryResponse(history)).forEach(historyResponse::add);
//        Collections.reverse(historyEntities);
        return historyResponse;
    }
}
