package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceUnit;

/**
 * Created by Administrator on 2017/4/16.
 * ��ʦ ѧԺ link��
 * ��Ҫ���� ��ʦ��ѧԺ������
 * ����ѧԺ ���з��� ��ѯ������ʦ�ĵ÷�
 */
@Entity
@PersistenceUnit(name="default")
public class teacherCollegeLink extends Model {
    @ManyToOne
    public teacherUser te;//teacher ʵ��
    @ManyToOne
    public college co;//college ʵ��


//    ���� ��ʦid ���� ����
    public static teacherCollegeLink getTeacherCollegeInfo(String te_id){
        return teacherCollegeLink.find("te_id",Long.valueOf(te_id)).first();
    }
}
