package models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Administrator on 2017/4/8.
 */
//@Entity
//@Table(name = "student")
//@PrimaryKeyJoinColumn(name = "st_ID")

@Entity
//@Table(name = "student")
@PersistenceUnit(name="default")
//子表主键名称，对应于BillingDetails7表外键
//@PrimaryKeyJoinColumn(name="user_id")
public class studentUser extends Model {
//    @Id
//    @Column(name = "st_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    public String st_id;//用户id（学号、教工号、领导号）
    public String name;//用户id（学号、教工号、领导号）
    public String sex;//性别
    public String xy;// 学院
    public String classroom;//班级
    public String zy;//专业
    public String jg;//籍贯
    public String zzmm;//政治面貌
    public String sfz;//身份证号
    public String lb;//学生类别
    public String mz;//民族
    public String rxdate;//入学时间
    public String jb;//年级


    //可改项目
    public String email;//邮箱
    public String dz;//通讯地址
//    public Date cs;//出生日期

    private void _parseJson(JsonObject json, studentUser st) {
        json.addProperty("name",st.name);
        json.addProperty("st_id",st.st_id);
        json.addProperty("sex",st.sex);
        json.addProperty("xy",st.xy);
        json.addProperty("classroom",st.classroom);
        json.addProperty("zy",st.zy);
        json.addProperty("jg",st.jg);
        json.addProperty("zzmm",st.zzmm);
        json.addProperty("sfz",st.sfz);
        json.addProperty("lb",st.lb);
        json.addProperty("mz",st.mz);
        json.addProperty("rxdate",st.rxdate);
        json.addProperty("jb", st.jb);

        json.addProperty("email", st.email);
        json.addProperty("dz", st.dz);

//        st.cs= new Date();
//        json.add("cs",st.cs);
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
    }
//    根据id 得到student列表
    public static studentUser findByStudentUserId(String st_id){
     return studentUser.find("st_id",st_id).first();
    }
//    获得同一个学号的 学生记录
    public static List<studentUser> list(String st_id){
        return studentUser.find("st_id=?", st_id).fetch();
    }

}
