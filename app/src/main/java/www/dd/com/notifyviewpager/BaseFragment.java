package www.dd.com.notifyviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;


/**
 * Created by yb on 2018/1/27.
 */

public abstract class BaseFragment extends Fragment {

    public View mView;
    public Activity mActivity;
    public BaseFragment mFragment;
    //是否使用懒加载
    public final static boolean mIsLazyLoad = false;
    private boolean mIsInit = false;
    private boolean mIsLoadData = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsInit = false;
        mFragment = this;
        mActivity = getActivity();
        Log.d("BaseFragment","onCreate");
        int contentLayoutId = getContentLayoutId();
        if (contentLayoutId == 0) {
            throw new RuntimeException("getContentLayoutId()方法返回的布局ID无效");
        }
        if (mView == null) {
            mView = LayoutInflater.from(mActivity).inflate(contentLayoutId, null);
            if (!isLazyLoad()) {
                mIsLoadData = true;
                initView(mView);
                initData();
            }
        }
    }

    @Override
    public synchronized View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        mIsInit = true;
        lazyLoad();
        return mView;
    }

    //懒加载
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    public boolean isLazyLoad() {
        return mIsLazyLoad;
    }

    private void lazyLoad() {
        if (!isLazyLoad() || !mIsInit || !getUserVisibleHint() || mIsLoadData) {
            return;
        }
        mIsLoadData = true;
        initView(mView);
        initData();
    }


    public abstract int getContentLayoutId();

    public abstract void initView(View view);

    public abstract void initData();


    public Map<String, Object> getMapList() {
        return new HashMap<String, Object>();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView = null;
        mActivity = null;
        Log.d("BaseFragment","onDestroy");
    }
}
