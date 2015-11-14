package soft.zzti.edu.cn.ruxuebaodian.tab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.activity.SchoolDetilActivity;
import soft.zzti.edu.cn.ruxuebaodian.adapter.KaiSheYuanXiao_Adapter;
import soft.zzti.edu.cn.ruxuebaodian.autolistview.AutoListView;
import soft.zzti.edu.cn.ruxuebaodian.entity.KaiSheYuanXiao;
import soft.zzti.edu.cn.ruxuebaodian.http.HttpUtils;
import soft.zzti.edu.cn.ruxuebaodian.jsontools.JsonTools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoxiangyu on 2015/11/9.
 */
public class kaisheyuanxiaoFragment extends Fragment implements AutoListView.OnRefreshListener, AutoListView.OnLoadListener,AdapterView.OnItemClickListener{
    View view;
    ListView listView;
    KaiSheYuanXiao_Adapter adapter;
    String zhuanyemingzi;
    int j=1;
    Toast toast;
    ProgressDialog MyDialog;
    private AutoListView lstv;
    List<KaiSheYuanXiao> list = new ArrayList<KaiSheYuanXiao>();
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            List<KaiSheYuanXiao> result = (ArrayList<KaiSheYuanXiao>) msg.obj;
            int j = 0;
            switch (msg.what) {
                case AutoListView.REFRESH:
                    lstv.onRefreshComplete();
                    list.clear();
                    list.addAll(result);
//                    dialog.cancel();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.major_content_page3, container, false);
//        if(MyDialog.isShowing()){
//            MyDialog.cancel();
//        }
//        listView = (ListView) view.findViewById(R.id.kaisheyuanxiao_pullRefreshlistview);
        Bundle intent = getActivity().getIntent().getExtras();
         zhuanyemingzi = intent.getString("specialname");
        Log.d("hahawoshi",zhuanyemingzi);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                getData();
//            }
//        }).start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        adapter = new KaiSheYuanXiao_Adapter(getActivity(), result1);
//        listView.setAdapter(adapter);


        lstv = (AutoListView)view.findViewById(R.id.kaisheyuanxiao_pullRefreshlistview);
        adapter = new KaiSheYuanXiao_Adapter(getActivity(), list);
        lstv.setAdapter(adapter);
//
        lstv.setOnRefreshListener(this);
        lstv.setOnLoadListener(this);
        lstv.setOnItemClickListener(this);
        initData();

        return view;
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
                    Thread.sleep(1500);
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
    public List<KaiSheYuanXiao> getData() {

//
        List<KaiSheYuanXiao> result1 = new ArrayList<KaiSheYuanXiao>();

        String jsonString = HttpUtils.request_kaisheyuanxiao(zhuanyemingzi, j++);

        List<Map<String, Object>> list = JsonTools.listKeyMaps("school", jsonString);

        Log.d("zhao", "000000000000000");
        ArrayList<Map> arrylist = new ArrayList<Map>();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map map = (Map) it.next();
            KaiSheYuanXiao zy = new KaiSheYuanXiao();
            zy.setSchoolname(map.get("schoolname").toString());
            zy.setSpecialtyname(map.get("specialtyname").toString());
            zy.setSchoolid(map.get("schoolid").toString());
            result1.add(zy);
        }

        return result1;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("item", "111111111");
//        Toast.makeText(getActivity(), "正在加载......", Toast.LENGTH_LONG).show();
//         MyDialog = ProgressDialog.show(getActivity(), " ", " Loading. Please wait ... ", true);

        KaiSheYuanXiao sc=list.get(i-1);
        Intent intent=new Intent(getActivity(),SchoolDetilActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("schoolid", sc.getSchoolid());
        bundle.putString("schoolname", sc.getSchoolname());
        intent.putExtras(bundle);
        startActivity(intent);
       getActivity().overridePendingTransition(R.anim.move_right_in_activity, 0);

    }


//    public List<KaiSheYuanXiao> getData() {
//
////
////        List<KaiSheYuanXiao> result1 = new ArrayList<KaiSheYuanXiao>();
//
//        String jsonString = HttpUtils.request_kaisheyuanxiao(zhuanyemingzi,j++);
//
//        List<Map<String, Object>> list = JsonTools.listKeyMaps("school", jsonString);
//
//        Log.d("zhao", "000000000000000");
//        ArrayList<Map> arrylist = new ArrayList<Map>();
//        for (Iterator it = list.iterator(); it.hasNext(); ) {
//            Map map = (Map) it.next();
//            KaiSheYuanXiao zy = new KaiSheYuanXiao();
//            zy.setSchoolname(map.get("schoolname").toString());
//            zy.setSpecialtyname(map.get("specialtyname").toString());
//            result1.add(zy);
//        }
//
//        return result1;
//    }
}
