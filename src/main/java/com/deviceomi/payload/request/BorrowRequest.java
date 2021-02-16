package com.deviceomi.payload.request;

import com.deviceomi.model.BorrowEntity;
import com.deviceomi.model.DeviceEntity;
import com.deviceomi.util.FormatDate;
import lombok.Data;

import javax.persistence.Column;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Data
public class BorrowRequest{

    private Long id;
    /**
     * */
    private String reson;

    /**
     * Ngày hoàn trả
     * */

    private String description;

    private String dateBorrow;

    private String dateReturn;

    private Long idDevice;

    private Long idDepartment;

    private Long userId;

    private Integer status;

    public BorrowEntity toEntity(BorrowEntity borrowEntity){
        if(getId() != null) borrowEntity.setId(getId());
        if(this.getStatus() != null) borrowEntity.setStatus(this.getStatus());
        if(this.getDateReturn() != null)
            borrowEntity.setDateReturn(FormatDate.stringToDate(this.getDateReturn()));
        borrowEntity.setReson(this.getReson());
        return borrowEntity;
    }

    public BorrowEntity toEntity(){
        BorrowEntity borrowEntity = new BorrowEntity();
        borrowEntity.setReson(getReson());
        if(getDateReturn() != null) borrowEntity.setDateReturn(FormatDate.stringToDate(getDateReturn()));
        if(getDateBorrow()!=null) borrowEntity.setDateBorrow(FormatDate.stringToDate(getDateBorrow()));
        else borrowEntity.setDateBorrow(FormatDate.stringToDate(LocalDate.now().toString()));
        if(getStatus() != null) borrowEntity.setStatus(getStatus());
        return borrowEntity;
    }
}
