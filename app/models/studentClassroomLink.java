package models;

import com.google.gson.JsonObject;
import net.sf.json.JSONObject;
import play.db.jpa.JPA;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/16.
 */
//学生 班级 link表
@Entity
@PersistenceUnit(name="default")
public class studentClassroomLink extends Model {
    @ManyToOne
    public studentUser st;//学生 实体
    @ManyToOne
    public classRoom classroom;//班级 实体
    public Integer status;//记录 学生是否完成评教


    private void _parseJson(JsonObject json, studentClassroomLink st) {
        json.addProperty("status",st.status);

    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
//    根据 学生id 查找 信息
    public static studentClassroomLink GetClassRoom(String stId){
        return studentClassroomLink.find("st_id=?",Long.valueOf(stId)).first();
    }
//    根据 学生id 返回 信息【同上】
    public static studentClassroomLink getClassInfo(String stId){
        return studentClassroomLink.find("st_id = ?",Long.valueOf(stId)).first();
    }
//根据 学生id 返回 学生 班级所有记录 以list的形式
    public static List<studentClassroomLink> findStudentClassByStudent(String stId){
        return studentClassroomLink.find("st_id=?",Long.valueOf(stId)).fetch();
    }

//    更新 状态
    public void changeStatus(Integer status){
        this.status = status;
    }

//    根据 班级 分组 获得班级总人数
public static  List<JSONObject> getTotalCount(){
    List<JSONObject> list=new ArrayList<JSONObject>();
    List lists= JPA.em().createNativeQuery("SELECT COUNT(st_id),classroom_id FROM `studentclassroomlink` GROUP BY classroom_id").getResultList();
    for (Object object : lists) {
        Object[] o=(Object[]) object;
        JSONObject json=new JSONObject();
        json.put("count", o[0]);
        json.put("classroom", o[1]);
        list.add(json);
    }
    return list;
}
//    根据班级分组 获得 status=0 的学生 人数
//SELECT count(st_id),classroom_id FROM `studentclassroomlink` WHERE `status`=0 GROUP BY classroom_id
//得到的是  未参评的学生人数
    public static  List<JSONObject> getNotDoneCount(){
        List<JSONObject> list=new ArrayList<JSONObject>();
        List lists= JPA.em().createNativeQuery("SELECT count(st_id),classroom_id FROM `studentclassroomlink` WHERE `status`=0 GROUP BY classroom_id").getResultList();
        for (Object object : lists) {
            Object[] o=(Object[]) object;
            JSONObject json=new JSONObject();
            json.put("count", o[0]);
            json.put("classroom", o[1]);
            list.add(json);
        }
        return list;
    }
}
