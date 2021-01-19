package com.deviceomi.controller;

import com.deviceomi.model.DepartmentEntity;
import com.deviceomi.payload.request.DepartmentRequest;
import com.deviceomi.payload.response.DepartmentResponse;
import com.deviceomi.service.DeparmentService;
import com.deviceomi.service.impl.DepartmentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "Department APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DepartmentController {
    @Autowired
    private final DeparmentService departmentService;

    /**
     * Get All Department
     * */
    @CrossOrigin("http://localhost:4200")
    @ApiOperation(value = "Xem danh sách phòng ban", response = List.class)
    @GetMapping("/departments")
    public ResponseEntity<?> getAllDepartment(){
        try{
            List<DepartmentEntity> listDepartment = departmentService.findAllDeparment();
            if(listDepartment.isEmpty()){
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(listDepartment, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Tạo phòng ban")
    @PostMapping("/departments")
    public ResponseEntity createDepartment(@ApiParam(value = "Tạo phòng ban") @Valid @RequestBody DepartmentRequest departmentRequest){
        try{
            return new ResponseEntity<>(departmentService.createDepartment(departmentRequest), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Xóa phòng ban")
    @DeleteMapping("/departments/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@ApiParam(value = "Xóa phòng ban") @PathVariable("id") Long id){
        try{
            departmentService.deleteDepartment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value= "Lấy phòng ban bằng id")
    @GetMapping("/departments/{id}")
    public ResponseEntity<DepartmentEntity> getDpById(@ApiParam(value="Lấy phòng ban bằng id") @PathVariable("id") Long id){
        Optional<DepartmentEntity> dp = departmentService.getDepartmentById(id);
        if(dp.isPresent()){
            return new ResponseEntity<>(dp.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Sửa phòng ban")
    @PutMapping("/departments")
    public ResponseEntity editDp(@ApiParam(value="Sửa phòng ban") @Valid @RequestBody DepartmentRequest departmentRequest){
        try{
            departmentService.updateDepartment(departmentRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value= "Lấy phòng ban region")
    @GetMapping("/regiondepartments")
    public ResponseEntity getRegionDP(){
        try{
            List<DepartmentResponse> dp = departmentService.findRegionDepartment();
            if(dp.isEmpty()){
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(dp, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
