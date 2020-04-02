package www.dd.com.notifyviewpager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private RecyclerView RecyclerViewTop;
    private RecyclerView RecyclerViewBottom;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] name = {"张飞", "刘备", "关羽", "孙尚香", "狂铁", "后羿", "小乔", "大乔"};
    private BaseFragmentPagerAdapter mAdapter;
    private HashMap<String, BaseFragment> mFragmentHashMap = new HashMap<>();
    private TopAdapterAdapter mBottomAdapterAdapter;
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerViewTop = (RecyclerView) findViewById(R.id.RecyclerView_top);
        RecyclerViewBottom = (RecyclerView) findViewById(R.id.RecyclerView_bottom);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        initView();
    }

    private void initView() {
        RecyclerViewTop.setLayoutManager(new GridLayoutManager(this, 3));
        final TopAdapterAdapter topAdapterAdapter = new TopAdapterAdapter(this);
        RecyclerViewTop.setAdapter(topAdapterAdapter);
        topAdapterAdapter.addAll(Arrays.asList(name));
        topAdapterAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(int position) {
                if (position != -1) {
                    String name = topAdapterAdapter.getList().get(position);
                    topAdapterAdapter.getList().remove(position);
                    topAdapterAdapter.notifyItemRemoved(position);
                    mBottomAdapterAdapter.add(name);
                    if (mFragmentHashMap.get(name) == null) {
                        ViewPagerFragment viewPagerFragment = ViewPagerFragment.setFragment(name);
                        mFragmentHashMap.put(name, viewPagerFragment);
                        titles.add(name);
                        mAdapter.addFragment(viewPagerFragment);
                    } else {
                        ViewPagerFragment viewPagerFragment = ViewPagerFragment.setFragment(name);
                        mFragmentHashMap.put(name, viewPagerFragment);
                        mAdapter.replaceFragment(viewPagerFragment, mFragmentHashMap.get(name));
                    }
                }


            }
        });

        RecyclerViewBottom.setLayoutManager(new GridLayoutManager(this, 3));
        mBottomAdapterAdapter = new TopAdapterAdapter(this);
        RecyclerViewBottom.setAdapter(mBottomAdapterAdapter);
        mBottomAdapterAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(int position) {
                String name = mBottomAdapterAdapter.getList().get(position);
                mBottomAdapterAdapter.getList().remove(position);
                mBottomAdapterAdapter.notifyItemRemoved(position);
                topAdapterAdapter.add(name);
                BaseFragment baseFragment = mFragmentHashMap.get(name);
                if (baseFragment != null) {
                    mAdapter.removeFragment(baseFragment);
                    mFragmentHashMap.remove(name);
                    titles.remove(name);
                }

            }
        });
        mAdapter = new MyAdapter(getSupportFragmentManager(), new ArrayList<BaseFragment>());
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    public class MyAdapter extends BaseFragmentPagerAdapter {
        public MyAdapter(FragmentManager fm, List<BaseFragment> fragments) {
            super(fm, fragments);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
