package soft.zzti.edu.cn.ruxuebaodian.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import soft.zzti.edu.cn.ruxuebaodian.R;

/**
 * Created by zhaoxiangyu on 2015/11/14.
 */
public class ZhuanYeLuQuXianSelectActivity extends Activity {
    EditText editText1;
    EditText editText2;
    EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuanyeluquxian_select);
        editText1 = (EditText) findViewById(R.id.zylu_syd);
        editText2 = (EditText) findViewById(R.id.zylu_wlk);
        editText3 = (EditText) findViewById(R.id.zylu_yxmc);

    }

    public void zy_sousuo(View view) {
        Intent intent = new Intent(this,ZhuanYeLuQuXiantActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("shengyuandi", editText1.getText().toString());
        bundle.putString("wenlike", editText2.getText().toString());
        bundle.putString("yuanxiaomingcheng", editText3.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void  zylq_back(View view) {
        finish();
        overridePendingTransition(R.anim.move_right_out_activity, 0);
    }
}
