package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceUnit;

/**
 * Created by zhangxuan on 16/10/19.
 */
@Entity
@PersistenceUnit(name="default")
public class RoleFunctionLink extends Model{
    @ManyToOne
    public Role role;

    @ManyToOne
    public Function function;
}

