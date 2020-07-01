/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import model.JenisWarna;
import Control.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.StockWarna;
import sun.net.dns.ResolverConfiguration;

/**
 *
 * @author BASER
 */
public class GUI_stockWarna extends javax.swing.JFrame {

    private Control control;
    private String pilih;
    private Integer idjen;
    
    public GUI_stockWarna() throws SQLException {
        initComponents();
        control = new Control();
        pilih="";
        idjen=0;
        showTBLStockWarna();
    }
    
    public void showTBLStockWarna() throws SQLException{
        DefaultTableModel tblSW = new DefaultTableModel(new String[]{"Warna", "Harga Warna", "Jenis Warna"},0);
        for(StockWarna sw : control.getDataWarna()){
            tblSW.addRow(new String[]{sw.getWarna().toString(), sw.getHarga_warna().toString(), sw.getJeniswarna().getWarna_bahan().toString()});
        }
        TBLstockwarna.setModel(tblSW);
        
    }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBLstockwarna = new javax.swing.JTable();
        BTNinsert = new javax.swing.JButton();
        BTNupdate = new javax.swing.JButton();
        BTNdelete = new javax.swing.JButton();
        BTNkembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("STOCK WARNA");

        TBLstockwarna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TBLstockwarna);

        BTNinsert.setText("Insert");
        BTNinsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNinsertActionPerformed(evt);
            }
        });

        BTNupdate.setText("Update");
        BTNupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNupdateActionPerformed(evt);
            }
        });

        BTNdelete.setText("Delete");
        BTNdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNdeleteActionPerformed(evt);
            }
        });

        BTNkembali.setText("Kembali");
        BTNkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNkembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(BTNinsert)
                        .addGap(39, 39, 39)
                        .addComponent(BTNupdate)
                        .addGap(43, 43, 43)
                        .addComponent(BTNdelete)
                        .addGap(33, 33, 33)
                        .addComponent(BTNkembali)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNinsert)
                    .addComponent(BTNupdate)
                    .addComponent(BTNdelete)
                    .addComponent(BTNkembali))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(526, 496));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BTNinsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNinsertActionPerformed
        StockWarna SW = new StockWarna();
        try {
            JComboBox cb = new JComboBox();
            for(JenisWarna jw : control.getDataJenisWarna()){
                cb.addItem(jw.getWarna_bahan());
            }
            int select = JOptionPane.showConfirmDialog(this,cb,"pilih jenis",JOptionPane.DEFAULT_OPTION);
            if(select == JOptionPane.OK_OPTION){
                pilih = (String)cb.getSelectedItem();
            }
            for(JenisWarna j : control.getDataJenisWarna()){
                if(j.getWarna_bahan().toString().equals(pilih)){
                    idjen = j.getId_jenisWarna();
                }
            }
            SW.setJeniswarna(control.getDataJenisWarna().get(idjen));
            SW.setWarna(JOptionPane.showInputDialog(this,"Warna"));
            SW.setHarga_warna(Integer.parseInt(JOptionPane.showInputDialog(this, "Harga Warna")));
            control.insertStockWarna(SW);
            showTBLStockWarna();
            idjen=0;
            pilih="";
            
            } catch (SQLException ex) {
                    Logger.getLogger(GUI_stockWarna.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }//GEN-LAST:event_BTNinsertActionPerformed

    private void BTNupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNupdateActionPerformed
        try {
            StockWarna stockWarna = control.getDataWarna().get(TBLstockwarna.getSelectedRow());
            JComboBox cb = new JComboBox();
            for(JenisWarna jw : control.getDataJenisWarna()){
                cb.addItem(jw.getWarna_bahan());
            }
            int select = JOptionPane.showConfirmDialog(this,cb,"pilih jenis",JOptionPane.DEFAULT_OPTION);
            if(select == JOptionPane.OK_OPTION){
                pilih = (String)cb.getSelectedItem();
            }
            for(JenisWarna j : control.getDataJenisWarna()){
                if(j.getWarna_bahan().toString().equals(pilih)){
                    idjen = j.getId_jenisWarna();
                }
            }
            int id = idjen;
            String warna = JOptionPane.showInputDialog("Warna");
            int harga = Integer.parseInt(JOptionPane.showInputDialog("Harga"));
            
            control.updateStockWarna(stockWarna, warna, harga, id);
            showTBLStockWarna();
            idjen=0;
            pilih="";
            
            } catch (SQLException ex) {
                    Logger.getLogger(GUI_stockWarna.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }//GEN-LAST:event_BTNupdateActionPerformed

    private void BTNdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNdeleteActionPerformed
        try {
            StockWarna stockWarna = control.getDataWarna().get(TBLstockwarna.getSelectedRow());
            control.deleteStockWarna(stockWarna);
            showTBLStockWarna();
        } catch (SQLException ex) {
            Logger.getLogger(GUI_stockWarna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BTNdeleteActionPerformed

    private void BTNkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNkembaliActionPerformed
        new GUI_menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_BTNkembaliActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_stockWarna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_stockWarna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_stockWarna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_stockWarna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new GUI_stockWarna().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNdelete;
    private javax.swing.JButton BTNinsert;
    private javax.swing.JButton BTNkembali;
    private javax.swing.JButton BTNupdate;
    private javax.swing.JTable TBLstockwarna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
