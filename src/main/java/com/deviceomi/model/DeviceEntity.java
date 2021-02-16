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
     * Thông số thiết bị
     */
    @Column(name="description")
    private String description;

    /**
     * Trạng thái thiết bị
     * */
    @Column(name = "status")
    private Integer status;

    /**
     * Tình trạng thiết bị
     * */
    @Column(name = "states")
    private Integer states;

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

    /*
    *  Phần thiết bị
    * */

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

    /*
     *  Phần thiết bị làm việc
     * */

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
     * Thông tin tin bổ sung
     */
    @Column(name="additionalInformation")
    private String additionalInformation;

    /*
    * Thiết bị khách
    * */

    /**
     * Ngày nhận thiết bị
     * */
    @Column(name="dateReceiveDevice")
    private Date dateReceiveDevice;

    /**
     * Ngày bàn giao cho đội dự án
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

    /*
    * cái này dùng chung
    * */
    /**
     * Người quản lý / vị trí
     */
    @Column(name="deviceManager")
    private String deviceManager;

    @Column(name="isLive")
    private Integer isLive;

    @JsonIgnore
    @OneToMany(mappedBy = "deviceBorrow")
    private Set<BorrowEntity> borrowDevices = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "deviceRepair")
    private Set<RepairEntity> repairDevices = new HashSet<>();
}
