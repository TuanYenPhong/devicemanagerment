package com.deviceomi.payload;

import com.deviceomi.model.DeviceEntity;
import com.deviceomi.util.FormatDate;
import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class DeviceBase {
    private Long id;
    /**
     * Loại thiết bị
     */
    private String typeDevice;

    /**
     * Mã thiết bị
     */
    private String codeDevice;

    /**
     * Mô tả
     */
    private String description;

    /**
     * Ngày nhập kho
     */
    private String inputDay;

    /**
     * Thời hạn bảo hành
     */
    private String guarantee;

    /**
     * Đơn vị cung cấp
     */
    private String supplyUnit;

    /**
     * Thời gian bàn giao lần đâu
     */
    private String firstTime;

    private Integer classificationDevice;

    public DeviceEntity toEntity() {

        DeviceEntity deviceEntity = new DeviceEntity();

        if (getId() != null){
            deviceEntity.setId(getId());
        }
        deviceEntity.setTypeDevice(getTypeDevice());
        deviceEntity.setCodeDevice(getCodeDevice());
        deviceEntity.setDescription(getDescription());
        if(getInputDay() != null)
            deviceEntity.setInputDay(FormatDate.stringToDate(getInputDay()));
        deviceEntity.setGuarantee(getGuarantee());
        deviceEntity.setSupplyUnit(getSupplyUnit());
        if(getFirstTime() != null)
        deviceEntity.setFirstTime(FormatDate.stringToDate(getFirstTime()));
        deviceEntity.setClassificationDevice(getClassificationDevice());
        return deviceEntity;
    }

    public DeviceBase toResponse (DeviceEntity deviceEntity){
        if (deviceEntity.getId() != null){
            setId(deviceEntity.getId());
        }
        if(deviceEntity.getTypeDevice()!=null)
            setTypeDevice(deviceEntity.getTypeDevice());
        if(deviceEntity.getCodeDevice()!=null)
            setCodeDevice(deviceEntity.getCodeDevice());
        if(deviceEntity.getDescription()!=null)
            setDescription(deviceEntity.getDescription());
        if(deviceEntity.getInputDay()!=null)
            setInputDay(FormatDate.dateToString(deviceEntity.getInputDay()));
        if(deviceEntity.getGuarantee()!=null)
            setGuarantee(deviceEntity.getGuarantee());
        if(deviceEntity.getSupplyUnit()!=null)
            setSupplyUnit(deviceEntity.getSupplyUnit());
        if(deviceEntity.getFirstTime()!=null)
            setFirstTime(FormatDate.dateToString(deviceEntity.getFirstTime()));
        if(deviceEntity.getClassificationDevice()!=null)
            setClassificationDevice(deviceEntity.getClassificationDevice());
        return this;
    }
}
