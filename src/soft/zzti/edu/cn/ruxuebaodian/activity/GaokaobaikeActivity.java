package soft.zzti.edu.cn.ruxuebaodian.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.adapter.GaokaobaikeAdapter;
import soft.zzti.edu.cn.ruxuebaodian.autolistview.AutoListView;
import soft.zzti.edu.cn.ruxuebaodian.entity.Gaokaobaike;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoxiangyu on 2015/11/11.
 */
public class GaokaobaikeActivity extends Activity implements AutoListView.OnRefreshListener, AutoListView.OnLoadListener, AdapterView.OnItemClickListener {
    private AutoListView lstv;
    private GaokaobaikeAdapter adapter;
    ArrayList<Gaokaobaike> list = new ArrayList<Gaokaobaike>();
    String htm = "";
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            List<Gaokaobaike> result = (ArrayList<Gaokaobaike>) msg.obj;
            List<Gaokaobaike> result1;
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
        setContentView(R.layout.activity_gaokaobaoke_list);
        lstv = (AutoListView) findViewById(R.id.gaokaobaoke_pull_refresh_list);

        lstv.setOnRefreshListener(this);
//        lstv.setOnLoadListener(this);
        lstv.setOnItemClickListener(this);
        initData();

        adapter = new GaokaobaikeAdapter(this, list);
        lstv.setAdapter(adapter);
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
    public List<Gaokaobaike> getData() {
        List<Gaokaobaike> baikelist = new ArrayList<Gaokaobaike>();

        Document doc = null;

        try {
            doc = Jsoup.connect("http://gaokao.eol.cn/gkbk_11391/").get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Elements e = doc.select("div[class=page_left]");
        Document doc1 = Jsoup.parse(String.valueOf(e));
        Elements e1 = doc1.select("li");
        for (int i = 0; i < e1.size(); i++) {
            String time = e1.get(i).getElementsByTag("span").text();
            String title = e1.get(i).getElementsByTag("a").text();
            String herf = e1.get(i).getElementsByTag("a").attr("href");
            Gaokaobaike gaokaobaike = new Gaokaobaike();
            gaokaobaike.setTime(time);
            gaokaobaike.setTitle(title);
            gaokaobaike.setHref(herf);
            baikelist.add(gaokaobaike);
            Document doc2 = null;
            try {

                doc2 = Jsoup.connect(herf).get();
            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            Elements e3 = doc2.select("div[class=page_wz]");
            Document doc3 = Jsoup.parse(String.valueOf(e3));
            Elements e4 = doc3.select("p");
            htm = e4.text();
            gaokaobaike.setContent(htm);
            System.out.println(htm);
//            System.out.println(herf);
//            System.out.println(a);
        }
        return baikelist;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Log.d("item","111111111");
        Gaokaobaike sc = list.get(i-1);
        final String href = sc.getHref();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Document doc = null;
//                try {
//                    doc = Jsoup.connect(href).get();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                Elements e = doc.select("div[class=page_wz]");
//                Document doc1 = Jsoup.parse(String.valueOf(e));
//                Elements e1 = doc1.select("p");
//                  htm = e1.text();
//            }
//        }).start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Intent intent_col = new Intent(getApplicationContext(),GaokaobaokeitemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("htm",  sc.getContent());
        intent_col.putExtras(bundle);
        startActivity(intent_col);
        overridePendingTransition(R.anim.move_right_in_activity, 0);
    }


    public void gaokaobaoke_back(View view) {
        finish();
        overridePendingTransition(R.anim.move_right_out_activity, 0);
    }

}
