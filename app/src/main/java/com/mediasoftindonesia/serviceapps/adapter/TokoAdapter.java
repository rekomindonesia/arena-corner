package com.mediasoftindonesia.serviceapps.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mediasoftindonesia.serviceapps.R;
import com.mediasoftindonesia.serviceapps.activity.BookingActivity;
import com.mediasoftindonesia.serviceapps.activity.LapanganActivity;
import com.mediasoftindonesia.serviceapps.model.Store;

import java.util.List;

public class TokoAdapter extends RecyclerView.Adapter<TokoAdapter.MyViewHolder> {

    private List<Store> dataModelArrayList;

    public TokoAdapter(List<Store> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_toko_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Store postResponseStore = dataModelArrayList.get(position);

        holder.tvName.setText(postResponseStore.getKategoriName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LapanganActivity.class);
                intent.putExtra("kategori_id", postResponseStore.getIdKategori());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.text_view_toko_name);
        }
    }
}
