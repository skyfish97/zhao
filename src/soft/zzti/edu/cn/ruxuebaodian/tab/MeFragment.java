package soft.zzti.edu.cn.ruxuebaodian.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import soft.zzti.edu.cn.ruxuebaodian.R;

public class MeFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.activity_me, container, false);
	}

}
