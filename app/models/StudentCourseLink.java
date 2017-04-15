package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */
@Entity
@PersistenceUnit(name="default")
public class StudentCourseLink extends Model {
    @ManyToOne
    public studentUser student;
    @ManyToOne
    public course course;
    @Column(name="status")
    public Integer status;//��¼״̬

    private void _parseJson(JsonObject json, StudentCourseLink st) {
        json.addProperty("status",st.status);

    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
    public static List<StudentCourseLink> findByStudent(studentUser student) {
        return StudentCourseLink.find("byStudent", student).fetch();
    }
//���� �γ�id ѧ��id ������Ϣ��¼
    public static StudentCourseLink findCourseById(String co_id,String stId){
        return StudentCourseLink.find("course_id=? and student_id=?", Long.valueOf(co_id), Long.valueOf(stId)).first();
    }
//    ����������һ����
//    ���� student_id �õ����ݿ�� ��¼
    public static List<StudentCourseLink> findCourseByStudent(String stId){
        return StudentCourseLink.find("student_id=?",Long.valueOf(stId)).fetch();
    }
//    ���� course_id  �õ����ݿ���¼

    public static List<StudentCourseLink> findStudentByCourse(String co_id){
        return StudentCourseLink.find("course_id=?",Long.valueOf(co_id)).fetch();
    }

    //    ״̬ �ı�
    public void SetStatus(int st){
        this.status = st;
//        Ĭ��Ϊ0
//        ����Ϊ1
    }
}
