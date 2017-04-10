package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2017/4/6.
 */
@Entity
@PersistenceUnit(name="default")
public class Menu extends Model {

    public String name;//menuӢ����
    public String displayName;//menu������
    public String url;//menu��Ӧ·��
    public String color;//menu��Ӧ��ɫ
    public String icon;//menu��Ӧͼ��·��


    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
    private void _parseJson(JsonObject json, Menu menu) {
        json.addProperty("id",menu.id);
        json.addProperty("name",menu.name);
        json.addProperty("displayName",menu.displayName);
        json.addProperty("url",menu.url);
        json.addProperty("color",menu.color);
        json.addProperty("icon",menu.icon);
    }
    public static List<Menu> findByRole(Role role) {
        List<Menu> list = new ArrayList<Menu>();
//        ����RoleMenuLinkģ�� �Լ���ģ���µķ���
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
