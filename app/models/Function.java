package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxuan on 16/10/19.
 */
@Entity
@PersistenceUnit(name="default")

public class Function extends Model {
    public String name;

//    public JsonObject toJson() {
//        JsonObject json = new JsonObject();
//        _parseJSON(json, this);
//        return json;
//    }

    private void _parseJSON(JsonObject json, Function function) {
        json.addProperty("id", function.id);
        json.addProperty("name",function.name);
    }
}
