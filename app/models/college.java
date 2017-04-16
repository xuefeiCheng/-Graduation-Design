package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;

/**
 * Created by Administrator on 2017/4/16.
 */
//ѧԺ ��
@Entity
@PersistenceUnit(name="default")
public class college extends Model {
    public String name;//ѧԺ����


    private void _parseJson(JsonObject json, college st) {
        json.addProperty("name", st.name);
        json.addProperty("id", st.id);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
//    ����id ����Ϣ
    public static college getCollegeInfo(String co_id){
        return college.find("id",Long.valueOf(co_id)).first();
    }
}