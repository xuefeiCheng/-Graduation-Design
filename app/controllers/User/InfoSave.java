package controllers.User;

import models.*;
import play.mvc.Controller;

/**
 * Created by Administrator on 2017/4/14.
 */
public class InfoSave extends Controller {
//    评学界面 保存到 studentCourseResult表中
//    调用的是 该表的 SetResult方法
public static void CourseResultSet(String coId ,Integer p1,Integer p2,Integer p3,Integer p4,Integer p5,Integer p6,Integer p7,Integer p8,Integer p9,Integer p10,String content){
    if(content==null){
        content="没有评语哦";
    }
    courseResult re = courseResult.GetResultById(coId);
    if(re==null){
        courseResult reset = new courseResult();
        reset.SetResult(coId,p1,p10,p2,p3,p4,p5,p6,p7,p8,p9,content);
        reset.save();
    }else{
        re.SetResult(coId,p1,p10,p2,p3,p4,p5,p6,p7,p8,p9,content);
        re.save();
    }
    renderJSON(re);
}

//    课程表 状态改变
    public static void statusChange(String coId){
        course co = course.findCourseById(coId);
        co.SetStatus(1);
        co.save();
        renderJSON(co);
    }


//    学生评教 模块 保存到teacherResult表中 调用的是该表中的SetResult方法
public static void teacherResultSet(String coId ,String stId ,Integer p1,Integer p2,Integer p3,Integer p4,Integer p5,Integer p6,Integer p7,Integer p8,Integer p9,Integer p10,String content){
    if(content==null){
        content="没有评语哦";
    }
    teacherResult re = teacherResult.GetResultByStCo(coId, stId);
    if(re==null){
        teacherResult reset = new teacherResult();
        reset.SetResult(coId,stId,p1,p10,p2,p3,p4,p5,p6,p7,p8,p9,content);
        reset.save();
    }else{
        re.SetResult(coId,stId,p1,p10,p2,p3,p4,p5,p6,p7,p8,p9,content);
        re.save();
    }
    renderJSON(re);
}

    //    课程 学生 link 表 状态改变
    public static void statusSave(String coId,String stId){
        StudentCourseLink co = StudentCourseLink.findCourseById(coId, stId);
        co.SetStatus(1);
        co.save();
        renderJSON(co);
    }

//    更新  教师 学院 link表中的 score字段
    public static void scoreSave(String teId,Float score){
        teacherCollegeLink te = teacherCollegeLink.getTeacherCollegeInfo(teId);
        te.changeScore(score);
        te.save();
        renderJSON(te);
    }

//获取  评教 标准
    public static void getPingjiaInfo(){
        renderJSON(pingjia.findAll());
    }
//    获取  评学 标准
}
