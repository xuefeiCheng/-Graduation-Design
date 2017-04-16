package models;

import com.google.gson.JsonObject;
import net.sf.json.JSONObject;
import play.db.jpa.JPA;
import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */
@Entity
@PersistenceUnit(name="default")
public class teacherUser extends Model {
//固定部分
//    public String te_id;//用户id（学号、教工号、领导号）
    public String name;//用户id（学号、教工号、领导号）
    public String sex;//性别
//    public String xy;// 学院
    public String jg;//籍贯
    public String zzmm;//政治面貌
    public String sfz;//身份证号
    public String mz;//民族
    public String rxdate;//入校年限
    public String zc;//职称

    @Column(name = "score")
    public Float score;//评教得分

//    可变部分
    public String email;//邮箱
    public String dz;//通讯地址
    public String phone;//通讯地址


    private void _parseJson(JsonObject json, teacherUser st) {
        json.addProperty("name",st.name);
        json.addProperty("id",st.id);
        json.addProperty("sex",st.sex);
//        json.addProperty("xy",st.xy);
        json.addProperty("jg",st.jg);
        json.addProperty("zzmm",st.zzmm);
        json.addProperty("sfz",st.sfz);
        json.addProperty("mz",st.mz);
        json.addProperty("rxdate",st.rxdate);
        json.addProperty("zc",st.zc);
        json.addProperty("score",st.score);

        json.addProperty("email", st.email);
        json.addProperty("dz", st.dz);
        json.addProperty("phone", st.phone);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }

    //    个人信息管理
    public void changeInfo(String email,String dz){
        this.email = email;
        this.dz = dz;
        this.phone = phone;
    }

    //    根据id 得到teacher列表
    public static teacherUser findByTeacherUserId(String te_id){
        return teacherUser.find("id", Long.valueOf(te_id)).first();
    }

}
