package com.deviceomi.payload.response;

import com.deviceomi.model.HistoryEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class HistoryResponse {
    @NotBlank
    private String history;


    public HistoryResponse(HistoryEntity historyEntity){
        StringBuilder stringBuilder=new StringBuilder("User ");
        stringBuilder.append(historyEntity.getCreatedBy());
        stringBuilder.append(" ");
        stringBuilder.append(historyEntity.getContent());
        stringBuilder.append(historyEntity.getEditObject());
        stringBuilder.append(" ở ");
        stringBuilder.append(historyEntity.getPage());
        stringBuilder.append(".Ngày thao tác ");
        stringBuilder.append(historyEntity.getCreatedDate());
        this.setHistory(stringBuilder.toString());
    }
}
