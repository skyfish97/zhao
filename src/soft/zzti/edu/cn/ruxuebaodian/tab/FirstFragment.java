package soft.zzti.edu.cn.ruxuebaodian.tab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView.ScaleType;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.activity.RecommentSchoolListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FirstFragment extends Fragment{
    EditText et_shengyuandi;
    EditText et_wenlike;
    EditText et_fenshu;

    String et1;
    String et2;
    String et3;

//    Button bt_shengyaundi;
    Button bt_tijiao;
//    Button bt_wenli;
//    Button bt_fenshu;
    
    DrawerLayout draw;
    ListView listview;
    View view;
 
    
    Handler handler_shengyaundi;
    Handler handler_nianxian;
    Handler handler_wenli;
   
    
    TextView tv_shengyuandi;
    TextView tv_nianxian;
    TextView tv_wenli;

	LinearLayout layout_syd;
	LinearLayout layout_wenli;
	LinearLayout layout_fenshu;
    
    private ViewPager viewPager;
	private List<ImageView> imageViews;
	private int[] imageResId;
	private List<View> dots;
	private int currentItem = 5000;
	private myAdapterpager pageAdapter;
	boolean nowAction = false;// 当前用户正在滑动视图
	private ScheduledExecutorService scheduledExecutorService;
	
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			viewPager.setCurrentItem(currentItem);
		};
	};
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.zhuye1, container, false);

		tv_shengyuandi = (TextView) view.findViewById(R.id.textview1);
		tv_wenli = (TextView) view.findViewById(R.id.textview_wenli);

//        bt_shengyaundi=(Button)view.findViewById (R.id.bt_shengyuandi);

        layout_syd = (LinearLayout) view.findViewById(R.id.layout_syd);
		layout_wenli = (LinearLayout) view.findViewById(R.id.layout_wenli);
		layout_fenshu = (LinearLayout) view.findViewById(R.id.layout_fenshu);


        bt_tijiao=(Button)view.findViewById(R.id.bt_tijiao);
//        bt_wenli=(Button)view.findViewById(R.id.bt_wenli);
//        bt_fenshu=(Button)view.findViewById(R.id.bt_fenshu);
        
        draw = (DrawerLayout)view. findViewById(R.id.drawer_layout);
        listview = (ListView)view. findViewById(R.id.left_drawer); 
        et_fenshu=(EditText)view.findViewById(R.id.ed_fenshu);
         
        imageResId = new int[] { R.drawable.main_banner, R.drawable.img1, R.drawable.img2,
				R.drawable.img3, R.drawable.img2};

		imageViews = new ArrayList<ImageView>();

		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(getActivity());
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageViews.add(imageView);
		}

		dots = new ArrayList<View>();
		dots.add(view.findViewById(R.id.v_dot0));
		dots.add(view.findViewById(R.id.v_dot1));
		dots.add(view.findViewById(R.id.v_dot2));
		dots.add(view.findViewById(R.id.v_dot3));
		dots.add(view.findViewById(R.id.v_dot4));

		viewPager = (ViewPager)view. findViewById(R.id.vp);
		pageAdapter = new myAdapterpager();
		viewPager.setCurrentItem(Integer.MAX_VALUE / 4);
		viewPager.setAdapter(pageAdapter);

		viewPager.setOnPageChangeListener(new MyPageChangeListener());

        
        handler_shengyaundi = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				 tv_shengyuandi = (TextView) view.findViewById(R.id.textview1);
				super.handleMessage(msg);
				 tv_shengyuandi.setText((CharSequence) msg.obj);

			}
		};

		handler_wenli = new Handler() {
			@Override
				public void handleMessage(Message msg) {
					tv_wenli = (TextView)view.findViewById(R.id.textview_wenli);
					super.handleMessage(msg);
					tv_wenli.setText((CharSequence) msg.obj);

				}
			};
        layout_syd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				listview.setAdapter(new MyAdapter());
				listview.setDivider(new ColorDrawable(Color.rgb(211, 211, 211)));
				listview.setDividerHeight(1);
				draw.openDrawer(Gravity.RIGHT);
				listview.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						
					
						// TODO Auto-generated method stub
						ImageView image = (ImageView) arg1
								.findViewById(R.id.checkboximage);
						image.setImageResource(R.drawable.checkboxok);
						TextView text = (TextView) arg1
								.findViewById(R.id.checkboxtextview);

						View view = LayoutInflater.from(getActivity()).inflate(
								R.layout.zhuye, null, true);

					
						String s = (String) text.getText();
						Message message = handler_shengyaundi.obtainMessage();
						message.obj = s;
						handler_shengyaundi.sendMessage(message);
						draw.openDrawer(Gravity.RIGHT);
						
						draw.closeDrawers();
						}
					
				});
				
			
				
			}
		});
     

