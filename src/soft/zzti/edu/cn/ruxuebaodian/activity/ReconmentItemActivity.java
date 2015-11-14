package soft.zzti.edu.cn.ruxuebaodian.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import soft.zzti.edu.cn.ruxuebaodian.R;

/**
 * Created by zhaoxiangyu on 2015/10/31.
 */
public class ReconmentItemActivity extends Activity {
    TextView tv_title, tv_2014_max,tv_2014_var, tv_2014_sk,
            tv_2013_max, tv_2013_var, tv_2013_sk,
            tv_2012_max, tv_2012_var, tv_2012_sk,
            tv_percent, tv_recommended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommded_schooldetail);
        inint();

        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.get("schoolname").toString();
        String v2014 = bundle.get("var2014").toString();
        String m2014 = bundle.get("max2014").toString();
        String v2013 = bundle.get("var2013").toString();
        String m2013 = bundle.get("max2013").toString();
        String v2012 = bundle.get("var2012").toString();
        String m2012 = bundle.get("max2012").toString();
        String rc = bundle.get("recommended").toString();
        String sk2014 = bundle.get("shengkong2014").toString();
        String sk2013 = bundle.get("shengkong2013").toString();
        String sk2012 = bundle.get("shengkong2012").toString();
        String per = bundle.get("percentage").toString();


        tv_title.setText(name);
        tv_2014_max.setText(m2014);
        tv_2014_var.setText(v2014);
        tv_2014_sk.setText(sk2014);

        tv_2013_max.setText(m2013);
        tv_2013_var.setText(v2013);
        tv_2013_sk.setText(sk2013);

        tv_2012_max.setText(m2012);
        tv_2012_var.setText(v2012);
        tv_2012_sk.setText(sk2012);

        tv_percent.setText(per);
        tv_recommended.setText(rc);


    }

    public void inint() {
        tv_title = (TextView) findViewById(R.id.tv_recommend_listview_item_title);
        tv_2014_max = (TextView) findViewById(R.id.tv_2014_max);
        tv_2014_var = (TextView) findViewById(R.id.tv_2014_var);
        tv_2014_sk = (TextView) findViewById(R.id.tv_2014_sk);
        tv_2013_max = (TextView) findViewById(R.id.tv_2013_max);
        tv_2013_var = (TextView) findViewById(R.id.tv_2013_var);
        tv_2013_sk = (TextView) findViewById(R.id.tv_2013_sk);
        tv_2012_max = (TextView) findViewById(R.id.tv_2012_max);
        tv_2012_var = (TextView) findViewById(R.id.tv_2012_var);
        tv_2012_sk = (TextView) findViewById(R.id.tv_2012_sk);
        tv_percent = (TextView) findViewById(R.id.tv_percent);
        tv_recommended = (TextView) findViewById(R.id.tv_recommended);

    }


    public void recommet_school_listvew_title(View v) {
        finish();
        overridePendingTransition(R.anim.move_right_out_activity, 0);
    }
}
