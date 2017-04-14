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
//    �洢 ��ʦ��ѧ����
//    ���� �γ�id ������Ŀ ��ѧ���� ����ʦid��
@Column(name = "coId")
public Long coId;
    @Column(name = "p1")
    public Integer p1;//������1

    @Column(name = "p2")
    public Integer p2;//������

    @Column(name = "p3")
    public Integer p3;//������

    @Column(name = "p4")
    public Integer p4;//������

    @Column(name = "p5")
    public Integer p5;//������

    @Column(name = "p6")
    public Integer p6;//������

    @Column(name = "p7")
    public Integer p7;//������

    @Column(name = "p8")
    public Integer p8;//������

    @Column(name = "p9")
    public Integer p9;//������

    @Column(name = "p10")
    public Integer p10;//������

    @Column(name = "content")
    public String content;//��������


    private void _parseJson(JsonObject json, courseResult coRe) {
        json.addProperty("coId",coRe.coId);//�γ�id
        json.addProperty("p1",coRe.p1);//������
        json.addProperty("p2",coRe.p2);//������
        json.addProperty("p3",coRe.p3);//������
        json.addProperty("p4",coRe.p4);//������
        json.addProperty("p5",coRe.p5);//������
        json.addProperty("p6",coRe.p6);//������
        json.addProperty("p7",coRe.p7);//������
        json.addProperty("p8",coRe.p8);//������
        json.addProperty("p9",coRe.p9);//������
        json.addProperty("p10",coRe.p10);//������
        json.addProperty("content",coRe.content);//��������
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
//���� id ��������
    public static courseResult GetResultById(String coId){
        return courseResult.find("coId", Long.valueOf(coId)).first();
    }
//    �޲��� ���� ����
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
//    ���� ���
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
