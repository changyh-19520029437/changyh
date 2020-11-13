package com.web.mapper;

import com.web.entity.GradeBean;
import com.web.entity.TeacherBean;
import com.web.entity.TestBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {

    //获取独立考试信息
    List<TestBean> getIndependentTestList(@Param("tid") Long tid);

    //获取班级信息
    List<GradeBean> getGradeListByTid(@Param("tid") Long tid);

    //查询老师信息
    List<TeacherBean> getTeacherList(@Param("tid") Long tid);

}
