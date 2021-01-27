package com.qianfeng.service.Impl;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Attendance;
import com.qianfeng.service.AttendanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AttendanceServiceImplTest {

    @Autowired
    private AttendanceService attendanceService;

    @Test
    public void add_attendance() {
        Attendance attendance = new Attendance();
        attendance.setEmpId(2);
        attendance.setWorkTypeId(3);
        attendance.setStartTime(new Date());
        attendance.setEndTime(new Date());
        attendance.setDays(0);
        attendance.setResultStatus("测试添加1");

        attendanceService.add_attendance(attendance);
    }

    @Test
    public void update_attendance() {

        Attendance attendance = new Attendance();
        attendance.setAttendanceId(3);
        attendance.setEmpId(2);
        attendance.setWorkTypeId(3);
        attendance.setStartTime(new Date());
        attendance.setEndTime(new Date());
        attendance.setDays(0);
        attendance.setResultStatus("测试修改2");
        attendanceService.update_attendance(attendance);

    }

    @Test
    public void delete_attendance() {

        attendanceService.delete_attendance(4);
    }

    @Test
    public void getAttendanceList() {
        Result list = attendanceService.getAttendanceList(1, 2);
        System.out.println(list);
    }

    @Test
    public void get_attendanceById() {
        Result attendanceById = attendanceService.get_attendanceById(3);
        System.out.println(attendanceById);

    }
}