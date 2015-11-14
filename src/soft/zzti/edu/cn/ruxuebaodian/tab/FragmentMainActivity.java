package soft.zzti.edu.cn.ruxuebaodian.tab;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.activity.*;
import soft.zzti.edu.cn.ruxuebaodian.sharesdk.OnekeyShare;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoxiangyu on 2015/10/26.
 */
public class FragmentMainActivity extends FragmentActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    public static String TEST_IMAGE;
    private static final String FILE_NAME = "/share_pic.jpg";
    private LinearLayout mTabMain;
    private LinearLayout mTabSelect;
    private LinearLayout mTabMe;

    private ImageButton mImgMain;
    private ImageButton mImgSelect;
    private ImageButton mImgMe;

    private TextView tv_main;
    private TextView tv_select;
    private TextView tv_me;


    EditText syd;
    EditText wlk;
    EditText fs;

    String et1;
    String et2;
    String et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
//        ShareSDK.initSDK(this);
        initView();
        initEvent();
        setSelect(0);
//        //初始化本地图片，把图片从drawable复制到sdddcard中
//        new Thread() {
//            public void run() {
//                initImagePath();
//            }
//        }.start();
    }

    private void initEvent() {
        mTabMain.setOnClickListener(this);
        mTabSelect.setOnClickListener(this);
        mTabMe.setOnClickListener(this);
    }

    private void initView() {
        tv_main = (TextView) findViewById(R.id.tv_main);
        tv_select = (TextView) findViewById(R.id.tv_select);
        tv_me = (TextView) findViewById(R.id.tv_me);


        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabMain = (LinearLayout) findViewById(R.id.id_tab_main);
        mTabSelect = (LinearLayout) findViewById(R.id.id_tab_Select);
        mTabMe = (LinearLayout) findViewById(R.id.id_tab_Me);

        mImgMain = (ImageButton) findViewById(R.id.id_tab_main_image);
        mImgSelect = (ImageButton) findViewById(R.id.id_tab_Select_img);
        mImgMe = (ImageButton) findViewById(R.id.id_tab_me_img);

        mFragments = new ArrayList<Fragment>();
        Fragment mTab01 = new FirstFragment();
        Fragment mTab02 = new SelectFragment();
        Fragment mTab03 = new MeFragment();



        mFragments.add(mTab01);
        mFragments.add(mTab02);
        mFragments.add(mTab03);

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

        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

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

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.id_tab_main:
                setSelect(0);
                break;
            case R.id.id_tab_Select:
                setSelect(1);
                break;
            case R.id.id_tab_Me:
                setSelect(2);
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
        Resources resource = (Resources) getBaseContext().getResources();
        resetImgs();
        // ����ͼƬΪ��ɫ
        // �л���������
        switch (i) {
            case 0:
                mImgMain.setImageResource(R.drawable.menu_information_icon2);

                ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.skyblue);
                tv_main.setTextColor(csl);
                break;
            case 1:
                mImgSelect.setImageResource(R.drawable.menu_data_icon2);
                ColorStateList csl1 = (ColorStateList) resource.getColorStateList(R.color.skyblue);
                tv_select.setTextColor(csl1);
                break;
            case 2:
                mImgMe.setImageResource(R.drawable.menu_i_icon2);
                ColorStateList csl2 = (ColorStateList) resource.getColorStateList(R.color.skyblue);
                tv_me.setTextColor(csl2);
                break;

        }
    }

    /**
     * �л�ͼƬ����ɫ
     */
    private void resetImgs() {
        mImgMain.setImageResource(R.drawable.menu_information_icon);
        mImgSelect.setImageResource(R.drawable.menu_data_icon);
        mImgMe.setImageResource(R.drawable.menu_i_icon);


        Resources resource = (Resources) getBaseContext().getResources();
        ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.gray);
        tv_main.setTextColor(csl);
        ColorStateList csl1 = (ColorStateList) resource.getColorStateList(R.color.gray);
        tv_select.setTextColor(csl1);
        ColorStateList csl2 = (ColorStateList) resource.getColorStateList(R.color.gray);
        tv_me.setTextColor(csl2);

    }

