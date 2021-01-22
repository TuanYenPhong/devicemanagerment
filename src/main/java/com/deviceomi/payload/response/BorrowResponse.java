package com.deviceomi.payload.response;

import com.deviceomi.model.BorrowEntity;
import com.deviceomi.payload.request.BorrowRequest;
import com.deviceomi.util.FormatDate;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class BorrowResponse {
    private Long id;

    private String idDevice;

    private String typeDevice;

    private String description;

    private String reson;

    private String nameBorrow;

    /**
     * Ng�y mu?n
     * */
    private String dateBorrow;

    /**
     * Ng�y ho�n tr?
     * */
    private String dateReturn;

    private Integer status;

    private String region;

    public BorrowResponse(BorrowEntity borrowEntity) {
        setId(borrowEntity.getId());

        if (borrowEntity.getStatus() != null){
            setStatus(borrowEntity.getStatus());
        }

        if(borrowEntity.getDeviceBorrow() !=null){
            setIdDevice(borrowEntity.getDeviceBorrow().getCodeDevice());
            setTypeDevice(borrowEntity.getDeviceBorrow().getTypeDevice());
            setDescription(borrowEntity.getDeviceBorrow().getDescription());
        }
        if(borrowEntity.getDeparmentBorrow() != null){
            setNameBorrow(borrowEntity.getDeparmentBorrow().getNameDepartment());
        }
        setReson(borrowEntity.getReson());
        setDateBorrow(borrowEntity.getCreatedDate().toString());
        if(borrowEntity.getDateReturn() != null){
            setDateReturn(borrowEntity.getDateReturn().toString());
        }
    }
}



