package controllers.User;

import models.StudentCourseLink;
import models.course;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */
public class getListController extends Controller {
//    根据 课程id 获得 课程信息
public static void getCourseJson(String co_id){
    course co = course.findCourseById(co_id);
    renderJSON(co.toJson());
}
//    以老师 角色登录时 需要的方法
//    查看 该教师 今年开通的课程有哪些
//    通过 开课老师的id 获得 课程列表
    public static void getCoursesListByTeacher(String userId){
        List<course> co =course.getCoursesByTeacherId(userId);
        renderJSON(co);
    }

//    查询 课程列表
//    通过 学生 id
public static void getCoursesListByStudent(String stId){
    List<StudentCourseLink> co = StudentCourseLink.findCourseByStudent(stId);
//    System.out.println(co);
    renderJSON(co);
}
}
