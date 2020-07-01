/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author BASER
 */
public class StockBahan {
    private Integer id_bahan;
    private String nama_bahan;
    private Integer stok;
    private Integer harga_satuan;

    public Integer getHarga_satuan() {
        return harga_satuan;
    }

    public void setHarga_satuan(Integer harga_satuan) {
        this.harga_satuan = harga_satuan;
    }

    public Integer getId_bahan() {
        return id_bahan;
    }

    public void setId_bahan(Integer id_bahan) {
        this.id_bahan = id_bahan;
    }

    public String getNama_bahan() {
        return nama_bahan;
    }

    public void setNama_bahan(String nama_bahan) {
        this.nama_bahan = nama_bahan;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    
    
    
}
