package soft.zzti.edu.cn.ruxuebaodian.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.adapter.ZhuanyepaimingAdapter;
import soft.zzti.edu.cn.ruxuebaodian.autolistview.AutoListView;
import soft.zzti.edu.cn.ruxuebaodian.entity.Zhuanyepaiming;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoxiangyu on 2015/11/13.
 */
public class ZhuanyepaimingActivity extends Activity implements AutoListView.OnRefreshListener, AutoListView.OnLoadListener {

    private AutoListView lstv;
    private ZhuanyepaimingAdapter adapter;
    ArrayList<Zhuanyepaiming> list = new ArrayList<Zhuanyepaiming>();
    ArrayList<String> a = new ArrayList<String>();
    ArrayList<String> b = new ArrayList<String>();
    ArrayList<String> p = new ArrayList<String>();
    ArrayList<String> r = new ArrayList<String>();
    ArrayList<String> n = new ArrayList<String>();
    ArrayList<String> c = new ArrayList<String>();

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            List<Zhuanyepaiming> result = (ArrayList<Zhuanyepaiming>) msg.obj;
            List<Zhuanyepaiming> result1;
            int j = 0;
            switch (msg.what) {
                case AutoListView.REFRESH:
                    lstv.onRefreshComplete();
                    list.clear();
                    list.addAll(result);
                    break;

                case AutoListView.LOAD:
                    lstv.onLoadComplete();
                    list.addAll(result);

                    break;
            }
            lstv.setResultSize(result.size());
            adapter.notifyDataSetChanged();
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanyepaiming_list);


        lstv = (AutoListView) findViewById(R.id.zhuanyepaiming_pull_refresh_list);
        adapter = new ZhuanyepaimingAdapter(this, p, r, n, c);
        lstv.setAdapter(adapter);


        lstv.setOnRefreshListener(this);
        lstv.setOnLoadListener(this);
//        lstv.setOnItemClickListener(this);
        initData();
    }

    private void initData() {
        loadData(AutoListView.REFRESH);
    }

    private void loadData(final int what) {
        // 这里模拟从服务器获取数据
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = handler.obtainMessage();
                msg.what = what;
                msg.obj = getData();
                handler.sendMessage(msg);

            }

        }).start();
    }

    public void onRefresh() {
        loadData(AutoListView.REFRESH);
    }

    public void onLoad() {
        loadData(AutoListView.LOAD);
    }

    // 测试数据
    public ArrayList<Zhuanyepaiming> getData() {
        Document con = null;
        ArrayList<Zhuanyepaiming> result1 = new ArrayList<Zhuanyepaiming>();
        int count = 1;
        Zhuanyepaiming zhuanyepaiming = new Zhuanyepaiming();
        Zhuanyepaiming zhuanyepaiming1 = new Zhuanyepaiming();

        try {
            con = Jsoup.connect("http://gkcx.eol.cn/sphtm/click_ben_zhuan_top100.htm").get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Elements util1 = con.select("div[class=gkcx_main]");
        Document doc = Jsoup.parse(String.valueOf(util1));
        Elements ele = doc.select("tr");

        for (int i = 1; i < ele.size(); i++) {

            Elements e = ele.get(i).select("td");
//    	 System.out.println(e.text());
            for (int j = 0; j < e.size(); j++) {
                if (j != 3 && j != 8) {
                    if (j == 1 || j == 2 | j == 6 || j == 7) {
//                        System.out.println(e.get(j).select("a").text().substring(1, e.get(j).select("a").text().length()));
//
                        for (int w = 0; w < e.get(j).select("a").size(); w++) {
                            System.out.println(e.get(j).select("a").get(w).text().substring(1, e.get(j).select("a").text().length()));
                            b.add(e.get(j).select("a").get(w).text().substring(1, e.get(j).select("a").text().length()));
                        }
                    } else {
                        System.out.println(e.get(j).text());
                        a.add(e.get(j).text());
                    }
                }
            }


        }
        for (int i = 0; i < a.size(); i++) {
            if (i == 0 || i % 2 == 0) {
//			System.out.println(i);
                p.add(a.get(i));
                n.add(b.get(i));
            } else {
                r.add(a.get(i));
                c.add(b.get(i));
            }
        }
        return result1;
    }

    public void zhuanyepaiming_back(View view) {
        finish();
        overridePendingTransition(R.anim.move_right_out_activity, 0);
    }
}
