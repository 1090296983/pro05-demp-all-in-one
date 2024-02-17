package com.atguigu.imperial.court.util;



import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

public class JDBCUtils {

    // 将数据源对象设置为静态属性，保证大对象的单一实例
    private static DataSource dataSource;


    static {

        // 1.创建一个用于存储外部属性文件信息的Properties对象
        Properties properties = new Properties();


        // 2.使用当前类的类加载器加载外部属性文件：jdbc.properties
        InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

        try {

            // 3.将外部属性文件jdbc.properties中的数据加载到properties对象中
            properties.load(inputStream);

            // 4.创建数据源对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
            //这里捕获到的异常封装为运行时异常继续抛出，为了避免在真正抛出异常后，catch 块捕获从而掩盖问题
            throw new RuntimeException(e);
        }

    }



    public static Connection getConnection() {
        return null;
    }





}
