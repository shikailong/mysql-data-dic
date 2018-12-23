package com.skl.export;

import com.skl.domin.Catalog;
import com.skl.domin.TableInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class TableInfoExcelExportService extends AbstractExcelExportService{

    public TableInfoExcelExportService(ExcelExportContext context) {
        super(context);
    }

    @Override
    public void export(String sheetName, String[] tableHeader, Catalog... catalogs) throws IOException {
        HSSFWorkbook demoWorkBook = context.getDemoWorkBook();
        for(int j = 0; j < catalogs.length; j++){
            Catalog catalog = catalogs[j];
            sheetName = catalog.getTableName();
            super.export(sheetName, tableHeader, catalog);
            System.out.println(sheetName + "---成功！");
        }
        exportExcel();
    }

    @Override
    protected void createInfoCellAndSetValue(HSSFSheet sheet, Catalog... catalogs) {
        List<TableInfo> tableInfos = catalogs[0].getTableInfos();
        HSSFCell headerCell = null;
        for( int i= 0; i < tableInfos.size(); i++){
            TableInfo tableInfo = tableInfos.get(i);
            // 创建一行
            HSSFRow catalogRow = sheet.createRow(i + 1);
            catalogRow.setHeightInPoints(18);

            int width = 15 * 256;

            setCellValue(sheet, catalogRow, 0, tableInfo.getColumnName(), width);
            setCellValue(sheet, catalogRow, 1, tableInfo.getTypeName(), width);
            setCellValue(sheet, catalogRow, 2, tableInfo.getColumnSize(), width);
            setCellValue(sheet, catalogRow, 3, tableInfo.getIsPrimeKey(), width);
            setCellValue(sheet, catalogRow, 4, tableInfo.getNullAble(), width);
            setCellValue(sheet, catalogRow, 5, tableInfo.getRemarks(), width);
        }
    }


}
