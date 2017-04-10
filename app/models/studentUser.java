package models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Administrator on 2017/4/8.
 */
//@Entity
//@Table(name = "student")
//@PrimaryKeyJoinColumn(name = "st_ID")

@Entity
//@Table(name = "student")
@PersistenceUnit(name="default")
//�ӱ��������ƣ���Ӧ��BillingDetails7�����
//@PrimaryKeyJoinColumn(name="user_id")
public class studentUser extends Model {
//    @Id
//    @Column(name = "st_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    public String st_id;//�û�id��ѧ�š��̹��š��쵼�ţ�
    public String name;//�û�id��ѧ�š��̹��š��쵼�ţ�
    public String sex;//�Ա�
    public String xy;// ѧԺ
    public String classroom;//�༶
    public String zy;//רҵ
    public String jg;//����
    public String zzmm;//������ò
    public String sfz;//���֤��
    public String lb;//ѧ�����
    public String mz;//����
    public String rxdate;//��ѧʱ��
    public String jb;//�꼶


    //�ɸ���Ŀ
    public String email;//����
    public String dz;//ͨѶ��ַ
//    public Date cs;//��������

    private void _parseJson(JsonObject json, studentUser st) {
        json.addProperty("name",st.name);
        json.addProperty("st_id",st.st_id);
        json.addProperty("sex",st.sex);
        json.addProperty("xy",st.xy);
        json.addProperty("classroom",st.classroom);
        json.addProperty("zy",st.zy);
        json.addProperty("jg",st.jg);
        json.addProperty("zzmm",st.zzmm);
        json.addProperty("sfz",st.sfz);
        json.addProperty("lb",st.lb);
        json.addProperty("mz",st.mz);
        json.addProperty("rxdate",st.rxdate);
        json.addProperty("jb", st.jb);

        json.addProperty("email", st.email);
        json.addProperty("dz", st.dz);

//        st.cs= new Date();
//        json.add("cs",st.cs);
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
//    ����id �õ�student�б�
    public static studentUser findByStudentUserId(String st_id){
     return studentUser.find("st_id",st_id).first();
    }
//    ���ͬһ��ѧ�ŵ� ѧ����¼
    public static List<studentUser> list(String st_id){
        return studentUser.find("st_id=?", st_id).fetch();
    }

}
