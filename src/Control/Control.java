package Control;

import model.*;
import database.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.rsa.RSACore;

public class Control {
    Koneksi koneksi;
    ArrayList<StockBahan> arrStockBahan;
    ArrayList<StockWarna> arrStockWarna;
    ArrayList<JenisWarna> arrJenisWarna;
    ArrayList<Transaksi> arrTransaksi;

    public Control() throws SQLException{
        koneksi = new Koneksi();
        arrStockBahan = new ArrayList<>();
        arrStockWarna = new ArrayList<>();
        arrJenisWarna = new ArrayList<>();
        arrTransaksi = new ArrayList<>();
    }
    
    public ArrayList<StockBahan> getDataBahan() throws SQLException{
        arrStockBahan.clear();
        ResultSet rs = koneksi.GetData("SELECT * FROM STOCK_BAHAN");
        while(rs.next()){
            StockBahan SH = new StockBahan();
            SH.setId_bahan(rs.getInt("ID_BAHAN"));
            SH.setNama_bahan(rs.getString("NAMA_BAHAN"));
            SH.setStok(rs.getInt("STOK"));
            SH.setHarga_satuan(rs.getInt("HARGA_SATUAN"));
            
            arrStockBahan.add(SH);
        }
        return arrStockBahan;
    }
    
    public ArrayList<JenisWarna> getDataJenisWarna() throws SQLException{
        arrJenisWarna.clear();
        ResultSet rs = koneksi.GetData("SELECT * FROM JENIS_WARNA");
        while(rs.next()){
            JenisWarna jw = new JenisWarna();
            jw.setId_jenisWarna(rs.getInt("ID_JENIS_WARNA"));
            jw.setWarna_bahan(rs.getString("WARNA_BAHAN"));
            
            arrJenisWarna.add(jw);
        }
        
        return arrJenisWarna;
    }
    
    public ArrayList<StockWarna> getDataWarna() throws SQLException{
        arrStockWarna.clear();
        ResultSet rs = koneksi.GetData("SELECT * FROM STOCK_WARNA JOIN JENIS_WARNA ON STOCK_WARNA.ID_JENIS_WARNA = JENIS_WARNA.ID_JENIS_WARNA");
        while(rs.next()){
            JenisWarna jw = new JenisWarna();
            jw.setId_jenisWarna(rs.getInt("ID_JENIS_WARNA"));
            jw.setWarna_bahan(rs.getString("WARNA_BAHAN"));
            
            StockWarna SW = new StockWarna();
            SW.setId_warna(rs.getInt("ID_WARNA"));
            SW.setWarna(rs.getString("WARNA"));
            SW.setHarga_warna(rs.getInt("HARGA_WARNA"));
            SW.setJeniswarna(jw);
            
            arrStockWarna.add(SW);
        }
        return arrStockWarna;
    }
    public ArrayList<Transaksi> getDataTransaksi() throws SQLException{
        arrTransaksi.clear();
        ResultSet rs = koneksi.GetData("SELECT * FROM TRANSAKSI JOIN STOCK_BAHAN ON TRANSAKSI.ID_BAHAN = STOCK_BAHAN.ID_BAHAN");
            while(rs.next()){
                StockBahan SH = new StockBahan();
                SH.setId_bahan(rs.getInt("ID_BAHAN"));
                SH.setNama_bahan(rs.getString("NAMA_BAHAN"));
                SH.setStok(rs.getInt("STOK"));
                SH.setHarga_satuan(rs.getInt("HARGA_SATUAN"));
                
                Transaksi T = new Transaksi();
                T.setStockBahan(SH);
                T.setId_transaksi(rs.getInt("ID_TRANSAKSI"));
                T.setTgl_transaksi(rs.getDate("TGL_TRANSAKSI"));
                T.setTotal_harga(rs.getInt("TOTAL_HARGA"));
                T.setJumlah_bahan(rs.getInt("JUMLAH_BAHAN"));
                
                ResultSet rsdetail = koneksi.GetData("SELECT * FROM DETAIL_TRANSAKSI JOIN STOCK_WARNA ON DETAIL_TRANSAKSI.ID_WARNA = STOCK_WARNA.ID_WARNA JOIN JENIS_WARNA ON STOCK_WARNA.ID_JENIS_WARNA = JENIS_WARNA.ID_JENIS_WARNA WHERE DETAIL_TRANSAKSI.ID_TRANSAKSI = " +rs.getString("ID_TRANSAKSI"));
                ArrayList<DetailTransaksi> DT = new ArrayList<>();
                while(rsdetail.next()){
                    JenisWarna jw = new JenisWarna();
                    jw.setId_jenisWarna(rsdetail.getInt("ID_JENIS_WARNA"));
                    jw.setWarna_bahan(rsdetail.getString("WARNA_BAHAN"));

                    StockWarna SW = new StockWarna();
                    SW.setId_warna(rsdetail.getInt("ID_WARNA"));
                    SW.setWarna(rsdetail.getString("WARNA"));
                    SW.setHarga_warna(rsdetail.getInt("HARGA_WARNA"));
                    SW.setJeniswarna(jw);
                    
                    DetailTransaksi deta = new DetailTransaksi();
                    deta.setStockWarna(SW);
                    deta.setJumlah(rsdetail.getInt("JUMLAH_BAHAN"));
                    
                    DT.add(deta);
                }
                T.setArrdetail(DT);
                arrTransaksi.add(T);
            }
        return arrTransaksi;
    }
    
