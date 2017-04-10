package models;

import java.util.LinkedList;

/**
 * Created by zhangxuan on 16/10/19.
 */
public class Breadcrumb extends LinkedList<Menu>{


    private Breadcrumb(Menu menu){
        this.add(_addParent(menu));
    }

    public static Breadcrumb getByMenu(Menu menu){
        return new Breadcrumb(menu);
    }

    private Menu _addParent(Menu menu) {
        if (menu.parent == null) {
            return menu;
        }else {
            this.add(_addParent(menu.parent));
            return menu;
        }
    }
}
