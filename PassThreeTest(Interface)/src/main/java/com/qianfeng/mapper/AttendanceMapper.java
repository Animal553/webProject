package com.qianfeng.mapper;

import com.qianfeng.entity.Attendance;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceMapper {

    void add_attendance(Attendance attendance);
    void update_attendance(Attendance attendance);
    void delete_attendance(Integer attendanceId);
    List<Attendance> getAttendanceList();
    Attendance get_attendanceById(Integer attendanceId);
}
