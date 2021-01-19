package com.deviceomi.payload.request;

import com.deviceomi.model.DeviceEntity;
import com.deviceomi.payload.DeviceBase;
import com.deviceomi.util.FormatDate;
import lombok.Data;

import java.util.Date;

@Data
public class DeviceRequest extends DeviceBase {
    /**
     * Thiết bị đi kèm
     */
    private String deviceInclude;

    /**
     * Ngày cấp gần nhất
     */
    private String dateRange;

    /**
     * Người quản lý / vị trí
     */
    private String deviceManager;

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

        if(getDeviceInclude() != null)
            deviceEntity.setDeviceInclude(getDeviceInclude());
        if(getDateRange() != null)
            deviceEntity.setDateRange(FormatDate.stringToDate(getDateRange()));
        if(getDeviceManager() != null)
            deviceEntity.setDeviceManager(getDeviceManager());

        if(getDateIssue() != null)
            deviceEntity.setDateIssue(getDateIssue());
        if(getDateReturn() != null)
            deviceEntity.setDateReturn(getDateReturn());
        if(getDateReturnCustomer() != null)
            deviceEntity.setDateReturnCustomer(getDateReturnCustomer());

        return deviceEntity;
    }
}
