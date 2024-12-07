/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.entity.KhachHang;
import com.quanlykhachsan.entity.KhuVuc;
import com.quanlykhachsan.entity.LoaiPhong;
import com.quanlykhachsan.entity.Phong;
import com.quanlykhachsan.enum_Class.TrangThaiPhong;

import com.quanlykhachsan.model.ConnectDB;
import com.quanlykhachsan.entity.KhuVuc;
import com.quanlykhachsan.entity.LoaiPhong;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class Phong_DAO {
        private List<Phong> list = new ArrayList<>();
        private ArrayList<Phong> dsPhong;
        public Phong_DAO(){
            docTuBang();
        }
    private Connection con = null;
          public List<Phong> getList(){
           return list;
       }
          
            public void setList(List<Phong> list) {
			this.list = list;
		}

			public boolean themPhong(Phong voucher){
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Phong VALUES(?,?,? , ? , ?)");
            ps.setString(1, voucher.getMaPhong());
            ps.setString(2, voucher.getTenPhong());
            ps.setString(3, voucher.getTrangThai().getTrangThaiPhong());
            ps.setString(4, voucher.getLoaiPhong().getMaLoaiPhong());
            ps.setString(5, voucher.getKhuVuc().getMaKhuVuc());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
      
       
                        
       public boolean capNhatPhong(Phong a) throws SQLException{
           
           try {
                    con = ConnectDB.getInstance().getConnection();
                System.out.println(a);
                String sql = "update  Phong set tenPhong = ? ,trangThaiPhong = ?, maLoaiPhong = ?, maKhuVuc = ? "
                        + "where maPhong = ?";
                PreparedStatement state = con.prepareStatement(sql);
                state.setString(1, a.getTenPhong());
                state.setString(2,a.getTrangThai().getTrangThaiPhong());
                state.setString(3, a.getLoaiPhong().getMaLoaiPhong());
                state.setString(4, a.getKhuVuc().getMaKhuVuc());
                state.setString(5, a.getMaPhong());
                state.executeUpdate();
                 state.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
            return true;
       }
       public DefaultTableModel docDuLieuVaoBan() {
    // Thêm tên cột vào DefaultTableModel
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Phòng", "Tên Phòng", "Loại Phòng", "Khu Vực", "Trạng Thái Phòng"}, 0);
    
    // Thêm dữ liệu vào DefaultTableModel
          list.stream().forEach(x -> {
        dtm.addRow(new Object[]{
            x.getMaPhong(), x.getTenPhong(), x.getLoaiPhong().getMaLoaiPhong(), x.getKhuVuc().getMaKhuVuc(), x.getTrangThai().getTrangThaiPhong()
        });
    });
    
    return dtm;
}

        public Phong timTheoMa(String ma){
            return list.stream().filter(x -> x.getMaPhong().equalsIgnoreCase(ma)).findFirst().orElse(null);
        }
       public void docTuBang(){
           list.clear();
           con =  ConnectDB.getInstance().getConnection();
           String sql = "select * from Phong";

           
           try {
               PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Phong phong = new Phong(rs.getString("maPhong"),
                           rs.getString("tenPhong"),
                           TrangThaiPhong.setTrangThaiPhong(rs.getString("trangThaiPhong")),
                           new LoaiPhong(rs.getString("maLoaiPhong")),
                           new KhuVuc(rs.getString("maKhuVuc")));
                   list.add(phong);
               }
             rs.close();
             ps.close();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
        
       }
       public void timPhongTheoLoaiPhong(String ma) {
    	List<Phong> temp = list.stream()
            .filter(x -> x.getLoaiPhong().getMaLoaiPhong().equals(ma))
            .collect(Collectors.toList());
    	list.clear();
    	list.addAll(temp);
       }
       public List<Phong> TimPhongTheoThoiGianCheckInCheckOut1(java.sql.Date checkIn, java.sql.Date checkOut) {
    	    List<Phong> dsPhong = new ArrayList<>();
    	    // Đảm bảo câu SQL được bao trong dấu ngoặc kép
    	    String sql = "SELECT p.* "
    	               + "FROM Phong p "
    	               + "JOIN ChiTietHoaDon ct ON ct.maPhong = p.maPhong "
    	               + "JOIN HoaDon hd ON hd.maHoaDon = ct.maHoaDon "
    	               + "WHERE NOT ("
    	               + "    (? BETWEEN hd.checkIN AND hd.checkOut) "
    	               + "    OR (? BETWEEN hd.checkIN AND hd.checkOut) "
    	               + ")";

    	    try (Connection con = ConnectDB.getInstance().getConnection();
    	         PreparedStatement ps = con.prepareStatement(sql)) {

    	        // Thiết lập các giá trị tham số cho PreparedStatement
    	        ps.setDate(1, checkIn);
    	        ps.setDate(2, checkOut);

    	        // Thực thi truy vấn
    	        try (ResultSet rs = ps.executeQuery()) {
    	            // Duyệt kết quả và thêm vào danh sách
    	            while (rs.next()) {
    	                Phong phong = new Phong(
    	                    rs.getString("maPhong"),
    	                    rs.getString("tenPhong"),
    	                    TrangThaiPhong.setTrangThaiPhong(rs.getString("trangThaiPhong")),
    	                    new LoaiPhong(rs.getString("maLoaiPhong")),
    	                    new KhuVuc(rs.getString("maKhuVuc"))
    	                );
    	                dsPhong.add(phong);
    	            }
    	        } catch (SQLException ex) {
    	            ex.printStackTrace();
    	        }
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	    }

    	    return dsPhong;
    	}

       public List<Phong> TimPhongTheoThoiGianCheckInCheckOut2(java.sql.Date checkIn, java.sql.Date checkOut) {
   	    List<Phong> dsPhong = new ArrayList<>();
   	    // Đảm bảo câu SQL được bao trong dấu ngoặc kép
   	    String sql = "SELECT p.* "
   	               + "FROM Phong p "
   	               + "JOIN ChiTietHoaDon ct ON ct.maPhong = p.maPhong "
   	               + "JOIN HoaDon hd ON hd.maHoaDon = ct.maHoaDon "
   	               + "WHERE (GETDATE() BETWEEN  hd.checkIN AND hd.checkOut) and "
   	               + "    ((? BETWEEN hd.checkIN AND hd.checkOut) "
	               + "    OR (? BETWEEN hd.checkIN AND hd.checkOut)) ";
//	               + "and (GETDATE() BETWEEN hd.checkIN AND hd.checkOut) ";

   	    try (Connection con = ConnectDB.getInstance().getConnection();
   	         PreparedStatement ps = con.prepareStatement(sql)) {

   	        // Thiết lập các giá trị tham số cho PreparedStatement
   	        ps.setDate(1, checkIn);
   	        ps.setDate(2, checkOut);
   	        // Thực thi truy vấn
   	        try (ResultSet rs = ps.executeQuery()) {
   	            // Duyệt kết quả và thêm vào danh sách
   	            while (rs.next()) {
   	                Phong phong = new Phong(
   	                    rs.getString("maPhong"),
   	                    rs.getString("tenPhong"),
   	                    TrangThaiPhong.setTrangThaiPhong(rs.getString("trangThaiPhong")),
   	                    new LoaiPhong(rs.getString("maLoaiPhong")),
   	                    new KhuVuc(rs.getString("maKhuVuc"))
   	                );
   	                dsPhong.add(phong);
   	            }
   	        } catch (SQLException ex) {
   	            ex.printStackTrace();
   	        }
   	    } catch (SQLException ex) {
   	        ex.printStackTrace();
   	    }

   	    return dsPhong;
   	}
       
       public List<Phong> TimPhongTheoThoiGianCheckInCheckOut3(java.sql.Date checkIn, java.sql.Date checkOut) {
      	    List<Phong> dsPhong = new ArrayList<>();
      	    // Đảm bảo câu SQL được bao trong dấu ngoặc kép
      	  String sql = "SELECT p.* "
  	               + "FROM Phong p "
  	               + "JOIN ChiTietHoaDon ct ON ct.maPhong = p.maPhong "
  	               + "JOIN HoaDon hd ON hd.maHoaDon = ct.maHoaDon "
  	               + "WHERE hd.checkIN >= GETDATE() and ("
  	               + "    (? BETWEEN hd.checkIN AND hd.checkOut) "
	               + "    OR (? BETWEEN hd.checkIN AND hd.checkOut) "
	               + ")";

  	    try (Connection con = ConnectDB.getInstance().getConnection();
  	         PreparedStatement ps = con.prepareStatement(sql)) {

  	        // Thiết lập các giá trị tham số cho PreparedStatement
  	        ps.setDate(1, checkIn);
  	        ps.setDate(2, checkOut);
      	        try (ResultSet rs = ps.executeQuery()) {
      	            // Duyệt kết quả và thêm vào danh sách
      	            while (rs.next()) {
      	                Phong phong = new Phong(
      	                    rs.getString("maPhong"),
      	                    rs.getString("tenPhong"),
      	                    TrangThaiPhong.setTrangThaiPhong(rs.getString("trangThaiPhong")),
      	                    new LoaiPhong(rs.getString("maLoaiPhong")),
      	                    new KhuVuc(rs.getString("maKhuVuc"))
      	                );
      	                dsPhong.add(phong);
      	            }
      	        } catch (SQLException ex) {
      	            ex.printStackTrace();
      	        }
      	    } catch (SQLException ex) {
      	        ex.printStackTrace();
      	    }

      	    return dsPhong;
      	}
       
       public List<Phong> timPhongTheoSoLuongNguoi(int soLuong) {
    	    // Xóa danh sách cũ trước khi thêm kết quả mới
    	    List<Phong> dsPhong = new ArrayList<Phong>();

    	    // Câu lệnh SQL với điều kiện lọc số lượng người
    	    String sql = "SELECT p.* FROM Phong p "
    	               + "JOIN LoaiPhong lp ON lp.maLoaiPhong = p.maLoaiPhong "
    	               + "WHERE lp.soLuongNguoi >= ?";

    	    try {
    	        // Kết nối cơ sở dữ liệu
    	        con = ConnectDB.getInstance().getConnection();
    	        PreparedStatement ps = con.prepareStatement(sql);
    	        
    	        // Thiết lập giá trị tham số (số lượng người)
    	        ps.setInt(1, soLuong);

    	        // Thực thi truy vấn
    	        ResultSet rs = ps.executeQuery();

    	        // Duyệt kết quả và thêm vào danh sách
    	        while (rs.next()) {
    	            Phong phong = new Phong(
    	                rs.getString("maPhong"),
    	                rs.getString("tenPhong"),
    	                TrangThaiPhong.setTrangThaiPhong(rs.getString("trangThaiPhong")),
    	                new LoaiPhong(rs.getString("maLoaiPhong")),
    	                new KhuVuc(rs.getString("maKhuVuc"))
    	            );
    	            dsPhong.add(phong);
    	        }
    	       
    	        // Đóng kết nối và result set
    	        rs.close();
    	        ps.close();
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	    }
			return dsPhong;
    	}

       public void timPhongTheoKhuVuc(KhuVuc kv) {
       	List<Phong> temp;
       	temp = list.stream()
               .filter(x -> x.getKhuVuc().equals(kv))
               .toList();
       	list.clear();
       	list.addAll(temp);
       }
       
public List<Phong> loadData1() {
    List<Phong> roomList = new ArrayList<>();
    String query = "SELECT p.maPhong, p.tenPhong, p.trangThaiPhong, lp.tenLoaiPhong, lp.moTa, lp.soLuongNguoi, lp.giaThuePhong " +
                   "FROM Phong p JOIN LoaiPhong lp ON lp.maLoaiPhong = p.maLoaiPhong";

    try (PreparedStatement ps = ConnectDB.getInstance().getConnection().prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            // Debug dữ liệu từ cơ sở dữ liệu
            System.out.println("Debug SQL: ");
            System.out.println("maPhong: " + rs.getString("maPhong"));
            System.out.println("tenPhong: " + rs.getString("tenPhong"));
            System.out.println("trangThaiPhong: " + rs.getString("trangThaiPhong"));
            System.out.println("tenLoaiPhong: " + rs.getString("tenLoaiPhong"));
            System.out.println("moTa: " + rs.getString("moTa"));
            System.out.println("giaThuePhong: " + rs.getDouble("giaThuePhong"));
            System.out.println("soLuongNguoi: " + rs.getInt("soLuongNguoi"));

            Phong phong = new Phong();
            phong.setMaPhong(rs.getString("maPhong"));
            phong.setTenPhong(rs.getString("tenPhong"));
            phong.setTrangThai(rs.getString("trangThaiPhong").equals("TRONG") ? TrangThaiPhong.TRONG : TrangThaiPhong.DA_COC);

            LoaiPhong loaiPhong = new LoaiPhong();
            loaiPhong.setTenLoaiPhong(rs.getString("tenLoaiPhong"));
            loaiPhong.setMoTa(rs.getString("moTa"));
            loaiPhong.setSoLuongNguoi(rs.getInt("soLuongNguoi"));
            loaiPhong.setGiaThuePhong(rs.getDouble("giaThuePhong"));

            // Debug thông tin LoaiPhong
            System.out.println("LoaiPhong debug info:");
            System.out.println("TenLoaiPhong: " + loaiPhong.getTenLoaiPhong());
            System.out.println("SoLuongNguoi: " + loaiPhong.getSoLuongNguoi());
            System.out.println("GiaThuePhong: " + loaiPhong.getGiaThuePhong());

            phong.setLoaiPhong(loaiPhong);

            roomList.add(phong);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return roomList;
}






       
    


       
    public Phong timPhong(String maHoaDon){
        try {
            PreparedStatement ps = ConnectDB.getInstance().getConnection().prepareStatement("select  maPhong from ChiTietHoaDon\r\n" + //
                                "where maHoaDon=?");
            ps.setString(1, maHoaDon);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Phong p = new Phong();
                Phong_DAO phongDao = new Phong_DAO();
                p = phongDao.timPhongTheoMa(rs.getString("maPhong"));
                return p;
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
    public Phong timPhongTheoMa(String maPhong){
        try {
            PreparedStatement ps = ConnectDB.getInstance().getConnection().prepareStatement("select * from Phong where maPhong = ?");
            ps.setString(1, maPhong);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Phong p = new Phong();
                p.setMaPhong(rs.getString("maPhong"));
                p.setTenPhong(rs.getString("tenPhong"));
                p.setTrangThai(TrangThaiPhong.TRONG);
                LoaiPhong_DAO lp_dao = new LoaiPhong_DAO();
                LoaiPhong lp = lp_dao.timTheoMa02(rs.getString("maLoaiPhong"));
                p.setLoaiPhong(lp);
                p.setKhuVuc(new KhuVuc(rs.getString("maKhuVuc")));
                return p;
            }
        } catch (Exception e) {
           e.printStackTrace();
        
        }
        return null;
    }
    public boolean doiPhong(String maPhongCu, Phong phongMoi) throws SQLException {
    Connection con = null;
    PreparedStatement statePhong = null;
    PreparedStatement stateGiaThuePhong = null;

    try {
        con = ConnectDB.getInstance().getConnection();

        // Cập nhật thông tin phòng
        String sqlPhong = "UPDATE Phong "
                + "SET tenPhong = ?, trangThaiPhong = ?, maLoaiPhong = ?, maKhuVuc = ? "
                + "WHERE maPhong = ?";
        statePhong = con.prepareStatement(sqlPhong);
        statePhong.setString(1, phongMoi.getTenPhong());
        statePhong.setString(2, phongMoi.getTrangThai().getTrangThaiPhong());
        statePhong.setString(3, phongMoi.getLoaiPhong().getMaLoaiPhong());
        statePhong.setString(4, phongMoi.getKhuVuc().getMaKhuVuc());
        statePhong.setString(5, maPhongCu);

        int rowsAffectedPhong = statePhong.executeUpdate();

        // Nếu thông tin phòng được cập nhật thành công, tiếp tục cập nhật giá thuê phòng
        if (rowsAffectedPhong > 0) {
            // Kiểm tra nếu loại phòng đã thay đổi thì cập nhật giá thuê phòng
            if (!phongMoi.getLoaiPhong().getMaLoaiPhong().equals(getLoaiPhongFromCurrentRoom(maPhongCu))) {
                // Cập nhật giá thuê phòng
                String sqlGiaThuePhong = "UPDATE Phong p "
                        + "JOIN LoaiPhong lp ON lp.maLoaiPhong = p.maLoaiPhong "
                        + "SET p.giaThuePhong = lp.giaThuePhong "
                        + "WHERE p.maPhong = ?";
                stateGiaThuePhong = con.prepareStatement(sqlGiaThuePhong);
                stateGiaThuePhong.setString(1, maPhongCu);
                stateGiaThuePhong.executeUpdate();
            }
        }

        return true;  // Nếu mọi thứ đều ổn, trả về true
    } catch (SQLException e) {
        e.printStackTrace();
        return false;  // Nếu có lỗi xảy ra, trả về false
    } finally {
        if (statePhong != null) statePhong.close();
        if (stateGiaThuePhong != null) stateGiaThuePhong.close();
        if (con != null) con.close();
    }
}

// Giả định rằng phương thức này sẽ lấy mã loại phòng hiện tại của phòng theo maPhongCu
private String getLoaiPhongFromCurrentRoom(String maPhong) throws SQLException {
    Connection con = null;
    PreparedStatement state = null;
    ResultSet rs = null;
    String maLoaiPhong = null;

    try {
        con = ConnectDB.getInstance().getConnection();
        String sql = "SELECT maLoaiPhong FROM Phong WHERE maPhong = ?";
        state = con.prepareStatement(sql);
        state.setString(1, maPhong);
        rs = state.executeQuery();

        if (rs.next()) {
            maLoaiPhong = rs.getString("maLoaiPhong");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (rs != null) rs.close();
        if (state != null) state.close();
        if (con != null) con.close();
    }

    return maLoaiPhong;
}
//  public boolean doiMaPhong(String maPhongCu, String maPhongMoi) {
//        String sql = "UPDATE ChiTietHoaDon SET maPhong = ? WHERE maPhong = ?";
//        
//        try {
//            con = ConnectDB.getInstance().getConnection(); // Khởi tạo kết nối
//            PreparedStatement ps = con.prepareStatement(sql);
//            
//            // Set giá trị cho các tham số trong câu lệnh SQL
//            ps.setString(1, maPhongMoi);
//            ps.setString(2, maPhongCu);
//
//            // Thực thi câu lệnh và kiểm tra kết quả
//            int rowsAffected = ps.executeUpdate();
//            return rowsAffected > 0; // Trả về true nếu thêm thành công
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Phong_DAO.class.getName()).log(Level.SEVERE, "Error updating LichSuDatPhong table", ex);
//        } 
//        return false; // Trả về false nếu xảy ra lỗi
//    }
  
  public boolean doiMaPhong(String maPhongCu, String maPhongMoi) {
    Connection con = null;
    PreparedStatement psUpdateMaPhong = null;

    String sqlUpdateMaPhong = "UPDATE ChiTietHoaDon SET maPhong = ? WHERE maPhong = ?";
    try {
        con = ConnectDB.getInstance().getConnection(); // Khởi tạo kết nối
        con.setAutoCommit(false); // Bắt đầu giao dịch

        // Đổi mã phòng
        psUpdateMaPhong = con.prepareStatement(sqlUpdateMaPhong);
        psUpdateMaPhong.setString(1, maPhongMoi);
        psUpdateMaPhong.setString(2, maPhongCu);
        int rowsAffected = psUpdateMaPhong.executeUpdate();

        if (rowsAffected > 0) {
            con.commit(); // Xác nhận giao dịch nếu thành công
            return true;
        } else {
            con.rollback(); // Hủy giao dịch nếu không có dòng nào bị ảnh hưởng
        }
    } catch (SQLException ex) {
        if (con != null) {
            try {
                con.rollback(); // Hủy giao dịch nếu có lỗi
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
        Logger.getLogger(Phong_DAO.class.getName()).log(Level.SEVERE, "Error updating ChiTietHoaDon table", ex);
    } finally {
        try {
            if (psUpdateMaPhong != null) psUpdateMaPhong.close();
            if (con != null) con.setAutoCommit(true); // Bật lại AutoCommit
        } catch (SQLException closeEx) {
            closeEx.printStackTrace();
        }
    }
    return false;
}


public Phong timTheoMa1(String maPhong) {
    String query = "SELECT maPhong, tenPhong, trangThaiPhong, lp.tenLoaiPhong, lp.giaThuePhong, lp.soLuongNguoi " +
                   "FROM Phong p JOIN LoaiPhong lp ON lp.maLoaiPhong = p.maLoaiPhong " +
                   "WHERE p.maPhong = ?";
    try (PreparedStatement ps = ConnectDB.getInstance().getConnection().prepareStatement(query)) {
        ps.setString(1, maPhong);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Phong phong = new Phong();
            phong.setMaPhong(rs.getString("maPhong"));
            phong.setTenPhong(rs.getString("tenPhong"));
            phong.setTrangThai(rs.getString("trangThaiPhong").equals("TRONG") ? TrangThaiPhong.TRONG : TrangThaiPhong.DA_COC);

            LoaiPhong loaiPhong = new LoaiPhong();
            loaiPhong.setTenLoaiPhong(rs.getString("tenLoaiPhong"));
            loaiPhong.setGiaThuePhong(rs.getDouble("giaThuePhong"));
            loaiPhong.setSoLuongNguoi(rs.getInt("soLuongNguoi"));
            phong.setLoaiPhong(loaiPhong);

            return phong;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null; // Return null if no data found
}




}
