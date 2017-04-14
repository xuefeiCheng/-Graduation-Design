package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PersistenceUnit;

/**
 * Created by Administrator on 2017/4/14.
 */
@Entity
@PersistenceUnit(name="default")
public class courseResult extends Model {
//    存储 教师评学内容
//    包括 课程id 评分项目 评学内容 【教师id】
@Column(name = "coId")
public Long coId;
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

    @Column(name = "content")
    public String content;//评价内容


    private void _parseJson(JsonObject json, courseResult coRe) {
        json.addProperty("coId",coRe.coId);//课程id
        json.addProperty("p1",coRe.p1);//评价项
        json.addProperty("p2",coRe.p2);//评价项
        json.addProperty("p3",coRe.p3);//评价项
        json.addProperty("p4",coRe.p4);//评价项
        json.addProperty("p5",coRe.p5);//评价项
        json.addProperty("p6",coRe.p6);//评价项
        json.addProperty("p7",coRe.p7);//评价项
        json.addProperty("p8",coRe.p8);//评价项
        json.addProperty("p9",coRe.p9);//评价项
        json.addProperty("p10",coRe.p10);//评价项
        json.addProperty("content",coRe.content);//评价内容
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
//根据 id 返回数据
    public static courseResult GetResultById(String coId){
        return courseResult.find("coId", Long.valueOf(coId)).first();
    }
//    无参数 构造 函数
public courseResult currentUser(){
    this.coId=null;
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
    this.content = null;
    return this;
}
//    保存 结果
    public void SetResult(String coId,Integer p1,Integer p2,Integer p3,Integer p4,Integer p5,Integer p6,Integer p7,Integer p8,Integer p9,Integer p10,String content){
        this.coId=Long.valueOf(coId);
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
        this.content = content;
    }
}
