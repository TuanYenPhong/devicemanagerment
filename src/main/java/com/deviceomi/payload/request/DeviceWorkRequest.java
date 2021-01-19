package com.deviceomi.payload.request;

import com.deviceomi.model.DeviceEntity;
import com.deviceomi.payload.DeviceBase;
import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class DeviceWorkRequest extends DeviceBase {
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
     * Status nua
     */

    @Override
    public DeviceEntity toEntity() {
        DeviceEntity deviceEntity = super.toEntity();

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = null;
        try {
            startDate = df.parse(getDateRange());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        deviceEntity.setDeviceInclude(getDeviceInclude());
        deviceEntity.setDateRange(startDate);
        deviceEntity.setDeviceManager(getDeviceManager());

        return deviceEntity;
    }
}
