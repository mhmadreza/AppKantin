package com.mr.kantin.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mr.kantin.Adapter.AdapterMakanan;
import com.mr.kantin.Api.ApiConfig;
import com.mr.kantin.Api.ApiInterface;
import com.mr.kantin.Model.Makanan.DataMakanan;
import com.mr.kantin.Model.Makanan.ResMakanan;
import com.mr.kantin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvDataMkn;
    private RecyclerView.Adapter adapterMakanan;
    private RecyclerView.LayoutManager lmMakanan;
    private List<DataMakanan> listMakanans = new ArrayList<>();

    private ProgressBar pbHome;
    private SwipeRefreshLayout srlHome;
    private FloatingActionButton fabHome;
    private ShimmerFrameLayout shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //pbHome = findViewById(R.id.pb_home);
        srlHome = findViewById(R.id.srl_home);

        //ShimmerFrameLayout container = findViewById(R.id.shimmer);
        //container.startShimmer(); // If auto-start is set to false

        shimmer = findViewById(R.id.shimmer);
        //shimmer.startShimmer();

        fabHome = findViewById(R.id.fab_home_tambah);
        rvDataMkn = findViewById(R.id.rv_makanan_home);
        rvDataMkn.setHasFixedSize(true);
        lmMakanan = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvDataMkn.setLayoutManager(lmMakanan);

        setUIMakanan();

        srlHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlHome.setRefreshing(true);
                setUIMakanan();
                srlHome.setRefreshing(false);
            }
        });

        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fabTambah = new Intent(HomeActivity.this, TambahActivity.class);
                startActivity(fabTambah);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUIMakanan();
    }

    public void setUIMakanan() {
        //shimmer.startShimmer();
        //pbHome.setVisibility(View.VISIBLE);
        //shimmer.startShimmer();
        /* GET data Makanan <DataMakanan> */
        ApiInterface makanan = ApiConfig.connectAPI().create(ApiInterface.class);
        Call<ResMakanan> tampilMakanan = makanan.makanan();

        tampilMakanan.enqueue(new Callback<ResMakanan>() {
            @Override
            public void onResponse(Call<ResMakanan> call, Response<ResMakanan> response) {

                int id = response.body().getKode();
                String status = response.body().getStatus();

                //Toast.makeText(HomeActivity.this, "Kode : "+id+"\nStatus : "+status, Toast.LENGTH_SHORT).show();

                listMakanans = response.body().getDataMkn();
                adapterMakanan = new AdapterMakanan(HomeActivity.this, listMakanans);
                rvDataMkn.setAdapter(adapterMakanan);
                shimmer.startShimmer();
                shimmer.setVisibility(View.GONE);
                rvDataMkn.setVisibility(View.VISIBLE);
                adapterMakanan.notifyDataSetChanged();

                //shimmer.stopShimmer();
                //pbHome.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<ResMakanan> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Gagal terhubung ke Server", Toast.LENGTH_SHORT).show();
                //pbHome.setVisibility(View.VISIBLE);
                shimmer.stopShimmer();
            }
        });

    }

}