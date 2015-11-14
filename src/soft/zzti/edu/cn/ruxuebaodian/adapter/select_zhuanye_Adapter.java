package soft.zzti.edu.cn.ruxuebaodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.entity.zhuanye;

import java.util.ArrayList;


public class select_zhuanye_Adapter extends BaseAdapter {

    private Context context;
    private ArrayList<zhuanye> schoolArrayList;
    private ArrayList<String> yx_list;
    public select_zhuanye_Adapter(Context context, ArrayList<zhuanye> schoolArrayList,ArrayList<String> yx_list) {
        this.context = context;
        this.schoolArrayList = schoolArrayList;
        this.yx_list = yx_list;
    }


    @Override
    public int getCount() {
        return schoolArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return schoolArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup parent) {

        view = LayoutInflater.from(context).inflate(
                R.layout.activity_slectzhuanye_list_item, null);



        zhuanye zy = schoolArrayList.get(i);

        TextView specialname = (TextView) view.findViewById(R.id.tv_specialname);
        TextView tv_zytype = (TextView) view.findViewById(R.id.tv_zytype);
        TextView tv_yxcount = (TextView) view.findViewById(R.id.tv_yxcount);
        TextView tv_ranking = (TextView) view.findViewById(R.id.tv_ranking);
        TextView tv_rankingType = (TextView) view.findViewById(R.id.tv_rankingType);

        specialname.setText(zy.getSpecialname().toString());
        tv_zytype.setText(zy.getZytype().toString());
        tv_yxcount.setText(yx_list.get(i).toString());
        tv_ranking.setText(zy.getRanking().toString());
        tv_rankingType.setText(zy.getRankingType().toString());


        view.setTag(new String[]{zy.getSpecialname()});
        return view;
    }


}
