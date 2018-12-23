package com.skl.factory;

import com.skl.export.ExcelExportService;
import com.skl.export.TableHeadExcelExportService;

/**
 * excel目录的导出工厂类
 * 工厂生产对象{@link TableHeadExcelExportService}
 */
public class TableHeadExcelExportFactory extends AbstractExcelExportFactory {

    @Override
    public ExcelExportService getExcelExportService() {
        return new TableHeadExcelExportService(super.context);
    }

}
