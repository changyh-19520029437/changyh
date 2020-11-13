package com.web.service;

import com.web.entity.CityBean;
import com.web.entity.GradeBean;
import com.web.entity.StudentBean;
import com.web.entity.UserBean;

import java.util.List;

public interface UserService {

    //查询所有用户
    List<UserBean> getUserList();

    //查询学生信息
    List<StudentBean> getStuAllList();

    //查询班级信息
    List<GradeBean> getGradeList();

    //省市县三级联动
    List<CityBean> getCityListById(Long id);
}
