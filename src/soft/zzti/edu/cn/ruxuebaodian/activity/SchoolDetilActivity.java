package soft.zzti.edu.cn.ruxuebaodian.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.jsontools.NormalLoadPictrue;

import java.io.IOException;

/**
 * Created by zhaoxiangyu on 2015/11/10.
 */
public class SchoolDetilActivity extends Activity {
    TextView schoolname;
    TextView jianjie;
    String a;
    ImageView imageView;
    String linkHref;
    String xuxiaojianjie;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaishexuexiao_daxue_list_item);

//        dialog = createLoadingDialog(this, "正在加载");
//        dialog.show();
        Bundle bundle = getIntent().getExtras();
        a = bundle.getString("schoolid");
//        Log.d("huangning",a);
        String b = bundle.getString("schoolname");
        initview();

        schoolname.setText(b);
//        Toast.makeText(this, "正在加载......", Toast.LENGTH_LONG).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                getimage();
                getjianjie();
            }
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        dialog.cancel();
        new NormalLoadPictrue().getPicture(linkHref, imageView);
        jianjie.setText(xuxiaojianjie);


    }

    public void initview() {
        schoolname = (TextView) findViewById(R.id.tv3_schoolname);
        imageView = (ImageView) findViewById(R.id.img3_daxuetupian);
        jianjie = (TextView) findViewById(R.id.tv3_xuexiaojianjie);
    }

    public void getimage() {

        Log.d("huangning", "11111111111");
        Document con = null;
        try {
            con = Jsoup.connect("http://gkcx.eol.cn/schoolhtm/" + a + "/schoolImageList/list_1.htm").get();

//                    Log.d("huangning", con.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements con1 = con.select("a[id=thumb_xixi-1]");
//        System.out.print("huangning1"+con1);
//        getElementById("thumb_xixi-1");
//        Log.d("huangning",con1.toString());
        Log.d("huangning", String.valueOf(con1));
        Document doc = Jsoup.parse(String.valueOf(con1));
        Element link = doc.select("img").first();
        linkHref = link.attr("src");
        Log.d("huangning3", linkHref);
//                Elements util =	con.getElementById("thumb_xixi-1").getAllElements();

    }

    //    http://gkcx.eol.cn/schoolhtm/schoolInfo/44/10056/detail.htm
    public void getjianjie() {
        Document con = null;
        try {
            con = Jsoup.connect("http://gkcx.eol.cn/schoolhtm/schoolInfo/" + a + "/10056/detail.htm").get();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements util1 = con.select("div[class=S_result S_result_kx mar_t_20]");
        Document doc = Jsoup.parse(String.valueOf(util1));
        xuxiaojianjie = doc.select("p").text();
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

    public void ksyx_back(View v) {
        this.finish();
        overridePendingTransition(R.anim.move_right_out_activity, 0);
    }
}
