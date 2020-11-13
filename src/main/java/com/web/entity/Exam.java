package com.web.entity;

        import lombok.Data;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;

/**
 *
 * 考题表
 *
 */

@Data
public class Exam implements Serializable {

    private Long    examid;     //考题id
    private String  ename;      //考题内容
    private String  etype;      //考题类型
    private Double  efenzhi;    //考题分值

    private Long    testid;     //考试id

    //将所有选项封装到List集合里
    private List<ExamOption> options = new ArrayList<ExamOption>();
}
