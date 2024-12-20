/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class ConnectDB {
  	public static void main(String[] args) throws SQLException {
		System.out.println("hello");
		ConnectDB a = new ConnectDB();
		a.connect();
                
	}
	public static Connection con = null;
	public static ConnectDB instance = new ConnectDB();
	
	public static ConnectDB getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
	
              try {
                  if (con == null || con.isClosed()) {
                      try {
                          connect();
                      } catch (SQLException ex) {
                          Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
                  
              } catch (SQLException ex) {
                  Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
              }
                  return con;
	}
	
	public void connect() throws SQLException {
		try {
			String url = "jdbc:sqlserver://localhost:1433;databasename=QuanLyKhachSan;encrypt=true;trustServerCertificate=true";
			String user = "sa";
			String pass = "sapassword";
			con = DriverManager.getConnection(url,user,pass);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void disconnect() {
		if(con!=null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
