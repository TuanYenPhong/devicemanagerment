package com.deviceomi.payload.response;

import com.deviceomi.model.DeviceEntity;
import com.deviceomi.payload.DeviceBase;
import com.deviceomi.util.FormatDate;
import lombok.Data;

import java.util.Date;

@Data
public class DeviceResponse_ extends DeviceBase {
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
    public DeviceResponse_ toResponse(DeviceEntity deviceEntity) {
        super.toResponse(deviceEntity);
        if(deviceEntity.getDeviceInclude() != null)
            setDeviceInclude(deviceEntity.getDeviceInclude());
        if(deviceEntity.getDateRange() != null)
            setDateRange(FormatDate.dateToString(deviceEntity.getDateRange()));
        if(deviceEntity.getDeviceManager() != null)
            setDeviceManager(deviceEntity.getDeviceManager());

        if(deviceEntity.getDateIssue() != null)
            setDateIssue(deviceEntity.getDateIssue());
        if(deviceEntity.getDateReturn() != null)
            setDateReturn(deviceEntity.getDateReturn());
        if(deviceEntity.getDateReturnCustomer() != null)
            setDateReturnCustomer(deviceEntity.getDateReturnCustomer());

        return this;
    }
}
