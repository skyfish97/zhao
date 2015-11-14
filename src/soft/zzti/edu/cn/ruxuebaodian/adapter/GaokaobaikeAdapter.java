package soft.zzti.edu.cn.ruxuebaodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import soft.zzti.edu.cn.ruxuebaodian.R;
import soft.zzti.edu.cn.ruxuebaodian.entity.Gaokaobaike;

import java.util.ArrayList;


public class GaokaobaikeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Gaokaobaike> newsArrayList;

    public GaokaobaikeAdapter(Context context, ArrayList<Gaokaobaike> newsArrayList) {
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
                R.layout.activity_gaokaobaike_list_item, null);
        TextView title = (TextView) view.findViewById(R.id.gaokaobaike_title);
        TextView cotent = (TextView) view.findViewById(R.id.gaokaobaike_content);
        TextView time = (TextView) view.findViewById(R.id.gaokaobaike_time);


        Gaokaobaike news = newsArrayList.get(i);

        title.setText(news.getTitle());
        cotent.setText(news.getContent());
        time.setText(news.getTime());

//			view.setTag(new String[]{school.getSchoolname()});
//			holder.text = (TextView) convertView.findViewById(R.id.text);
        view.setTag(new String[]{news.getTitle()});
//
        return view;
    }


}
