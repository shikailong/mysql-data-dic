package com.skl.factory;

import com.skl.export.ExcelExportService;
import com.skl.export.TableInfoExcelExportService;

/**
 * excel详情的导出工厂类
 * 工厂生产对象 {@link TableInfoExcelExportService}
 */
public class TableInfoExcelExportFactory extends AbstractExcelExportFactory {

    @Override
    public ExcelExportService getExcelExportService() {
        return new TableInfoExcelExportService(super.context);
    }
    
}
