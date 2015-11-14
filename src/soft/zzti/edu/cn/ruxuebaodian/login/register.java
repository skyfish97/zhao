package soft.zzti.edu.cn.ruxuebaodian.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
public class register extends Activity {
    private static String url = "http://192.168.93.1:8080/RuXueBaoDian/zhuce_last.action?";
    private final String url_constant = "http://192.168.93.1:8080/RuXueBaoDian/zhuce_last.action?";
    EditText et1;
    EditText et2;
    EditText et3;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Bundle bundle = this.getIntent().getExtras();
        phone = bundle.getString("phone");
        inintview();
    }

    public void inintview() {
        et1 = (EditText) findViewById(R.id.et_nicheng);
        et2 = (EditText) findViewById(R.id.et_password);
        et3 = (EditText) findViewById(R.id.et_password_again);
    }

    public void zhuce(View v) {


        String nicheng = et1.getText().toString();
        String password = et2.getText().toString();
        String again_password = et3.getText().toString();
        loginRemoteService(phone, nicheng, password);
    }

    public void loginRemoteService(String phone, String nicheng, String password) {
        String result = null;
        try {
            Log.d("1111111111111111", "33333333333333333");
            HttpClient httpclient = new DefaultHttpClient();

            url = url_constant + "phone=" + phone + "&nicheng=" + nicheng + "&password=" + password;
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
//                    Toast.makeText(getApplicationContext(), "注册成功",
//                            Toast.LENGTH_SHORT).show();
                }
            }
            if (result == null) {
                json = "注册失败请重新注册";
                Toast.makeText(getApplicationContext(), "注册失败",
                        Toast.LENGTH_SHORT).show();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
            builder.setTitle("注册")
                    .setMessage(result)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();

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
        finish();
    }
}
