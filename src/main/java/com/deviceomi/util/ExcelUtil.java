package com.deviceomi.util;

import com.deviceomi.payload.response.DeviceResponse_;
import com.deviceomi.payload.response.RepairResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {

    public static ByteArrayInputStream exportRepair(List<RepairResponse> searchResult)
            throws IOException {
        String[] columns = {
                "STT", "LOẠI THIẾT BỊ", "MÃ THIẾT BỊ", "TRẠNG THÁI", "NỘI DUNG", "NGÀY SỬA", "NGÀY HOÀN THÀNH"
        };

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("customer");
        // XSSFCreationHelper createHelper = workbook.getCreationHelper();
        // Locale localeEN = new Locale("en", "EN");
        // NumberFormat en = NumberFormat.getInstance(localeEN);
        int titleRowNumber = 0;
        int headerRowNumber = 3;
        int dataRowNumber = 4;

        // Font cell
        XSSFFont headerCellFont = workbook.createFont();
        headerCellFont.setBold(true);
        headerCellFont.setFontHeight(10);

        // Font title
        XSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeight(15);

        // Font color
        XSSFColor headerCellBackgroundColor = new XSSFColor(java.awt.Color.YELLOW); // Yellow

        // Title Style
        XSSFCellStyle titleCellStyle = workbook.createCellStyle();
        titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
        titleCellStyle.setFont(titleFont);

        // Header cell style
        XSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerCellFont);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setFillForegroundColor(headerCellBackgroundColor);
        headerCellStyle.setFillBackgroundColor(headerCellBackgroundColor);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // tableHeadCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MMMM-dd-yyyy"));
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

        // Word cell style
        XSSFCellStyle wordCellStyle = workbook.createCellStyle();
        wordCellStyle.setBorderBottom(BorderStyle.THIN);
        wordCellStyle.setBorderTop(BorderStyle.THIN);
        wordCellStyle.setBorderLeft(BorderStyle.THIN);
        wordCellStyle.setBorderRight(BorderStyle.THIN);
        wordCellStyle.setAlignment(HorizontalAlignment.LEFT);
        // tableDataCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MMMM-dd-yyyy"));

        // Number cell style
        XSSFCellStyle numberCellStyle = workbook.createCellStyle();
        numberCellStyle.setBorderBottom(BorderStyle.THIN);
        numberCellStyle.setBorderTop(BorderStyle.THIN);
        numberCellStyle.setBorderLeft(BorderStyle.THIN);
        numberCellStyle.setBorderRight(BorderStyle.THIN);
        numberCellStyle.setAlignment(HorizontalAlignment.RIGHT);

        // Merge title cell
        sheet.addMergedRegion(new CellRangeAddress(titleRowNumber, titleRowNumber + 1, 0, columns.length - 1));

        /* Tạo title */
        XSSFRow titleRow = sheet.createRow(titleRowNumber);
        XSSFCell titleCell = titleRow.createCell(0);
        XSSFRichTextString titleCellValue = new XSSFRichTextString();
        titleCell.setCellStyle(titleCellStyle);
//        titleCellValue.append("Dashboard " + currentUser + " Report");
        titleCell.setCellValue(titleCellValue);

        // Append table header
        XSSFRow headerRow = sheet.createRow(headerRowNumber);

        for(int i = 0; i < columns.length; i++){
            XSSFCell headerCell = headerRow.createCell(i);
            XSSFRichTextString headerCellValue = new XSSFRichTextString();
            headerCell.setCellStyle(headerCellStyle);
            headerCellValue.append(columns[i]);
            headerCell.setCellValue(headerCellValue);
        }

        /* Tạo các hàng dữ liệu */
        XSSFRow dataRow;
        XSSFCell dataCell;
        String /*stt,*/ typeDevice, codeDevice, status = null, contents, dateRepair, dateFinish;
        // int sumVisits = 0, sumUniqueVisits = 0, sumClicks = 0;
        // double sumCr = 0, sumCost = 0;

        int totalColumns = searchResult.size();
        
        for(int i = 0; i < totalColumns; i++){
            dataRow = sheet.createRow(i + dataRowNumber);

//            stt = (i + 1) + "";

            typeDevice = searchResult.get(i).getEntities().getTypeDevice();

            codeDevice = searchResult.get(i).getEntities().getCodeDevice();

            Integer idStatus = searchResult.get(i).getStatus();

            switch(idStatus){
                case 1:
                    status = "Sửa";
                    break;
                case 2:
                    status = "Bảo hành";
                    break;
                case 3:
                    status = "Nâng cấp";
                    break;
                case 4:
                    status = "Đang chờ xử lí";
                    break;
                case 5:
                    status = "Xong";
                    break;
                default:
                    break;
            }
            contents = searchResult.get(i).getDescription();
            dateRepair = searchResult.get(i).getDateRepair();
            dateFinish = searchResult.get(i).getDateFinish();

            /* Đưa dữ liệu vào các cột */
            for(int j = 0; j < columns.length; j++) {
                dataCell = dataRow.createCell(j);
                switch (j) {
                    case 0:
                        dataCell.setCellValue(i + 1);
                        dataCell.setCellStyle(numberCellStyle);
                        break;
                    case 1:
                        dataCell.setCellValue(typeDevice);
                        dataCell.setCellStyle(wordCellStyle);
                        break;
                    case 2:
                        dataCell.setCellValue(codeDevice);
                        dataCell.setCellStyle(numberCellStyle);
                        break;
                    case 3:
                        dataCell.setCellValue(status);
                        dataCell.setCellStyle(numberCellStyle);
                        break;
                    case 4:
                        dataCell.setCellValue(contents);
                        dataCell.setCellStyle(wordCellStyle);
                        break;
                    case 5:
                        dataCell.setCellValue(dateRepair);
                        dataCell.setCellStyle(wordCellStyle);
                        break;
                    case 6:
                        dataCell.setCellValue(dateFinish);
                        dataCell.setCellStyle(wordCellStyle);
                        break;
                    default:
                        break;
                }
            }
        }

        // Autosize column
        for(int i = 0; i < columns.length; i++)
            sheet.autoSizeColumn(i);

        // / Viết ra file excel /
        ByteArrayOutputStream excelFile = new ByteArrayOutputStream();
        workbook.write(excelFile);
        workbook.close();
        return new ByteArrayInputStream(excelFile.toByteArray());
    }


    public static ByteArrayInputStream exportDevice(List<DeviceResponse_> searchResult) throws IOException {
        String[] columns = {
                "STT", "LOẠI THIẾT BỊ", "MÃ THIẾT BỊ", "MÔ TẢ", "NGÀY NHẬP KHO", "THỜI HẠN BẢO HÀNH", "DƠN VỊ CUNG CẤP", "THỜI GIAN BÀN GIAO LẦN ĐẦU"
        };

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("device");
        // XSSFCreationHelper createHelper = workbook.getCreationHelper();
        // Locale localeEN = new Locale("en", "EN");
        // NumberFormat en = NumberFormat.getInstance(localeEN);
        int titleRowNumber = 0;
        int headerRowNumber = 3;
        int dataRowNumber = 4;

        // Font cell
        XSSFFont headerCellFont = workbook.createFont();
        headerCellFont.setBold(true);
        headerCellFont.setFontHeight(10);

        // Font title
        XSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeight(15);

        // Font color
        XSSFColor headerCellBackgroundColor = new XSSFColor(java.awt.Color.YELLOW); // Yellow

        // Title Style
        XSSFCellStyle titleCellStyle = workbook.createCellStyle();
        titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
        titleCellStyle.setFont(titleFont);

        // Header cell style
        XSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerCellFont);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setFillForegroundColor(headerCellBackgroundColor);
        headerCellStyle.setFillBackgroundColor(headerCellBackgroundColor);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // tableHeadCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MMMM-dd-yyyy"));
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

        // Word cell style
        XSSFCellStyle wordCellStyle = workbook.createCellStyle();
        wordCellStyle.setBorderBottom(BorderStyle.THIN);
        wordCellStyle.setBorderTop(BorderStyle.THIN);
        wordCellStyle.setBorderLeft(BorderStyle.THIN);
        wordCellStyle.setBorderRight(BorderStyle.THIN);
        wordCellStyle.setAlignment(HorizontalAlignment.LEFT);
        // tableDataCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MMMM-dd-yyyy"));

        // Number cell style
        XSSFCellStyle numberCellStyle = workbook.createCellStyle();
        numberCellStyle.setBorderBottom(BorderStyle.THIN);
        numberCellStyle.setBorderTop(BorderStyle.THIN);
        numberCellStyle.setBorderLeft(BorderStyle.THIN);
        numberCellStyle.setBorderRight(BorderStyle.THIN);
        numberCellStyle.setAlignment(HorizontalAlignment.RIGHT);

        // Merge title cell
        sheet.addMergedRegion(new CellRangeAddress(titleRowNumber, titleRowNumber + 1, 0, columns.length - 1));

        /* Tạo title */
        XSSFRow titleRow = sheet.createRow(titleRowNumber);
        XSSFCell titleCell = titleRow.createCell(0);
        XSSFRichTextString titleCellValue = new XSSFRichTextString();
        titleCell.setCellStyle(titleCellStyle);
