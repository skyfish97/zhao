package soft.zzti.edu.cn.ruxuebaodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.entity.KaiSheYuanXiao;

import java.util.List;


public class KaiSheYuanXiao_Adapter extends BaseAdapter {

    private Context context;
    private List<KaiSheYuanXiao> schoolArrayList;
    public KaiSheYuanXiao_Adapter(Context context,  List<KaiSheYuanXiao> schoolArrayList) {
        this.context = context;
        this.schoolArrayList = schoolArrayList;
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
                R.layout.kaisheyuanxiao_list_item, null);

        KaiSheYuanXiao zy = (KaiSheYuanXiao) schoolArrayList.get(i);


        TextView tv_xxname = (TextView) view.findViewById(R.id.tv_xxname);
        TextView tv_zymc = (TextView) view.findViewById(R.id.tv_zymc);

        tv_xxname.setText(zy.getSchoolname());
        tv_zymc.setText(zy.getSpecialtyname());


        view.setTag(new String[]{zy.getSchoolname()});
        return view;
    }


}
