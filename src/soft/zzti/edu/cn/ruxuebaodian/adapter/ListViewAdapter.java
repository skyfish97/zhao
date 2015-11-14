package soft.zzti.edu.cn.ruxuebaodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.entity.School;

import java.util.ArrayList;


public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<School> schoolArrayList;

    public ListViewAdapter(Context context, ArrayList<School> schoolArrayList) {
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
                R.layout.activity_recommeded_list_item, null);


        School school = schoolArrayList.get(i);

        TextView schoolname = (TextView) view.findViewById(R.id.tv_schoolName);
        TextView schoolprovince = (TextView) view.findViewById(R.id.tv_provice);
        TextView percentage = (TextView) view.findViewById(R.id.tv_percent);
        TextView var2014 = (TextView) view.findViewById(R.id.tv_AvgScore);
        TextView batch = (TextView) view.findViewById(R.id.tv_Batch);

        schoolname.setText(school.getSchoolname().toString());
        schoolprovince.setText(school.getSchoolprovince().toString());
        percentage.setText(school.getPercentage().toString());
        var2014.setText(school.getVar2014().toString());
        batch.setText(school.getBatch().toString());

//			view.setTag(new String[]{school.getSchoolname()});
//			holder.text = (TextView) convertView.findViewById(R.id.text);
        view.setTag(new String[]{school.getSchoolname()});
//
        return view;
    }


}
