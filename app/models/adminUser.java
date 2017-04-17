package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;

/**
 * Created by Administrator on 2017/4/10.
 */
@Entity
@PersistenceUnit(name="default")
public class adminUser extends Model {
    //�̶�����
//    public String ad_id;//�û�id��ѧ�š��̹��š��쵼�ţ�
    public String name;//�û�name��ѧ�š��̹��š��쵼�ţ�
    public String sex;//�Ա�
    public String xy;// ѧԺ
    public String jg;//����
    public String zzmm;//������ò
    public String sfz;//���֤��
    public String mz;//����
    public String rxdate;//��У����
    public String js;//��ɫ

    //    �ɱ䲿��
    public String email;//����
    public String dz;//ͨѶ��ַ
    public String phone;//ͨѶ��ַ


    private void _parseJson(JsonObject json, adminUser st) {
        json.addProperty("name",st.name);
        json.addProperty("id",st.id);
        json.addProperty("sex",st.sex);
        json.addProperty("xy",st.xy);
        json.addProperty("jg",st.jg);
        json.addProperty("zzmm",st.zzmm);
        json.addProperty("sfz",st.sfz);
        json.addProperty("mz",st.mz);
        json.addProperty("rxdate",st.rxdate);
        json.addProperty("js",st.js);

        json.addProperty("email", st.email);
        json.addProperty("dz", st.dz);
        json.addProperty("phone", st.phone);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }

    //    ������Ϣ����
    public void changeInfo(String email,String dz){
        this.email = email;
        this.dz = dz;
        this.phone = phone;
    }

    //    ����id �õ�leader�б�
    public static adminUser findByAdminUserId(String te_id){
        return adminUser.find("id",Long.valueOf(te_id)).first();
    }
}
