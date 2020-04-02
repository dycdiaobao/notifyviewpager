package www.dd.com.notifyviewpager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 DingDing
 * @创建时间 2020/3/31 14:57
 * @描述
 * @更新者 $
 * @更新时间 $
 * 更新描述
 */
public class TopAdapterAdapter extends BaseAdapter<TopAdapterAdapter.TopAdapterViewHolder, String> {

    public TopAdapterAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public TopAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.recycler_top, parent, false);
        return new TopAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TopAdapterViewHolder viewHolder, final int position) {
        viewHolder.name.setText(mList.get(position));
        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(viewHolder.getLayoutPosition());
                }
            }
        });
    }

    class TopAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public TopAdapterViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);

        }
    }

    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }
}