package com.deviceomi.search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeviceWorkSearch {
    private String typeDevice;
    private String codeDevice;
    private String status;
    private String fromDate;
    private String toDate;

}
