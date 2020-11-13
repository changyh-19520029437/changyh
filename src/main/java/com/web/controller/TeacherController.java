package com.web.controller;


import com.web.comm.ResultInfo;
import com.web.entity.Exam;
import com.web.entity.GradeBean;
import com.web.entity.TestBean;
import com.web.service.TeacherService;
import com.web.utils.PoiInput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/teacherController")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    /**
     * 只能查看登录用户有权限的部分列表
     * 登录成功了，要把用户信息存入session中
     */

    Long tid = 1L;

    @RequestMapping("/getIndependentTestList")
    public String getIndependentTestList(Model model){

        /**
         * SELECT DISTINCT a.* from tb_test a
         * LEFT JOIN tb_test_grade b on a.testid = b.testid
         * LEFT JOIN tb_grade c on c.gid = b.gid
         * LEFT JOIN tb_teacher_grade d On d.gid = c.gid
         * where d.tid = 2
         */

        List<TestBean> list = teacherService.getIndependentTestList(tid);

        model.addAttribute("list", list);

        return "test_independent";
    }

    @RequestMapping("/getGradeListByTid")
    public String getGradeListByTid(Model model){

        //把老师可以发布考试的班级查询出来，直接给到页面
        List<GradeBean> list = teacherService.getGradeListByTid(tid);

        model.addAttribute("list", list);

        return "test_mark";
    }

    @RequestMapping("/fileUpload")
    @ResponseBody
    public ResultInfo fileUpload(MultipartFile file, HttpServletRequest request){

        /**
         * 返回上传成功还是失败，上传失败需要将原因返回回去
         * 上传成功将总分返回回去
         */
        List<Exam> list = PoiInput.getTestList(file);

        double totalscroe = 0.0;

        for (Exam exam : list) {

            totalscroe+=exam.getEfenzhi();
        }
        request.getSession().setAttribute("list", list);

        return new ResultInfo(true, ""+totalscroe);
    }

    @RequestMapping("/saveTestMark")
    public String saveTestMark(TestBean testBean, Long[] gids, HttpServletRequest request){

        List<Exam> list = (List<Exam>) request.getSession().getAttribute("list");

        teacherService.saveTestMark(tid, list, testBean, gids);

        return "redirect:getIndependentTestList.do";
    }

}



