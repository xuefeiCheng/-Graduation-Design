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
    public String name;//课程名
    public String te_id;//开课老师id

    private void _parseJson(JsonObject json, course co) {
        json.addProperty("id",co.id);//课程id
        json.addProperty("name",co.name);//课程name
        json.addProperty("te_id", co.te_id);//开课老师id
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

//    根据 课程id 返回本课程在数据库表中的 记录
//    用于 学生角色时  根据学生id 查找 学生 课程link表 查到课程id ->课程的具体信息
//    根据 课程id -> 【教师表】 教师的信息
    public static course findCourseById(String co_id){
        return course.find("id", Long.valueOf(co_id)).first();
    }
//    根据 老师id 查找该老师开通的课程
    public static List<course> getCoursesByTeacherId(String te_Id){
        List<course> CoursesList = new ArrayList<course>();
        CoursesList = course.find("te_id",te_Id).fetch();
        return CoursesList;

    }
}
