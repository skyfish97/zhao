package soft.zzti.edu.cn.ruxuebaodian.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import soft.zzti.edu.cn.ruxuebaodian.R;

/**
 * Created by zhaoxiangyu on 2015/11/13.
 */
public class DaXueLuQuXianSelectActivity extends Activity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daxueluquxian_select);
        editText1 = (EditText) findViewById(R.id.et_syd);
        editText2 = (EditText) findViewById(R.id.et_wlk);
        editText3 = (EditText) findViewById(R.id.et_yxmc);
    }
    public void sousuo(View view){
        Intent intent = new Intent(this,DaXueLuQuXiantActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("shengyuandi",editText1.getText().toString());
        bundle.putString("wenlike",editText2.getText().toString());
        bundle.putString("yuanxiaomingcheng",editText3.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.move_right_in_activity,0);
    }

    public void dx_back(View view){
        finish();
        overridePendingTransition(R.anim.move_right_out_activity,0);
    }
}
