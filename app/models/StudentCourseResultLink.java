package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */
@Entity
@PersistenceUnit(name="default")
public class StudentCourseResultLink extends Model {
    @ManyToOne
    public studentUser student;
    @ManyToOne
    public courseResult coRe;

    //    根据 student_id 得到数据库表 记录
    public static StudentCourseResultLink findCourseResultByStudent(String stId){
        return StudentCourseResultLink.find("student_id=?",Long.valueOf(stId)).first();
    }

}


