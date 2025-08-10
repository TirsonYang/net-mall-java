package com.net.mall.common.utils;

import com.net.mall.common.exception.BaseException;
import com.net.mall.pojo.entity.OrdersEntity;
import com.net.mall.pojo.vo.OrdersVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class ExcelUtil {

    public static void exportMonth(HttpServletResponse response, List<OrdersVO> list){

        String fileName = null;
        try {
            fileName = URLEncoder.encode("订单Excel表格.zip","UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("导出失败！");
        }

        response.setContentType("application/zip");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        Year now = Year.now();
        List<OrdersVO> resList = list.stream()
                .filter(e -> e.getOrderTime().getYear() == Year.now().getValue())
                .collect(Collectors.toList());
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
            for (int j=0;j<12;j++) {
                // 工作簿对象
                XSSFWorkbook workbook = new XSSFWorkbook();
                // 样式对象
                XSSFCellStyle cellStyle = workbook.createCellStyle();
                // 水平居中
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                // 垂直居中
                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

                Month month = Month.of(j + 1);
                XSSFSheet sheet = workbook.createSheet();
                XSSFRow title = sheet.createRow(0);

                // 标题
                XSSFCell headersCell = title.createCell(0);
                headersCell.setCellStyle(cellStyle);
                headersCell.setCellValue("点一点" + month.getValue() + "月订单");
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
                // 表头
                XSSFRow row1 = sheet.createRow(1);
                String[] headers = {"订单编号", "订单状态", "下单用户id", "机位id", "下单时间", "支付时间", "支付方式", "实际总价", "优惠金额", "原始价格", "下单手机号"};
                for (int i = 0; i < headers.length; i++) {
                    // 填充表头
                    row1.createCell(i).setCellValue(headers[i]);
                    // 列宽自适应
                    // TODO 列宽自适应需要调整
                    sheet.autoSizeColumn(i);
                }
                //记录行数
                int k = 0;
                for (OrdersVO vo : resList) {
                    if (vo.getOrderTime().getMonth().getValue() == month.getValue()) {
                        XSSFRow row = sheet.createRow(k + 2);
                        row.createCell(0).setCellValue(vo.getOrderNum());
                        row.createCell(1).setCellValue(switchStatus(vo.getStatus()));
                        row.createCell(2).setCellValue(vo.getUserId());
                        row.createCell(3).setCellValue(vo.getComputerId());
                        row.createCell(4).setCellValue(vo.getOrderTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        if (vo.getCheckoutTime() == null) {
                            row.createCell(5).setCellValue("/");
                        } else {
                            row.createCell(5).setCellValue(vo.getCheckoutTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        }
                        row.createCell(6).setCellValue(switchPayMethod(vo.getPayMethod()));
                        row.createCell(7).setCellValue(vo.getTotal().toString());
                        row.createCell(8).setCellValue(vo.getPreference().toString());
                        row.createCell(9).setCellValue(vo.getAmount().toString());
                        row.createCell(10).setCellValue(vo.getPhone());
                        k++;
                    }
                }
                String entryName = "点一点" + month.getValue() + "月订单.xlsx";
                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                workbook.write(bos);
                ZipEntry zipEntry = new ZipEntry(entryName);
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.write(bos.toByteArray());
                zipOutputStream.closeEntry();
            }
            zipOutputStream.finish();
        } catch (Exception e) {
            throw new BaseException("导出失败！");
        }

    }


    private static String switchStatus(Integer i){
        switch (i){
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

    private static String switchPayMethod(Integer i){
        switch (i){
            case 1:
                return "微信";
            case 2:
                return "支付宝";
            default:
                return "/";
        }
    }

}
