package models;

import models.Breadcrumb;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceUnit;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhangxuan on 16/10/19.
 */
@Entity
@PersistenceUnit(name="default")
public class RoleMenuLink extends Model{
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
        return RoleMenuLink.find("role=?1 and menu=?2",role,menu).first();
    }
    /**
     * 修复角色对下级菜单有权限，但对上级菜单没有权限的情况
     */
    public static void fix(){
        List<Role> allRoles = Role.findAll();
        for (Role role : allRoles) {
            List<Menu> menus = Menu.findByRole(role);
            for (Menu menu : menus) {
                Breadcrumb breadcrumb = Breadcrumb.getByMenu(menu);
                Collections.reverse(breadcrumb);
                for (Menu menu2fix : breadcrumb) {
                    fix(menu2fix,role);
                }
            }
        }
    }

    private static void fix(Menu menu2fix, Role role) {

        if (RoleMenuLink.findByMenu(menu2fix).size() == 0) {
            RoleMenuLink link = new RoleMenuLink();
            link.role = role;
            link.menu = menu2fix;
            link.save();
        }
    }
}
