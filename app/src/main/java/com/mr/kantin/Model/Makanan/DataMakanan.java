package com.mr.kantin.Model.Makanan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataMakanan {

    @SerializedName("id_mkn")
    private String idMkn;
    @SerializedName("nama_mkn")
    private String namaMkn;
    @SerializedName("harga_mkn")
    private String hargaMkn;

    public String getIdMkn() {
        return idMkn;
    }

    public void setIdMkn(String idMkn) {
        this.idMkn = idMkn;
    }

    public String getNamaMkn() {
        return namaMkn;
    }

    public void setNamaMkn(String namaMkn) {
        this.namaMkn = namaMkn;
    }

    public String getHargaMkn() {
        return hargaMkn;
    }

    public void setHargaMkn(String hargaMkn) {
        this.hargaMkn = hargaMkn;
    }

}