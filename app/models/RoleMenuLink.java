package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */

@Entity
@PersistenceUnit(name="default")
public class RoleMenuLink extends Model {

    @ManyToOne
    public Role role;

    @ManyToOne
    public Menu menu;
    public static List<RoleMenuLink> findByMenu(Menu menu) {
        return RoleMenuLink.find("byMenu", menu).fetch();
    }

    public static List<RoleMenuLink> findByRole(Role role) {
        return RoleMenuLink.find("byRole",role).fetch();
    }

    public static RoleMenuLink findByRoleAndMenu(Role role, Menu menu) {
//        查询语句   查询在 RoleMenuLink表中 role_id = role(参数)并且 menu_id = menu的数据
        return RoleMenuLink.find("role=?1 and menu=?2",role,menu).first();
    }
}
