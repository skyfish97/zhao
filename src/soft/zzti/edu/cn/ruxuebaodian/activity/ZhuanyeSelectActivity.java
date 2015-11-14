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
import android.widget.*;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.adapter.select_zhuanye_Adapter;
import soft.zzti.edu.cn.ruxuebaodian.autolistview.AutoListView;
import soft.zzti.edu.cn.ruxuebaodian.entity.KaiSheYuanXiao;
import soft.zzti.edu.cn.ruxuebaodian.entity.School;
import soft.zzti.edu.cn.ruxuebaodian.entity.ZhuanYeCount;
import soft.zzti.edu.cn.ruxuebaodian.entity.zhuanye;
import soft.zzti.edu.cn.ruxuebaodian.http.HttpUtils;
import soft.zzti.edu.cn.ruxuebaodian.jsontools.JsonTools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoxiangyu on 2015/11/5.
 */
public class ZhuanyeSelectActivity extends Activity implements AutoListView.OnRefreshListener, AutoListView.OnLoadListener,AdapterView.OnItemClickListener  {
    EditText et_zhuye_select;
    Dialog dialog;
    private AutoListView lstv;
    private select_zhuanye_Adapter adapter;
    ArrayList<zhuanye> list = new ArrayList<zhuanye>();
    ArrayList<KaiSheYuanXiao> list_kaisheyuanxiao = new ArrayList<KaiSheYuanXiao>();
    int i = 0;
    String zhuanyemingzi="";
    EditText et_zhuanyemizi;
    ArrayList<String> yx_list = new ArrayList<String>();
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            List<zhuanye> result = (ArrayList<zhuanye>) msg.obj;
            List<School> result1;
            int j = 0;
            switch (msg.what) {
                case AutoListView.REFRESH:
                    lstv.onRefreshComplete();
                    list.clear();
                    list.addAll(result);
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
        setContentView(R.layout.activity_zhuanye_query);
//        et_zhuye_select = (EditText) findViewById(R.id.et_zhuanye_slect);
        et_zhuanyemizi = (EditText) findViewById(R.id.et_zhuanye_slect);

        dialog = createLoadingDialog(this, "正在加载");
        dialog.show();


        lstv = (AutoListView) findViewById(R.id.zhuanye_select_pull_refresh_list);
        adapter = new select_zhuanye_Adapter(this, list,yx_list);
        lstv.setAdapter(adapter);


        lstv.setOnRefreshListener(this);
        lstv.setOnLoadListener(this);
        lstv.setOnItemClickListener(this);
        initData();
    }

    public void zhuye_select(View v) {
        String zhuanye_text = et_zhuye_select.getText().toString();

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
    public List<zhuanye> getData() {

//
        List<zhuanye> result1 = new ArrayList<zhuanye>();
         if(zhuanyemingzi.equals("")){
             i++;
         }else {
            i=1;
         }
        String jsonString = HttpUtils.request_all_zhuanye(i,zhuanyemingzi);

        List<Map<String, Object>> list = JsonTools.listKeyMaps("school", jsonString);

        Log.d("zhao", "000000000000000");
        ArrayList<Map> arrylist = new ArrayList<Map>();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map map = (Map) it.next();
            zhuanye zy = new zhuanye();
            zy.setSpecialname(map.get("specialname").toString());
            zy.setSpecialurl(map.get("specialurl").toString());
            zy.setZycengci(map.get("zycengci").toString());
            zy.setZytype(map.get("zytype").toString());
            zy.setRanking(map.get("ranking").toString());
            zy.setRankingType(map.get("rankingType").toString());
            result1.add(zy);
        }
       for (int i=0;i<result1.size();i++){
           zhuanye zy1 = result1.get(i);
           String a = zy1.getSpecialname().toString();
           String jsonString2 = HttpUtils.request_kaisheyuanxiao(a,1);
               ZhuanYeCount tatal = (ZhuanYeCount) JsonTools.getTotal_zhuanyeyuanxiao("totalRecord", jsonString2);
               Log.d("skyfishzhao", "1111111111==" + tatal.getTotalRecord());
               yx_list.add(tatal.getTotalRecord());
//           Log.d("skyfish","1111111111=="+a);
       }

        return result1;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("item", "111111111");
        zhuanye sc=list.get(i-1);
        Intent intent=new Intent(this,kaisheyuanxiaoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("specialurl",sc.getSpecialurl());
        bundle.putString("specialname", sc.getSpecialname());
        bundle.putString("zytype", sc.getZytype());
        bundle.putString("zycengci", sc.getZycengci());
         String total = yx_list.get(i-1);
        bundle.putString("total",total);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.move_right_in_activity, 0);

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
    public void zhuanye_select(View v) {
        zhuanyemingzi = et_zhuanyemizi.getText().toString();
        loadData(AutoListView.REFRESH);

    }
    public void zhuanye_finsh(View v) {
        this.finish();
        overridePendingTransition(R.anim.move_right_out_activity, 0);
    }
}
