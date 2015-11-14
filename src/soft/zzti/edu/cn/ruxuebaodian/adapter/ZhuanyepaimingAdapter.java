package soft.zzti.edu.cn.ruxuebaodian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import soft.zzti.edu.cn.ruxuebaodian.R;

import java.util.ArrayList;


public class ZhuanyepaimingAdapter extends BaseAdapter {

    private Context context;
    ArrayList<String> p = new ArrayList<String>();
    ArrayList<String> r = new ArrayList<String>();
    ArrayList<String> n = new ArrayList<String>();
    ArrayList<String> c = new ArrayList<String>();
    public ZhuanyepaimingAdapter(Context context,ArrayList<String> p,ArrayList<String> r,ArrayList<String> n,ArrayList<String> c) {
        this.context = context;
        this.p = p;
        this.r = r;
        this.n = n;
        this.c = c;
    }


    @Override
    public int getCount() {
        return p.size();
    }

    @Override
    public Object getItem(int i) {
        return p.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(
                R.layout.activity_zhuanyepaiming_list_item, null);
        TextView title = (TextView) view.findViewById(R.id.zhuanyepaiming_name);
        TextView paihang = (TextView) view.findViewById(R.id.zhuanyepaiming_paihang);
        TextView cengci = (TextView) view.findViewById(R.id.zhuanyepaiming_cengci);
        TextView redu = (TextView) view.findViewById(R.id.zhuanyepaiming_redu);


//        String news = a.get(i);
//        Log.d("hhhhhhhhhhhhhhhhh==>",i);
        title.setText(n.get(i));
        paihang.setText(p.get(i));
        cengci.setText(c.get(i));
        redu.setText(r.get(i));


//			view.setTag(new String[]{school.getSchoolname()});
//			holder.text = (TextView) convertView.findViewById(R.id.text);
//
        return view;
    }


}
