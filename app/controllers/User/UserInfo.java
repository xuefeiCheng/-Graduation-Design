package controllers.User;

import com.google.gson.JsonObject;
import models.*;
import play.mvc.Controller;

import java.util.List;

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
    //    ���ڻ�ȡ ѧ�� ������Ϣ ���ҵ��� ѧ���û���
    public static void getStudentJson(String userId){
        studentUser st = studentUser.findByStudentUserId(userId);
        renderJSON(st.toJson());
    }
//    ���ڻ�ȡ  ѧ��������Ϣ ���ҵ��� ѧ�� �༶��
public static void getStudentClassJson(String userId){
//    List<studentClassroomLink> stClass = studentClassroomLink.findStudentClassByStudent(userId);
    studentClassroomLink ss = studentClassroomLink.GetClassRoom(userId);
//    ss �� ���� student���е���Ϣ Ҳ���� class�е���Ϣ
    renderJSON(ss.classroom);
}
//    ���ڻ�ȡ ѧ����Ϣ ���� ѧ�����е��������� �Լ��༶��Ϣname ѧԺ��Ϣname
    public static JsonObject studentInfo(String userId){
        JsonObject json = new JsonObject();
//        ���ѧ�� ʵ��
//        studentUser st =studentUser.findByStudentUserId(stId);
//        ���ѧ�� �γ̱��е�����
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
    //    ���ڻ�ȡ ��ʦ��Ϣ ���� ��ʦ���е��������� �Լ� ѧԺ��Ϣname
    public static JsonObject teacherInfo(String userId){
        JsonObject json = new JsonObject();
//        ���ѧ�� ʵ��
//        studentUser st =studentUser.findByStudentUserId(stId);
//        ���ѧ�� �γ̱��е�����
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


    //    ���� ѧԺid ���� ��ʦ id
//    ��� ��ʦ ѧԺ link ���е�ȫ����Ϣ
    public static void getTeacherCollegeInfo(String nn){
        teacherCollegeLink te = teacherCollegeLink.getTeacherCollegeInfo(nn);
        renderJSON(te);
    }
}
