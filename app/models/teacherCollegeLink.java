package models;

import net.sf.json.JSONObject;
import play.db.jpa.JPA;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

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

    public float score;//�洢  ��ʦ���÷�


//    ���� ��ʦid ���� ����
    public static teacherCollegeLink getTeacherCollegeInfo(String te_id){
        return teacherCollegeLink.find("te_id =?",Long.valueOf(te_id)).first();
    }

//    ���� ��ʦ�÷�
//    ����  ��ʦ �÷�
//    ���� ���̵÷�
    public void changeScore(Float score){
        this.score =score;
    }

    //    �� ѧԺ id ���� ��ѯ����
//    ���� ѧԺ ���� ��� �༶ �Լ� �༶������
    public static List<JSONObject> getScoreGroupByCollege(){
        List<JSONObject> list=new ArrayList<JSONObject>();
        List lists= JPA.em().createNativeQuery("SELECT score,te_id,co_id FROM `teachercollegelink` GROUP BY co_id," +
                "te_id;").getResultList();
        for (Object object : lists) {
            Object[] o=(Object[]) object;
            college co = college.getCollegeInfo(o[2].toString());
            teacherUser te = teacherUser.findByTeacherUserId(o[1].toString());
            JSONObject json=new JSONObject();
            json.put("score", o[0]);
            json.put("teacher", o[1]);
            json.put("college", o[2]);
            json.put("teacherName", te.name);
            json.put("collegeName", co.name);
            list.add(json);
        }
        return list;

    }
}
