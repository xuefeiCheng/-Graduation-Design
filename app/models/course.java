package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/11.
 */

@Entity
@PersistenceUnit(name="default")
public class course extends Model {
    public String name;//�γ���
    public String te_id;//������ʦid

    private void _parseJson(JsonObject json, course co) {
        json.addProperty("id",co.id);//�γ�id
        json.addProperty("name",co.name);//�γ�name
        json.addProperty("te_id", co.te_id);//������ʦid
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
//    public static Map<String,Object> getCoursesByTeacherId(String userId) {
////        Map<String,Object> CoursesMap = new HashMap<String,Object>();
//        List<course> CoursesList = new ArrayList<course>();
//        CoursesList = course.findById(userId);
//        return CoursesList;
//    }

//    ���� �γ�id ���ر��γ������ݿ���е� ��¼
//    ���� ѧ����ɫʱ  ����ѧ��id ���� ѧ�� �γ�link�� �鵽�γ�id ->�γ̵ľ�����Ϣ
//    ���� �γ�id -> ����ʦ�� ��ʦ����Ϣ
    public static course findCourseById(String co_id){
        return course.find("id", Long.valueOf(co_id)).first();
    }
//    ���� ��ʦid ���Ҹ���ʦ��ͨ�Ŀγ�
    public static List<course> getCoursesByTeacherId(String te_Id){
        List<course> CoursesList = new ArrayList<course>();
        CoursesList = course.find("te_id",te_Id).fetch();
        return CoursesList;

    }
}
