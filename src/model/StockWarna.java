package model;

/**
 *
 * @author BASER
 */
public class StockWarna {
    private Integer id_warna;
    private String warna;
    private Integer harga_warna;
    private JenisWarna jeniswarna;

    public Integer getId_warna() {
        return id_warna;
    }

    public void setId_warna(Integer id_warna) {
        this.id_warna = id_warna;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public Integer getHarga_warna() {
        return harga_warna;
    }

    public void setHarga_warna(Integer harga_warna) {
        this.harga_warna = harga_warna;
    }

    public JenisWarna getJeniswarna() {
        return jeniswarna;
    }

    public void setJeniswarna(JenisWarna jeniswarna) {
        this.jeniswarna = jeniswarna;
    }
    
    
}
