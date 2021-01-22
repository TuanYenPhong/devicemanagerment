package com.deviceomi.controller;

import com.deviceomi.model.RepairEntity;
import com.deviceomi.payload.request.RepairRequest;
import com.deviceomi.payload.response.RepairResponse;
import com.deviceomi.search.RepairSearch;
import com.deviceomi.service.RepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Api(value = "Repair APIs")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public class RepairController {

    @Autowired
    RepairService repairService;

    @ApiOperation(value = "Tạo thiết bị sửa chữa")
    @PostMapping("/repair")
    public void createRepair(@ApiParam(value = "Tạo thiết bị sửa chữa")@RequestBody RepairRequest repairRequest){
        repairService.saveOrUpdate(repairRequest);
    }

    @ApiOperation(value = "Sửa thiết bị sửa chữa")
    @PutMapping("/repair")
    public void updateRepair(@ApiParam(value = "Sửa thiết bị sửa chữa")@RequestBody RepairRequest repairRequest){
        repairService.saveOrUpdate(repairRequest);
    }

    @ApiOperation(value = "Xóa thiết bị sửa chữa")
    @DeleteMapping("/repair/{id}")
    public ResponseEntity delete(@ApiParam(value = "Xóa thiết bị sửa chữa")@PathVariable Long id){
        if (id == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        repairService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Lấy thiết bị sửa chữa theo pageIndex")
    @GetMapping("/repair/page")
    public ResponseEntity<?> getRepairs(@ApiParam(value = "Lấy thiết bị sửa chữa theo pageIndex")@RequestParam(value = "pageIndex", defaultValue = "10") Integer pageIndex, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        Page<RepairEntity> repairs = repairService.paginationRepair(PageRequest.of(pageIndex - 1, pageSize));
        int totalPage = repairs.getTotalPages();
        List<RepairResponse> repairResponses = new ArrayList<>();
        repairs.stream().map(repair -> new RepairResponse(repair)).forEach(repairResponses::add);
        //repairResponses.forEach(repairResponse -> repairResponse.setTotalPage(totalPage));
        repairs.nextOrLastPageable();
        return new ResponseEntity<>(repairResponses, HttpStatus.OK);
    }

    @ApiOperation(value = "Lấy tất cả thiết bị sửa chữa")
    @GetMapping("/repairs")
    public ResponseEntity<?> getAllRepair(){
        List<RepairResponse> repairResponses = repairService.findAll();
        HttpStatus httpStatus =  repairResponses != null ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(repairResponses, httpStatus);
    }

    @ApiOperation(value = "Tìm thiết bị sửa chữa")
    @GetMapping("/repair/search")
    public ResponseEntity<?> searchRepairs(@ApiParam(value = "Tìm thiết bị sửa chữa")@RequestBody RepairSearch repairSearch){
        List<RepairResponse> repairResponses = repairService.search(repairSearch);
        return new ResponseEntity<>(repairResponses, HttpStatus.OK);
    }

    @ApiOperation(value = "Tìm thiết bị sửa chữa theo id")
    @GetMapping("/repair/{id}")
    public ResponseEntity<RepairResponse> searchRepairsById(@ApiParam(value = "Tìm thiết bị sửa chữa theo id")@PathVariable Long id){
        RepairResponse repairResponse = repairService.getRepairById(id);
        return new ResponseEntity<>(repairResponse, HttpStatus.OK);
    }
}
