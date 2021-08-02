package com.mr.kantin.Api;

import com.mr.kantin.Model.Makanan.DataMakanan;
import com.mr.kantin.Model.Makanan.ResMakanan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    /* Data API ENDPOINT Makanan */
    @GET("makanan.php")
    Call<ResMakanan> makanan();

    /* Tambah Data Makanan */
    @FormUrlEncoded
    @POST("tambah.php")
    Call<ResMakanan> tambahMakanan(
            @Field("nama_mkn") String nama_mkn,
            @Field("harga_mkn") String harga_mkn
    );

}
