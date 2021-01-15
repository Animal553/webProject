package com.qianfeng.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
    //数据库的最底层的操作

    public Integer getCount(Connection connection ,String sql){

        Integer count= null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int add(Connection connection , String sql , Object... objects){
        PreparedStatement preparedStatement =null;
        int j=0;
        try {
            preparedStatement  = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            if (objects!=null&&objects.length>=0){
                for (int i = 0; i < objects.length; i++) {
                    preparedStatement.setObject(i+1,objects[i]);
                }
                 j = preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                rs.next();
                j = rs.getInt(1);
                System.out.println(j);
                return j;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return j;
    }

    public boolean update(Connection connection , String sql , Object... objects){
        PreparedStatement preparedStatement =null;
        try {
            preparedStatement  = connection.prepareStatement(sql);
            if (objects!=null&&objects.length>=0){
                for (int i = 0; i < objects.length; i++) {
                    preparedStatement.setObject(i+1,objects[i]);
                }
                int i = preparedStatement.executeUpdate();
                if (i>0)
                {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public <T>List<T>  getList(Connection connection , String sql , Class<T> clazz , Object... objects){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        List<T> list = new ArrayList<>();
        T t = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (objects!=null&&objects.length>0){
                for (int i = 0; i < objects.length; i++) {
                    preparedStatement.setObject(i+1,objects[i]);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()){
                t = clazz.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = resultSetMetaData.getColumnLabel(i+1);
                    Object columnValue = resultSet.getObject(columnLabel);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                list.add(t);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return list;
    }


    public <T>T get(Connection connection , String sql , Class<T> clazz , Object... objects){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        T t = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (objects!=null&&objects.length>0){
                for (int i = 0; i < objects.length; i++) {
                    preparedStatement.setObject(i+1,objects[i]);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()){
                t = clazz.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = resultSetMetaData.getColumnLabel(i+1);
                    Object columnValue = resultSet.getObject(columnLabel);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return t;
    }
}
