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
    //    ���� �γ�id ��� �γ���Ϣ
    public static void getCourseJson(String co_id) {
        course co = course.findCourseById(co_id);
        renderJSON(co.toJson());
    }

    //    ����ʦ ��ɫ��¼ʱ ��Ҫ�ķ���
//    �鿴 �ý�ʦ ���꿪ͨ�Ŀγ�����Щ
//    ͨ�� ������ʦ��id ��� �γ��б�
    public static void getCoursesListByTeacher(String userId) {
        List<course> co = course.getCoursesByTeacherId(userId);
        renderJSON(co);
    }

    //    ��ѯ �γ��б�
//    ͨ�� ѧ�� id
    public static void getCoursesListByStudent(String stId) {
        List<StudentCourseLink> co = StudentCourseLink.findCourseByStudent(stId);
//    System.out.println(co);
        renderJSON(co);
    }

    //    ͨ�� ѧ�� id �γ�id ���ҿγ�ѧ��link���е�����  ��ѯ ��ѧ���ĸ��ſγ̵�״̬
    public static void GetStatusNow(String stId, String coId) {
        StudentCourseLink stCo = StudentCourseLink.findCourseById(coId, stId);
        renderJSON(stCo);
    }

    //    ��ѯ ��ѧ���
//���� ѧ��id ���� ѧ�� courseResult  link���е�����
    public static void findCourseResultByStudent(String stId) {
        StudentCourseResultLink coRe = StudentCourseResultLink.findCourseResultByStudent(stId);
        renderJSON(coRe);
    }

    //    ͨ���γ�id ����ѡ�޸ÿγ̵����� ѧ��list
    public static void getStudentListByCourse(String coId) {
        List<StudentCourseLink> stList = StudentCourseLink.findStudentByCourse(coId);
//    List<Group> groups= GroupView.group.findAllByName(name);
//    System.out.println(co);
        renderJSON(stList);
    }

    //    ���� �γ�id ���� courseResultģ���з���GetResultById  �鵽��ѧ ��� ����
    public static void getCourseResultByCoId(String cooId) {
        courseResult co = courseResult.GetResultById(cooId);
        renderJSON(co);
    }

    //    ����ѧ��id �γ�id ���ظ�����¼ ��ѯ �����
    public static void getResult(String coId, String stId) {
        teacherResult co = teacherResult.GetResultByStCo(coId, stId);
        renderJSON(co);
    }

    //    ���� �γ�id ���Ҹ���ʦ��ͨ�����ſε�����ͬѧ ������list
    public static void getTeacherResultListByCoId(String coId) {
        List<teacherResult> teRe = teacherResult.findByCoId(coId);
        renderJSON(teRe);
    }

    //�԰༶Ϊ��λ �õ��༶������
//    ���� count �༶����
//    classroom �༶ id
    public static void GetTotalCount() {
        List<JSONObject> teRe = studentClassroomLink.getTotalCount();
        renderJSON(teRe);
    }

    //    ��ѧԺΪ��λ ��� ���а༶ ��������
//    ��� �༶id
//    ÿ���༶ ��Ӧ��������
//    json ��ʽ
//    [
//          {"percent":22.02,"classroom":101331,"college":1},
//          {"percent":30.43,"classroom":101661,"college":1},
//          {"percent":20.1,"classroom":201331,"college":2}
//  ]
    public static void GetPercentGroupByCollege() {
        List<JSONObject> list = classRoomCollegeLink.getPercentGroupByCollege();
        renderJSON(list);
    }

    //    ��ѧԺ Ϊ��λ ����������ʦ��list ���Ҳ�� ��ʦ�ĵ÷� score
//    ��ѯ�ı� Ϊ ��ʦ�û���
    public static void getScoreGroupByCollege() {
        List<JSONObject> list = teacherCollegeLink.getScoreGroupByCollege();
        renderJSON(list);
    }

    //     ѧ�� ����״̬����
    public static void SetP(String stId) {
        List<JSONObject> total = StudentCourseLink.getCourseTotalByStId(stId);
        List<JSONObject> done = StudentCourseLink.getCourseDoneByStId(stId);
//        int xx =Integer.parseInt(total.get(0).toString());
//        int yy =Integer.parseInt(done.get(0).toString());
        studentClassroomLink co = studentClassroomLink.GetClassRoom(stId);
        if (total.get(0) == done.get(0)) {
//            �� ѧ�� �༶ link�� ����״̬ Ϊ1
            co.changeStatus(1);
        } else {
            co.changeStatus(0);
//            ״̬������ ������ ����״̬Ϊ0
        }
        co.save();
        renderJSON(co);
    }

    //    ����ѧ����������
//    �༶������ ��ѯmodel studentclassroomlink��getTotalCount()����
//    �԰༶���� ��ѯ �༶�� δ������̵����� ��ѯ model studentclassroomlink��getNotDoneCount()����
//    ������ = ��������-δ����������/������
    public static void setPercent() {
        List<JSONObject> totalCount = studentClassroomLink.getTotalCount();
        List<JSONObject> notDoneCount = studentClassroomLink.getNotDoneCount();
//        ����������� ��Ҫ ���浽  ��� �༶ѧԺlink����
//        ����  �༶id
//        ���� �ñ����� ���������� ����
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

