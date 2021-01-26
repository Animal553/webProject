package com.qianfeng.mapper;

import com.qianfeng.entity.Class;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassMapper {
    void addClass(Class clazz);
    void deleteClass(Integer classId);
    void updateClass(Class clazz);
    List<Class> getClassList();
    Class getClass(Integer classId);

}
