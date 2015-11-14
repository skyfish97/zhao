package soft.zzti.edu.cn.ruxuebaodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.entity.DaXueLuQuXian;

import java.util.ArrayList;


public class DaXueLuQuXianAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DaXueLuQuXian> newsArrayList;

    public DaXueLuQuXianAdapter(Context context, ArrayList<DaXueLuQuXian> newsArrayList) {
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
                R.layout.activity_daxueluquxian_list_item, null);
        TextView title = (TextView) view.findViewById(R.id.tv_xxmz);
        TextView diqu = (TextView) view.findViewById(R.id.tv_sf);
        TextView nianfen = (TextView) view.findViewById(R.id.tv_nf);
        TextView luqupici = (TextView) view.findViewById(R.id.tv_lqpc);
        TextView pingjunfen = (TextView) view.findViewById(R.id.tv_pjf);
        TextView shengkongxian = (TextView) view.findViewById(R.id.tv_skx);
        TextView xiancha = (TextView) view.findViewById(R.id.tv_xc);



        DaXueLuQuXian news = newsArrayList.get(i);

        title.setText(news.getSchoolname());
        diqu.setText(news.getLocalprovince());
        nianfen.setText(news.getYear());
        luqupici.setText(news.getBatch());
        pingjunfen.setText(news.getVar_score());
        shengkongxian.setText(news.getProvincescore());
        xiancha.setText(news.getFencha());

//			view.setTag(new String[]{school.getSchoolname()});

        return view;
    }


}
