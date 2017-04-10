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

//    ���� ѧ�� ������Ϣ
    public static void changeInfo_st(String userId ,String email,String dz){
        studentUser user = studentUser.findByStudentUserId(userId);
        user.changeInfo(email,dz);
        user.save();
        renderJSON(user);
    }
    //    ���ڻ�ȡ ѧ�� ������Ϣ
    public static void getStudentJson(String userId){
        studentUser st = studentUser.findByStudentUserId(userId);
        renderJSON(st.toJson());
    }
//    ���� ��ʦ ������Ϣ
    public static void changeInfo_te(String userId ,String email,String dz){
        teacherUser user = teacherUser.findByTeacherUserId(userId);
        user.changeInfo(email, dz);
        user.save();
        renderJSON(user);
    }
    //    ���ڻ�ȡ ��ʦ ������Ϣ
    public static void getTeacherJson(String userId){
        teacherUser user = teacherUser.findByTeacherUserId(userId);
        renderJSON(user.toJson());
    }

    //    ���� ���� ������Ϣ
    public static void changeInfo_le(String userId ,String email,String dz){
        leaderUser user = leaderUser.findByLeaderUserId(userId);
        user.changeInfo(email, dz);
        user.save();
        renderJSON(user);
    }
    //    ���ڻ�ȡ ���� ������Ϣ
    public static void getLeaderJson(String userId){
        leaderUser user = leaderUser.findByLeaderUserId(userId);
        renderJSON(user.toJson());
    }

    //    ���� ����Ա ������Ϣ
    public static void changeInfo_ad(String userId ,String email,String dz){
        adminUser user = adminUser.findByAdminUserId(userId);
        user.changeInfo(email, dz);
        user.save();
        renderJSON(user);
    }
    //    ���ڻ�ȡ ����Ա ������Ϣ
    public static void getAdminJson(String userId){
        adminUser user = adminUser.findByAdminUserId(userId);
        renderJSON(user.toJson());
    }
}
