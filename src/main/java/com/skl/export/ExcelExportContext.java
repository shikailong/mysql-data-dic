package com.skl.export;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelExportContext {

    private static class ExcelExportContextInner{
        private static ExcelExportContext instance = new ExcelExportContext();
    }
    private ExcelExportContext(){
        this.demoWorkBook = new HSSFWorkbook();
        this.cellStyle = getBlueCellStyle();
    }

    public static ExcelExportContext getInstance(){
        return ExcelExportContextInner.instance;
    }

    // 创建工作本
    private HSSFWorkbook demoWorkBook ;
    private HSSFCellStyle cellStyle;

    public HSSFWorkbook getDemoWorkBook() {
        return demoWorkBook;
    }
    public HSSFCellStyle getCellStyle() {
        return cellStyle;
    }

    private HSSFCellStyle getBlueCellStyle(){
        HSSFCellStyle linkStyle = demoWorkBook.createCellStyle();
        HSSFFont cellFont = demoWorkBook.createFont();
        cellFont.setUnderline((byte) 1);
        cellFont.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        linkStyle.setFont(cellFont);
        return linkStyle;
    }

}
