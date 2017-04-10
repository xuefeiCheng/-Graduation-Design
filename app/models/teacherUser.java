package models;

import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;

/**
 * Created by Administrator on 2017/4/9.
 */
@Entity
@PersistenceUnit(name="default")
public class teacherUser extends Model {
//�̶�����
    public String te_id;//�û�id��ѧ�š��̹��š��쵼�ţ�
    public String name;//�û�id��ѧ�š��̹��š��쵼�ţ�
    public String sex;//�Ա�
    public String xy;// ѧԺ
    public String jg;//����
    public String zzmm;//������ò
    public String sfz;//���֤��
    public String mz;//����
    public String rxdate;//��У����
    public String zc;//ְ��

//    �ɱ䲿��
    public String email;//����
    public String dz;//ͨѶ��ַ


    private void _parseJson(JsonObject json, teacherUser st) {
        json.addProperty("name",st.name);
        json.addProperty("te_id",st.te_id);
        json.addProperty("sex",st.sex);
        json.addProperty("xy",st.xy);
        json.addProperty("jg",st.jg);
        json.addProperty("zzmm",st.zzmm);
        json.addProperty("sfz",st.sfz);
        json.addProperty("mz",st.mz);
        json.addProperty("rxdate",st.rxdate);
        json.addProperty("zc",st.zc);

        json.addProperty("email", st.email);
        json.addProperty("dz", st.dz);
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
    }

    //    ����id �õ�teacher�б�
    public static teacherUser findByTeacherUserId(String te_id){
        return teacherUser.find("te_id",te_id).first();
    }
}
