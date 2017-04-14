package controllers.User;

import models.StudentCourseLink;
import models.StudentCourseResultLink;
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
//    查询 评学结果
//根据 学生id 查找 学生 courseResult  link表中的数据
    public  static void findCourseResultByStudent(String stId){
        StudentCourseResultLink coRe = StudentCourseResultLink.findCourseResultByStudent(stId);
        renderJSON(coRe);
    }

//    通过课程id 查找选修该课程的所有 学生list
public static void getStudentListByCourse(String coId){
    List<StudentCourseLink> stList = StudentCourseLink.findStudentByCourse(coId);
//    List<Group> groups= GroupView.group.findAllByName(name);
//    System.out.println(co);
    renderJSON(stList);
}
}
