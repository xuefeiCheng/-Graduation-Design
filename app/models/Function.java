package models;

import com.google.gson.JsonArray;
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

    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }

    private void _parseJson(JsonObject json, Function function) {
        json.addProperty("id", function.id);
        json.addProperty("name", function.name);
    }

    private static List<Function> findByRole(Role role) {
        List<Function> list = new ArrayList<Function>();
        List<RoleFunctionLink> links = RoleFunctionLink.find("byRole", role).fetch();
        for (RoleFunctionLink link : links) {
            list.add(link.function);
        }
        return list;
    }

}
