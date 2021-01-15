package com.qianfeng.service.Impl;

import com.qianfeng.dao.Impl.IndustryDaoImpl;
import com.qianfeng.dao.IndustryDao;
import com.qianfeng.entity.Industry;
import com.qianfeng.entity.Source;
import com.qianfeng.service.IndustryService;
import com.qianfeng.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class IndustryServiceImpl implements IndustryService {
    private IndustryDao industryDao = new IndustryDaoImpl();
    @Override
    public List<Industry> selectIndustryList() {
        List<Industry> industryList = null;
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "select industry_id as industryId , industry_name as industryName from industry";
            industryList = industryDao.getIndustryList(connection,sql,Industry.class);
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
        return industryList;
    }

    @Override
    public Industry selectIndustry(Integer industryId) {
        Connection connection = JDBCUtils.getConnection();
        Industry industry =null;
        try {
            connection.setAutoCommit(false);
            String sql ="select industry_id as industryId , industry_name as industryName from industry where industry_id = ?";
            industry = industryDao.getIndustry(connection,sql,Industry.class,industryId);
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
        return industry;
    }
}
