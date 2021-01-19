package com.deviceomi.payload.request;

import com.deviceomi.model.DeviceEntity;
import com.deviceomi.payload.DeviceBase;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class DeviceCustomerRequest extends DeviceBase {

    /**
     * Ngày bàn giao
     */
    private Date dateIssue;

    /**
     * Ngày thu hồi
     */
    private Date dateReturn;

    /**
     * Ngày trả khách hàng
     */
    private Date dateReturnCustomer;


    @Override
    public DeviceEntity toEntity() {
        DeviceEntity deviceEntity = super.toEntity();

        deviceEntity.setDateIssue(getDateIssue());
        deviceEntity.setDateReturn(getDateReturn());
        deviceEntity.setDateReturnCustomer(getDateReturnCustomer());

        return deviceEntity;
    }
}
