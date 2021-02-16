package com.deviceomi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public  static Date stringToDate(String date){
        Date startDate = null;
        try {
            if(date != null && !date.trim().equals(""))
                startDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    public static String dateToString(Date date){
        return df.format(date);
    }
}
