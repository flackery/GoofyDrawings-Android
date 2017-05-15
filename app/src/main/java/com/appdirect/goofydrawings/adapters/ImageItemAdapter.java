package com.appdirect.goofydrawings.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appdirect.goofydrawings.R;
import com.appdirect.goofydrawings.api.ImageSearchResult;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mehlert on 2017-05-12.
 */

public class ImageItemAdapter extends RecyclerView.Adapter<ImageItemAdapter.ImageItemViewHolder> {


    private final Context mContext;
    private final OnItemClickListener clickListener;
    private List<ImageSearchResult.Item> items;

    public ImageItemAdapter(Context context, List<ImageSearchResult.Item> items, OnItemClickListener clickListener) {
        this.items = items;
        this.mContext = context;
        this.clickListener = clickListener;
    }

    @Override
    public ImageItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_image, viewGroup, false);

        ImageItemViewHolder viewHolder = new ImageItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ImageItemViewHolder itemViewHolder, final int position) {
        ImageSearchResult.Item item = items.get(position);

        Glide.with(mContext)
                .load(item.image.thumbnailLink)
                .fitCenter()
                .dontAnimate()
                .into(itemViewHolder.imageView);

        itemViewHolder.titleView.setText(item.title);
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClicked(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return (null != items ? items.size() : 0);
    }

    public void setData(List<ImageSearchResult.Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public ImageSearchResult.Item getItem(int position) {
        return items.get(position);
    }

    public class ImageItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image) protected ImageView imageView;
        @BindView(R.id.title) protected TextView titleView;

        public ImageItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onClicked(int position);
    }

}
