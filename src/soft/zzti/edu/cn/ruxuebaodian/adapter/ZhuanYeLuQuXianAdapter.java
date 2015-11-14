package soft.zzti.edu.cn.ruxuebaodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.entity.ZhuanYeLuQu;

import java.util.ArrayList;


public class ZhuanYeLuQuXianAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ZhuanYeLuQu> newsArrayList;

    public ZhuanYeLuQuXianAdapter(Context context, ArrayList<ZhuanYeLuQu> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }


    @Override
    public int getCount() {
        return newsArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return newsArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        view = LayoutInflater.from(context).inflate(
                R.layout.activity_zhuanyeluquxian_list_item, null);
        TextView schoolname = (TextView) view.findViewById(R.id.zylu_xxmz);
        TextView zhuanyemingcheng = (TextView) view.findViewById(R.id.zylq_zymc);
        TextView zhaoshengdiqu = (TextView) view.findViewById(R.id.zylq_dq);
        TextView wenlike = (TextView) view.findViewById(R.id.zylu_wlk);
        TextView nianfen = (TextView) view.findViewById(R.id.zylq_nf);
        TextView batch = (TextView) view.findViewById(R.id.zylu_batch);
        TextView pingjunfen = (TextView) view.findViewById(R.id.zylu_varsocre);




        ZhuanYeLuQu news = newsArrayList.get(i);

        schoolname.setText(news.getSchoolname());
        zhuanyemingcheng.setText(news.getSpecialtyname());
        zhaoshengdiqu.setText(news.getLocalprovince());
        wenlike.setText(news.getStudenttype());
        nianfen.setText(news.getYear());
        batch.setText(news.getBatch());
        pingjunfen.setText(news.getVar_score());

//			view.setTag(new String[]{school.getSchoolname()});

        return view;
    }


}
