package soft.zzti.edu.cn.ruxuebaodian.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import soft.zzti.edu.cn.ruxuebaodian.R;

import java.io.IOException;

/**
 * Created by zhaoxiangyu on 2015/11/12.
 */
public class GaokaozixunitemActivity extends Activity {
    TextView textView;
    String href;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaokaozixun_news_detail);
        textView = (TextView) findViewById(R.id.gaokaozixun_news);
        Bundle bundle = getIntent().getExtras();
        href = bundle.getString("href");
//        System.out.println("================>"+href);
        getdata();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textView.setText(text);
    }
    public void getdata() {

        new Thread(new Runnable() {

            @Override
            public void run() {
                Document con = null;
                try {
                    con = Jsoup.connect(href).header("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0").get();
                    System.out.println("================>" + href);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Elements util1 = con.select("div[class=content]");
                Document doc = Jsoup.parse(String.valueOf(util1));
                Elements ele = doc.select("p");
                text = ele.text();
                System.out.println("================>" + con);
//                for(int i=0;i<ele.size()-3;i++){
//                    text = text+ele.get(i).text();
////            System.out.println(ele.get(i).text());
//                }

            }
        }).start();

    }

    public void gaokaozixun_news_back(View v){
        finish();
        overridePendingTransition(R.anim.move_right_out_activity,0);
    }
}
