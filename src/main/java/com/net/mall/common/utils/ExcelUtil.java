package com.net.mall.common.utils;

import com.net.mall.common.exception.BaseException;
import com.net.mall.pojo.vo.OrdersVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class ExcelUtil {

    public static void export(HttpServletResponse response, List<OrdersVO> list, LocalDateTime startTime, LocalDateTime endTime) {

        String fileName = null;
        try {
            fileName = URLEncoder.encode("订单Excel表格.zip", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("导出失败！");
        }

        response.setContentType("application/zip");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
            // 工作簿对象
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 样式对象
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            // 水平居中
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            // 垂直居中
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            XSSFSheet sheet = workbook.createSheet();
            XSSFRow title = sheet.createRow(0);

            sheet.setColumnWidth(0,4000); //订单编号
            sheet.setColumnWidth(1,2500); //订单状态
            sheet.setColumnWidth(2,3000); //下单用户id
            sheet.setColumnWidth(3,2000); //机位id
            sheet.setColumnWidth(4,5000); //下单时间
            sheet.setColumnWidth(5,5000); //支付时间
            sheet.setColumnWidth(6,3000); //支付方式
            sheet.setColumnWidth(7,3000); //实际总价
            sheet.setColumnWidth(8,3000); //优惠金额
            sheet.setColumnWidth(9,3000); //原始价格
            sheet.setColumnWidth(10,3500); //下单手机号
            // 标题
            XSSFCell headersCell = title.createCell(0);
            headersCell.setCellStyle(cellStyle);
            headersCell.setCellValue("点一点 " + startTime.toString() + " - " + endTime.toString() + " 订单");
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
            // 表头
            //设置表头样式
            XSSFFont headersFont = workbook.createFont();
            headersFont.setBold(true);
            XSSFCellStyle st = workbook.createCellStyle();
            st.setFont(headersFont);


            XSSFRow row1 = sheet.createRow(1);
            String[] headers = {"订单编号", "订单状态", "下单用户id", "机位id", "下单时间", "支付时间", "支付方式", "实际总价", "优惠金额", "原始价格", "下单手机号"};
            for (int i = 0; i < headers.length; i++) {
                // 填充表头
                XSSFCell cell = row1.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(st);
            }
            for (int i=0;i<list.size();i++) {
                XSSFRow row = sheet.createRow(i + 2);
                row.createCell(0).setCellValue(list.get(i).getOrderNum());
                row.createCell(1).setCellValue(switchStatus(list.get(i).getStatus()));
                row.createCell(2).setCellValue(list.get(i).getUserId());
                row.createCell(3).setCellValue(list.get(i).getComputerId());
                row.createCell(4).setCellValue(list.get(i).getOrderTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                if (list.get(i).getCheckoutTime() == null) {
                    row.createCell(5).setCellValue("/");
                } else {
                    row.createCell(5).setCellValue(list.get(i).getCheckoutTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }
                row.createCell(6).setCellValue(switchPayMethod(list.get(i).getPayMethod()));
                row.createCell(7).setCellValue(list.get(i).getTotal().toString());
                row.createCell(8).setCellValue(list.get(i).getPreference().toString());
                row.createCell(9).setCellValue(list.get(i).getAmount().toString());
                row.createCell(10).setCellValue(list.get(i).getPhone());
            }
            XSSFRow tailRow = sheet.createRow(list.size() + 3);
            XSSFCell cell1 = tailRow.createCell(6);
            cell1.setCellValue("实际收入：");
            XSSFCell cell2 = tailRow.createCell(8);
            cell2.setCellValue("共优惠：");
            BigDecimal income=new BigDecimal("0");
            BigDecimal preference=new BigDecimal("0");
            for (OrdersVO vo : list) {
                income=income.add(vo.getTotal());
                preference=preference.add(vo.getPreference());
            }
            XSSFCell cell3 = tailRow.createCell(7);
            cell3.setCellValue(income.toString());
            cell3.setCellStyle(st);
            XSSFCell cell4 = tailRow.createCell(9);
            cell4.setCellValue(preference.toString());
            cell4.setCellStyle(st);



            //设置标题样式
            XSSFFont titleFont = workbook.createFont();
            titleFont.setFontHeightInPoints((short) 20);
            titleFont.setBold(true);
            XSSFCellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headersCell.setCellStyle(titleStyle);






            String entryName = "点一点订单.xlsx";
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            ZipEntry zipEntry = new ZipEntry(entryName);
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.write(bos.toByteArray());
            zipOutputStream.closeEntry();
            zipOutputStream.finish();
    } catch(Exception e){
        throw new BaseException("导出失败！");
    }
}


private static String switchStatus(Integer i) {
    switch (i) {
        case 1:
            return "待付款";
        case 2:
            return "已付款";
        case 3:
            return "已完成";
        case 4:
            return "已取消";
        default:
            return "/";
    }
}

private static String switchPayMethod(Integer i) {
    switch (i) {
        case 1:
            return "微信";
        case 2:
            return "支付宝";
        default:
            return "/";
    }
}

}
