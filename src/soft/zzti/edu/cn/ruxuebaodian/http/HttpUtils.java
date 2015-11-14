package soft.zzti.edu.cn.ruxuebaodian.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    public HttpUtils() {
        // TODO Auto-generated constructor stub
    }

    //	URL	url = new URL("http://data.api.gkcx.eol.cn/soudaxue/queryschoolgufen.html?messtype=json&page=1&size=10&fsxxxS="
//			+ java.net.URLEncoder.encode("河南")+"&examineeType="+java.net.URLEncoder.encode("理科")+"&mark=550");
//	http://data.api.gkcx.eol.cn/soudaxue/queryschoolgufen.html?messtype=json&page=1&size=10&fsxxxS=河南&examineeType=理科&mark=550
    public static String request(String shengyuandi, String wenlike, String fenshu, int i) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
//		httpUrl = httpUrl + "?";
//        java.net.URLEncoder.encode(shengyuandi)

        try {
            URL url = new URL("http://data.api.gkcx.eol.cn/soudaxue/queryschoolgufen.html?messtype=json&page=" + i + "&size=10&fsxxxS=" + java.net.URLEncoder.encode(shengyuandi) + "&examineeType=" + java.net.URLEncoder.encode(wenlike) + "&mark=" + java.net.URLEncoder.encode(fenshu)
            );
//            URL url = new URL("http://data.api.gkcx.eol.cn/soudaxue/queryschoolgufen.html?messtype=json&page=" + i + "&size=10&fsxxxS=%E6%B2%B3%E5%8D%97&examineeType=%E7%90%86%E7%A7%91&mark=550");
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String request_all_zhuanye(int i,String zhuanyemingzi) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
//		httpUrl = httpUrl + "?";
//        java.net.URLEncoder.encode(shengyuandi)
//        http://data.api.gkcx.eol.cn/soudaxue/queryspecialty.html?messtype=json&zycengci=&zytype=&page=1&size=10&keyWord2=%E8%BD%AF%E4%BB%B6%E5%B7%A5%E7%A8%8B
        try {
            URL url = new URL( "http://data.api.gkcx.eol.cn/soudaxue/queryspecialty.html?messtype=json&zycengci=&zytype=&page="+i+"&size=10&keyWord2="+java.net.URLEncoder.encode(zhuanyemingzi));
//                    http://data.api.gkcx.eol.cn/soudaxue/queryspecialty.html?messtype=json&zycengci=&zytype=&page=" +i + "&size=10"+"&keyWord2="+"软件工程"
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
//            data.api.gkcx.eol.cn/soudaxue/queryspecialty.html?messtype=jsonp&zycengci=&zytype=&page=1&size=10&keyWord2=软件工程java.net.URLEncoder.encode(zhuanyemingzi)
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String request_zycount(String specialurl) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
//		httpUrl = httpUrl + "?";
//        java.net.URLEncoder.encode(shengyuandi)

        try {
            URL url = new URL("http://gkcx.eol.cn/schoolhtm"+specialurl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();

            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String request_kaisheyuanxiao(String zhuye_name,int j) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        if (zhuye_name.equals("")) {
            return result;
        } else {

//		httpUrl = httpUrl + "?";
//        java.net.URLEncoder.encode(shengyuandi)
            http:
//data.api.gkcx.eol.cn/soudaxue/querySchoolSpecialty.html?messtype=jsonp&zycengci=&keyWord1=%E5%93%B2%E5%AD%A6
            try {

                URL url = new URL("http://data.api.gkcx.eol.cn/soudaxue/querySchoolSpecialty.html?messtype=json&zycengci=&page="+j+"&size=10&keyWord1=" + java.net.URLEncoder.encode(zhuye_name)
                );
//            URL url = new URL("http://data.api.gkcx.eol.cn/soudaxue/queryschoolgufen.html?messtype=json&page=" + i + "&size=10&fsxxxS=%E6%B2%B3%E5%8D%97&examineeType=%E7%90%86%E7%A7%91&mark=550");
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream is = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String strRead = null;
                while ((strRead = reader.readLine()) != null) {
                    sbf.append(strRead);
                    sbf.append("\r\n");
                }
                reader.close();
                result = sbf.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    public static String request_dxlx(String syd1, String wlk1, String scname, int i) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
//		httpUrl = httpUrl + "?";
//        java.net.URLEncoder.encode(shengyuandi)

        try {
            URL url = new URL("http://data.api.gkcx.eol.cn/soudaxue/queryProvinceScore.html?messtype=json&page="+i+"&size=10&keyWord="+java.net.URLEncoder.encode(scname,"UTF-8")+"&province="+java.net.URLEncoder.encode(syd1,"UTF-8")+"&fstype="+java.net.URLEncoder.encode(wlk1,"UTF-8")
            );
//            URL url = new URL("http://data.api.gkcx.eol.cn/soudaxue/queryschoolgufen.html?messtype=json&page=" + i + "&size=10&fsxxxS=%E6%B2%B3%E5%8D%97&examineeType=%E7%90%86%E7%A7%91&mark=550");
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String request_zylqx(String syd1, String wlk1, String scname, int i) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
//		httpUrl = httpUrl + "?";
//        java.net.URLEncoder.encode(shengyuandi)

        try {
            URL url = new URL("http://data.api.gkcx.eol.cn/soudaxue/querySpecialtyScore.html?messtype=json&page="+i+"&size=10&keyWord="+java.net.URLEncoder.encode(scname,"UTF-8")+"&province="+java.net.URLEncoder.encode(syd1,"UTF-8")+"&fstype="+java.net.URLEncoder.encode(wlk1,"UTF-8")
            );
//            URL url = new URL("http://data.api.gkcx.eol.cn/soudaxue/queryschoolgufen.html?messtype=json&page=" + i + "&size=10&fsxxxS=%E6%B2%B3%E5%8D%97&examineeType=%E7%90%86%E7%A7%91&mark=550");
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
