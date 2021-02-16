package com.deviceomi.payload.response;

import com.deviceomi.model.DeviceEntity;
import com.deviceomi.model.RepairEntity;
import com.deviceomi.util.FormatDate;
import lombok.Data;

@Data
public class RepairResponse {
    private Long id;
    private String description;
    private String dateRepair;
    private String dateFinish;
    private Integer status;
    private DeviceEntity entities;

    public RepairResponse(RepairEntity repairEntity) {
        if(repairEntity != null)
            this.id = repairEntity.getId();
            this.description = repairEntity.getDescription();
            if(repairEntity.getDateRepair() != null)
                this.dateRepair = FormatDate.dateToString(repairEntity.getDateRepair());
            if(repairEntity.getDateFinish() != null)
                this.dateFinish = FormatDate.dateToString(repairEntity.getDateFinish());
            if(repairEntity.getDeviceRepair() != null) {
                this.entities = repairEntity.getDeviceRepair();
            }
            if(repairEntity.getStatus() != null)
                this.status = repairEntity.getStatus();
    }
}
