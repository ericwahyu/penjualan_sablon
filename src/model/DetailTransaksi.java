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
public class DetailTransaksi {
    private StockWarna stockWarna;
    private Integer Jumlah;

    public StockWarna getStockWarna() {
        return stockWarna;
    }

    public void setStockWarna(StockWarna stockWarna) {
        this.stockWarna = stockWarna;
    }

    public Integer getJumlah() {
        return Jumlah;
    }

    public void setJumlah(Integer Jumlah) {
        this.Jumlah = Jumlah;
    }
    
}
