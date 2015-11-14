package soft.zzti.edu.cn.ruxuebaodian.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import soft.zzti.edu.cn.ruxuebaodian.R;

/**
 * Created by zhaoxiangyu on 2015/11/11.
 */
public class GaokaobaokeitemActivity extends Activity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaokaobaoke_news_detail);
        textView = (TextView) findViewById(R.id.gaokaobaoke_news);
        Bundle bundle = getIntent().getExtras();
      String htm =  bundle.getString("htm");
        textView.setText(htm);
    }

    public void gaokaobaoke_news_back(View v){
        this.finish();
        overridePendingTransition(R.anim.move_right_out_activity,0);
    }
}
