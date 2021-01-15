package com.qianfeng.service.Impl;

import com.qianfeng.dao.Impl.LevelDaoImpl;
import com.qianfeng.dao.LevelDao;
import com.qianfeng.entity.Level;
import com.qianfeng.service.LevelService;
import com.qianfeng.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LevelServiceImpl implements LevelService {
    private LevelDao levelDao = new LevelDaoImpl();
    @Override
    public List<Level> getLevelList() {
        List<Level> levelList = null;
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "select level_id as levelId , level_name as levelName from level";
            levelList = levelDao.getLevelList(connection,sql,Level.class);
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
        return levelList;
    }

    @Override
    public Level getLevel(Integer levelId) {
        Connection connection = JDBCUtils.getConnection();
        Level level = null;
        try {
            connection.setAutoCommit(false);
            String sql ="select level_id as levelId , level_name as levelName from level where level_id =?";
            level = levelDao.getLevel(connection,sql,Level.class,levelId);
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
        return level;
    }
}
