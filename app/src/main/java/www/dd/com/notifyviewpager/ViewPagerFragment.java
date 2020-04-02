package www.dd.com.notifyviewpager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * @创建者 DingDing
 * @创建时间 2020/3/31 14:30
 * @描述
 * @更新者 $
 * @更新时间 $
 * 更新描述
 */
public class ViewPagerFragment extends BaseFragment {

    private TextView mName;

    public static ViewPagerFragment setFragment(String name){
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        viewPagerFragment.setArguments(args);
        return viewPagerFragment;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_view_pager;
    }

    @Override
    public void initView(View view) {
        mName = view.findViewById(R.id.name);
        Bundle arguments = getArguments();
        String name = arguments.getString("name");
        mName.setText(name);
    }

    @Override
    public void initData() {

    }

}