//    public void recomment(View v) {
//        syd = (EditText) findViewById(R.id.et_shengyuandi);
//        wlk = (EditText) findViewById(R.id.et_wenlike);
//        fs = (EditText) findViewById(R.id.et_fenshu);
//
//
//
//        et1 = syd.getText().toString();
//        et2 = wlk.getText().toString();
//        et3 = fs.getText().toString();
//
//        Intent intent = new Intent(this, RecommentSchoolListActivity.class);
////        intent.putExtra("syd", et1);
////        intent.putExtra("wlk", et2);
////        intent.putExtra("fs", et3);
//        Log.d("wowowowowowow", et1);
//        Bundle bundle = new Bundle();
//        bundle.putString("syd", et1);
//        bundle.putString("wlk", et2);
//        bundle.putString("fs", et3);
//        intent.putExtras(bundle);
//        startActivity(intent);
//        overridePendingTransition(R.anim.enter, 0);
//    }
public void zhuanyechaxun(View view){
    Intent intent = new Intent(this, ZhuanyeSelectActivity.class);
    startActivity(intent);
    overridePendingTransition(R.anim.move_right_in_activity,0);
}
    public void about_gkbd(View v){
       Intent intent = new Intent(this, AboutGKBDActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.move_right_in_activity, 0);
    }

    public void fenxiang(View v){
                OnekeyShare oks = new OnekeyShare();
        Log.d("haha","55555555555555555555");
                oks.setNotification(R.drawable.icon512, "ShareSDK onekeyshare sample");
                oks.setAddress("12345678901");
                oks.setTitle("ShareSDK title");
                oks.setTitleUrl("http://sharesdk.cn");
                oks.setText("ShareSDK text");
                oks.setImagePath(FragmentMainActivity.TEST_IMAGE);
                oks.setImageUrl("http://img.appgo.cn/imgs/sharesdk/content/2013/07/25/1374723172663.jpg");
                oks.setUrl("http://sharesdk.cn");
                oks.setComment("renren weibo comment");
                oks.setSite("qzone share website");
                oks.setSiteUrl("http://sharesdk.cn");
                oks.setLatitude(23.122619f);
                oks.setLongitude(113.372338f);
                oks.setSilent(false);
                oks.show(FragmentMainActivity.this);


    }

    public void gaokaobaike(View v){
        Intent intent = new Intent(this, GaokaobaikeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.move_right_in_activity,0);
    }
    public void gaokaozixun(View v){
        Intent intent = new Intent(this, GaokaozixunActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.move_right_in_activity,0);
    }
    public void zhuanyepaiming(View view){
        Intent intent = new Intent(this, ZhuanyepaimingActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.move_right_in_activity,0);
    }

    public void  daxuefenshuxian(View view){
        Intent intent = new Intent(this,DaXueLuQuXianSelectActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.move_right_in_activity,0);
    }
    public void  zhuanyeluquxian(View view){
        Intent intent = new Intent(this,ZhuanYeLuQuXianSelectActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.move_right_in_activity,0);
    }

    //把图片从drawable复制到sdcard中
    private void initImagePath() {
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                    && Environment.getExternalStorageDirectory().exists()) {
                TEST_IMAGE = Environment.getExternalStorageDirectory().getAbsolutePath() + FILE_NAME;
            }
            else {
                TEST_IMAGE = getApplication().getFilesDir().getAbsolutePath() + FILE_NAME;
            }
            File file = new File(TEST_IMAGE);
            if (!file.exists()) {
                file.createNewFile();
                Bitmap pic = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
                FileOutputStream fos = new FileOutputStream(file);
                pic.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            }
        } catch(Throwable t) {
            t.printStackTrace();
            TEST_IMAGE = null;
        }
    }

}
