package models;
import com.google.gson.JsonObject;
import play.db.jpa.Model;
import play.libs.Crypto;


import javax.persistence.*;

//@Entity
//hibernate  表继承的一种模式
//@Inheritance(strategy = InheritanceType.JOINED)
//@PersistenceUnit(name="default")
//@SecondaryTable(name="student",pkJoin=@PrimaryKeyJoinColumn(name="st_ID",referencedColumnName="user_id"))

@Entity
@PersistenceUnit(name="default")
//@Table(name = "User")
//@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Model {
//    与学生表连接
//    @ManyToOne
//    public studentUser st;
    @ManyToOne
    public Role role;
//    public Role role;
//    @Id
//    @Column(name = "user_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    public String user_id;//用户id（学号、教工号、领导号）
//    @Column(name = "name")
//    public String name;//个人真实姓名（例如：程雪飞）
    @Column(name = "password")
    public String password;//登录密码
//    public String role_id;//权限id
//    public int status;//登录状态

//    user 密码转换  加密
    public void UserChange(String user_id, String password) {
        String passwordHash = Crypto.passwordHash(password);
        this.user_id = user_id;
        this.password = passwordHash;
    }
//更改密码
    public void setPassword(String psd){
        this.password = psd;
    }
//    根据用户id 查找用户
//    查找条件  要和表中 列名对应起来
    public static User findByUserId(String user_id){
        return User.find("user_id",user_id).first();
    }

//    检测登录 通过用户id  和加密的密码 返回用户密码和用户id是否为空 用于前端界面信息提示
    public static boolean checkLogin(String user_id, String password) {
        return User.find("byUserIdAndPassword", user_id, Crypto.passwordHash(password)).first()!=null;
    }
//    toJson 方法是  用于被调用 但是 不是在该页面被调用 而是在引用该User模块的 控制器/模块 调用
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
    private void _parseJson(JsonObject json, User user) {
//        给对象添加属性
        json.addProperty("id", user.id);
        json.addProperty("user_id", user.user_id);
//        json.addProperty("name", user.name);
//        json.addProperty("role_id", user.role_id);
        json.add("role",role.toJson());
//        json.add("st",st.toJson());
    }

//    public static User findByUsername(String username) {
//        return User.find("byUsername",username).first();
//    }
    public static User findByUsername(String username) {
        return User.find("name",username).first();
    }

    public static User login(String user_id){
        User user = User.findByUserId(user_id);
        return user;
    }


}