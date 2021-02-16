package com.deviceomi.controller;

import com.deviceomi.payload.request.DevicePersonRequest;
import com.deviceomi.payload.response.BorrowResponse;
import com.deviceomi.payload.response.DevicePersonResponse;
import com.deviceomi.search.DevicePersonSearch;
import com.deviceomi.service.DevicePersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/device_person")
@Api(value = "Device Person APIs")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public class DevicePersonController {
    @Autowired
    private DevicePersonService devicePersonService;

    @ApiOperation(value = "Hiển thị tất cả thông tin device cá nhân")
    @GetMapping("")
    public ResponseEntity getAllDevicePerson(){
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
    public ResponseEntity create(@ApiParam("Thêm device cá nhân") @Valid @RequestBody DevicePersonRequest devicePersonRequest){
        try{
            devicePersonService.create(devicePersonRequest);
            return new ResponseEntity<>(devicePersonRequest, HttpStatus.CREATED);
        } catch (Exception e){
            System.out.println("loi");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Chỉnh sửa device cá nhân")
    @PutMapping("")
    public ResponseEntity update(@ApiParam("Chỉnh sửa device cá nhân") @Valid @RequestBody DevicePersonRequest devicePersonRequest){
//        try {
//            if(devicePersonService.update(devicePersonRequest)) return new ResponseEntity(HttpStatus.OK);
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }catch (Exception e){return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);}
        if(devicePersonService.update(devicePersonRequest)) return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Tìm kiếm device cá nhân theo các tiêu chí khác nhau")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/search")
    public ResponseEntity search(@ApiParam("Tìm kiếm device cá nhân")@RequestBody DevicePersonSearch devicePersonSearch){
        try {
            List<DevicePersonResponse> devicePersonResponse=devicePersonService.search(devicePersonSearch);
            if(!devicePersonResponse.isEmpty()) return new ResponseEntity(devicePersonResponse,HttpStatus.OK);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);}
    }

    @ApiOperation(value = "Xóa device cá nhân")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDevicePerson(@ApiParam("Xóa device cá nhân") @PathVariable Long id){
        try {
            if(devicePersonService.delete(id)) return new ResponseEntity(HttpStatus.OK);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Tìm device cá nhân dựa vào id")
    @GetMapping("/{id}")
    public ResponseEntity getBorrowById(@ApiParam(value = "Tìm mượn trả dựa vào id") @PathVariable("id") Long id){
        DevicePersonResponse devicePersonResponse = devicePersonService.getDeviceById(id);
        try{
            if(devicePersonResponse != null){
                return new ResponseEntity<>(devicePersonResponse, HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
