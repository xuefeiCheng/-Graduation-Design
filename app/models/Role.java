package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;

/**
 * Created by zhangxuan on 16/10/19.
 */
@Entity
@PersistenceUnit(name="default")
public class Role extends Model {
    public String name;

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJSON(json, this);
        return json;
    }

    private void _parseJSON(JsonObject json, Role role) {
        json.addProperty("id", role.id);
        json.addProperty("name",role.name);
    }
}
