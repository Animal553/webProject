package com.qianfeng.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    private static ThreadLocal<Connection> pool = new ThreadLocal();
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driver = null;


    static {
        InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = properties.getProperty("jdbc_url");
        user = properties.getProperty("jdbc_user");
        password = properties.getProperty("jdbc_password");
        driver = properties.getProperty("jdbc_driver");
    }

    public static Connection getConnection(){
        Connection connection = pool.get();
        if (connection==null){
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url,user,password);
                pool.set(connection);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void close(){
        Connection connection = pool.get();
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        pool.remove();
    }
}
