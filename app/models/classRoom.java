package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

/**
 * Created by Administrator on 2017/4/16.
 */
//�༶��
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
}
