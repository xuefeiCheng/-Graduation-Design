package controllers.User;

import models.*;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */
public class getListController extends Controller {
//    ���� �γ�id ��� �γ���Ϣ
public static void getCourseJson(String co_id){
    course co = course.findCourseById(co_id);
    renderJSON(co.toJson());
}
//    ����ʦ ��ɫ��¼ʱ ��Ҫ�ķ���
//    �鿴 �ý�ʦ ���꿪ͨ�Ŀγ�����Щ
//    ͨ�� ������ʦ��id ��� �γ��б�
    public static void getCoursesListByTeacher(String userId){
        List<course> co =course.getCoursesByTeacherId(userId);
        renderJSON(co);
    }

//    ��ѯ �γ��б�
//    ͨ�� ѧ�� id
public static void getCoursesListByStudent(String stId){
    List<StudentCourseLink> co = StudentCourseLink.findCourseByStudent(stId);
//    System.out.println(co);
    renderJSON(co);
}
//    ͨ�� ѧ�� id �γ�id ���ҿγ�ѧ��link���е�����  ��ѯ ��ѧ���ĸ��ſγ̵�״̬
    public static void GetStatusNow(String stId,String coId){
        StudentCourseLink stCo = StudentCourseLink.findCourseById(coId, stId);
        renderJSON(stCo);
    }
//    ��ѯ ��ѧ���
//���� ѧ��id ���� ѧ�� courseResult  link���е�����
    public  static void findCourseResultByStudent(String stId){
        StudentCourseResultLink coRe = StudentCourseResultLink.findCourseResultByStudent(stId);
        renderJSON(coRe);
    }

//    ͨ���γ�id ����ѡ�޸ÿγ̵����� ѧ��list
public static void getStudentListByCourse(String coId){
    List<StudentCourseLink> stList = StudentCourseLink.findStudentByCourse(coId);
//    List<Group> groups= GroupView.group.findAllByName(name);
//    System.out.println(co);
    renderJSON(stList);
}

//    ���� �γ�id ���� courseResultģ���з���GetResultById  �鵽��ѧ ��� ����
    public static void getCourseResultByCoId(String cooId){
        courseResult co = courseResult.GetResultById(cooId);
        renderJSON(co);
    }
//    ����ѧ��id �γ�id ���ظ�����¼ ��ѯ �����
    public static void getResult(String coId,String stId){
        teacherResult co = teacherResult.GetResultByStCo(coId,stId);
        renderJSON(co);
    }
//    ���� �γ�id ���Ҹ���ʦ��ͨ�����ſε�����ͬѧ ������list
    public static void getTeacherResultListByCoId(String coId){
        List<teacherResult> teRe = teacherResult.findByCoId(coId);
        renderJSON(teRe);
    }
}
