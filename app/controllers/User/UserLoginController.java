package controllers.User;
import models.User;
import play.data.validation.Required;
import play.mvc.Controller;


public class UserLoginController extends  Controller{
//�����û�
public static void login(@Required String username, @Required String password){
//    @SuppressWarnings("deprecation")
    User user = User.find("user_id = ? and password = ? ", username, password).first();
//    System.out.println(user);
    if(user != null){
       renderJSON(user);
    }else{
        System.out.println("�û��������벻��ȷ");
    }
}
}