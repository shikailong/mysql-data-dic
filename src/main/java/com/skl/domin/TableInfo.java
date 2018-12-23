package com.skl.domin;

public class TableInfo {

    private String tableName;
    private String columnName;
    private String typeName;
    private int columnSize;
    private int decimalDigits;
    private int nullAble;
    private String remarks;
    private String isPrimeKey;

    public TableInfo(String tableName, String columnName, String typeName, int columnSize, int decimalDigits, int nullAble, String isPrimeKey, String remarks) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.typeName = typeName;
        this.columnSize = columnSize;
        this.decimalDigits = decimalDigits;
        this.nullAble = nullAble;
        this.isPrimeKey = isPrimeKey;
        this.remarks = remarks;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getColumnSize() {
        return columnSize + "";
    }

    public String getDecimalDigits() {
        return decimalDigits + "";
    }

    public String getNullAble() {
        return nullAble == 0 ? "是" : "否";
    }

    public String getRemarks() {
        return remarks;
    }

    public String getIsPrimeKey() {
        return isPrimeKey;
    }
}
