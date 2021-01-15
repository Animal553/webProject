package com.qianfeng.service.Impl;

import com.qianfeng.dao.Impl.SourceDaoImpl;
import com.qianfeng.dao.SourceDao;
import com.qianfeng.entity.Source;
import com.qianfeng.service.SourceService;
import com.qianfeng.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SourceServletImpl implements SourceService {
    private SourceDao sourceDao = new SourceDaoImpl();
    @Override
    public List<Source> selectSourceList() {
        List<Source> sourceList = null;
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "select source_id as sourceId , source_name as sourceName from source";
            sourceList = sourceDao.getSourceList(connection,sql,Source.class);
            connection.commit();
        } catch (SQLException e) {
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close();
        }
        return sourceList;
    }

    @Override
    public Source selectSource(Integer sourceId) {
        Connection connection = JDBCUtils.getConnection();
        Source source = null;
        try {
            connection.setAutoCommit(false);
            String sql ="select source_id as sourceId , source_name as sourceName from source where source_id =?";
            source = sourceDao.getSource(connection,sql,Source.class,sourceId);
            connection.commit();
        } catch (SQLException e) {
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close();
        }
        return source;
    }
}
