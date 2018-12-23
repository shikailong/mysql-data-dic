package com.skl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.skl.database.DataQueryService;
import com.skl.domin.Catalog;
import com.skl.domin.TableInfo;
import com.skl.export.ExcelExportContext;
import com.skl.export.ExcelExportService;
import com.skl.factory.AbstractExcelExportFactory;

public class App {

    public static void main(String[] args) throws SQLException, IOException {

        DataQueryService service = new DataQueryService(
                "", "", "");
        // 查询数据
        List<Catalog> catalogs = service.queryDate();
        System.out.println("数据查询成功！");

        // 导出数据
        ExcelExportService headExcelExportService =
                AbstractExcelExportFactory.getExcelExportServiceFactory(1).getExcelExportService();
        headExcelExportService.export("目录",
                new String[]{"表名称", "表注释", "表详细字段"}, catalogs.toArray(new Catalog[catalogs.size()]));

        ExcelExportService infoExcelExportService =
                AbstractExcelExportFactory.getExcelExportServiceFactory(2).getExcelExportService();
        infoExcelExportService.export("",
                new String[]{"名称", "类型", "长度", "是否为主键", "是否非空", "注释"}, catalogs.toArray(new Catalog[catalogs.size()]));

        System.out.println("success");

    }


}
