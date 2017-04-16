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
 * 老师 学院 link表
 * 主要用于 老师在学院的排名
 * 根据学院 进行分组 查询的是老师的得分
 */
@Entity
@PersistenceUnit(name="default")
public class teacherCollegeLink extends Model {
    @ManyToOne
    public teacherUser te;//teacher 实体
    @ManyToOne
    public college co;//college 实体

    public float score;//存储  教师最后得分


//    根据 教师id 返回 数据
    public static teacherCollegeLink getTeacherCollegeInfo(String te_id){
        return teacherCollegeLink.find("te_id =?",Long.valueOf(te_id)).first();
    }

//    更新 教师得分
//    输入  教师 得分
//    更新 评教得分
    public void changeScore(Float score){
        this.score =score;
    }

    //    以 学院 id 分组 查询数据
//    根据 学院 分组 查出 班级 以及 班级评教率
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
