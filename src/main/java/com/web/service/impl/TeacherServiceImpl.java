package com.web.service.impl;


import com.web.entity.*;
import com.web.mapper.TeacherMapper;
import com.web.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    //获取独立考试信息
    public List<TestBean> getIndependentTestList(Long tid) {

        return teacherMapper.getIndependentTestList(tid);
    }

    //获取班级信息
    public List<GradeBean> getGradeListByTid(Long tid) {

        return teacherMapper.getGradeListByTid(tid);
    }

    //查询老师信息
    public List<TeacherBean> getTeacherList(Long tid) {

        return teacherMapper.getTeacherList(tid);
    }

    //添加考试信息
    public void saveTestMark(Long tid, List<Exam> list, TestBean testBean, Long[] gids) {

        //先把老师的名字查出来



        //先保存考试信息，再将testid拿回来

        Long testid = 0L;

        //先保存试题 或者 先保存考试和班级的关联关系
        //先保存班级和考试信息 tb_test_grade
        //在这里遍历或者将数组传进xml再遍历

        /**
         * 保存试题
         */
        for (Exam exam : list) {

            exam.setTestid(testid);

            //先去保存试题，再拿回试题id tb_exam
            List<ExamOption> options = exam.getOptions();

            //判断这个试题有没有选项，有的话，保存试题的选项
            for (ExamOption option : options) {

                option.setExamid(exam.getExamid());

                //再去保存 tb_examoptions
            }

        }


    }



}
