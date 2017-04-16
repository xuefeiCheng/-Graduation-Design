package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;

/**
 * Created by Administrator on 2017/4/16.
 */
//�༶��
@Entity
@PersistenceUnit(name="default")
public class classRoom extends Model{
    public String name;//�༶ ����

    private void _parseJson(JsonObject json, classRoom st) {
        json.addProperty("name", st.name);
        json.addProperty("id", st.id);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
//    ����id ��������
    public static classRoom getClassInfo(String cl_id){
        return classRoom.find("id",Long.valueOf(cl_id)).first();
    }
}
