package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceUnit;

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


//    根据 教师id 返回 数据
    public static teacherCollegeLink getTeacherCollegeInfo(String te_id){
        return teacherCollegeLink.find("te_id",Long.valueOf(te_id)).first();
    }
}
