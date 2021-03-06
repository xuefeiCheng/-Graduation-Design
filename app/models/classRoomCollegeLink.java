package models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.sf.json.JSONObject;
import play.db.jpa.JPA;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceUnit;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 * 班级 学院 link表
 * 对应班级 学院表 包括班级的一个评教率
 */
@Entity
@PersistenceUnit(name="default")
public class classRoomCollegeLink extends Model{
    @ManyToOne
    public classRoom classroom;//班级表
    @ManyToOne
    public college college;//学院表

    public float percent;//存储 班级评教率

    private void _parseJson(JsonObject json, classRoomCollegeLink st) {
        json.addProperty("status", st.percent);

    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }

    public static List<classRoomCollegeLink> classRoomCollegeLink(){
        return classRoomCollegeLink.findAll();
    }
//根据 班级 id 返回数据
    public static classRoomCollegeLink getInfoByClassId(String classId){
        return classRoomCollegeLink.find("classroom_id =?",Long.valueOf(classId)).first();
    }
//     更新 评教率
    public void changeP(float p){
        this.percent = p;
    }

//    根据 学院 分组 查出 班级 以及 班级评教率
public static List<JSONObject> getPercentGroupByCollege(){
    List<JSONObject> list=new ArrayList<JSONObject>();
    List lists= JPA.em().createNativeQuery("SELECT percent,classroom_id,college_id FROM `classroomcollegelink` GROUP BY college_id," +
            "classroom_id;").getResultList();
    for (Object object : lists) {
        Object[] o=(Object[]) object;
        college co = models.college.getCollegeInfo(o[2].toString());
        classRoom ca = classRoom.getClassInfo(o[1].toString());
        JSONObject json=new JSONObject();
        json.put("percent", o[0]);
        json.put("classroom", o[1]);
        json.put("college", o[2]);
        json.put("classRoomName", ca.name);
        json.put("collegeName", co.name);
        list.add(json);
    }
    return list;

}

//    以学院分组 输出学院id 用于 评教率统计功能中 动态产生界面所需id
    public static List<JSONObject> getCollegeIdGroup(){
        List lists= JPA.em().createNativeQuery("SELECT college_id FROM `classroomcollegelink` GROUP BY college_id").getResultList();
        return lists;
    }
}
