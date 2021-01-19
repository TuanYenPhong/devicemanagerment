package com.deviceomi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="device")
@Data
public class DeviceEntity extends AuditEntity{
    /**
     * Loại thiết bị
     */
    @Column(name="typeDevice")
    private String typeDevice;

    /**
     * Mã thiết bị
     */
    @Column(name="codeDevice")
    private String codeDevice;

    /**
     * Mô tả
     */
    @Column(name="description")
    private String description;

    /**
     * Ngày nhập kho
     */
    @Column(name="inputDay")
    private Date inputDay;

    /**
     * Thời hạn bảo hành
     */
    @Column(name="guarantee")
    private String guarantee;

    /**
     * Đơn vị cung cấp
     */
    @Column(name="supplyUnit")
    private String supplyUnit;

    /**
     * Thời gian bàn giao lần đâu
     */
    @Column(name="firstTime")
    private Date firstTime;

    /**
     * Thiết bị đi kèm
     */
    @Column(name="deviceInclude")
    private String deviceInclude;

    /**
     * Ngày cấp gần nhất
     */
    @Column(name="dateRange")
    private Date dateRange;

    /**
     * Người quản lý / vị trí
     */
    @Column(name="deviceManager")
    private String deviceManager;

    /**
     * Ngày bàn giao
     */
    @Column(name="dateIssue")
    private Date dateIssue;

    /**
     * Ngày thu hồi
     */
    @Column(name="dateReturn")
    private Date dateReturn;

    /**
     * Ngày trả khách hàng
     */
    @Column(name="dateCustomer")
    private Date dateReturnCustomer;

    @JsonIgnore
    @OneToMany(mappedBy = "deviceBorrow")
    private Set<BorrowEntity> borrowDevices = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "deviceRepair")
    private Set<RepairEntity> repairDevices = new HashSet<>();

    @Column(name = "status")
    private Integer status;

    /**
     * xac dinh loai thiet bi nao vd : work, test ...
     * 0 : device
     * 1 : thiet bi lam viec
     * 2 : thiet bi test
     * 3 : tb khac
     * 4: tb khach hang
     * */
    @Column(name = "classification_device")
    private Integer classificationDevice;

    //convert to entity
//    public DeviceEntity toEntity(DeviceEntity deviceEntity){
//        typeDevice = deviceEntity.getTypeDevice();
//
//        codeDevice = deviceEntity.getCodeDevice();
//
//        description = deviceEntity.getDescription();
//
//        inputDay = deviceEntity.getInputDay();
//
//        guarantee = deviceEntity.getGuarantee();
//
//        supplyUnit = deviceEntity.getSupplyUnit();
//
//        firstTime = deviceEntity.getFirstTime();
//
//        deviceInclude = deviceEntity.getDeviceInclude();
//
//        dateRange = deviceEntity.getDateRange();
//
//        deviceManager = deviceEntity.getDeviceManager();
//
//        dateIssue = deviceEntity.getDateIssue();
//
//        dateReturn = deviceEntity.getDateReturn();
//
//        return this;
//    }
}
