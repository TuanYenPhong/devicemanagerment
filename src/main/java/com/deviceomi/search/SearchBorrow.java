package com.deviceomi.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchBorrow {
    private String type;
    private Date fromDateBorrow;
    private Date toDateBorrow;
    private Date fromDateReturn;
    private Date toDateReturn;

}
