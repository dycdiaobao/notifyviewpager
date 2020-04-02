package www.dd.com.notifyviewpager;

import android.app.Activity;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by yb on 2018/2/5.
 */

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, Y> extends RecyclerView.Adapter<T> {
    protected List<Y> mList = new ArrayList<>();
    protected Activity mActivity;

    public BaseAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public abstract T onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(T viewHolder, int position);


    public List<Y> getList() {
        return mList;
    }

    public void add(Y bean) {
        mList.add(bean);
        notifyItemChanged(mList.size() - 1);
    }

    public void add(int index, Y bean) {
        if (index < 0 || index > mList.size()) {
            return;
        }
        mList.add(index, bean);
        notifyDataSetChanged();
    }

    public void addAll(Collection<Y> list) {
        if (list != null && list.size() > 0) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addAll(int index, Collection<? extends Y> list) {
        if (list != null) {
            if (index < 0 || index > mList.size()) {
                return;
            }
            mList.addAll(index, list);
            notifyDataSetChanged();
        }
    }

    public void removeAll(Collection<Y> list) {
        if (list != null) {
            mList.removeAll(list);
            notifyDataSetChanged();
        }
    }

    public void remove(int index) {
        if (index >= 0 && index < mList.size()) {
            mList.remove(index);
            notifyDataSetChanged();
        }
    }

    public void remove(Y bean) {
        mList.remove(bean);
        notifyDataSetChanged();
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    private WeakReference<RecyclerView> mWeakReference;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mWeakReference = new WeakReference<>(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mWeakReference != null) {
            mWeakReference.clear();
            mWeakReference = null;
        }
    }
}
