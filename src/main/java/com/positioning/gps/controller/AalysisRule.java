package com.positioning.gps.controller;

import com.positioning.gps.entity.JobCardEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class AalysisRule {
    /*
       数据域解析---室外定位数据上报（0x01）
    */

    /* 功能码解析 */
    public static String functionCode(String data) {
        return data.substring(4, 6); // 获取功能码
    }

    /* 数据长度解析 */
    public static String dataLength(String data) {
         return data.substring(6, 8); // 获取数据长度
    }

    /* 设备码解析 */
    public static String deveui(String data) {
        return data.substring(8, 24); // 获取设备码
    }

    /* 发送时间 */
    public static String time(String data) {
        Date sendingTime = new Date(); // 建立时间变量
        sendingTime.setTime(Long.parseLong(data.substring(24, 32), 16) * 1000); //将截取的时间赋予的时间变量
        return DateFormat.getDateTimeInstance().format(sendingTime); //将格式化时间变量赋给showSendingTime
    }

    /* 纬度 */
    public static Double latitude(String data) {
        return Integer.valueOf(data.substring(32, 40), 16) * 0.000001; //将截取的纬度数据，并转换成纬度
    }

    /* 经度 */
    public static Double longitude(String data) {
        return Integer.valueOf(data.substring(40, 48), 16) * 0.000001; //将截取的纬度数据，并转换成纬度
    }

    /* 速度 */
    public static Integer speed(String data) {
        return Integer.valueOf(data.substring(48, 50), 16); //将截取的16进制 速度数据 Km/h
    }

    /* 方位角 */
    public static Integer azimuth(String data) {
        return Integer.valueOf(data.substring(50, 54), 16); //将截取的16进制 方位角数据 度
    }

    /* 海拔 */
    public static Integer altitude(String data) {
        return Integer.valueOf(data.substring(54, 58), 16); //将截取的16进制 海拔数据
    }

    /* 步数 */
    public static Integer stepCount(String data) {
        return Integer.valueOf(data.substring(70, 74), 16); //将截取的16进制 步数数据
    }

    /* 电量 */
    public static Integer battery(String data) {
        return Integer.valueOf(data.substring(74, 76), 16); //将截取的16进制 电量数据
    }

    /* 定位信息导出到excel */
    public static int downLoadToExcel(OutputStream outputStream, List<JobCardEntity> jobCardEntityVOList) {
        //文档对象
        HSSFWorkbook wb = new HSSFWorkbook();
        int rowNum = 0;
        Sheet sheet = wb.createSheet("工卡信息导入");
        Row row0 = sheet.createRow(rowNum++);
        //因为场景不同，titil不同，可以在外面写成数组当参数传进来
        row0.createCell(0).setCellValue("时间\n" );
        row0.createCell(1).setCellValue("工卡ID\n" );
        row0.createCell(2).setCellValue("纬度\n");
        row0.createCell(3).setCellValue("经度\n");
        row0.createCell(4).setCellValue("速度\n");
        row0.createCell(5).setCellValue("方位角\n");
        row0.createCell(6).setCellValue("海拔\n");
        row0.createCell(7).setCellValue("步数\n");
        row0.createCell(8).setCellValue("电量\n");


        if (jobCardEntityVOList != null && jobCardEntityVOList.size() > 0) {
            for (JobCardEntity jobCardEntityVO : jobCardEntityVOList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(jobCardEntityVO.getTime());
                row.createCell(1).setCellValue(jobCardEntityVO.getDeveui());
                row.createCell(2).setCellValue(jobCardEntityVO.getLatitude());
                row.createCell(3).setCellValue(jobCardEntityVO.getLongitude());
                row.createCell(4).setCellValue(jobCardEntityVO.getSpeed());
                row.createCell(5).setCellValue(jobCardEntityVO.getAzimuth());
                row.createCell(6).setCellValue(jobCardEntityVO.getAltitude());
                row.createCell(7).setCellValue(jobCardEntityVO.getStepCount());
                row.createCell(8).setCellValue(jobCardEntityVO.getBattery());
            }
        }
        try {
            wb.write(outputStream);
//            LOGGER.info("表数据写入到excel表成功,一共写入了" + (rowNum - 1) + "条数据");
            outputStream.close();
        } catch (IOException e) {
//            LOGGER.error("流关闭异常！", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
//                    LOGGER.error("流关闭异常！", e);
                }
            }
        }
        return rowNum - 1;
    }
}