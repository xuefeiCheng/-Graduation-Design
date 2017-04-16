package controllers.User;

import com.google.gson.JsonObject;
import models.*;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */
public class UserInfo extends Controller {

//    更改 学生 个人信息
    public static void changeInfo_st(String userId ,String email,String dz){
        studentUser user = studentUser.findByStudentUserId(userId);
        user.changeInfo(email,dz);
        user.save();
        renderJSON(user);
    }
    //    用于获取 学生 个人信息 查找的是 学生用户表
    public static void getStudentJson(String userId){
        studentUser st = studentUser.findByStudentUserId(userId);
        renderJSON(st.toJson());
    }
//    用于获取  学生个人信息 查找的是 学生 班级表
public static void getStudentClassJson(String userId){
//    List<studentClassroomLink> stClass = studentClassroomLink.findStudentClassByStudent(userId);
    studentClassroomLink ss = studentClassroomLink.GetClassRoom(userId);
//    ss 中 包括 student表中的信息 也包括 class中的信息
    renderJSON(ss.classroom);
}
//    用于获取 学生信息 包括 学生表中的所有数据 以及班级信息name 学院信息name
    public static JsonObject studentInfo(String userId){
        JsonObject json = new JsonObject();
//        获得学生 实体
//        studentUser st =studentUser.findByStudentUserId(stId);
//        获得学生 课程表中的数据
        studentClassroomLink st = studentClassroomLink.GetClassRoom(userId);
        json.addProperty("name",st.st.name);
        json.addProperty("id",st.st.id);
        json.addProperty("sex",st.st.sex);
        json.addProperty("zy",st.st.zy);
        json.addProperty("jg",st.st.jg);
        json.addProperty("zzmm",st.st.zzmm);
        json.addProperty("sfz",st.st.sfz);
        json.addProperty("lb",st.st.lb);
        json.addProperty("mz",st.st.mz);
        json.addProperty("rxdate",st.st.rxdate);
        json.addProperty("jb", st.st.jb);

        json.addProperty("email", st.st.email);
        json.addProperty("dz", st.st.dz);
        json.addProperty("phone", st.st.phone);

        json.addProperty("classroom", st.classroom.name);

        return json;
    }
//    更改 教师 个人信息
    public static void changeInfo_te(String userId ,String email,String dz){
        teacherUser user = teacherUser.findByTeacherUserId(userId);
        user.changeInfo(email, dz);
        user.save();
        renderJSON(user);
    }
    //    用于获取 教师 个人信息
    public static void getTeacherJson(String userId){
        teacherUser user = teacherUser.findByTeacherUserId(userId);
        renderJSON(user.toJson());
    }
    //    用于获取 教师信息 包括 教师表中的所有数据 以及 学院信息name
    public static JsonObject teacherInfo(String userId){
        JsonObject json = new JsonObject();
//        获得学生 实体
//        studentUser st =studentUser.findByStudentUserId(stId);
//        获得学生 课程表中的数据
        teacherCollegeLink st = teacherCollegeLink.getTeacherCollegeInfo(userId);
        json.addProperty("name",st.te.name);
        json.addProperty("id",st.te.id);
        json.addProperty("sex",st.te.sex);
//        json.addProperty("xy",st.te.xy);
        json.addProperty("jg",st.te.jg);
        json.addProperty("zzmm",st.te.zzmm);
        json.addProperty("sfz",st.te.sfz);
        json.addProperty("mz",st.te.mz);
        json.addProperty("rxdate",st.te.rxdate);
        json.addProperty("zc",st.te.zc);
        json.addProperty("score",st.te.score);

        json.addProperty("email", st.te.email);
        json.addProperty("dz", st.te.dz);
        json.addProperty("phone", st.te.phone);

        json.addProperty("college", st.co.name);

        return json;
    }

    //    更改 督导 个人信息
    public static void changeInfo_le(String userId ,String email,String dz){
        leaderUser user = leaderUser.findByLeaderUserId(userId);
        user.changeInfo(email, dz);
        user.save();
        renderJSON(user);
    }
    //    用于获取 督导 个人信息
    public static void getLeaderJson(String userId){
        leaderUser user = leaderUser.findByLeaderUserId(userId);
        renderJSON(user.toJson());
    }

    //    更改 管理员 个人信息
    public static void changeInfo_ad(String userId ,String email,String dz){
        adminUser user = adminUser.findByAdminUserId(userId);
        user.changeInfo(email, dz);
        user.save();
        renderJSON(user);
    }
    //    用于获取 管理员 个人信息
    public static void getAdminJson(String userId){
        adminUser user = adminUser.findByAdminUserId(userId);
        renderJSON(user.toJson());
    }


    //    输入 学院id 或者 教师 id
//    获得 教师 学院 link 表中的全部信息
    public static void getTeacherCollegeInfo(String nn){
        teacherCollegeLink te = teacherCollegeLink.getTeacherCollegeInfo(nn);
        renderJSON(te);
    }
}
