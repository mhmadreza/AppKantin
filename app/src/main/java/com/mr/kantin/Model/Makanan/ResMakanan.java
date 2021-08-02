package com.mr.kantin.Model.Makanan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResMakanan {

    @SerializedName("kode")
    private Integer kode;
    @SerializedName("status")
    private String status;
    @SerializedName("dataMkn")
    private List<DataMakanan> dataMkn = null;

    public Integer getKode() {
        return kode;
    }

    public void setKode(Integer kode) {
        this.kode = kode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataMakanan> getDataMkn() {
        return dataMkn;
    }

    public void setDataMkn(List<DataMakanan> dataMkn) {
        this.dataMkn = dataMkn;
    }

}