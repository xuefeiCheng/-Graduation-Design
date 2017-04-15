package controllers.User;

import models.*;
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
//    通过 学生 id 课程id 查找课程学生link表中的数据  查询 该学生的该门课程的状态
    public static void GetStatusNow(String stId,String coId){
        StudentCourseLink stCo = StudentCourseLink.findCourseById(coId, stId);
        renderJSON(stCo);
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

//    根据 课程id 调用 courseResult模块中方法GetResultById  查到评学 结果 数据
    public static void getCourseResultByCoId(String cooId){
        courseResult co = courseResult.GetResultById(cooId);
        renderJSON(co);
    }
//    根据学生id 课程id 返回该条记录 查询 结果表
    public static void getResult(String coId,String stId){
        teacherResult co = teacherResult.GetResultByStCo(coId,stId);
        renderJSON(co);
    }
//    根据 课程id 查找该老师开通的这门课的所有同学 的评分list
    public static void getTeacherResultListByCoId(String coId){
        List<teacherResult> teRe = teacherResult.findByCoId(coId);
        renderJSON(teRe);
    }
}
