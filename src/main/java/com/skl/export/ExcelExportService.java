package com.skl.export;

import com.skl.domin.Catalog;

import java.io.IOException;

public interface ExcelExportService {

    /**
     * 导出excel操作顶级接口
     * @param sheetName sheet名称
     * @param tableHeader header的名称
     * @param catalogs 数据
     */
    void export(String sheetName, String[] tableHeader, Catalog... catalogs) throws IOException;

}
