package com.web.service.impl;

import com.web.entity.CityBean;
import com.web.entity.GradeBean;
import com.web.entity.StudentBean;
import com.web.entity.UserBean;
import com.web.mapper.UserMapper;
import com.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    //查询所有用户
    public List<UserBean> getUserList() {
        return userMapper.getUserList();
    }

    //查询学生信息
    public List<StudentBean> getStuAllList() {
        return userMapper.getStuAllList();
    }

    //查询班级信息
    public List<GradeBean> getGradeList() {
        return userMapper.getGradeList();
    }

    //省市县三级联动
    public List<CityBean> getCityListById(Long id) {
        return userMapper.getCityListById(id);
    }


}
