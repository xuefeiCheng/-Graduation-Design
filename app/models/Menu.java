package models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxuan on 16/10/19.
 */
@Entity
@PersistenceUnit(name="default")
public class Menu extends Model {
    public static final Menu all = new Menu();
    @ManyToOne
    public Menu parent;
    public String name;
    public String displayName;

    public List<Menu> children(){
        if (this == Menu.all) {
            return Menu.find("byParentIsNull").fetch();
        }else{
            return Menu.find("byParent", this).fetch();
        }
    }

    public JsonObject toJson(){;
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }

    private void _parseJson(JsonObject json, Menu menu) {
        json.addProperty("id",menu.id);
        json.addProperty("name",menu.name);
        json.addProperty("displayName",menu.displayName);
        if(menu.parent!=null){
            json.addProperty("parentId",menu.parent.id);
        }
        List<Menu> children = menu.children();
        if (children.size() > 0) {
            JsonArray jsonArray = new JsonArray();
            for (Menu m : children) {
                JsonObject j = new JsonObject();
                _parseJson(j, m);
                jsonArray.add(j);
            }
            json.add("children",jsonArray);
        }
    }

    public static List<Menu> findByRole(Role role) {
        List<Menu> list = new ArrayList<Menu>();
        List<RoleMenuLink> links = RoleMenuLink.findByRole(role);
        for (RoleMenuLink link : links) {
            list.add(link.menu);
        }
        return list;
    }

    public static Menu findByName(String name) {
        return Menu.find("byName",name).first();
    }
}
