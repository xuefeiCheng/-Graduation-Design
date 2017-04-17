package models;

import com.google.gson.JsonObject;
import net.sf.json.JSONObject;
import play.db.jpa.JPA;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/16.
 */
//ѧ�� �༶ link��
@Entity
@PersistenceUnit(name="default")
public class studentClassroomLink extends Model {
    @ManyToOne
    public studentUser st;//ѧ�� ʵ��
    @ManyToOne
    public classRoom classroom;//�༶ ʵ��
    public Integer status;//��¼ ѧ���Ƿ��������


    private void _parseJson(JsonObject json, studentClassroomLink st) {
        json.addProperty("status",st.status);

    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        _parseJson(json, this);
        return json;
    }
//    ���� ѧ��id ���� ��Ϣ
    public static studentClassroomLink GetClassRoom(String stId){
        return studentClassroomLink.find("st_id=?",Long.valueOf(stId)).first();
    }
//    ���� ѧ��id ���� ��Ϣ��ͬ�ϡ�
    public static studentClassroomLink getClassInfo(String stId){
        return studentClassroomLink.find("st_id = ?",Long.valueOf(stId)).first();
    }
//���� ѧ��id ���� ѧ�� �༶���м�¼ ��list����ʽ
    public static List<studentClassroomLink> findStudentClassByStudent(String stId){
        return studentClassroomLink.find("st_id=?",Long.valueOf(stId)).fetch();
    }

//    ���� ״̬
    public void changeStatus(Integer status){
        this.status = status;
    }

//    ���� �༶ ���� ��ð༶������
public static  List<JSONObject> getTotalCount(){
    List<JSONObject> list=new ArrayList<JSONObject>();
    List lists= JPA.em().createNativeQuery("SELECT COUNT(st_id),classroom_id FROM `studentclassroomlink` GROUP BY classroom_id").getResultList();
    for (Object object : lists) {
        Object[] o=(Object[]) object;
        JSONObject json=new JSONObject();
        json.put("count", o[0]);
        json.put("classroom", o[1]);
        list.add(json);
    }
    return list;
}
//    ���ݰ༶���� ��� status=0 ��ѧ�� ����
//SELECT count(st_id),classroom_id FROM `studentclassroomlink` WHERE `status`=0 GROUP BY classroom_id
//�õ�����  δ������ѧ������
    public static  List<JSONObject> getNotDoneCount(){
        List<JSONObject> list=new ArrayList<JSONObject>();
        List lists= JPA.em().createNativeQuery("SELECT count(st_id),classroom_id FROM `studentclassroomlink` WHERE `status`=0 GROUP BY classroom_id").getResultList();
        for (Object object : lists) {
            Object[] o=(Object[]) object;
            JSONObject json=new JSONObject();
            json.put("count", o[0]);
            json.put("classroom", o[1]);
            list.add(json);
        }
        return list;
    }
}
