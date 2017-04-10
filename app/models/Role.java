package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.*;

/**
 * Created by zhangxuan on 16/10/19.
 */
@Entity
@PersistenceUnit(name="default")
public class Role extends Model {
    public String name;
//    @ManyToOne
//    public Function function;

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJSON(json, this);
        return json;
    }

    private void _parseJSON(JsonObject json, Role role) {
        json.addProperty("id", role.id);
        json.addProperty("name", role.name);
//        调用的是关联表中的 toJson方法
//        json.add("function",function.toJson());
    }
}