    public void insertTransaksi(Transaksi transaksi){
        try{
            String datetransaksi = new SimpleDateFormat("dd/MM/yyyy").format(transaksi.getTgl_transaksi());
            koneksi.ManipulasiData("INSERT INTO TRANSAKSI VALUES (ID_TRANSAKSI.NEXTVAL, TO_DATE('" +datetransaksi+ "','DD/MM/YYYY')," +transaksi.getTotal_harga()+ "," +transaksi.getStockBahan().getId_bahan()+ "," +transaksi.getJumlah_bahan()+ ")");
            ResultSet rs = koneksi.GetData("SELECT ID_TRANSAKSI.CURRVAL FROM DUAL");
            System.out.println(rs);
            rs.next();
            int id_transaksi = rs.getInt("CURRVAL");
            for(DetailTransaksi Dt : transaksi.getArrdetail()){
                koneksi.ManipulasiData("INSERT INTO DETAIL_TRANSAKSI VALUES (" +id_transaksi+ "," +Dt.getStockWarna().getId_warna()+ "," +Dt.getJumlah()+ ")");
            }
            koneksi.ManipulasiData("UPDATE STOCK_BAHAN SET STOK = STOK - " +transaksi.getJumlah_bahan()+ "WHERE ID_BAHAN = " +transaksi.getStockBahan().getId_bahan());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void insertJenisWarna(JenisWarna jenisWarna){
        try{
            koneksi.ManipulasiData("INSERT INTO JENIS_WARNA (ID_JENIS_WARNA, WARNA_BAHAN) VALUES (ID_JENIS_WARNA.NEXTVAL,'" +jenisWarna.getWarna_bahan()+ "')");
            ResultSet rs = koneksi.GetData("SELECT ID_JENIS_WARNA.CURRVAL FROM DUAL");
            System.out.println(rs);
            rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    public void updateJenisWarna(JenisWarna jenisWarna, String warna_bahan){
        koneksi.ManipulasiData("UPDATE JENIS_WARNA SET WARNA_BAHAN = '" +warna_bahan.toString()+ "' WHERE ID_JENIS_WARNA = " +jenisWarna.getId_jenisWarna());
    }
    public void deleteJenisWarna(JenisWarna jenisWarna){
        koneksi.ManipulasiData("DELETE JENIS_WARNA WHERE ID_JENIS_WARNA = " +jenisWarna.getId_jenisWarna());
    }
    
    public void insertStockWarna(StockWarna stockWarna){
        try {
            koneksi.ManipulasiData("INSERT INTO STOCK_WARNA (ID_WARNA, WARNA, HARGA_WARNA, ID_JENIS_WARNA) VALUES (ID_WARNA.NEXTVAL, '" +stockWarna.getWarna()+ "'," +stockWarna.getHarga_warna()+ "," +stockWarna.getJeniswarna().getId_jenisWarna()+ ")");
            ResultSet rs = koneksi.GetData("SELECT ID_WARNA.CURRVAL FROM DUAL");
            System.out.println(rs);
            rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateStockWarna(StockWarna stockWarna, String warna, int harga, int idjenis){
        koneksi.ManipulasiData("UPDATE STOCK_WARNA SET WARNA= '" +warna+ "', HARGA_WARNA = " +harga+ ", ID_JENIS_WARNA = " +idjenis+ "WHERE ID_WARNA = " +stockWarna.getJeniswarna().getId_jenisWarna());
    }
    public void deleteStockWarna(StockWarna stockWarna){
        koneksi.ManipulasiData("DELETE STOCK_WARNA WHERE ID_WARNA = " +stockWarna.getId_warna());
    }
    
    public void insertStockBahan(StockBahan stockBahan){
        try {
            koneksi.ManipulasiData("INSERT INTO STOCK_BAHAN (ID_BAHAN, NAMA_BAHAN, STOK, HARGA_SATUAN) VALUES (ID_BAHAN.NEXTVAL, '" +stockBahan.getNama_bahan()+ "'," +stockBahan.getStok()+ "," +stockBahan.getHarga_satuan()+ ")");
            ResultSet rs = koneksi.GetData("SELECT ID_BAHAN.CURRVAL FROM DUAL");
            System.out.println(rs);
            rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateStockBahan(StockBahan stockBahan, String bahan, int stok, int harga){
        koneksi.ManipulasiData("UPDATE STOCK_BAHAN SET NAMA_BAHAN ='" +bahan+ "', STOK = " +stok+ ", HARGA_SATUAN = " +harga+ "WHERE ID_BAHAN = " +stockBahan.getId_bahan());
    } 
    public void deleteStockBahan(StockBahan stockBahan){
        koneksi.ManipulasiData("DELETE STOCK_BAHAN WHERE ID_BAHAN = " +stockBahan.getId_bahan());
    }
}
