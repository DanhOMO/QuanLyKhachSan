/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.main;



import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatInspector;
import com.quanlykhachsan.model.ConnectDB;
import com.quanlykhachsan.view.DangNhap_GUI;
import com.quanlykhachsan.view.ThongKe_GUI;
import com.quanlykhachsan.view.TrangChu_GUI;
import java.awt.geom.FlatteningPathIterator;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 *
 * @author nguye
 */
public class Main {
       public static void main(String[] args) {
           JFrame test = new DangNhap_GUI();
           ConnectDB con = new ConnectDB(); 
             try {
            UIManager.setLookAndFeel(new FlatLightLaf()); // You can choose FlatLightLaf, FlatDarkLaf, etc.
        } catch (Exception e) {
            
            throw new IllegalArgumentException("Loi o main");
        }
           try {
               con.connect();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
           test.setVisible(true);
           test.setSize(1650, 800);
           test.pack();
           
    }
         
         
         
}   
