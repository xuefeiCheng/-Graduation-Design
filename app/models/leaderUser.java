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
public class leaderUser extends Model {
    //�̶�����
    public String le_id;//�û�id��ѧ�š��̹��š��쵼�ţ�
    public String name;//�û�name��ѧ�š��̹��š��쵼�ţ�
    public String sex;//�Ա�
    public String xy;// ѧԺ
    public String jg;//����
    public String zzmm;//������ò
    public String sfz;//����֤��
    public String mz;//����
    public String rxdate;//��У����
    public String zw;//ְλ

    //    �ɱ䲿��
    public String email;//����
    public String dz;//ͨѶ��ַ
    public String phone;//ͨѶ��ַ


    private void _parseJson(JsonObject json, leaderUser st) {
        json.addProperty("name",st.name);
        json.addProperty("le_id",st.le_id);
        json.addProperty("sex",st.sex);
        json.addProperty("xy",st.xy);
        json.addProperty("jg",st.jg);
        json.addProperty("zzmm",st.zzmm);
        json.addProperty("sfz",st.sfz);
        json.addProperty("mz",st.mz);
        json.addProperty("rxdate",st.rxdate);
        json.addProperty("zw",st.zw);

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
    public static leaderUser findByLeaderUserId(String te_id){
        return leaderUser.find("le_id",te_id).first();
    }
}