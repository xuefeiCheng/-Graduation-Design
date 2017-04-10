package controllers.User;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import models.Menu;
import models.User;
import models.studentUser;
import play.Play;
import play.mvc.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/6.
 */
public class UserMenuController extends Controller {
//    根据 权限id 获得 menu
    public static JsonObject getMenu(String userId){
        JsonObject json = new JsonObject();
        JsonArray menus = new JsonArray();
//        JsonObject json1 = getStudent(userId);

        User user = User.findByUserId(userId);
//        json.addProperty("username",user.name);
        json.addProperty("userId",user.user_id);
        json.addProperty("psd",user.password);
        json.addProperty("roleId",user.role.id);
        json.addProperty("roleName",user.role.name);
        List<Menu> userMenu = Menu.findByRole(user.role);
        for (Menu m : userMenu) {
            JsonObject jsonMenu = new JsonObject();
            jsonMenu.addProperty("id",m.id);
            jsonMenu.addProperty("name",m.displayName);
//            jsonMenu.addProperty("url", Play.configuration.getProperty(m.displayName+".url"));
            jsonMenu.addProperty("url", m.url);
            jsonMenu.addProperty("color", m.color);
            jsonMenu.addProperty("icon", m.icon);
            menus.add(jsonMenu);
        }
        json.add("menus",menus);
//        json.add("st",json1);
//        json.add("st",stlist.toJson());
        return json;
    }
//    获得学生 对象
        public static JsonObject getStudent(String userId){
            JsonObject json = new JsonObject();
            studentUser st = studentUser.findByStudentUserId(userId);
            json.add("students", st.toJson());
            return json;
        }


//    更改密码
    public static void changePassword(String userId ,String newPassword){
        User user = User.findByUserId(userId);
        user.setPassword(newPassword);
        user.save();
        renderJSON(user);
    }
//private static JsonObject changePassword(JsonObject data) {
//    long userId = data.get("userId").getAsLong();
//    String oldPassword = data.get("oldPassword").getAsString();
//    String newPassword = data.get("newPassword").getAsString();
//    User user = User.findById(userId);
//    boolean loginOK = User.checkLogin(user.user_id, oldPassword);
//    if (!loginOK) {
////        throw new Exception("old password not correct");
//        System.out.println("old password not correct");
//    }
//    user.setPassword(newPassword);
//    user.save();
//    return null; //返回null，则response没有data属性
//}

}
