package com.mr.kantin.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mr.kantin.Api.ApiConfig;
import com.mr.kantin.Api.ApiInterface;
import com.mr.kantin.Model.Makanan.ResMakanan;
import com.mr.kantin.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {

    private EditText etNama, etHarga;
    private Button btnTambah;
    private String nama, harga;
    //private int harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Data Makanan");

        etNama = findViewById(R.id.et_nama_makanan);
        etHarga = findViewById(R.id.et_harga_makanan);
        btnTambah = findViewById(R.id.btn_tambah_makanan);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                harga = etHarga.getText().toString();

                if (nama.trim().equals("")){
                    etNama.setError("Isi Nama dengan benar");
                } else if (harga.trim().equals("")){
                    etHarga.setError("Isi Harga dengan benar");
                }else {
                    tambahData();
                }

            }
        });

    }

    private void tambahData(){
        ApiInterface makanan = ApiConfig.connectAPI().create(ApiInterface.class);
        Call<ResMakanan> tambahMakanan = makanan.tambahMakanan(nama, harga);

        tambahMakanan.enqueue(new Callback<ResMakanan>() {
            @Override
            public void onResponse(Call<ResMakanan> call, Response<ResMakanan> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getStatus();

                Toast.makeText(TambahActivity.this, "Kode : "+kode+ "\nPesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResMakanan> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}