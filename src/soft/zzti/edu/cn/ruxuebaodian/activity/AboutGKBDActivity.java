package soft.zzti.edu.cn.ruxuebaodian.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import soft.zzti.edu.cn.ruxuebaodian.R;

/**
 * Created by zhaoxiangyu on 2015/11/9.
 */
public class AboutGKBDActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_gkbd);
    }
    public void about_back(View v){
        this.finish();
        overridePendingTransition(R.anim.move_right_out_activity, 0);
    }
}
