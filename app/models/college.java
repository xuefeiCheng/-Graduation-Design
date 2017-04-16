package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;

/**
 * Created by Administrator on 2017/4/16.
 */
//学院 表
@Entity
@PersistenceUnit(name="default")
public class college extends Model {
    public String name;//学院名称


    private void _parseJson(JsonObject json, college st) {
        json.addProperty("name", st.name);
        json.addProperty("id", st.id);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
}