//        titleCellValue.append("Dashboard " + currentUser + " Report");
        titleCell.setCellValue(titleCellValue);

        // Append table header
        XSSFRow headerRow = sheet.createRow(headerRowNumber);

        for(int i = 0; i < columns.length; i++){
            XSSFCell headerCell = headerRow.createCell(i);
            XSSFRichTextString headerCellValue = new XSSFRichTextString();
            headerCell.setCellStyle(headerCellStyle);
            headerCellValue.append(columns[i]);
            headerCell.setCellValue(headerCellValue);
        }

        /* Tạo các hàng dữ liệu */
        XSSFRow dataRow;
        XSSFCell dataCell;
        String /*stt,*/ typeDevice, codeDevice, description, dateInput, guarantee, supplyUnit, firstTime;
        // int sumVisits = 0, sumUniqueVisits = 0, sumClicks = 0;
        // double sumCr = 0, sumCost = 0;

        int totalColumns = searchResult.size();

        for(int i = 0; i < totalColumns; i++){
            dataRow = sheet.createRow(i + dataRowNumber);

//            stt = (i + 1) + "";

            typeDevice = searchResult.get(i).getTypeDevice();
            codeDevice = searchResult.get(i).getCodeDevice();
            description = searchResult.get(i).getDescription();
            dateInput = searchResult.get(i).getInputDay();
            guarantee = searchResult.get(i).getGuarantee();
            supplyUnit = searchResult.get(i).getSupplyUnit();
            firstTime = searchResult.get(i).getFirstTime();

            /* Đưa dữ liệu vào các cột */
            for(int j = 0; j < columns.length; j++) {
                dataCell = dataRow.createCell(j);
                switch (j) {
                    case 0:
                        dataCell.setCellValue(i + 1);
                        dataCell.setCellStyle(numberCellStyle);
                        break;
                    case 1:
                        dataCell.setCellValue(typeDevice);
                        dataCell.setCellStyle(wordCellStyle);
                        break;
                    case 2:
                        dataCell.setCellValue(codeDevice);
                        dataCell.setCellStyle(numberCellStyle);
                        break;
                    case 3:
                        dataCell.setCellValue(description);
                        dataCell.setCellStyle(numberCellStyle);
                        break;
                    case 4:
                        dataCell.setCellValue(dateInput);
                        dataCell.setCellStyle(wordCellStyle);
                        break;
                    case 5:
                        dataCell.setCellValue(guarantee);
                        dataCell.setCellStyle(wordCellStyle);
                        break;
                    case 6:
                        dataCell.setCellValue(supplyUnit);
                        dataCell.setCellStyle(wordCellStyle);
                    case 7:
                        dataCell.setCellValue(firstTime);
                        dataCell.setCellStyle(wordCellStyle);
                        break;
                    default:
                        break;
                }
            }
        }

        // Autosize column
        for(int i = 0; i < columns.length; i++)
            sheet.autoSizeColumn(i);

        // / Viết ra file excel /
        ByteArrayOutputStream excelFile = new ByteArrayOutputStream();
        workbook.write(excelFile);
        workbook.close();
        return new ByteArrayInputStream(excelFile.toByteArray());
    }
}
