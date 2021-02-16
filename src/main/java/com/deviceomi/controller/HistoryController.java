package com.deviceomi.controller;

import com.deviceomi.payload.response.HistoryResponse;
import com.deviceomi.service.HistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/history")
@Api(value = "History API")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @ApiOperation(value = "Lấy lịch sử thao tác")
    @GetMapping("")
    public ResponseEntity findAllHistory(){
        try {
            List<HistoryResponse> history=historyService.findAllHistory();
            if(!history.isEmpty()) return new ResponseEntity(history,HttpStatus.OK);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
