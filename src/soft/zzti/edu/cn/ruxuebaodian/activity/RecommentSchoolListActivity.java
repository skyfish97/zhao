package soft.zzti.edu.cn.ruxuebaodian.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.adapter.ListViewAdapter;
import soft.zzti.edu.cn.ruxuebaodian.autolistview.AutoListView;
import soft.zzti.edu.cn.ruxuebaodian.entity.School;
import soft.zzti.edu.cn.ruxuebaodian.entity.SchoolTotal;
import soft.zzti.edu.cn.ruxuebaodian.http.HttpUtils;
import soft.zzti.edu.cn.ruxuebaodian.jsontools.JsonTools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by zhaoxiangyu on 2015/10/27.
 */
public class RecommentSchoolListActivity extends Activity implements AutoListView.OnRefreshListener, AutoListView.OnLoadListener,AdapterView.OnItemClickListener {
    String shengyuandi;
    String wenlike;
    String fenshu;
    Dialog dialog;
//    ArrayList<School> schoolobj = new ArrayList<School>();

    TextView tv_totalRecord;
    SchoolTotal total;

    private AutoListView lstv;
    private ListViewAdapter adapter;
    ArrayList<School> list = new ArrayList<School>();
    int i = 1;


    public RecommentSchoolListActivity() {

    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            List<School> result = (ArrayList<School>) msg.obj;
            List<School> result1;
            int j = 0;
            switch (msg.what) {
                case AutoListView.REFRESH:
                    lstv.onRefreshComplete();
                    list.clear();
                    list.addAll(result);
                    tv_totalRecord.setText(total.getNum());
                    dialog.cancel();
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
        setContentView(R.layout.activity_recommended_list);

        tv_totalRecord = (TextView) findViewById(R.id.tv_sc_count);

        dialog = createLoadingDialog(this, "正在加载");
        dialog.show();

        Bundle bundle = this.getIntent().getExtras();
        shengyuandi = bundle.get("shengyuandi").toString();
        wenlike = bundle.get("wenlike").toString();
        fenshu = bundle.get("fenshu").toString();
        Log.d("zhao", shengyuandi + "---" + wenlike + "-----" + fenshu);


        lstv = (AutoListView) findViewById(R.id.pull_refresh_list);
        adapter = new ListViewAdapter(this, list);
        lstv.setAdapter(adapter);
        lstv.setOnRefreshListener(this);
        lstv.setOnLoadListener(this);
        lstv.setOnItemClickListener(this);
        initData();



//        new Thread() {
//            public void run() {
//                //			http://data.api.gkcx.eol.cn/soudaxue/queryschoolgufen.html?messtype=json&page=1&size=10&fsxxxS=%E6%B2%B3%E5%8D%97&examineeType=%E7%90%86%E7%A7%91&mark=550
//                //			String path = "http://data.api.gkcx.eol.cn/soudaxue/queryschoolgufen.html?messtype=json&page=1&size=10&fsxxxS=河南&examineeType=理科&mark=550";
////                String jsonString1 = HttpUtils.request(shengyuandi, wenlike, fenshu, 1);
////                SchoolTotal list1 = JsonTools.getTotal("totalRecord", jsonString1);
//////                int totalRecord =Integer.valueOf(list1.getNum());
////                total = new SchoolTotal();
////                total.setNum(list1.getNum());
//////                tv_totalRecord.setText(totalRecord);
////
//
////                System.out.print("hhhhhhhhhhhhh===>" + list1.getNum());
//                for (int i = 1; i < 30; i++) {
//                    String jsonString = HttpUtils.request(shengyuandi, wenlike, fenshu, i);
//                    List<Map<String, Object>> list = JsonTools.listKeyMaps("school", jsonString);
////                    Log.d("zhaoxiangyu",list.toString());
////                    if (list.toString().equals("[]")) {
////                        break;
////                    }
//                    Log.d("zhao","000000000000000");
//                    ArrayList<Map> arrylist = new ArrayList<Map>();
//                    for (Iterator it = list.iterator(); it.hasNext(); ) {
//                        Map map = (Map) it.next();
//                        School school = new School();
//                        school.setSchoolname(map.get("schoolname").toString());
//                        school.setSchoolprovince(map.get("schoolprovince").toString());
//                        school.setPercentage(map.get("percentage").toString());
//                        school.setVar2014(map.get("var2014").toString());
//                        school.setBatch(map.get("batch").toString());
//                        System.out.println("11111111111111111111111===>" + map.get("schoolname").toString());
//                        schoolobj.add(school);
//
//                    }
//                }
////                Log.d("11111111111111111", list.toString());
//            }
//        }.start();
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                mProgressDialog.cancel();
////                tv_totalRecord.setText(total.getNum())    ;
//                ListView listView_tongzhi = (ListView) findViewById(R.id.recommend_listview);
//                RecommentListAdapter tongzhiAdapter = new RecommentListAdapter(getApplication(), schoolobj);
//                listView_tongzhi.setAdapter(tongzhiAdapter);
//                dialog.cancel();
//            }
//        }, 3500);


    }


    public static Dialog createLoadingDialog(Context context, String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.network_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.layout_loadingnet);// 加载布局
        // main.xml中的ImageView
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.imageView_login_progress_circle);
        TextView tipTextView = (TextView) v.findViewById(R.id.textView_progress_info);// 提示文字
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                context, R.anim.my_progerss_cricle);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog

        loadingDialog.setCancelable(false);// 不可以用“返回键”取消
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
        return loadingDialog;

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
    public List<School> getData() {

//        String jsonString1 = HttpUtils.request(shengyuandi, wenlike, fenshu, 1);


        List<School> result1 = new ArrayList<School>();
//                        for (int i = 1; i < 30; i++) {
        String jsonString = HttpUtils.request(shengyuandi, wenlike, fenshu, i++);
        SchoolTotal list1 = JsonTools.getTotal("totalRecord", jsonString);
        total = new SchoolTotal();
        total.setNum(list1.getNum());
        List<Map<String, Object>> list = JsonTools.listKeyMaps("school", jsonString);
//                    Log.d("zhaoxiangyu",list.toString());
//                    if (list.toString().equals("[]")) {
//                        break;
//                    }
        Log.d("zhao", "000000000000000");
        ArrayList<Map> arrylist = new ArrayList<Map>();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map map = (Map) it.next();
            School school = new School();
            school.setSchoolid(map.get("schoolid").toString());
            school.setSchoolname(map.get("schoolname").toString());
            school.setSchoolprovince(map.get("schoolprovince").toString());
            school.setF211(map.get("f211").toString());
            school.setF985(map.get("f985").toString());
            school.setBatch(map.get("batch").toString());
            school.setVar2014(map.get("var2014").toString());
            school.setMax2014(map.get("max2014").toString());
            school.setVar2013(map.get("var2013").toString());
            school.setMax2013(map.get("max2013").toString());
            school.setVar2012(map.get("var2012").toString());
            school.setMax2012(map.get("max2012").toString());
            school.setUrl(map.get("url").toString());
            school.setRecommended(map.get("recommended").toString());
            school.setShengkong2014(map.get("shengkong2014").toString());
            school.setShengkong2013(map.get("shengkong2013").toString());
            school.setShengkong2012(map.get("shengkong2012").toString());
            school.setPercentage(map.get("percentage").toString());

            System.out.println("11111111111111111111111===>" + map.get("schoolname").toString());
            result1.add(school);

        }
//                }


        return result1;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("item","111111111");
        School sc=list.get(i-1);
        Intent intent_col=new Intent(getApplicationContext(),ReconmentItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("schoolname",sc.getSchoolname());
        bundle.putString("var2014",sc.getVar2014());
        bundle.putString("max2014",sc.getMax2014());
        bundle.putString("var2013",sc.getVar2013());
        bundle.putString("max2013",sc.getMax2013());
        bundle.putString("var2012",sc.getVar2012());
        bundle.putString("max2012",sc.getMax2012());
        bundle.putString("var2011",sc.getVar2011());
        bundle.putString("max2011",sc.getMax2011());
        bundle.putString("recommended",sc.getRecommended());
        bundle.putString("shengkong2014",sc.getShengkong2014());
        bundle.putString("shengkong2013",sc.getShengkong2013());
        bundle.putString("shengkong2012",sc.getShengkong2012());
        bundle.putString("percentage",sc.getPercentage());

        intent_col.putExtras(bundle);
        startActivity(intent_col);
        overridePendingTransition(R.anim.move_right_in_activity, 0);
    }


    public void back(View v) {
        finish();
        overridePendingTransition(R.anim.exit, 0);

    }




}
