package com.deviceomi.search;

import lombok.Data;

@Data
public class RepairSearch {
    private String codeDevice;
    private String typeDevice;
    private String fromDateRepair;
    private String toDateRepair;
    private String fromDateFinish;
    private String toDateFinish;
    private Integer status;

}
