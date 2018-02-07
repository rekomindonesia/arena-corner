package com.mediasoftindonesia.taniapps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediasoftindonesia.taniapps.api.ProdukJadi;

import java.util.List;

/**
 * Created by macair on 12/28/17.
 */

public class MainAdapter3 extends RecyclerView.Adapter<MainAdapter3.ViewHolder> {
    private List<ProdukJadi> mDataset;
    private Context context;
    public CardView cvMain;

    public MainAdapter3(Context context, List<ProdukJadi> mDataset) {
        this.mDataset = mDataset;
        this.context = context;
    }

    @Override
    public MainAdapter3.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MainAdapter3.ViewHolder vh2 = new MainAdapter3.ViewHolder(v);
        return vh2;
    }

    @Override
    public void onBindViewHolder(MainAdapter3.ViewHolder holder, int position) {

        final String nama1 = this.mDataset.get(position).getNama();
        final String harga1 = this.mDataset.get(position).getHarga();
        final String tanggal1 = this.mDataset.get(position).getTanggal();
        final String deskripsi1 = this.mDataset.get(position).getDeskripsi();

        holder.mTitle.setText(mDataset.get(position).getNama());
        if (mDataset.get(position).getStatus().equals("0")){
            holder.mStatus.setImageResource(R.drawable.kotak);
            //Toast.makeText(context, "Produk habis", Toast.LENGTH_LONG).show();

        }else {
            //holder.mStatus.setImageResource(R.mipmap.ic_launcher);
            holder.cvMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(context, PemesananActivity.class);

                    in.putExtra("nama", nama1);
                    in.putExtra("harga", harga1);
                    in.putExtra("tanggal", tanggal1);
                    in.putExtra("deskripsi", deskripsi1);

                    context.startActivity(in);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTitle;
        public CardView cvMain;
        public ImageView mStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            cvMain = (CardView) itemView.findViewById(R.id.cv_main);
            mStatus = (ImageView) itemView.findViewById(R.id.status);
        }
    }
}
