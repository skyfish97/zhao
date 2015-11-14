package soft.zzti.edu.cn.ruxuebaodian.login;

import java.io.IOException;

import android.content.Intent;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import soft.zzti.edu.cn.ruxuebaodian.R;

public class LoginMyActivity extends Activity {
    private static String url = "http://192.168.93.1:8080/RuXueBaoDian/login.action?";
    private final String url_constant = "http://192.168.93.1:8080/RuXueBaoDian/login.action?";
    private EditText txphone;
    private EditText txPassword;
    LinearLayout layout;
    Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectDiskReads()
//                .detectDiskWrites()
//                .detectNetwork()
//                .penaltyLog()
//                .build());
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                .detectLeakedSqlLiteObjects()
//
//                .penaltyLog()
//                .penaltyDeath()
//                .build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//        inintview();

    }

//    public void inintview() {
//        txphone = (EditText) findViewById(R.id.login_phone);
//        txPassword = (EditText) findViewById(R.id.login_password);
//    }

//    public void login(View v) {
//        Log.d("1111111111111111", "111111111111111");
//
//        String phone = txphone.getText().toString();
//        Log.v("phone = ", phone);
//        String password = txPassword.getText().toString();
//        Log.v("passwd = ", password);
//        if (phone.equals("") || password.equals("")) {
//            Toast.makeText(getApplicationContext(), "请填写信息",
//                    Toast.LENGTH_SHORT).show();
//        } else {
//            Log.d("1111111111111111", "222222222222222222");
//            loginRemoteService(phone, password);
//        }
//    }


//    public void loginRemoteService(String phone, String password) {
//        String result = null;
//        try {
//            Log.d("1111111111111111", "33333333333333333");
////创建一个HttpClient对象
//            HttpClient httpclient = new DefaultHttpClient();
//
//            url = url_constant + "phone=" + phone + "&password=" + password;
//            Log.d("远程URL", url);
//            Log.d("1111111111111111", "44444444444444444");
//            //创建HttpGet对象
//            HttpGet request = new HttpGet(url);
//            Log.d("1111111111111111", "8888888888888888888888");
//            Log.d("1111111111111111", "00000000000000000000000");
//            request.addHeader("Accept", "text/json");
//            Log.d("1111111111111111", "99999999999999999");
//            //获取响应的结果
//            HttpResponse response = httpclient.execute(request);
//            Log.d("1111111111111111", "5555555555555555555");
//            //获取HttpEntity
//            HttpEntity entity = response.getEntity();
//            //获取响应的结果信息
//            String json = EntityUtils.toString(entity, "UTF-8");
//            Log.d("1111111111111111", "6666666666666666666666");
//            //JSON的解析过程
//            if (json != null) {
//                JSONObject jsonObject = new JSONObject(json);
//                result = jsonObject.get("message").toString();
//                Log.d("1111111111111111", "111111111111111");
//                if (result == "登录成功！") {
//                    Toast.makeText(getApplicationContext(), "登陆成功",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//            if (result == null) {
//                json = "登录失败请重新登录";
//            }
//            //创建提示框提醒是否登录成功
//            Builder builder = new Builder(LoginMyActivity.this);
//            builder.setTitle("提示")
//                    .setMessage(result)
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    }).create().show();
//
//        } catch (ClientProtocolException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (JSONException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

//    public void zhucezhanghao(View v) {
//        Log.d("zhao", "111111111111111");
//        final Animation animation1 = AnimationUtils.loadAnimation(this,
//                R.anim.enter);
//
//        layout = (LinearLayout) findViewById(R.id.linearlayout);
//        Intent intent = new Intent(this, PhoneActivity.class);
//        layout.startAnimation(animation1);
//        this.startActivity(intent);
//    }


}
