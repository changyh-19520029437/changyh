package com.web.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 考试信息表
 */

@Data
public class TestBean implements Serializable {

    private Long     testid;        //考试id
    private String   testname;      //考试名字
    private double   totalscore;    //总分
    private double   passscore;     //通过分数
    private Integer  testtime;      //考试时间
    private Date     starttime;     //考试开始时间
    private Date     endtime;       //考试结束时间
    private Date     updatetime;    //考试更新时间
    private String   testauthor;    //试卷上传人
    private Long     authorid;      //试卷上传人id


}
