package soft.zzti.edu.cn.ruxuebaodian.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import soft.zzti.edu.cn.ruxuebaodian.R;

/**
 * Created by zhaoxiangyu on 2015/11/9.
 */
public class jibenxinxiFragment extends Fragment {
    View view;

    TextView specialname;
    TextView zytype;
    TextView zycengci;
    TextView total;
    TextView shoyuxuewei;
    //    TextView specialurl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.major_content_page1, container, false);
        specialname = (TextView) view.findViewById(R.id.tv1_zhuanyemingcheng);
        zytype = (TextView) view.findViewById(R.id.tv1_suoshuleibie);
        zycengci = (TextView) view.findViewById(R.id.tv1_xuelicengci);
        total = (TextView) view.findViewById(R.id.ty1_kaisheyuanxiaoshuliang) ;
        shoyuxuewei = (TextView) view.findViewById(R.id.tv1_shouyuxuewei);
        Bundle bundle = getActivity().getIntent().getExtras();

        String a = bundle.getString("specialname");
        String b = bundle.getString("zytype");
        String c = bundle.getString("zycengci");
        String t = bundle.getString("total");

        Log.d("zhaonishi", t);

        specialname.setText(a);
        zytype.setText(b);
        zycengci.setText(c);
        total.setText(t);
        shoyuxuewei.setText(b);

        return view;
    }


}
