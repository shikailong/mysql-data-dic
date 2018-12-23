package com.skl.export;

import com.skl.domin.Catalog;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class AbstractExcelExportService implements ExcelExportService{

    protected ExcelExportContext context;

    public AbstractExcelExportService(ExcelExportContext context) {
        this.context = context;
    }


    public void export(String sheetName, String[] tableHeader, Catalog... catalogs) throws IOException {
        try {
            HSSFSheet sheet = context.getDemoWorkBook().createSheet(sheetName);
            // 创建表头并且进行复制
            createHeaderCellAndSetValue(sheet, sheetName, tableHeader);
            // 创建详情的单元格并进行赋值
            createInfoCellAndSetValue(sheet, catalogs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建表格顶部的第一行并且赋值
     * @param sheet
     * @param sheetName
     * @param tableHeader
     */
    private void createHeaderCellAndSetValue(HSSFSheet sheet, String sheetName, String[] tableHeader){
        // 设置表头，从sheet中得到
        HSSFHeader header = sheet.getHeader();
        header.setCenter(sheetName);

        HSSFRow headerRow = sheet.createRow((short) 0);
        headerRow.setHeightInPoints(30);

        HSSFCellStyle fontStyle = getBoldFontStyle();
        int cellNumber = tableHeader.length;
        for (int i = 0; i < cellNumber; i++) {
            // 创建一个单元格
            HSSFCell cell = headerRow.createCell(i);
            // 设置cell的值
            cell.setCellValue(tableHeader[i]);
            cell.setCellStyle(fontStyle);
            sheet.setColumnWidth(i, 50 * 256);
        }
        HSSFCell cell = headerRow.createCell(cellNumber);
        // 设置cell的值
        setCellFormula(cell, "目录", "点击回到目录");
        sheet.setColumnWidth(cellNumber, 25 * 256);
    }

    /**
     * 设置单元格为超链接
     * @param cell 单元格
     * @param jumpSheetName 跳转sheet名称
     * @param value 单元格内容
     */
    protected void setCellFormula(HSSFCell cell, String jumpSheetName, String value){
        cell.setCellType(CellType.FORMULA);
        cell.setCellFormula("HYPERLINK(\"#"+ jumpSheetName +"!A1\",\"" + value + "\")");
        cell.setCellStyle(context.getCellStyle());
    }

    /**
     * 获取黑体字体
     * @return
     */
    private HSSFCellStyle getBoldFontStyle(){
        HSSFWorkbook demoWorkBook = context.getDemoWorkBook();
        // 设置字体
        HSSFFont font = demoWorkBook.createFont();
        font.setBold(true); // 宽度
        HSSFCellStyle cellStyle = demoWorkBook.createCellStyle();
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 创建详情的单元格并进行赋值
     * @param sheet 本次进行操作的sheet
     * @param catalogs 进行复制的数据
     */
    protected abstract void createInfoCellAndSetValue(HSSFSheet sheet, Catalog... catalogs);


    /**
     * 设置单元格的内容
     * @param catalogRow 单元格
     * @param sheet 表单
     * @param cellValue 单元格的值
     */
    protected void setCellValue(HSSFSheet sheet, HSSFRow catalogRow, int index, String cellValue, int width){
        HSSFCell cell = catalogRow.createCell(index);
        cell.setCellValue(cellValue);
        sheet.setColumnWidth(5, width);
    }

    /**
     * 导出生成excel表格
     * @throws IOException
     */
    public void exportExcel() throws IOException {

        String filePath = System.getProperty("user.dir") + File.separator + "鑫机缘数据字典.xls";
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream toClient = new FileOutputStream(file);
        context.getDemoWorkBook().write(toClient);
        toClient.flush();
        toClient.close();
    }

}
