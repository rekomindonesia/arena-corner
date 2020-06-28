package com.mediasoftindonesia.serviceapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediasoftindonesia.serviceapps.R;
import com.mediasoftindonesia.serviceapps.activity.BookingActivity;
import com.mediasoftindonesia.serviceapps.activity.LapanganActivity;
import com.mediasoftindonesia.serviceapps.model.Lapangan;
import com.mediasoftindonesia.serviceapps.model.Store;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LapanganAdapter extends RecyclerView.Adapter<LapanganAdapter.MyViewHolder> {

    private List<Lapangan> dataModelArrayList;
    private Context context;

    public LapanganAdapter(List<Lapangan> dataModelArrayList, Context context) {
        this.dataModelArrayList = dataModelArrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_lapangan_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Lapangan postResponseStore = dataModelArrayList.get(position);

        holder.tvName.setText(postResponseStore.getNameStore());
        holder.tvAddress.setText(postResponseStore.getAlamat());
        holder.tvPrice.setText(String.valueOf(postResponseStore.getHarga()));

        Picasso.with(context)
                .load("http://172.20.10.3:80/images/" + postResponseStore.getImgStore())
                .into(holder.ivImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BookingActivity.class);
                intent.putExtra("id_store", postResponseStore.getIdStore());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvAddress, tvPrice;
        private ImageView ivImage;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.text_view_lapangan_name);
            tvAddress = itemView.findViewById(R.id.text_view_lapangan_address);
            tvPrice = itemView.findViewById(R.id.text_view_lapangan_price);
            ivImage = itemView.findViewById(R.id.image_view_lapangan);
        }
    }
}
