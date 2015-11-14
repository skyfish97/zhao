package soft.zzti.edu.cn.ruxuebaodian.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import soft.zzti.edu.cn.ruxuebaodian.R;

import java.io.IOException;

/**
 * Created by zhaoxiangyu on 2015/11/9.
 */
public class zhuanyegaikuangFragment extends Fragment{
    View view;
    Document con=null;
    String a;
    String b;
    WebView wb ;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.major_content_page2now, container, false);
       textView = (TextView) view.findViewById(R.id.tv2_yuanxiaogaikuang);
        Bundle bundle = getActivity().getIntent().getExtras();
         a = bundle.getString("specialurl");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    con = Jsoup.connect("http://gkcx.eol.cn"+a).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements util =	con.select("div[class=query_box S_result_kx mar_t_20]");
                Document doc = Jsoup.parse(String.valueOf(util));
                   b =doc.select("p").text();
            }
        }).start();
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textView.setText(b);
        return view;
    }
}
