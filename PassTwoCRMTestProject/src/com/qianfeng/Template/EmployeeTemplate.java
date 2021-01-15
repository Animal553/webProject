package com.qianfeng.Template;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import java.util.Date;
@ColumnWidth(25)
public class EmployeeTemplate {
        @ExcelProperty("员工号")
        private Integer empId ;
        @ExcelProperty("员工姓名")
        private String empName;
        @ExcelProperty("员工年龄")
        private Integer age;
        @ExcelProperty("部门名称")
        private String deptName;
        @ExcelProperty("入职时间")
        @DateTimeFormat(value = "yyyy年MM月dd日 HH:mm:ss")
        private Date hireDate;
        @ExcelProperty("跟新时间")
        @DateTimeFormat(value = "yyyy年MM月dd日 HH:mm:ss")
        private Date updateTime;

    public EmployeeTemplate() {
    }

    public Integer getEmpId() {
            return empId;
        }

        public void setEmpId(Integer empId) {
            this.empId = empId;
        }

        public String getEmpName() {
            return empName;
        }

        public void setEmpName(String empName) {
            this.empName = empName;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public Date getHireDate() {
            return hireDate;
        }

        public void setHireDate(Date hireDate) {
            this.hireDate = hireDate;
        }

        public Date getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }

        @Override
        public String toString() {
            return "EmployeeTemplate{" +
                    "empId=" + empId +
                    ", empName='" + empName + '\'' +
                    ", age=" + age +
                    ", deptName='" + deptName + '\'' +
                    ", hireDate=" + hireDate +
                    ", updateTime=" + updateTime +
                    '}';
        }
}
