package controllers.User;

import models.StudentCourseLink;
import models.course;
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
}