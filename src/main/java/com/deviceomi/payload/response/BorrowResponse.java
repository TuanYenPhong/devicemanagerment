package com.deviceomi.payload.response;

import com.deviceomi.model.BorrowEntity;
import com.deviceomi.payload.request.BorrowRequest;
import com.deviceomi.util.FormatDate;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class BorrowResponse {
    private Long id;

    private String codeDevice;

    private String typeDevice;

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

    public BorrowResponse(BorrowEntity borrowEntity) {
        if(borrowEntity.getId()!=null){
            this.setId(borrowEntity.getId());
        }
        if(borrowEntity.getDeviceBorrow() !=null){
            this.setCodeDevice(borrowEntity.getDeviceBorrow().getCodeDevice());
            this.setTypeDevice(borrowEntity.getDeviceBorrow().getTypeDevice());
        }
        if(borrowEntity.getDeparmentBorrow() != null)
            this.setNameBorrow(borrowEntity.getDeparmentBorrow().getNameDepartment());
        else this.setNameBorrow(borrowEntity.getUserBorrow().getFullname());

        if (borrowEntity.getStatus() != null) this.setStatus(borrowEntity.getStatus());
        if(borrowEntity.getDateReturn() != null)   setDateReturn(FormatDate.dateToString(borrowEntity.getDateReturn()));
        this.setReson(borrowEntity.getReson());
        if(borrowEntity.getDateBorrow() != null){
            this.setDateBorrow(FormatDate.dateToString(borrowEntity.getDateBorrow()));
        }

    }
}



