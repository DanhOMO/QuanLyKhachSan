/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
		return con;
	}
	
	public void connect() throws SQLException {
		try {
			String url = "jdbc:sqlserver://DANHOMO:1433;databasename=QuanLyKhachSan;encrypt=true;trustServerCertificate=true";
			String user = "danhomo";
			String pass = "danh1472004";
			con = DriverManager.getConnection(url,user,pass);
			System.out.println("Kết nối thành công");
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
