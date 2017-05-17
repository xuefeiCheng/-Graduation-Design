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

    //    ���� student_id �õ����ݿ�� ��¼
    public static StudentCourseResultLink findCourseResultByStudent(String stId){
        return StudentCourseResultLink.find("student_id=?",Long.valueOf(stId)).first();
    }

}


