package controllers.User;

import models.*;
import net.sf.json.JSONObject;
import play.db.jpa.JPA;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/11.
 */
public class getListController extends Controller {
    //    根据 课程id 获得 课程信息
    public static void getCourseJson(String co_id) {
        course co = course.findCourseById(co_id);
        renderJSON(co.toJson());
    }

    //    以老师 角色登录时 需要的方法
//    查看 该教师 今年开通的课程有哪些
//    通过 开课老师的id 获得 课程列表
    public static void getCoursesListByTeacher(String userId) {
        List<course> co = course.getCoursesByTeacherId(userId);
        renderJSON(co);
    }

    //    查询 课程列表
//    通过 学生 id
    public static void getCoursesListByStudent(String stId) {
        List<StudentCourseLink> co = StudentCourseLink.findCourseByStudent(stId);
//    System.out.println(co);
        renderJSON(co);
    }

    //    通过 学生 id 课程id 查找课程学生link表中的数据  查询 该学生的该门课程的状态
    public static void GetStatusNow(String stId, String coId) {
        StudentCourseLink stCo = StudentCourseLink.findCourseById(coId, stId);
        renderJSON(stCo);
    }

    //    查询 评学结果
//根据 学生id 查找 学生 courseResult  link表中的数据
    public static void findCourseResultByStudent(String stId) {
        StudentCourseResultLink coRe = StudentCourseResultLink.findCourseResultByStudent(stId);
        renderJSON(coRe);
    }

    //    通过课程id 查找选修该课程的所有 学生list
    public static void getStudentListByCourse(String coId) {
        List<StudentCourseLink> stList = StudentCourseLink.findStudentByCourse(coId);
//    List<Group> groups= GroupView.group.findAllByName(name);
//    System.out.println(co);
        renderJSON(stList);
    }

    //    根据 课程id 调用 courseResult模块中方法GetResultById  查到评学 结果 数据
    public static void getCourseResultByCoId(String cooId) {
        courseResult co = courseResult.GetResultById(cooId);
        renderJSON(co);
    }

    //    根据学生id 课程id 返回该条记录 查询 结果表
    public static void getResult(String coId, String stId) {
        teacherResult co = teacherResult.GetResultByStCo(coId, stId);
        renderJSON(co);
    }

    //    根据 课程id 查找该老师开通的这门课的所有同学 的评分list
    public static void getTeacherResultListByCoId(String coId) {
        List<teacherResult> teRe = teacherResult.findByCoId(coId);
        renderJSON(teRe);
    }

    //以班级为单位 得到班级总人数
//    返回 count 班级人数
//    classroom 班级 id
    public static void GetTotalCount() {
        List<JSONObject> teRe = studentClassroomLink.getTotalCount();
        renderJSON(teRe);
    }

    //    以学院为单位 查出 所有班级 的评教率
//    输出 班级id
//    每个班级 对应的评教率
//    json 格式
//    [
//          {"percent":22.02,"classroom":101331,"college":1},
//          {"percent":30.43,"classroom":101661,"college":1},
//          {"percent":20.1,"classroom":201331,"college":2}
//  ]
    public static void GetPercentGroupByCollege() {
        List<JSONObject> list = classRoomCollegeLink.getPercentGroupByCollege();
        renderJSON(list);
    }

    //    以学院 为单位 查找所有老师的list 并且查出 老师的得分 score
//    查询的表 为 老师用户表
    public static void getScoreGroupByCollege() {
        List<JSONObject> list = teacherCollegeLink.getScoreGroupByCollege();
        renderJSON(list);
    }

    //     学生 评教状态更改
    public static void SetP(String stId) {
        List<JSONObject> total = StudentCourseLink.getCourseTotalByStId(stId);
        List<JSONObject> done = StudentCourseLink.getCourseDoneByStId(stId);
//        int xx =Integer.parseInt(total.get(0).toString());
//        int yy =Integer.parseInt(done.get(0).toString());
        studentClassroomLink co = studentClassroomLink.GetClassRoom(stId);
        if (total.get(0) == done.get(0)) {
//            查 学生 班级 link表 更改状态 为1
            co.changeStatus(1);
        } else {
            co.changeStatus(0);
//            状态不更改 或者是 更改状态为0
        }
        co.save();
        renderJSON(co);
    }

    //    计算学生的评教率
//    班级总人数 查询model studentclassroomlink的getTotalCount()方法
//    以班级分组 查询 班级中 未完成评教的人数 查询 model studentclassroomlink的getNotDoneCount()方法
//    评教率 = （总人数-未参评人数）/总人数
    public static void setPercent() {
        List<JSONObject> totalCount = studentClassroomLink.getTotalCount();
        List<JSONObject> notDoneCount = studentClassroomLink.getNotDoneCount();
//        计算的评教率 需要 保存到  库表 班级学院link表中
//        输入  班级id
//        返回 该表数据 并将评教率 更新
//        System.out.println(totalCount.get(1).toString());
        if (totalCount.size() > 0) {
            for (int i = 0; i < totalCount.size(); i++) {
                classRoomCollegeLink li = classRoomCollegeLink.getInfoByClassId(totalCount.get(i).getString("classroom"));
                Float c = Float.valueOf(totalCount.get(i).getString("count"));
                Float n = Float.valueOf(notDoneCount.get(i).getString("count"));
                Float result = (c - n) / c;

                li.changeP(result);
                li.save();
//                renderJSON(li);
            }
//        renderJSON(totalCount.get(1).getString("count"));
//        renderJSON(totalCount);
        }
    }
}

