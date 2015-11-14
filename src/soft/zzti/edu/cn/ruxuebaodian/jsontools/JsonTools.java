package soft.zzti.edu.cn.ruxuebaodian.jsontools;

import org.json.JSONArray;
import org.json.JSONObject;
import soft.zzti.edu.cn.ruxuebaodian.entity.SchoolTotal;
import soft.zzti.edu.cn.ruxuebaodian.entity.ZhuanYeCount;

import java.util.*;

public class JsonTools {

    public JsonTools() {
        // TODO Auto-generated constructor stub
    }

    public static SchoolTotal getTotal(String key, String jsonString) {
        SchoolTotal schoolTotal = new SchoolTotal();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject jsonObject1 = jsonObject.getJSONObject(key);
            schoolTotal.setNum(jsonObject1.getString("num"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schoolTotal;
    }

    public static ZhuanYeCount getTotal_zhuanyeyuanxiao(String key, String jsonString) {
        ZhuanYeCount Total = new ZhuanYeCount();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject jsonObject1 = jsonObject.getJSONObject(key);
            Total.setTotalRecord(jsonObject1.getString("num"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Total;
    }
    public static ZhuanYeCount getzycount(String key, String jsonString) {
        ZhuanYeCount zycount = new ZhuanYeCount();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject jsonObject1 = jsonObject.getJSONObject(key);
            zycount.setTotalRecord(jsonObject1.getString("num"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zycount;
    }
    //
//	public static School getSchool(String key,String jsonString){
//		Person person = new Person();
//		try {
//			JSONObject jsonObject1 = new JSONObject(jsonString);
//			JSONObject personObject = jsonObject1.getJSONObject(key);
//			person.setName(personObject.getString("name"));
//			person.setId(personObject.getString("id"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return person;
//	}
    public static List<Map<String, Object>> listKeyMaps(String key, String jsonString) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                Map<String, Object> map = new HashMap<String, Object>();
                Iterator<String> iterator = jsonObject2.keys();
                while (iterator.hasNext()) {
                    String json_key = iterator.next();
                    Object json_values = jsonObject2.get(json_key);
                    if (json_values == null) {
                        json_values = "";
                    }
                    map.put(json_key, json_values);
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();    // TODO: handle exception
        }
        return list;

    }
}
