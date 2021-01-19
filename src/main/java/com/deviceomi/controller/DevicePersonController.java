package com.deviceomi.controller;

import com.deviceomi.payload.request.DevicePersonRequest;
import com.deviceomi.payload.response.DevicePersonResponse;
import com.deviceomi.search.DevicePersonSearch;
import com.deviceomi.service.DevicePersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/device_person")
@Api(value = "Device Person APIs")
public class DevicePersonController {
    @Autowired
    private DevicePersonService devicePersonService;

    @ApiOperation(value = "Hiển thị tất cả thông tin device cá nhân")
    @GetMapping("")
    private ResponseEntity getAllDevicePerson(){
        try {
            List<DevicePersonResponse> devicePerson=devicePersonService.findAll();
            if(!devicePerson.isEmpty()) return new ResponseEntity(devicePerson, HttpStatus.OK);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Thêm device cá nhân")
    @PostMapping("")
    private ResponseEntity create(@ApiParam("Thêm device cá nhân") @Valid @RequestBody DevicePersonRequest devicePersonRequest){
        try {
            List<DevicePersonResponse> devicePersonResponse=devicePersonService.create(devicePersonRequest);
            if(!devicePersonResponse.isEmpty())
                return new ResponseEntity(devicePersonResponse, HttpStatus.OK);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){ return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR); }

    }

    @ApiOperation(value = "Chỉnh sửa device cá nhân")
    @PutMapping("")
    private ResponseEntity update(@ApiParam("Chỉnh sửa device cá nhân") @Valid @RequestBody DevicePersonRequest devicePersonRequest){
        try {
            return new ResponseEntity(devicePersonService.update(devicePersonRequest),HttpStatus.NO_CONTENT);
        }catch (Exception e){return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);}
    }

    @ApiOperation(value = "Tìm kiếm device cá nhân theo các tiêu chí khác nhau")
    @GetMapping("/search")
    private ResponseEntity search(@RequestBody DevicePersonSearch devicePersonSearch){
        try {
            List<DevicePersonResponse> devicePersonResponse=devicePersonService.search(devicePersonSearch);
            if(!devicePersonResponse.isEmpty()) return new ResponseEntity(devicePersonResponse,HttpStatus.OK);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);}
    }

}
