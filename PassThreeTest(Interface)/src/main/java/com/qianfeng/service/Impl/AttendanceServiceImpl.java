package com.qianfeng.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.Page.Page;
import com.qianfeng.Page.Result;
import com.qianfeng.entity.Attendance;
import com.qianfeng.mapper.AttendanceMapper;
import com.qianfeng.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceMapper attendanceMapper;

    @Transactional
    @Override
    public Result add_attendance(Attendance attendance) {
        attendanceMapper.add_attendance(attendance);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }

    @Transactional
    @Override
    public Result update_attendance(Attendance attendance) {
        attendanceMapper.update_attendance(attendance);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }

    @Transactional
    @Override
    public Result delete_attendance(Integer attendanceId) {
        attendanceMapper.delete_attendance(attendanceId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        return result;
    }

    @Transactional
    @Override
    public Result getAttendanceList(Integer pageNo, Integer pageSize) {

        if (pageNo==null){
            pageNo=1;
        }
        if (pageSize==null){
            pageSize=4;
        }
        PageHelper.startPage(pageNo,pageSize);
        List<Attendance> attendanceList = attendanceMapper.getAttendanceList();
        PageInfo<Attendance> info = new PageInfo<>(attendanceList);

        Page page = new Page();
        page.setHasNext(info.isHasNextPage());
        page.setHasPre(info.isHasPreviousPage());
        page.setPageNo(info.getPageNum());
        page.setPageSize(info.getPageSize());
        page.setPageCount(info.getPages());
        page.setPage(info.getList());

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(page);

        return result;
    }

    @Transactional
    @Override
    public Result get_attendanceById(Integer attendanceId) {
        Attendance attendance = attendanceMapper.get_attendanceById(attendanceId);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("ok");
        result.setObj(attendance);

        return result;
    }
}
