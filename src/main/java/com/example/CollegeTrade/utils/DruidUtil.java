package com.example.CollegeTrade.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DruidUtil {
    /*
    数据源对象：javax.sql.DataSource接口对象，表示数据量，可以直接理解为连接池
    Druid实现这个数据源接口是DruidDataSource
     */

    //定义私有静态数据源对象
    private static DataSource dataSource;

    //static代码块完成数据赋值
    static {
        try {
            Properties properties = new Properties();
            //配置文件转换为数据流
            InputStream inputStream = DruidUtil.class.getClassLoader().getResourceAsStream("application.properties");
            //加载配置文件
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //定义静态工厂方法，返回数据源
    public static DataSource getDataSource() {
        return dataSource;
    }

}

