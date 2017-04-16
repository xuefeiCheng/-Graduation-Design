package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

/**
 * Created by Administrator on 2017/4/16.
 */
//班级表
public class classRoom extends Model{
    public String name;//班级 名称

    private void _parseJson(JsonObject json, classRoom st) {
        json.addProperty("name", st.name);
        json.addProperty("id", st.id);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
}
