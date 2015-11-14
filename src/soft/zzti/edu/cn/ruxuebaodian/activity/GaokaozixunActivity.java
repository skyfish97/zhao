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
import soft.zzti.edu.cn.ruxuebaodian.adapter.GaokaozixunAdapter;
import soft.zzti.edu.cn.ruxuebaodian.autolistview.AutoListView;
import soft.zzti.edu.cn.ruxuebaodian.entity.Gaokaozixun;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoxiangyu on 2015/11/12.
 */
public class GaokaozixunActivity extends Activity implements AutoListView.OnRefreshListener, AutoListView.OnLoadListener,AdapterView.OnItemClickListener {

    private AutoListView lstv;
    private GaokaozixunAdapter adapter;
    ArrayList<Gaokaozixun> list = new ArrayList<Gaokaozixun>();
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            List<Gaokaozixun> result = (ArrayList<Gaokaozixun>) msg.obj;
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
        setContentView(R.layout.activity_gaokaozixun_list);
        lstv = (AutoListView) findViewById(R.id.gaokaozixun_pull_refresh_list);
        lstv.setOnRefreshListener(this);
//        lstv.setOnLoadListener(this);
        lstv.setOnItemClickListener(this);
        initData();
        adapter = new GaokaozixunAdapter(this, list);
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
    public List<Gaokaozixun> getData() {
        List<Gaokaozixun> zixunlist = new ArrayList<Gaokaozixun>();
        Document con = null;
        try {
            con = Jsoup.connect("http://roll.edu.sina.com.cn/all/gk/kaoshi/index_1.shtml").get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Elements util1 = con.select("ul[class=list_009]");
        Document doc = Jsoup.parse(String.valueOf(util1));
        Elements ele = doc.select("li");
        for(int i=0;i<ele.size();i++){
//            System.out.println(ele.get(i).select("a").text());
            Gaokaozixun gk = new Gaokaozixun();
            gk.setTitle(ele.get(i).select("a").text());
            gk.setTime(ele.get(i).select("span").text());
            gk.setHref(ele.get(i).select("a").attr("href"));
            zixunlist.add(gk);
        }



        return zixunlist;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Log.d("item","111111111");
        Gaokaozixun sc = list.get(i-1);

        Intent intent_col = new Intent(getApplicationContext(),GaokaozixunitemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("href",sc.getHref());
        intent_col.putExtras(bundle);
        startActivity(intent_col);
        overridePendingTransition(R.anim.move_right_in_activity, 0);
    }
public void gaokaozixun_back(View view){
    finish();
    overridePendingTransition(R.anim.move_right_out_activity,0);
}


}
