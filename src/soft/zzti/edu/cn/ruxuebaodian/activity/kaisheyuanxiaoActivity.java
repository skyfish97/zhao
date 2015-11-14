package soft.zzti.edu.cn.ruxuebaodian.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.adapter.KaiSheYuanXiao_Adapter;
import soft.zzti.edu.cn.ruxuebaodian.entity.KaiSheYuanXiao;
import soft.zzti.edu.cn.ruxuebaodian.tab.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoxiangyu on 2015/11/6.
 */
public class kaisheyuanxiaoActivity extends FragmentMainActivity implements View.OnClickListener {
    TextView tv;
    //    List<School> result1 = new ArrayList<School>();
    KaiSheYuanXiao_Adapter adapter;
    ListView listView;

    String a;
    String b;
    String c;
    String specialurl;
    List<KaiSheYuanXiao> result1 = new ArrayList<KaiSheYuanXiao>();
    ViewPager mViewPager;

    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;


    private List<Fragment> mFragments;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectzhuanye_detail);
        initView();
        initEvent();
        setSelect(0);
        tv = (TextView) findViewById(R.id.tv_zhuanye_name);

        Bundle bundle = this.getIntent().getExtras();
        a = bundle.getString("specialname");
//        b = bundle.getString("zytype");
//        c = bundle.getString("zycengci");


//        specialurl = bundle.getString("zycengci");
//        Log.d("zhaonishi", "000000000000000");
        tv.setText(a);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                getdata();
//            }
//        }).start();
//
//        try {
//
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        adapter = new KaiSheYuanXiao_Adapter(getApplication(), result1);
//        listView.setAdapter(adapter);
//        Log.d("xianyan5",result1.());
//        System.out.print("xianyan" +  result2.get(1).getSchoolname());


    }

    private void initEvent() {

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.guide_viewpage);


        rb1 = (RadioButton) findViewById(R.id.tv_top_basic_information);
        rb2 = (RadioButton) findViewById(R.id.tv_top_major_general);
        rb3 = (RadioButton) findViewById(R.id.tv_top_open_university);
        rb4 = (RadioButton) findViewById(R.id.tv_top_enroll_count);

        mFragments = new ArrayList<Fragment>();
        Fragment mTab01 = new jibenxinxiFragment();
        Fragment mTab02 = new zhuanyegaikuangFragment();
        Fragment mTab03 = new kaisheyuanxiaoFragment();
        Fragment mTab04 = new luqurenshuFragment();
        mFragments.add(mTab01);
        mFragments.add(mTab02);
        mFragments.add(mTab03);
        mFragments.add(mTab04);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_top_basic_information:
                setSelect(0);
                break;
            case R.id.tv_top_major_general:
                setSelect(1);
                break;
            case R.id.tv_top_open_university:
                setSelect(2);
                break;
            case R.id.tv_top_enroll_count:
                setSelect(3);
                break;

            default:
                break;
        }
    }

    private void setSelect(int i) {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }

    private void setTab(int i) {
        resetImgs();
        // 设置图片为亮色
        // 切换内容区域
        switch (i) {
            case 0:
                rb1.setBackground(getResources().getDrawable(R.drawable.tabok));
                rb1.setTextColor(getResources().getColor(R.color.skyblue));
                break;
            case 1:

                rb2.setBackground(getResources().getDrawable(R.drawable.tabok));
                rb2.setTextColor(getResources().getColor(R.color.skyblue));

                break;
            case 2:

                rb3.setBackground(getResources().getDrawable(R.drawable.tabok));
                rb3.setTextColor(getResources().getColor(R.color.skyblue));

                break;
            case 3:
                rb4.setBackground(getResources().getDrawable(R.drawable.tabok));
                rb4.setTextColor(getResources().getColor(R.color.skyblue));

                break;
        }
    }

    /**
     * 切换图片至暗色
     */
    private void resetImgs() {
        rb1.setBackground(getResources().getDrawable(R.drawable.tabno));
        rb2.setBackground(getResources().getDrawable(R.drawable.tabno));
        rb3.setBackground(getResources().getDrawable(R.drawable.tabno));
        rb4.setBackground(getResources().getDrawable(R.drawable.tabno));

        rb1.setTextColor(getResources().getColor(R.color.black));
        rb2.setTextColor(getResources().getColor(R.color.black));
        rb3.setTextColor(getResources().getColor(R.color.black));
        rb4.setTextColor(getResources().getColor(R.color.black));


    }


    public void ksyx_back(View v) {
        finish();
    }
}
