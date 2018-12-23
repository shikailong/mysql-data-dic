package com.skl.database;

import com.mysql.jdbc.StringUtils;
import com.skl.domin.Catalog;
import com.skl.domin.TableInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @description: 请写点注释吧
 * @author: 史凯龙
 * @date 2018/11/22 10:33
 */
public class DataQueryService {

    private String url = "jdbc:mysql://localhost:3306/test";
    private String userName = "root";
    private String password = "123456";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public
    DataQueryService(String url, String userName, String password) {
        if(!StringUtils.isNullOrEmpty(url)){
            this.url = url;
        }
        if(!StringUtils.isNullOrEmpty(userName)){
            this.userName = userName;
        }
        if(!StringUtils.isNullOrEmpty(password)){
            this.password = password;
        }
    }

    /**
     * 查询数据
     * @return
     * @throws SQLException
     */
    public List<Catalog> queryDate() throws SQLException {
        Properties props =new Properties();
        props.setProperty("user", userName);
        props.setProperty("password", password);
        props.setProperty("remarks", "true"); //设置可以获取remarks信息
        props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息

        Connection connection = DriverManager.getConnection(url, props);
        DatabaseMetaData metaData = connection.getMetaData();

        ResultSet tables = metaData.getTables(null, "%", "%", new String[]{"TABLE"});
        List<Catalog> catalogList = new ArrayList<>();


        while (tables.next()){
            String tableName = tables.getString("TABLE_NAME");
            String tableRemarks = tables.getString("REMARKS");
            ResultSet columns = metaData.getColumns(null, "%", tableName, "%");

            Catalog catalog = new Catalog(tableName, tableRemarks);

            catalogList.add(catalog);
            List<TableInfo> tableInfos = new ArrayList<>();
            catalog.setTableInfos(tableInfos);
            while (columns.next()){
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");
                int datasize = columns.getInt("COLUMN_SIZE");
                int digits = columns.getInt("DECIMAL_DIGITS");
                int nullable = columns.getInt("NULLABLE");
                String remarks = columns.getString("REMARKS");
                String isAutoincrement = columns.getString("IS_AUTOINCREMENT");
                tableInfos.add(new TableInfo(tableName, columnName, columnType, datasize,
                        digits, nullable, isAutoincrement, remarks));
            }
        }
        return catalogList;
    }

}
