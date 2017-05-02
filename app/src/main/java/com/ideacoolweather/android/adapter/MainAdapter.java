package com.ideacoolweather.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ideacoolweather.android.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by idea on 4/6 0006.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {


    private Context mContext;
    private List<String> listName;
    private LayoutInflater mInflater;
    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public MainAdapter(Context mContext, List<String> listName) {
        this.mContext = mContext;
        this.listName = listName;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainHolder(mInflater.inflate(R.layout.main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MainHolder holder, final int position) {
        holder.titleName.setText(listName.get(position));
        if (mOnItemClickLitener != null) {
            holder.titleName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listName == null ? 0 : listName.size();
    }

    static class MainHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title_name)
        TextView titleName;
        public MainHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
