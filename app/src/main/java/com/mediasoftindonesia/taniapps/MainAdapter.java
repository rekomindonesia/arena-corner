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

import com.mediasoftindonesia.taniapps.api.Produk;

import java.util.List;

/**
 * Created by macair on 12/15/17.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Produk> mDataset;
    private Context context;

    public MainAdapter(Context context, List<Produk> mDataset) {
        this.mDataset = mDataset;
        this.context = context;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {

        final String nama1 = this.mDataset.get(position).getNama();
        final String harga1 = this.mDataset.get(position).getHarga();
        final String tanggal1 = this.mDataset.get(position).getTanggal();
        final String deskripsi1 = this.mDataset.get(position).getDeskripsi();
        final String status1 = this.mDataset.get(position).getStatus();

        holder.mTitle.setText(mDataset.get(position).getNama());
        if (mDataset.get(position).getStatus().equals("0")){
            //Toast.makeText(context, "Produk habis", Toast.LENGTH_LONG).show();
            holder.mStatus.setImageResource(R.drawable.kotak);

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

        /*if(kumpBooking[position].getStatus().equals("0")){
            txtstatus.setText("Dalam proses");
            txtcek.setText("Selesaikan...");
            img_cek.setImageResource(R.drawable.loader);
        }else{
            txtstatus.setText("Selesai");
            img_cek.setImageResource(R.drawable.ceklis);
        }*/

        /*Picasso.with(this.context)
                .load(this.mDataset.get(position).getFoto())
                .resize(50 ,50)
                .noFade()
                .into(holder.mImage);*/

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTitle;
        public CardView cvMain;
        public ImageView mImage, mStatus;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.title);
            cvMain = (CardView) itemView.findViewById(R.id.cv_main);
            mImage = (ImageView) itemView.findViewById(R.id.imageCard);
            mStatus = (ImageView) itemView.findViewById(R.id.status);
        }
    }
}
