package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;

/**
 * Created by Administrator on 2017/5/17.
 */
@Entity
@PersistenceUnit(name="default")
public class pingjia extends Model {
    public String content;//ÆÀ¼ÛÄÚÈÝ

    private void _parseJson(JsonObject json, pingjia st) {
        json.addProperty("content", st.content);
        json.addProperty("id", st.id);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }

}
