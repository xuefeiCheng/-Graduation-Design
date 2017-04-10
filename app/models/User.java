package models;
import play.db.jpa.Model;
import play.libs.Crypto;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class User extends Model {

    public Role role;
    public String user_id;//用户id（学号、教工号、领导号）
    public String name;//个人真实姓名（例如：程雪飞）
    public String password;//登录密码
    public String role_id;//权限id
    public int status;//登录状态

//    user 密码转换  加密
    public User(String user_id, String password) {
        String passwordHash = Crypto.passwordHash(password);
        this.user_id = user_id;
        this.password = passwordHash;
    }

//    根据用户id 查找用户
    public static User findByUserId(String user_id){
        return User.find("byUserId",user_id).first();
    }

//    检测登录 通过用户id  和加密的密码 返回用户密码和用户id是否为空 用于前端界面信息提示
    public static boolean checkLogin(String user_id, String password) {
        return User.find("byUserIdAndPassword", user_id, Crypto.passwordHash(password)).first()!=null;
    }

////    获得当前用户
//    public static User currentUser(){
//        User user = User.find("byUserId", connected()).first();
//        if (user == null) {
//            throw new RuntimeException("User is null!");
//        }
//        return user;
//    }
    public static User login(String user_id){
        User user = User.findByUserId(user_id);
        return user;
    }

    // Query
//    public static Model.Finder<Integer, User> find =
//            new Model.Finder<>(Integer.class, User.class);

    // Authentification
//    public static User authenticate(String user_id, String password) {
//        User user =  find("user_id?=",user_id).first();
//        if (user == null) {
//            return user;
//        } else if (Crypto.passwordHash(password)) {
//            return user;
//        } else {
//            return null;
//        }
//    }
}