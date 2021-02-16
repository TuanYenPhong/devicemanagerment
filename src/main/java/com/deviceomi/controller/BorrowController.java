package com.deviceomi.controller;

import com.deviceomi.model.BorrowEntity;
import com.deviceomi.payload.request.BorrowRequest;
import com.deviceomi.payload.response.BorrowResponse;
import com.deviceomi.payload.response.UserBorrowResponse;
import com.deviceomi.search.SearchBorrow;
import com.deviceomi.service.BorrowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Api(value = "Borrow APIs")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public class BorrowController {
    @Autowired
    BorrowService borrowService;

    @ApiOperation(value = "Tìm tất cả mượn trả")
    @GetMapping("/borrow")
    public ResponseEntity findAllBorrow(){

        try{
            HashMap hashMapBorrow = borrowService.findAllBorrow();
            if(hashMapBorrow.isEmpty()){
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(hashMapBorrow, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Tạo mượn trả")
    @PostMapping("/borrow")
    public ResponseEntity createBorrow(@ApiParam("Tạo mượn trả") @Valid @RequestBody BorrowRequest borrowRequest){
        try{
            borrowService.saveBorrow(borrowRequest);
            System.out.println("ok");
            return new ResponseEntity<>(borrowRequest, HttpStatus.CREATED);
        } catch (Exception e){
            System.out.println("loi");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Chỉnh sửa mượn trả")
    @PutMapping("/borrow")
    public ResponseEntity putBorrow(@ApiParam("Chỉnh sửa mượn trả") @Valid @RequestBody BorrowRequest borrowRequest){
//        try{
//            borrowService.updateBorrow(borrowRequest);
//            return new ResponseEntity<>( HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        borrowService.updateBorrow(borrowRequest);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @ApiOperation(value = "Tìm mượn trả dựa vào departmentid")
    @GetMapping("/borrow/{id}")
    public ResponseEntity findByDepartment(@PathVariable("id") Long id){
        try{
            List<BorrowEntity> listBorrow = borrowService.findByDeparmentId(id);
            if(listBorrow.isEmpty()){
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(listBorrow, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @ApiOperation(value = "Tìm mượn trả dựa vào departmentid")
//    @GetMapping("/borrows")
//    public ResponseEntity findParamDepartment(@RequestParam("id") Long id){
//
//        System.out.println("param = " + id);
//        return null;
//    }

    @GetMapping("/borrow_user")
    public ResponseEntity searchUserBorrow(){
        try {
            List<UserBorrowResponse> userBorrowResponse=borrowService.findAllUserBorrow();
            if(userBorrowResponse.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
            return new ResponseEntity(userBorrowResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Xóa mượn trả")
    @DeleteMapping("/borrow/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@ApiParam(value = "Xóa mượn trả") @PathVariable("id") Long id){
        try{
            borrowService.deleteBorrow(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Tìm mượn trả dựa vào id")
    @GetMapping("/borrow/id/{id}")
    public ResponseEntity getBorrowById(@ApiParam(value = "Tìm mượn trả dựa vào id") @PathVariable("id") Long id){
        BorrowResponse borrowResponse = borrowService.getBorrowById(id);
        try{
            if(borrowResponse != null){
                return new ResponseEntity<>(borrowResponse, HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Tìm mượn trả id")
    @GetMapping("/borrowid")
    public ResponseEntity getBorrow(){
        try{
            List<BorrowEntity> borrowEntity = borrowService.findBorrow();
            if(!borrowEntity.isEmpty()){
                return new ResponseEntity<>(borrowEntity, HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/borrow/search")
//    public ResponseEntity searchBorrow(SearchBorrow searchBorrow){
//        try{
//            List<BorrowResponse> borrows = borrowService.searchBorrow(searchBorrow);
//            if(!borrows.isEmpty()){
//                return new ResponseEntity<>(borrows, HttpStatus.OK);
//            } else{
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
