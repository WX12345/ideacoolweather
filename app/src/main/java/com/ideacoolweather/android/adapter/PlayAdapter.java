package com.ideacoolweather.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ideacoolweather.android.R;
import com.ideacoolweather.android.model.Girls;
import com.ideacoolweather.android.util.ImageLoaderUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by idea on 4/6 0006.
 */

public class PlayAdapter extends RecyclerView.Adapter<PlayAdapter.PlayHolder> {
    private Context mContext;
    private List<Girls.Girl> grilList;
    private LayoutInflater mInflater;
    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public PlayAdapter(Context mContext, List<Girls.Girl> grilList) {
        this.mContext = mContext;
        this.grilList = grilList;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public PlayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlayHolder(mInflater.inflate(R.layout.play_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final PlayHolder holder, final int position) {
        ImageLoaderUtils.showPictureWithApplication(mContext,grilList.get(position).url,holder.image);
        if (mOnItemClickLitener != null) {
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return grilList == null ? 0 : grilList.size();
    }

    static class PlayHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        public PlayHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
