package soft.zzti.edu.cn.ruxuebaodian.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.adapter.ZhuanYeLuQuXianAdapter;
import soft.zzti.edu.cn.ruxuebaodian.autolistview.AutoListView;
import soft.zzti.edu.cn.ruxuebaodian.entity.ZhuanYeLuQu;
import soft.zzti.edu.cn.ruxuebaodian.http.HttpUtils;
import soft.zzti.edu.cn.ruxuebaodian.jsontools.JsonTools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoxiangyu on 2015/11/13.
 */
public class ZhuanYeLuQuXiantActivity extends Activity implements AutoListView.OnRefreshListener, AutoListView.OnLoadListener {

    private AutoListView lstv;
    private ZhuanYeLuQuXianAdapter adapter;
    ArrayList<ZhuanYeLuQu> list = new ArrayList<ZhuanYeLuQu>();
    int i = 1;
    String syd;
    String wlk;
    String xx;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            List<ZhuanYeLuQu> result = (ArrayList<ZhuanYeLuQu>) msg.obj;
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
        setContentView(R.layout.activity_zhuanyeluquxian_list);
        lstv = (AutoListView) findViewById(R.id.pull_zhuanyeluquxian_refresh_list);
        Bundle bundle = getIntent().getExtras();
        syd = bundle.getString("shengyuandi");
        wlk = bundle.getString("wenlike");
        xx = bundle.getString("yuanxiaomingcheng");
        lstv.setOnRefreshListener(this);
        lstv.setOnLoadListener(this);
//        lstv.setOnItemClickListener(this);
        initData();
        adapter = new ZhuanYeLuQuXianAdapter(this, list);
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
    public List<ZhuanYeLuQu> getData() {


        List<ZhuanYeLuQu> result1 = new ArrayList<ZhuanYeLuQu>();
        String jsonString = HttpUtils.request_zylqx(syd, wlk, xx, i++);

//        System.out.println("11111111111111111111111===>" + syd+wlk+xx);
        List<Map<String, Object>> list1 = JsonTools.listKeyMaps("school", jsonString);
                System.out.println("11111111111111111111111===>" + list1);
        for (Iterator it = list1.iterator(); it.hasNext(); ) {
            Map map = (Map) it.next();
            ZhuanYeLuQu daXueLuQuXian = new ZhuanYeLuQu();
            daXueLuQuXian.setSchoolname(map.get("schoolname").toString());
            daXueLuQuXian.setSpecialtyname(map.get("specialtyname").toString());
            daXueLuQuXian.setLocalprovince(map.get("localprovince").toString());
            daXueLuQuXian.setStudenttype(map.get("studenttype").toString());
            daXueLuQuXian.setYear(map.get("year").toString());
            daXueLuQuXian.setBatch(map.get("batch").toString());
            daXueLuQuXian.setVar_score(map.get("var_score").toString());
//            System.out.println("11111111111111111111111===>" + map.get("schoolname").toString());
            result1.add(daXueLuQuXian);

        }

        return result1;
    }

    public void zylq_list_back(View view) {
        finish();
        overridePendingTransition(R.anim.move_right_out_activity, 0);
    }
}
