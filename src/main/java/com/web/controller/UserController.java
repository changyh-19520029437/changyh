package com.web.controller;

import com.web.entity.*;
import com.web.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    //查询所有用户
    @RequestMapping("/getUserList")
    @ResponseBody
    public List<UserBean> getUserList(){

        return userService.getUserList();
    }


    //查询学生信息
    @RequestMapping("/getStuAllList")
    @ResponseBody
    public List<StudentBean> getStuAllList(){

        return userService.getStuAllList();
    }

    //查询班级列表
    @ResponseBody
    @RequestMapping("/getGradeList")
    public List<GradeBean> getGradeList(){

        return userService.getGradeList();
    }


    //省市县三级联动
    @ResponseBody
    @RequestMapping("/getCityListById")
    public List<CityBean> getCityListById(Long id){

        return userService.getCityListById(id);
    }

    //上传试题
    @RequestMapping("/fileUpload")
    public void fileUpload(@RequestBody MultipartFile filename){

        try {
            //从SpringMVC上传上来的文件，获取到输入流
            InputStream inputStream = filename.getInputStream();


            //创建一个Excel，把流给它
            /**
             * HSSFWorkbook HSSF
             * poi导出excel最常用的方式
             * 但是此种方式的局限就是导出的行数至多为65535行，超出65536条后系统就会报错
             * 此方式因为行数不足七万行所以一般不会发生内存不足的情况（OOM）
             */
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

            /**
             * XSSFWorkbook XSSF
             * 这种形式的出现是为了突破HSSFWorkbook的65535行局限
             * 其对应的是excel2007(1048576行，16384列)扩展名为“.xlsx”，最多可以导出104万行
             * 不过这样就伴随着一个问题---OOM内存溢出
             * 原因是你所创建的book sheet row cell等此时是存在内存的并没有持久化
             *
             * XSSFWorkbook XSSF = null;
             */

            /**
             * SXSSFWorkbook SXSS
             * 从POI 3.8版本开始，提供了一种基于XSSF的低内存占用的SXSSF方式
             * 对于大型excel文件的创建，一个关键问题就是，要确保不会内存溢出
             *
             * SXSSFWorkbook SXSS = null;
             */


            /**
             * 获取工作簿里面的工作表：sheet
             * 可以用名字来获取，也可以用角标来获取
             * 习惯性用角标来获取
             */
            HSSFSheet sheet = workbook.getSheetAt(0);

            /**
             * 开始遍历工作表内的所有行，需要知道有多少行
             */
            int num = sheet.getLastRowNum();

            List<Exam> list = new ArrayList<Exam>();

            for (int i=2; i <= num; i++){

                //使用角标获取到Excel工作表中的行，0和1不是试题
                HSSFRow row = sheet.getRow(i);

                //考题对象
                Exam exam = new Exam();

                /**
                 * 获取行中的第一列：题型
                 */
                HSSFCell cell = row.getCell(0);

                String etype = cell.getStringCellValue();

                //将题型装进exam对象
                exam.setEtype(etype);

                /**
                 * 获取题干
                 */
                String enmae = row.getCell(1).getStringCellValue();

                //将题干装进exam对象
                exam.setEname(enmae);

                /**
                 * 获取答案
                 * 答案和选项需要装进到另外一个List集合内
                 * 答案拿到的是一个
                 */
                String answer = row.getCell(2).getStringCellValue();

                /**
                 * 获取分值
                 */
                double efenzhi = row.getCell(3).getNumericCellValue();

                //将分值装进exam对象
                exam.setEfenzhi(efenzhi);


                /**
                 * 选项,开始判断，单选和多选有四个选项
                 * 判断题有两个选项，填空题和问答题，没有选项
                 */
                if("单选题".equals(etype)){

                    //获取选项A，B，C，D
                    String xxA = row.getCell(4).getStringCellValue();
                    String xxB = row.getCell(5).getStringCellValue();
                    String xxC = row.getCell(6).getStringCellValue();
                    String xxD = row.getCell(7).getStringCellValue();

                    //从选项表中拿到选项对象
                    ExamOption oA = new ExamOption();
                    ExamOption oB = new ExamOption();
                    ExamOption oC = new ExamOption();
                    ExamOption oD = new ExamOption();

                    //将选项对应的值赋给对应的对象xxA-oA,xxB-oB,xxC-oC,xxD-oD
                    oA.setOname(xxA);
                    oB.setOname(xxB);
                    oC.setOname(xxC);
                    oD.setOname(xxD);

                    /**
                     * 判断答案是否正确
                     */
                    //将选项与答案进行比对，如果一致，将1（答案正确）赋值给Istrue对象
                    if("A".equalsIgnoreCase(answer)){
                        oA.setIstrue(1);
                    }
                    else if ("B".equalsIgnoreCase(answer)){
                        oB.setIstrue(1);
                    }
                    else if ("C".equalsIgnoreCase(answer)){
                        oC.setIstrue(1);
                    }
                    else if ("D".equalsIgnoreCase(answer)){
                        oD.setIstrue(1);
                    }
                    else{
                        System.out.print("单选题：题目出错");
                    }

                    //将答案
                    exam.getOptions().add(oA);
                    exam.getOptions().add(oB);
                    exam.getOptions().add(oC);
                    exam.getOptions().add(oD);

                }
                else if ("多选题".equals(etype)){

                    //因为在不同的if判断里，所以对象是可以重复的
                    //获取选项A，B，C，D
                    String xxA = row.getCell(4).getStringCellValue();
                    String xxB = row.getCell(5).getStringCellValue();
                    String xxC = row.getCell(6).getStringCellValue();
                    String xxD = row.getCell(7).getStringCellValue();

                    //从选项表中拿到选项对象
                    ExamOption oA = new ExamOption();
                    ExamOption oB = new ExamOption();
                    ExamOption oC = new ExamOption();
                    ExamOption oD = new ExamOption();

                    //将选项对应的值赋给对应的对象xxA-oA,xxB-oB,xxC-oC,xxD-oD
                    oA.setOname(xxA);
                    oB.setOname(xxB);
                    oC.setOname(xxC);
                    oD.setOname(xxD);

                    /**
                     * 多选需要先把答案拿出来，进行分割
                     */
                    String[] split = answer.split("\\|");

                    List<String> answers = Arrays.asList(split);

                    if (answers.contains("A")){
                        oA.setIstrue(1);
                    }
                    else if (answers.contains("B")){
                        oA.setIstrue(1);
                    }
                    else if (answers.contains("C")){
                        oA.setIstrue(1);
                    }
                    else if (answers.contains("D")){
                        oA.setIstrue(1);
                    }

                    //将选项对应的值赋给对应的对象xxA-oA,xxB-oB,xxC-oC,xxD-oD
                    exam.getOptions().add(oA);
                    exam.getOptions().add(oB);
                    exam.getOptions().add(oC);
                    exam.getOptions().add(oD);

                }
                else if ("判断题".equals(etype)){

                    //因为在不同的if判断里，所以对象是可以重复的
                    //获取选项A，B，C，D
                    String xxA = row.getCell(4).getStringCellValue();
                    String xxB = row.getCell(5).getStringCellValue();

                    //从选项表中拿到选项对象
                    ExamOption oA = new ExamOption();
                    ExamOption oB = new ExamOption();

                    //将选项对应的值赋给对应的对象xxA-oA,xxB-oB,xxC-oC,xxD-oD
                    oA.setOname(xxA);
                    oB.setOname(xxB);

                    //将选项与答案进行比对，如果一致，将1（答案正确）赋值给Istrue对象
                    if("A".equalsIgnoreCase(answer)){
                        oA.setIstrue(1);
                    }
                    else if ("B".equalsIgnoreCase(answer)){
                        oB.setIstrue(1);
                    }
                    else{
                        System.out.print("判断题：题目出错");
                    }

                    exam.getOptions().add(oA);
                    exam.getOptions().add(oB);

                }

                list.add(exam);

            }

            System.out.print(1);
            System.out.print(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
