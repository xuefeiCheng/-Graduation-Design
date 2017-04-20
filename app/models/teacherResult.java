package models;

import com.google.gson.JsonObject;
import net.sf.json.JSONObject;
import net.sf.oval.constraint.Min;
import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/4/15.
 */
@Entity
@PersistenceUnit(name="default")
public class teacherResult extends Model {
//    �洢 ѧ����������
//    ��Ҫ���� �γ�id ѧ��id �������� ����
    @Column(name = "coId")
    public Long coId;//�γ�id


    @Column(name = "stId")
    public Long stId;//ѧ��id
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

    @Column(name = "score")
    public Integer score;//ÿ��ѧ�������ܷ�

    @Column(name = "content")
    public String content;//��������

    private void _parseJson(JsonObject json, teacherResult teRe) {
        json.addProperty("coId",teRe.coId);//�γ�id
        json.addProperty("stId",teRe.stId);//�γ�id
        json.addProperty("p1",teRe.p1);//������
        json.addProperty("p2",teRe.p2);//������
        json.addProperty("p3",teRe.p3);//������
        json.addProperty("p4",teRe.p4);//������
        json.addProperty("p5",teRe.p5);//������
        json.addProperty("p6",teRe.p6);//������
        json.addProperty("p7",teRe.p7);//������
        json.addProperty("p8",teRe.p8);//������
        json.addProperty("p9",teRe.p9);//������
        json.addProperty("p10",teRe.p10);//������
        json.addProperty("score",teRe.score);//������
        json.addProperty("content",teRe.content);//��������
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
//    ����ѧ��id  �γ�id ���ظ�����¼
    public static teacherResult GetResultByStCo(String co_id,String stId){
        return teacherResult.find("coId=? and stId=?",  Long.valueOf(co_id), Long.valueOf(stId)).first();
    }
    //���� id ��������
    public static teacherResult GetResultById(String coId){
        return teacherResult.find("coId", Long.valueOf(coId)).first();
    }
//    ���� �γ�id �õ���id�µ���������list
    public static List<teacherResult> findByCoId(String coId) {
        List<teacherResult> list = new ArrayList<teacherResult>();
//        coId and order by score desc
//        find("docid in (?1) order by publish_time desc").setParameter("1",ids).fetch(currentPage,pageSize);
//        ���ݿ�� ����  ��������
        list = teacherResult.find("coId in (?1) order by score desc",Long.valueOf(coId)).fetch();

        return list;
    }
//    ��ҳ
public static JSONObject listByIds(String coId, Integer p, Integer ps) {
    JSONObject returnjson=new JSONObject();
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    long count = 0L;
    if(p == null) {
        p = 1;
    }
    if(ps == null) {
        ps = 5;
    }


        List<teacherResult> warnList = new ArrayList<teacherResult>();
        List<teacherResult> statisWarnList = new ArrayList<teacherResult>();

                warnList = teacherResult.find("coId in (?1) order by score desc", Long.valueOf(coId)).fetch(p,ps);
                statisWarnList = teacherResult.find("coId in (?1) order by score desc", Long.valueOf(coId)).fetch();
                count = statisWarnList.size();

        Map<String, Object> docInfo;
        for(teacherResult w : warnList) {
            docInfo = new HashMap<String, Object>();
            docInfo.put("coId",w.coId);//�γ�id
            docInfo.put("stId",w.stId);//�γ�id
            docInfo.put("p1",w.p1);//������
            docInfo.put("p2",w.p2);//������
            docInfo.put("p3",w.p3);//������
            docInfo.put( "p4",w.p4);//������
            docInfo.put("p5",w.p5);//������
            docInfo.put( "p7",w.p7);//������
            docInfo.put( "p8",w.p8);//������
            docInfo.put("p9",w.p9);//������
            docInfo.put( "p10",w.p10);//������
            docInfo.put("score",w.score);//������
            docInfo.put( "content",w.content);//��������
            result.add(docInfo);
        }

    returnjson.put("rows", result);
    returnjson.put("count", count);
    returnjson.put("page", p);
    returnjson.put("pageSize", ps);
    return returnjson;
}
//��ҳ  ��ȡ ���̽��
    public Map<String,Object> findResultByCoId (String coId,@Min(0) Integer p, @Min(0) Integer ps) {
        Map<String,Object> teacherResultMap = new HashMap<String,Object>();
        List<teacherResult> list = new ArrayList<teacherResult>();
        long count = 0L;
        if (null == p) {
            p = 1;
        }
        if (null == ps) {
            ps = 5;
        }
        count = teacherResult.count("coId in (?1)", Long.valueOf(coId));
        list = teacherResult.find("coId in (?1) order by score desc",Long.valueOf(coId)).fetch(p,ps);
        teacherResultMap.put("rows",list);
        teacherResultMap.put("count",count);
        teacherResultMap.put("page",p);
        teacherResultMap.put("pageSize",ps);
        return teacherResultMap;
    }
    //    �޲��� ���� ����
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
    //    ���� ���
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
