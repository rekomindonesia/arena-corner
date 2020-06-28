package com.mediasoftindonesia.serviceapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediasoftindonesia.serviceapps.R;
import com.mediasoftindonesia.serviceapps.activity.TokoActivity;
import com.mediasoftindonesia.serviceapps.model.PostResponseService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuServiceAdapter extends RecyclerView.Adapter<MenuServiceAdapter.MyViewHolder> {

    private List<PostResponseService> dataModelArrayList;
    private Context context;

    public MenuServiceAdapter(List<PostResponseService> dataModelArrayList, Context context) {
        this.dataModelArrayList = dataModelArrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_menu_service_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final PostResponseService postResponseData = dataModelArrayList.get(position);

        Picasso.with(context)
                .load("http://172.20.10.3:80/images/" + postResponseData.getImgKota())
                .into(holder.ivKota);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TokoActivity.class);
                intent.putExtra("kota_id", postResponseData.getIdKota());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivKota;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivKota = itemView.findViewById(R.id.image_view_service);
        }
    }

}
