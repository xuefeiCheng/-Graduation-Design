package models;

import play.db.jpa.Model;

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


    public static List<StudentCourseLink> findByStudent(studentUser student) {
        return StudentCourseLink.find("byStudent", student).fetch();
    }
//    两个方法是一样的
//    根据 student_id 得到数据库表 记录
    public static List<StudentCourseLink> findCourseByStudent(String stId){
        return StudentCourseLink.find("student_id=?",Long.valueOf(stId)).fetch();
    }
//    根据 course_id  得到数据库表记录

    public static List<StudentCourseLink> findStudentByCourse(String co_id){
        return StudentCourseLink.findById(co_id);
    }
}
