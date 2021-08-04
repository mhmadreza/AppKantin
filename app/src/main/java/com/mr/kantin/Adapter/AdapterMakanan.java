package com.mr.kantin.Adapter;

import android.app.Activity;
import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mr.kantin.Api.ApiConfig;
import com.mr.kantin.Api.ApiInterface;
import com.mr.kantin.Model.Makanan.DataMakanan;
import com.mr.kantin.Model.Makanan.ResMakanan;
import com.mr.kantin.R;
import com.mr.kantin.Ui.HomeActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterMakanan extends RecyclerView.Adapter<AdapterMakanan.ViewHolder> {

    private Context context;
    private List<DataMakanan> dataMakanans;
    private String idMakanan;
    private View dialog;
    private LayoutInflater liDialog;

    public AdapterMakanan(@NonNull Context context, List<DataMakanan> dataMakanans) {
        this.context = context;
        this.dataMakanans = dataMakanans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater v = LayoutInflater.from(parent.getContext());
        View vh = v.inflate(R.layout.item_makanan, parent, false);

        return new ViewHolder(vh);

//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.item_makanan, parent, false);
//        return new AdapterMakanan(view);
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
        private CardView cvDataMakanan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdMkn = itemView.findViewById(R.id.tv_id_makanan);
            tvNamaMkn = itemView.findViewById(R.id.tv_nama_makanan);
            tvHargaMkn = itemView.findViewById(R.id.tv_harga_makanan);
            cvDataMakanan = itemView.findViewById(R.id.cv_makanan);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(context);
                    dialogPesan.setMessage("Mau Ngapain Bro ?");
                    dialogPesan.setCancelable(true);

                    idMakanan = tvIdMkn.getText().toString();

                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            hapusData();
                            dialog.dismiss();
                            ((HomeActivity)context).setUIMakanan();
                        }
                    });

                    dialogPesan.setNegativeButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialogPesan.show();
                    return false;
                }
            });


        }

        private void hapusData(){
            ApiInterface dataMkn = ApiConfig.connectAPI().create(ApiInterface.class);
            Call<ResMakanan> hapusMakanan = dataMkn.hapusMakanan(idMakanan);

            hapusMakanan.enqueue(new Callback<ResMakanan>() {
                @Override
                public void onResponse(Call<ResMakanan> call, Response<ResMakanan> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getStatus();

                    Toast.makeText(context, "Kode : "+kode+"\nPesan : "+pesan, Toast.LENGTH_SHORT).show();
                    
                }

                @Override
                public void onFailure(Call<ResMakanan> call, Throwable t) {
                    Toast.makeText(context, "Gagal Konek ke server", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}
