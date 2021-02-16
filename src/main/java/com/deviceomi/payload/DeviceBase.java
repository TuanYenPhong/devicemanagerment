package com.deviceomi.payload;

import com.deviceomi.model.DeviceEntity;
import com.deviceomi.util.FormatDate;
import lombok.Data;

import javax.persistence.Column;
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
     * Thông số thiết bị
     */
    private String description;

    /**
     * Trạng thái thiết bị
     * */
    private Integer status;

    /**
     * Tình trạng thiết bị
     * */
    private Integer states;

    /**
     * xac dinh loai thiet bi nao vd : work, test ...
     * 0 : device
     * 1 : thiet bi lam viec
     * 2 : thiet bi test
     * 3 : tb khac
     * 4: tb khach hang
     * */
    private Integer classificationDevice;

    /*
     *  Phần thiết bị
     * */

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

    /*
     *  Phần thiết bị làm việc
     * */

    /**
     * Thiết bị đi kèm
     */
    private String deviceInclude;

    /**
     * Ngày cấp gần nhất
     */
    private String dateRange;

    /**
     * Thồn tin bổ sung
     */
    private String additionalInformation;

    /*
     * Thiết bị khách
     * */

    /**
     * Ngày nhận thiết bị
     * */
    private String dateReceiveDevice;

    /**
     * Ngày bàn giao cho đội dự án
     */
    private String dateIssue;

    /**
     * Ngày thu hồi
     */
    private String dateReturn;

    /**
     * Ngày trả khách hàng
     */
    private String dateReturnCustomer;

    /*
     * cái này dùng chung
     * */
    /**
     * Người quản lý / vị trí
     */
    private String deviceManager;

    public DeviceEntity toEntity() {

        DeviceEntity deviceEntity = new DeviceEntity();

        if (getId() != null){
            deviceEntity.setId(getId());
        }

        if (getTypeDevice() != null)
            deviceEntity.setTypeDevice(getTypeDevice());
        if (getCodeDevice() != null)
            deviceEntity.setCodeDevice(getCodeDevice().trim());
        if (getDescription() != null)
            deviceEntity.setDescription(getDescription());
        if (getStatus() != null)
            deviceEntity.setStatus(getStatus());
        if (getStates() != null)
            deviceEntity.setStates(getStates());
        if (getClassificationDevice() != null)
            deviceEntity.setClassificationDevice(getClassificationDevice());
        if (getInputDay() != null)
            deviceEntity.setInputDay(FormatDate.stringToDate(getInputDay()));
        if (getGuarantee() != null)
            deviceEntity.setGuarantee(getGuarantee());
        if (getSupplyUnit() != null)
            deviceEntity.setSupplyUnit(getSupplyUnit());
        if (getFirstTime() != null)
            deviceEntity.setFirstTime(FormatDate.stringToDate(getFirstTime()));
        if (getDeviceInclude() != null)
            deviceEntity.setDeviceInclude(getDeviceInclude());
        if (getDateRange() != null)
            deviceEntity.setDateRange(FormatDate.stringToDate(getDateRange()));
        if (getAdditionalInformation() != null)
            deviceEntity.setAdditionalInformation(getAdditionalInformation());
        if (getDateReceiveDevice() != null)
            deviceEntity.setDateReceiveDevice(FormatDate.stringToDate(getDateReceiveDevice()));
        if (getDateIssue() != null)
            deviceEntity.setDateIssue(FormatDate.stringToDate(getDateIssue()));
        if (getDateReturn() != null)
            deviceEntity.setDateReturn(FormatDate.stringToDate(getDateReturn()));
        if (getDateReturnCustomer() != null)
            deviceEntity.setDateReturnCustomer(FormatDate.stringToDate(getDateReturnCustomer()));
        if (getDeviceManager() != null)
            deviceEntity.setDeviceManager(getDeviceManager());

            deviceEntity.setIsLive(1);
        return deviceEntity;
    }

    public DeviceBase toResponse (DeviceEntity deviceEntity){
        if (deviceEntity.getId() != null){
            setId(deviceEntity.getId());
        }

        if (deviceEntity.getTypeDevice() != null)
            setTypeDevice(deviceEntity.getTypeDevice());
        if (deviceEntity.getCodeDevice() != null)
            setCodeDevice(deviceEntity.getCodeDevice());
        if (deviceEntity.getDescription() != null)
            setDescription(deviceEntity.getDescription());
        if (deviceEntity.getStatus() != null)
            setStatus(deviceEntity.getStatus());
        if (deviceEntity.getStates() != null)
            setStates(deviceEntity.getStates());
        if (deviceEntity.getClassificationDevice() != null)
            setClassificationDevice(deviceEntity.getClassificationDevice());
        if (deviceEntity.getInputDay() != null)
            setInputDay(FormatDate.dateToString(deviceEntity.getInputDay()));
        if (deviceEntity.getGuarantee() != null)
            setGuarantee(deviceEntity.getGuarantee());
        if (deviceEntity.getSupplyUnit() != null)
            setSupplyUnit(deviceEntity.getSupplyUnit());
        if (deviceEntity.getFirstTime() != null)
            setFirstTime(FormatDate.dateToString(deviceEntity.getFirstTime()));
        if (deviceEntity.getDeviceInclude() != null)
            setDeviceInclude(deviceEntity.getDeviceInclude());
        if (deviceEntity.getDateRange() != null)
            setDateRange(FormatDate.dateToString(deviceEntity.getDateRange()));
        if (deviceEntity.getAdditionalInformation() != null)
            setAdditionalInformation(deviceEntity.getAdditionalInformation());
        if (deviceEntity.getDateReceiveDevice() != null)
            setDateReceiveDevice(FormatDate.dateToString(deviceEntity.getDateReceiveDevice()));
        if (deviceEntity.getDateIssue() != null)
            setDateIssue(FormatDate.dateToString(deviceEntity.getDateIssue()));
        if (deviceEntity.getDateReturn() != null)
            setDateReturn(FormatDate.dateToString(deviceEntity.getDateReturn()));
        if (deviceEntity.getDateReturnCustomer() != null)
            setDateReturnCustomer(FormatDate.dateToString(deviceEntity.getDateReturnCustomer()));
        if (deviceEntity.getDeviceManager() != null)
            setDeviceManager(deviceEntity.getDeviceManager());
        return this;
    }
}
