package com.deviceomi.controller;

import com.deviceomi.payload.request.DeviceRequest;
import com.deviceomi.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Api(value = "Device APIs")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

//    @ApiOperation(value = "Tạo thiết bị làm việc")
//    @PostMapping("/device")
//    public ResponseEntity<?> createDeviceWork(@ApiParam(value = "Tạo thiết bị làm việc") @Valid @RequestBody DeviceWorkRequest deviceWorkRequest) {
//        deviceService.saveOrUpdate(deviceWorkRequest);
//        return new ResponseEntity<>(deviceWorkRequest, HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "Cập nhập thiết bị làm việc")
//    @PutMapping("/device")
//    public ResponseEntity<?> updateDeviceWork(@ApiParam(value = "Cập nhập thiết bị làm việc") @Valid @RequestBody DeviceWorkRequest deviceWorkRequest) {
//        deviceService.saveOrUpdate(deviceWorkRequest);
//        return new ResponseEntity<>(deviceWorkRequest, HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "Hiển thị tất cả thiết bị làm việc")
//    @GetMapping("/device")
//    public ResponseEntity<?> showDeviceWork(@ApiParam(value = "Hiển thị tất cả thiết bị làm việc") @Valid @RequestBody DeviceWorkRequest deviceWorkRequest) {
//        deviceService.saveOrUpdate(deviceWorkRequest);
//        return new ResponseEntity<>(deviceWorkRequest, HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "Tạo thiết bị test")
//    @PostMapping("/testNew")
//    public ResponseEntity<?> createDeviceTest(@ApiParam(value = "Tạo thiết bị test") @Valid @RequestBody DeviceTestRequest deviceTestRequest) {
//        deviceService.saveOrUpdate(deviceTestRequest);
//        return new ResponseEntity<>(deviceTestRequest, HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "Cập nhập thiết bị làm việc")
//    @PutMapping("/testUpdate")
//    public ResponseEntity<?> updateDeviceTest(@ApiParam(value = "Cập nhập thiết bị test") @Valid @RequestBody DeviceTestRequest deviceTestRequest) {
//        deviceService.saveOrUpdate(deviceTestRequest);
//        return new ResponseEntity<>(deviceTestRequest, HttpStatus.OK);
//    }

    @ApiOperation(value = "Tạo thiết bị")
    @PostMapping("/device")
    public ResponseEntity<?> createDeviceWork(@ApiParam(value = "Tạo thiết bị") @Valid @RequestBody DeviceRequest deviceRequest) {
        try{
            if (deviceService.save(deviceRequest) != null)
                return new ResponseEntity<>(deviceRequest, HttpStatus.OK);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Cập nhập thiết bị")
    @PutMapping("/device")
    public ResponseEntity<?> updateDeviceWork(@ApiParam(value = "Cập nhập thiết bị") @Valid @RequestBody DeviceRequest deviceRequest) {
        try{
            if (deviceService.save(deviceRequest) != null)
                return new ResponseEntity<>(deviceRequest, HttpStatus.OK);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Xóa thiết bị")
    @DeleteMapping("/device/{id}")
    public void deleteDevice(@PathVariable Long id) {
        if (id == null)
            return;
        deviceService.delete(id);
    }

    /**
     * tuan
     * show all device database
     * */
    @ApiOperation(value = "Hiển thị tất cả thiết bị cho mượn trả và sửa chưa")
    @GetMapping("/device/all")
    public ResponseEntity<?> showDeviceAll() {
        try{
            if (deviceService.getAllDevice() != null)
                return new ResponseEntity<>(deviceService.getAllDevice(), HttpStatus.OK);
            else
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Hiển thị tất cả thiết bị")
    @GetMapping("/devices")
    public ResponseEntity<?> showDevices() {
        try{
            if (deviceService.getDevices() != null)
                return new ResponseEntity<>(deviceService.getDevices(), HttpStatus.OK);
            else
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Lấy thiết bị theo ID")
    @GetMapping("/device/{id}")
    public ResponseEntity<?> showDeviceById(@ApiParam(value = "Lấy thiết bị theo ID")@PathVariable Long id) {
        return new ResponseEntity<>(deviceService.getDeviceById(id), HttpStatus.OK);
    }
}
