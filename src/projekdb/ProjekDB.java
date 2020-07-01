/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekdb;

import GUI.*;
import database.*;
import java.sql.SQLException;
import java.text.ParseException;

public class ProjekDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        //new GUI_transaksi().show();
        new GUI_login().show();
    }
    
}
