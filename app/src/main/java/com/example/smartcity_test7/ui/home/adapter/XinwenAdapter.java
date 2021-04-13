package com.example.smartcity_test7.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_test7.R;
import com.example.smartcity_test7.ui.home.pojo.ItemData;

import java.util.List;

public class XinwenAdapter extends RecyclerView.Adapter<XinwenAdapter.ViewHolder> {
    private List<ItemData> dataList;

    public XinwenAdapter(List<ItemData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_itemdata, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemData data = dataList.get(position);
        holder.content_item.setText(data.getContent());
        holder.likeNum_item.setText(String.valueOf(data.getLikeNumber()));
        holder.viewNum.setText(String.valueOf(data.getViewsNumber()));
        holder.title_item.setText(data.getTitle());
        Glide.with(holder.rootView).load(data.getImgUrl()).into(holder.imageView2);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static
    class ViewHolder extends ServiceAdapter.ViewHolder {
        public View rootView;
        public TextView likeNum_item;
        public TextView viewNum;
        public TextView content_item;
        public TextView title_item;
        public ImageView imageView2;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.likeNum_item = (TextView) rootView.findViewById(R.id.likeNum_item);
            this.viewNum = (TextView) rootView.findViewById(R.id.viewNum);
            this.content_item = (TextView) rootView.findViewById(R.id.content_item);
            this.title_item = (TextView) rootView.findViewById(R.id.title_item);
            this.imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
        }

    }
}
