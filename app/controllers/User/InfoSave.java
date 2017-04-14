package controllers.User;

import models.course;
import models.courseResult;
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
}
