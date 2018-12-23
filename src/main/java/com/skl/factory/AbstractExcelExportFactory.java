package com.skl.factory;

import com.skl.export.ExcelExportContext;
import com.skl.export.ExcelExportService;

/**
 * 抽象工厂
 * 导出上下文 {@link ExcelExportContext}
 * @author shikailong
 */
public abstract class AbstractExcelExportFactory {

    protected ExcelExportContext context = ExcelExportContext.getInstance();

    /**
     * 生成文件导出service
     * @return
     */
    public abstract ExcelExportService getExcelExportService();

    /**
     * 生成默认的工厂
     * @param type 1标识表头工厂，2表示内容工厂
     * @return
     */
    public final static AbstractExcelExportFactory getExcelExportServiceFactory(int type){
        if(type == 1){
            return new TableHeadExcelExportFactory();
        }else{
            return new TableInfoExcelExportFactory();
        }
    }

}
