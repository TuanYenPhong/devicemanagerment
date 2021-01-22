package com.deviceomi.payload.request;

import com.deviceomi.model.DeviceEntity;
import com.deviceomi.model.RepairEntity;
import com.deviceomi.util.FormatDate;
import lombok.Data;

@Data
public class RepairRequest {
    private Long id;
    private Long idDevice;
    private Integer status;
    private String description;
    private String dateRepair;
    private String dateFinish;
    private DeviceEntity entities;

    public RepairEntity toEntity(RepairEntity repairEntity){
        if (getId() != null){
            repairEntity.setId(getId());
        }
        if(getDescription() != null)
            repairEntity.setDescription(getDescription());
        if(getDateRepair() != null)
            repairEntity.setDateRepair(FormatDate.stringToDate(getDateRepair()));
        if(getDateFinish() != null)
            repairEntity.setDateFinish(FormatDate.stringToDate(getDateFinish()));
        if(getStatus() != null){
            repairEntity.setStatus(getStatus());
        }
        return repairEntity;
    }

    public RepairEntity toEntity(){
        RepairEntity repairEntity = new RepairEntity();
        if (getId() != null){
            repairEntity.setId(getId());
        }
        if(getDescription() != null)
            repairEntity.setDescription(getDescription());
        if(getDateRepair() != null)
            repairEntity.setDateRepair(FormatDate.stringToDate(getDateRepair()));
        if(getDateFinish() != null)
            repairEntity.setDateFinish(FormatDate.stringToDate(getDateFinish()));
        if(getStatus() != null){
            repairEntity.setStatus(getStatus());
        }
        return repairEntity;
    }
}