//        bt_nianxian.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//
//				listview.setAdapter(new MyAdapter2());
//
//				listview.setDivider(new ColorDrawable(Color.rgb(211, 211, 211)));
//				listview.setDividerHeight(1);
//				draw.openDrawer(Gravity.RIGHT);
//				listview.setOnItemClickListener(new OnItemClickListener() {
//
//					@Override
//					public void onItemClick(AdapterView<?> arg0, View arg1,
//							int arg2, long arg3) {
//
//						// TODO Auto-generated method stub
//						ImageView image = (ImageView) arg1
//								.findViewById(R.id.checkboximage);
//						image.setImageResource(R.drawable.checkboxok);
//						TextView text = (TextView) arg1
//								.findViewById(R.id.checkboxtextview);
//
//						View view = LayoutInflater.from(getActivity()).inflate(
//								R.layout.zhuye, null, true);
//
//						tv_nianxian = (TextView) view.findViewById(R.id.textview_nianxian);
//						String s = (String) text.getText();
//						Message message =handler_nianxian.obtainMessage();
//						message.obj = s;
//						handler_nianxian.sendMessage(message);
//
//						draw.closeDrawers();
//					}
//
//				});
//
//			}
//		});
        layout_wenli.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				listview.setAdapter(new MyAdapter3());

				listview.setDivider(new ColorDrawable(Color.rgb(211, 211, 211)));
				listview.setDividerHeight(1);
				draw.openDrawer(Gravity.RIGHT);
			    listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
					// TODO Auto-generated method stub
					ImageView image = (ImageView) arg1
							.findViewById(R.id.checkboximage);
					image.setImageResource(R.drawable.checkboxok);
					TextView text = (TextView) arg1
							.findViewById(R.id.checkboxtextview);

					
					String s = (String) text.getText();
					Message message =handler_wenli.obtainMessage();
					message.obj = s;
					handler_wenli.sendMessage(message);
					
					draw.closeDrawers();
				
					
				}
			});
			}
		});
     layout_fenshu.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			 et_fenshu.setVisibility(view.VISIBLE);
		}
	});
        

        bt_tijiao = (Button)view.findViewById(R.id.bt_tijiao);

                bt_tijiao.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        et1 =   tv_shengyuandi.getText().toString();
						System.out.print("zhao1111111111111111=====>"+et1);

                    //  et2 = tv_nianxian.getText().toString();
                        et2 = tv_wenli.getText().toString();
                        et3 = et_fenshu.getText().toString();
                         Boolean bo=isNetworkAvailable(getActivity());
						if(bo){
							if(et1.equals("不限") || et2.equals("不限") || et3.equals("")) {
								Toast.makeText(getActivity().getApplicationContext(), "请填写信息完整", Toast.LENGTH_SHORT).show();
							}else{
								Intent intent = new Intent(getActivity(), RecommentSchoolListActivity.class);
								Bundle bundle = new Bundle();
								bundle.putString("shengyuandi", et1);
								bundle.putString("wenlike", et2);
								bundle.putString("fenshu", et3);
								intent.putExtras(bundle);
								startActivity(intent);
								getActivity().overridePendingTransition(R.anim.enter, 0);
							}
						}else {
							Toast.makeText(getActivity(), "请链接网络", Toast.LENGTH_LONG).show();
						}

                    }
                });
                
               
		return view;    
        
    }

    class MyAdapter extends BaseAdapter {
		String text[] = { "北京", "天津", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江",
				"上海", "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南",
				"广东", "广西", "海南", "重庆", "四川", "贵州", "云南", "西藏", "陕西", "甘肃",
				"青海", "宁夏", "新疆" };
		int image[] = { R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, };

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return text.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return text[arg0];
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			View view = LayoutInflater.from(getActivity()).inflate(
					R.layout.checkboxitem, null);
			ImageView imageview = (ImageView) view
					.findViewById(R.id.checkboximage);
			TextView textview = (TextView) view
					.findViewById(R.id.checkboxtextview);
			textview.setTextColor(Color.BLACK);
			imageview.setImageResource(image[arg0]);
			textview.setText(text[arg0]);
			return view;
		}

    }
    class MyAdapter2 extends BaseAdapter {
		String text[] = { "2015", "2014", "2013", "2012", "2011" };
		int image[] = { R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, R.drawable.checkboxno,
				R.drawable.checkboxno, 
				 };

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return text.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return text[arg0];
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			View view = LayoutInflater.from(getActivity()).inflate(
					R.layout.checkboxitem, null);
			ImageView imageview = (ImageView) view
					.findViewById(R.id.checkboximage);
			TextView textview = (TextView) view
					.findViewById(R.id.checkboxtextview);
			imageview.setImageResource(image[arg0]);
			textview.setTextColor(Color.BLACK);
			textview.setText(text[arg0]);
			
			return view;
		}

	}
    
    class MyAdapter3 extends BaseAdapter {
		String text[] = { "文科","理科" };
		int image[] = { R.drawable.checkboxno, R.drawable.checkboxno,
				 };

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return text.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return text[arg0];
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			View view = LayoutInflater.from(getActivity()).inflate(
					R.layout.checkboxitem, null);
			ImageView imageview = (ImageView) view
					.findViewById(R.id.checkboximage);
			TextView textview = (TextView) view
					.findViewById(R.id.checkboxtextview);
			imageview.setImageResource(image[arg0]);
			textview.setTextColor(Color.BLACK);
			textview.setText(text[arg0]);
			return view;
		}

	}
    public void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 3,
				TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	public void onStop() {
		scheduledExecutorService.shutdown();
		super.onStop();
	}
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				if (!nowAction) {
					System.out.println("currentItem: " + currentItem);
					currentItem = currentItem+1;
					handler.obtainMessage().sendToTarget();
				}
			}
		}
	}
	private class MyPageChangeListener implements OnPageChangeListener {

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			currentItem = position;	
			changeDotsBg(currentItem % imageViews.size());
		}

		// 其中arg0这个参数
		// 有三种状态（0，1，2）。
		// arg0 == 1的时辰默示正在滑动，
		// arg0 == 2的时辰默示滑动完毕了，
		// arg0 == 0的时辰默示什么都没做。
		public void onPageScrollStateChanged(int arg0) {
			if (arg0 == 0) {
				nowAction = false;
			}
			if (arg0 == 1) {
				nowAction = true;
			}
			if (arg0 == 2) {
			}
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
		
		private void changeDotsBg(int currentitem){
			for(int i = 0; i < dots.size();i++){
				dots.get(i).setBackgroundResource(R.drawable.dot_normal);
			}
			dots.get(currentitem).setBackgroundResource(R.drawable.dot_focused);
		}
	}


	public class myAdapterpager extends PagerAdapter {
		
		

		@Override
		public int getCount() {
			return Integer.MAX_VALUE / 2;
		}

		@Override
		public Object instantiateItem(View arg0, int position) {
			View view = null;
			if (position % imageViews.size() < 0) {
				view = imageViews.get(imageViews.size() + position);
			} else {
				view = imageViews.get(position % imageViews.size());
			}
			ViewParent vp = view.getParent();
			if (vp != null) {
				ViewGroup parent = (ViewGroup) vp;
				parent.removeView(view);
			}
			((ViewPager) arg0).addView(view);

			return view;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}

	public static boolean isNetworkAvailable(Context context) {   
        ConnectivityManager cm = (ConnectivityManager) context   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        if (cm == null) {   
        } else {
            NetworkInfo[] info = cm.getAllNetworkInfo();   
            if (info != null) {   
                for (int i = 0; i < info.length; i++) {   
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {   
                        return true;   
                    }   
                }   
            }   
        }   
        return false;   
    } 
}
