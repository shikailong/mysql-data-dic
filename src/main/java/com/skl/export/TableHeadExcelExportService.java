package com.skl.export;

import com.skl.domin.Catalog;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.io.IOException;

public class TableHeadExcelExportService extends AbstractExcelExportService{

    public TableHeadExcelExportService(ExcelExportContext context) {
        super(context);
    }

    @Override
    public void export(String sheetName, String[] tableHeader, Catalog... catalogs) throws IOException {
        super.export(sheetName, tableHeader, catalogs);
        exportExcel();
    }

    @Override
    protected void createInfoCellAndSetValue(HSSFSheet sheet, Catalog... catalogs) {
        for( int i= 0; i < catalogs.length; i++){
            Catalog catalog = catalogs[i];
            // 创建一行
            HSSFRow catalogRow = sheet.createRow(i + 1);
            catalogRow.setHeightInPoints(18);

            int width = 50 * 256;
            setCellValue(sheet, catalogRow, 0, catalog.getTableName(), width);

            width = 75 * 256;
            setCellValue(sheet, catalogRow, 1, catalog.getTableDesc(), width);

            HSSFCell cell = catalogRow.createCell(2);
            setCellFormula(cell, catalog.getTableName(), "点击跳转");
            sheet.setColumnWidth(2, 25 * 256);
        }
    }


}
