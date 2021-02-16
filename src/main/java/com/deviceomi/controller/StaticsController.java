package com.deviceomi.controller;

import com.deviceomi.model.RepairEntity;
import com.deviceomi.payload.response.DeviceResponse_;
import com.deviceomi.payload.response.RepairResponse;
import com.deviceomi.search.DeviceWorkSearch;
import com.deviceomi.search.RepairSearch;
import com.deviceomi.service.DeviceService;
import com.deviceomi.service.RepairService;
import com.deviceomi.util.ExcelUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Api(value = "Statics APIs")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public class StaticsController {
    @Autowired
    RepairService repairService;
    @Autowired
    DeviceService deviceService;
    /**
     * thống kê sửa chữa
     * */
    @PostMapping("/statics/export/repair")
    public ResponseEntity<?> repairExportToExcel(@Valid @RequestBody RepairSearch repairSearch/*, HttpServletResponse response*/) throws IOException {
        List<RepairResponse> repairs = null;
        repairs = repairService.search(repairSearch);
        ByteArrayInputStream in = ExcelUtil.exportRepair(repairs);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=repair.xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @PostMapping("/statics/search/repair")
    public ResponseEntity<?> searchRepair(@Valid @RequestBody RepairSearch repairSearch) {
        List<RepairResponse> repairs = null;
        if(repairSearch == null){
            repairs = repairService.findAll();
        }else {
            repairs = repairService.search(repairSearch);
        }
        return new ResponseEntity<>(repairs, HttpStatus.OK);
    }

    /**
     * thống kê thiết bị
     * */
    @PostMapping("/statics/export/device")
    public ResponseEntity<?> deviceExportToExcel(@Valid @RequestBody DeviceWorkSearch deviceWorkSearch) throws IOException {
        List<DeviceResponse_> deviceResponse_s = null;
        deviceResponse_s = deviceService.searchDevice(deviceWorkSearch);
        ByteArrayInputStream in = ExcelUtil.exportDevice(deviceResponse_s);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=device.xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @PostMapping("/statics/search/device")
    public ResponseEntity<?> searchDevice(@Valid @RequestBody DeviceWorkSearch deviceWorkSearch) {
        List<DeviceResponse_> deviceResponse_s = null;
        if(deviceWorkSearch == null){
            deviceResponse_s = deviceService.getDevices();
        }else {
            deviceResponse_s = deviceService.searchDevice(deviceWorkSearch);
        }
        return new ResponseEntity<>(deviceResponse_s, HttpStatus.OK);
    }
}
