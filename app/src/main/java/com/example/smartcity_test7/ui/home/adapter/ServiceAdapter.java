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
import com.example.smartcity_test7.ui.home.pojo.ItemService;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    private List<ItemService> serviceList;

    public ServiceAdapter(List<ItemService> serviceList) {
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemService service = serviceList.get(position);
        holder.name_service.setText(service.getServiceName());
        if (service.getServiceName().equals("更多服务")){
            Glide.with(holder.rootView).load(R.drawable.ic_launcher_foreground).into(holder.img_service);
        }else {
            Glide.with(holder.rootView).load(service.getImgUrl()).into(holder.img_service);
        }
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView img_service;
        public TextView name_service;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_service = (ImageView) rootView.findViewById(R.id.img_service);
            this.name_service = (TextView) rootView.findViewById(R.id.name_service);
        }

    }
}
