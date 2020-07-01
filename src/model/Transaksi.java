/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.*;


public class Transaksi {
    private Integer id_transaksi;
    private StockBahan stockBahan;
    private Integer total_harga;
    private Date tgl_transaksi;
    private Integer jumlah_bahan;
    private ArrayList<DetailTransaksi> arrdetail;

    public Integer getJumlah_bahan() {
        return jumlah_bahan;
    }

    public void setJumlah_bahan(Integer jumlah_bahan) {
        this.jumlah_bahan = jumlah_bahan;
    }
    
    public StockBahan getStockBahan() {
        return stockBahan;
    }

    public void setStockBahan(StockBahan stockBahan) {
        this.stockBahan = stockBahan;
    }

    
    public ArrayList<DetailTransaksi> getArrdetail() {
        return arrdetail;
    }

    public void setArrdetail(ArrayList<DetailTransaksi> arrdetail) {
        this.arrdetail = arrdetail;
    }
    
    
    
    public Integer getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(Integer id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public Integer getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(Integer total_harga) {
        this.total_harga = total_harga;
    }

    public Date getTgl_transaksi() {
        return tgl_transaksi;
    }

    public void setTgl_transaksi(Date tgl_transaksi) {
        this.tgl_transaksi = tgl_transaksi;
    }
    
                
}
