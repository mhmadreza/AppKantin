package com.mr.kantin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mr.kantin.Model.Makanan.DataMakanan;
import com.mr.kantin.R;

import java.util.List;

public class AdapterMakanan extends RecyclerView.Adapter<AdapterMakanan.ViewHolder> {

    private Context context;
    private List<DataMakanan> dataMakanans;

    public AdapterMakanan(Context context, List<DataMakanan> dataMakanans) {
        this.context = context;
        this.dataMakanans = dataMakanans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_makanan, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMakanan.ViewHolder holder, int position) {

        DataMakanan dMkn = dataMakanans.get(position);

        holder.tvIdMkn.setText(String.valueOf(dMkn.getIdMkn()));
        holder.tvNamaMkn.setText(dMkn.getNamaMkn());
        holder.tvHargaMkn.setText(String.valueOf(dMkn.getHargaMkn()));

    }

    @Override
    public int getItemCount() {
        return dataMakanans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvIdMkn, tvNamaMkn, tvHargaMkn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdMkn = itemView.findViewById(R.id.tv_id_makanan);
            tvNamaMkn = itemView.findViewById(R.id.tv_nama_makanan);
            tvHargaMkn = itemView.findViewById(R.id.tv_harga_makanan);

        }
    }
}
