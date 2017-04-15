package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/15.
 */
@Entity
@PersistenceUnit(name="default")
public class teacherResult extends Model {
//    存储 学生评教内容
//    主要包括 课程id 学生id 各项评分 评语
    @Column(name = "coId")
    public Long coId;//课程id


    @Column(name = "stId")
    public Long stId;//学生id
    @Column(name = "p1")
    public Integer p1;//评价项1

    @Column(name = "p2")
    public Integer p2;//评价项

    @Column(name = "p3")
    public Integer p3;//评价项

    @Column(name = "p4")
    public Integer p4;//评价项

    @Column(name = "p5")
    public Integer p5;//评价项

    @Column(name = "p6")
    public Integer p6;//评价项

    @Column(name = "p7")
    public Integer p7;//评价项

    @Column(name = "p8")
    public Integer p8;//评价项

    @Column(name = "p9")
    public Integer p9;//评价项

    @Column(name = "p10")
    public Integer p10;//评价项

    @Column(name = "score")
    public Integer score;//每个学生评教总分

    @Column(name = "content")
    public String content;//评价内容

    private void _parseJson(JsonObject json, teacherResult teRe) {
        json.addProperty("coId",teRe.coId);//课程id
        json.addProperty("stId",teRe.stId);//课程id
        json.addProperty("p1",teRe.p1);//评价项
        json.addProperty("p2",teRe.p2);//评价项
        json.addProperty("p3",teRe.p3);//评价项
        json.addProperty("p4",teRe.p4);//评价项
        json.addProperty("p5",teRe.p5);//评价项
        json.addProperty("p6",teRe.p6);//评价项
        json.addProperty("p7",teRe.p7);//评价项
        json.addProperty("p8",teRe.p8);//评价项
        json.addProperty("p9",teRe.p9);//评价项
        json.addProperty("p10",teRe.p10);//评价项
        json.addProperty("score",teRe.score);//评价项
        json.addProperty("content",teRe.content);//评价内容
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
//    根据学生id  课程id 返回该条记录
    public static teacherResult GetResultByStCo(String co_id,String stId){
        return teacherResult.find("coId=? and stId=?",  Long.valueOf(co_id), Long.valueOf(stId)).first();
    }
    //根据 id 返回数据
    public static teacherResult GetResultById(String coId){
        return teacherResult.find("coId", Long.valueOf(coId)).first();
    }
//    根据 课程id 得到该id下的所有数据list
    public static List<teacherResult> findByCoId(String coId) {
        List<teacherResult> list = new ArrayList<teacherResult>();
//        coId and order by score desc
//        find("docid in (?1) order by publish_time desc").setParameter("1",ids).fetch(currentPage,pageSize);
//        数据库表 排序  降序排列
        list = teacherResult.find("coId in (?1) order by score desc",Long.valueOf(coId)).fetch();

        return list;
    }
    //    无参数 构造 函数
    public teacherResult currentUser(){
        this.coId=null;
        this.stId=null;
        this.p1 = null;
        this.p2 = null;
        this.p3 = null;
        this.p4 = null;
        this.p5 = null;
        this.p6 = null;
        this.p7 = null;
        this.p8 = null;
        this.p9 = null;
        this.p10 = null;
        this.score = null;
        this.content = null;
        return this;
    }
    //    保存 结果
    public void SetResult(String coId,String stId,Integer p1,Integer p2,Integer p3,Integer p4,Integer p5,Integer p6,Integer p7,Integer p8,Integer p9,Integer p10,String content){
        this.coId=Long.valueOf(coId);
        this.stId=Long.valueOf(stId);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
        this.p7 = p7;
        this.p8 = p8;
        this.p9 = p9;
        this.p10 = p10;
        this.score = p1+p2+p3+p4+p5+p6+p7+p8+p9+p10;
        this.content = content;
    }
}
