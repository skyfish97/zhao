package soft.zzti.edu.cn.ruxuebaodian.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import soft.zzti.edu.cn.ruxuebaodian.R;

import java.io.IOException;

/**
 * Created by sky on 2015/10/20.
 */
public class PhoneActivity extends Activity {
    private static String url = "http://192.168.93.1:8080/RuXueBaoDian/zhuce.action?";
    private final String url_constant = "http://192.168.93.1:8080/RuXueBaoDian/zhuce.action?";
    LinearLayout linearLayout;
    EditText et1;
    EditText et2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        intentview();
    }

    public void intentview() {
        et1 = (EditText) findViewById(R.id.zhuce_phonenum);
        et2 = (EditText) findViewById(R.id.zhuce_et_yanzhengma);
    }

    public void next(View v) {


        String zhuce_phone = et1.getText().toString();
        String zhuce_yanzhengma = et2.getText().toString();
        if (zhuce_phone.equals("") || zhuce_yanzhengma.equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "手机号和验证码不能为空", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            loginRemoteService(zhuce_phone, zhuce_yanzhengma);
        }


    }

    public void loginRemoteService(String zhuce_phone, String zhuce_yanzhengma) {
        String result = null;
        try {
            Log.d("1111111111111111", "33333333333333333");
            HttpClient httpclient = new DefaultHttpClient();

            url = url_constant + "zhuce_phone=" + zhuce_phone + "&zhuce_yanzhengma=" + zhuce_yanzhengma;
            Log.d("URL", url);
            HttpGet request = new HttpGet(url);
            request.addHeader("Accept", "text/json");
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity, "UTF-8");
            if (json != null) {
                JSONObject jsonObject = new JSONObject(json);
                result = jsonObject.get("message").toString();
                if (result.equals("注册成功！")) {
                    Log.d("zhao", "zhaohzoahzoaohzhoa");
                    Toast.makeText(getApplicationContext(), "验证成功",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplication(), register.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", zhuce_phone);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                if (result.equals("注册失败！")) {
                    Log.d("zhao", "zhaohzoahzoaohzhoa");
                    Toast.makeText(getApplicationContext(), "验证失败--请检查验证码是否正确",
                            Toast.LENGTH_SHORT).show();
                }
            }
            if (result == null) {
                json = "注册失败请重新注册";
                Toast.makeText(getApplicationContext(), "注册失败",
                        Toast.LENGTH_SHORT).show();
            }

//            AlertDialog.Builder builder = new AlertDialog.Builder(PhoneActivity.this);
//            builder.setTitle("注册ʾ")
//                    .setMessage(result)
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    }).create().show();

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void finish(View v) {
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout_exit);

        final Animation animation2 = AnimationUtils.loadAnimation(this,
                R.anim.exit);
        linearLayout.startAnimation(animation2);
        finish();
    }
}
