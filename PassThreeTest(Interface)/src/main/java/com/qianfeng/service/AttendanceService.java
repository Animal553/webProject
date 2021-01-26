package com.qianfeng.service;

import com.qianfeng.Page.Result;
import com.qianfeng.entity.Attendance;

import java.util.List;

public interface AttendanceService {
    Result add_attendance(Attendance attendance);
    Result update_attendance(Attendance attendance);
    Result delete_attendance(Integer attendanceId);
    Result getAttendanceList(Integer pageNo, Integer pageSize);
    Result get_attendanceById(Integer attendanceId);
}
