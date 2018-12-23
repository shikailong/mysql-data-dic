package com.skl.domin;

import java.util.List;

public class Catalog {

    private String tableName;
    private String tableDesc;

    List<TableInfo> tableInfos;

    public Catalog(String tableName, String tableDesc) {
        this.tableName = tableName;
        this.tableDesc = tableDesc;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableInfos(List<TableInfo> tableInfos) {
        this.tableInfos = tableInfos;
    }

    public List<TableInfo> getTableInfos() {
        return tableInfos;
    }
}
