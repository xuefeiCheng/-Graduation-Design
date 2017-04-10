package controllers.User;

import models.adminUser;
import models.leaderUser;
import models.studentUser;
import models.teacherUser;
import play.mvc.Controller;

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
    //    用于获取 学生 个人信息
    public static void getStudentJson(String userId){
        studentUser st = studentUser.findByStudentUserId(userId);
        renderJSON(st.toJson());
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
}
