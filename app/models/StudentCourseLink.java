package models;

import com.google.gson.JsonObject;
import net.sf.json.JSONObject;
import play.db.jpa.JPA;
import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Integer status;//登录状态

    private void _parseJson(JsonObject json, StudentCourseLink st) {
        json.addProperty("status", st.status);

    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
    public static List<StudentCourseLink> findByStudent(studentUser student) {
        return StudentCourseLink.find("byStudent", student).fetch();
    }
//根据 课程id 学生id 查找信息记录
    public static StudentCourseLink findCourseById(String co_id,String stId){
        return StudentCourseLink.find("course_id=? and student_id=?", Long.valueOf(co_id), Long.valueOf(stId)).first();
    }
//    两个方法是一样的
//    根据 student_id 得到数据库表 记录
    public static List<StudentCourseLink> findCourseByStudent(String stId){
        return StudentCourseLink.find("student_id=?",Long.valueOf(stId)).fetch();
    }
//    根据 course_id  得到数据库表记录

    public static List<StudentCourseLink> findStudentByCourse(String co_id){
        return StudentCourseLink.find("course_id=?",Long.valueOf(co_id)).fetch();
    }

    //    状态 改变
    public void SetStatus(int st){
        this.status = st;
//        默认为0
//        已评为1
    }

//    获得  学生的选修的所有课程数
public static List<JSONObject> getCourseTotalByStId(String stId){
    List count =JPA.em().createNativeQuery("SELECT count(course_id) FROM `studentcourselink` WHERE student_id="+stId).getResultList();
    return count;

}
  //    查询 学生评教的课程数
    public static List<JSONObject> getCourseDoneByStId(String stId){
        return JPA.em().createNativeQuery("SELECT count(course_id) FROM `studentcourselink` WHERE `status`=1 AND student_id="+stId).getResultList();

    }
}
