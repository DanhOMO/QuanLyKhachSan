package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.CaLamViec;
import com.quanlykhachsan.entity.HoaDon;
import com.quanlykhachsan.entity.Phong;
import com.quanlykhachsan.model.ConnectDB;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultKeyedValuesDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ThongKe_DAO {
  private CaLamViec_DAO listCaLamViec = new CaLamViec_DAO();
  private HoaDon_DAO hoaDonDAO = new HoaDon_DAO();
  private Phong_DAO listPhong = new Phong_DAO();
  private LichSuDatPhong_DAO listDatPhong = new LichSuDatPhong_DAO();
  private KhachHang_DAO listKH = new KhachHang_DAO();
  private LichSuDatDichVu_DAO listDatDichVu = new LichSuDatDichVu_DAO();
  private ChiTietHoaDon_DAO listCTHD = new ChiTietHoaDon_DAO();
    public static void main(String[] args) {
      try {
          ConnectDB con = new ConnectDB();
          con.connect();
          ThongKe_DAO a = new ThongKe_DAO();
          a.setDataToThongKeSoDonDatPhong(new JLabel(), LocalDate.now());
      } catch (SQLException ex) {
          Logger.getLogger(ThongKe_DAO.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  
  public DefaultTableModel docDuLieuVaoBan() {
    // Thêm tên cột vào DefaultTableModel
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Ca Làm Việc", "Ca Làm Việc", "Ngày Giao Ca", "Tổng Tiền", "Mã Nhân Viên"}, 0);
    
    // Thêm dữ liệu vào DefaultTableModel
          listCaLamViec.getList().stream().forEach(x -> {
        dtm.addRow(new Object[]{
            x.getMaCaLamViec(), x.getTenCaLamViec().getCa(), x.getNgayLamViec(), x.getTongTienTrongCa(), x.getNhanVien().getMaNhanVien()
        });
    });
    
    return dtm;
}
    public void setDataToThongKeSoKhachHang(JLabel tongSoKhachHang){
       tongSoKhachHang.setText(listKH.hienBangNV().size() + " Số Khách Hàng");
    }
    public void setDataToThongKeDichVU(JLabel tongSoDichVu ){
        tongSoDichVu.setText(listDatDichVu.getList().size() + " Đơn Dịch Vụ Đã Đặt");
    }
    public void setDataToThongKeDichVU(JLabel tongSoDichVu, LocalDate ngayThongKe ){
        tongSoDichVu.setText(listDatDichVu.getList().stream().filter(x-> x.getThoiGianDatDichVu().equals(ngayThongKe)).count() + " Đơn Dịch Vụ Đã Đặt");
    }
    public void setDataToThongKeSoDonDatPhong(JLabel tongSoPhong){
        tongSoPhong.setText(listCTHD.getList().size()+ " Đơn Đặt Phòng");
    }
    public void setDataToThongKeSoDonDatPhong(JLabel tongSoPhong, LocalDate ngayThongKe){
        tongSoPhong.setText(listCTHD.getList().stream().filter(x -> x.getNgayLapHoaDon().equals(ngayThongKe)).count() + " Đơn Đặt Phòng");
    }
    
public void setDataToChartThongKeDoanhThuTrongCa(JPanel jpnItem) {
    // Step 1: Kiểm tra danh sách ca làm việc
    if (listCaLamViec != null && listCaLamViec.getList() != null && !listCaLamViec.getList().isEmpty()) {
        
        // Bước 2: Tính tổng doanh thu cho từng nhân viên
        Map<String, Double> employeeRevenue = new HashMap<>();
        for (CaLamViec value : listCaLamViec.getList()) {
            String maNhanVien = value.getNhanVien().getMaNhanVien();
            double doanhThu = value.getTongTienTrongCa(); // Giả sử mỗi ca có doanh thu

            // Cộng doanh thu vào tổng của nhân viên
            employeeRevenue.put(maNhanVien, employeeRevenue.getOrDefault(maNhanVien, 0.0) + doanhThu);
        }

        // Bước 3: Tạo Pie Dataset với doanh thu
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Double> entry : employeeRevenue.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        // Bước 4: Tạo biểu đồ tròn thống kê doanh thu
        JFreeChart pieChart = ChartFactory.createPieChart("Thống Kê Doanh Thu Nhân Viên", dataset, true, true, false);

        // Tùy chỉnh biểu đồ nếu cần
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setSimpleLabels(true);
        
        // Hiển thị biểu đồ trên JPanel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(400, 400));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    } else {
        throw new IllegalArgumentException("Danh sách Ca Làm Việc trống hoặc NULL");
    }
}

public void setDataToChartThongKeDoanhThuTrongCa(JPanel jpnItem, Date ngayThongKe) {

    if (listCaLamViec != null && listCaLamViec.getList() != null && !listCaLamViec.getList().isEmpty()) {
        
        // Bước 1: Tính tổng tiền cho từng nhân viên
        Map<String, Double> employeeRevenue = new HashMap<>();
        LocalDate dateToCheck = null;
        if (ngayThongKe != null) {
            dateToCheck = ngayThongKe.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        for (CaLamViec value : listCaLamViec.getList()) {
            LocalDate shiftDate = value.getNgayLamViec();

            // Kiểm tra ngày nếu có ngày thống kê được chỉ định
            if (dateToCheck != null && !shiftDate.equals(dateToCheck)) {
                continue; // Bỏ qua nếu ngày làm việc không khớp với ngày thống kê
            }

            // Tính tổng tiền của ca làm việc cho từng nhân viên
            String maNhanVien = value.getNhanVien().getMaNhanVien();
            double tongTienCa = value.getTongTienTrongCa(); // Giả sử có thuộc tính `tongTienCa` là tổng tiền của ca
            
            employeeRevenue.put(maNhanVien, employeeRevenue.getOrDefault(maNhanVien, 0.0) + tongTienCa);
        }

        // Bước 2: Tạo Pie Dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Double> entry : employeeRevenue.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        // Bước 3: Tạo biểu đồ tròn
        JFreeChart pieChart = ChartFactory.createPieChart("Thống Kê Tổng Tiền Ca Làm Việc", dataset, true, true, false);
        
        // Tùy chỉnh biểu đồ
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setSimpleLabels(true);
        
        // Hiển thị biểu đồ trong JPanel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(300, 300));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    } else {
        throw new IllegalArgumentException("Danh sách Ca Làm Việc trống hoặc NULL");
    }
}

public void setDataToChartThongKeGiaoCa(JPanel jpnItem, LocalDate ngayThongKe) {

    if (listCaLamViec != null && listCaLamViec.getList() != null && !listCaLamViec.getList().isEmpty()) {
        TaskSeriesCollection ds = new TaskSeriesCollection();

        // Tạo một TaskSeries cho mỗi mã nhân viên, với tổng tiền trong ca
        Map<String, TaskSeries> employeeTaskSeriesMap = new HashMap<>();

        for (CaLamViec value : listCaLamViec.getList()) {
            // Kiểm tra nếu ngày làm việc trùng với ngày thống kê
            if (value.getNgayLamViec().equals(ngayThongKe)) {
                String maNhanVien = value.getNhanVien().getMaNhanVien();
                String seriesLabel = maNhanVien + " - Tổng tiền: " + value.getTongTienTrongCa() + " VND";

                // Nếu chưa có TaskSeries cho mã nhân viên này, tạo mới
                if (!employeeTaskSeriesMap.containsKey(seriesLabel)) {
                    TaskSeries taskSerie = new TaskSeries(seriesLabel);
                    employeeTaskSeriesMap.put(seriesLabel, taskSerie);
                }

                // Lấy TaskSeries của mã nhân viên này
                TaskSeries taskSerie = employeeTaskSeriesMap.get(seriesLabel);

                // Thiết lập ngày làm việc
                Date startDate = Date.from(ngayThongKe.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date endDate = Date.from(ngayThongKe.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

                // Tạo Task với tên ca làm việc
                String taskLabel = value.getTenCaLamViec().getCa();
                Task task = new Task(taskLabel, startDate, endDate);

                // Thêm task vào TaskSeries của mã nhân viên
                taskSerie.add(task);
            }
        }

        // Thêm tất cả TaskSeries vào dataset
        for (TaskSeries taskSerie : employeeTaskSeriesMap.values()) {
            ds.add(taskSerie);
        }

        // Tạo biểu đồ với trục đổi chỗ
        JFreeChart chart = ChartFactory.createGanttChart("Thống Kê Giao Ca", "Nhân Viên và Tổng Tiền", "Ngày Làm Việc", ds);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 300));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    } else {
        throw new IllegalArgumentException("List Ca Lam Viec is NULL hoặc rỗng");
    }
}

public void setDataToChartThongKeGiaoCa(JPanel jpnItem) {

    if (listCaLamViec != null && listCaLamViec.getList() != null && !listCaLamViec.getList().isEmpty()) {
        TaskSeriesCollection ds = new TaskSeriesCollection();

        // Tạo một TaskSeries cho mỗi mã nhân viên, với tổng tiền trong ca
        Map<String, TaskSeries> employeeTaskSeriesMap = new HashMap<>();

        for (CaLamViec value : listCaLamViec.getList()) {
            String maNhanVien = value.getNhanVien().getMaNhanVien();
            String seriesLabel = maNhanVien + " - Tổng tiền: " + value.getTongTienTrongCa() + " VND";

            // Nếu chưa có TaskSeries cho mã nhân viên này, tạo mới
            if (!employeeTaskSeriesMap.containsKey(seriesLabel)) {
                TaskSeries taskSerie = new TaskSeries(seriesLabel);
                employeeTaskSeriesMap.put(seriesLabel, taskSerie);
            }

            // Lấy TaskSeries của mã nhân viên này
            TaskSeries taskSerie = employeeTaskSeriesMap.get(seriesLabel);

            // Thiết lập ngày làm việc
            LocalDate ngayLamViec = value.getNgayLamViec();
            Date startDate = Date.from(ngayLamViec.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date endDate = Date.from(ngayLamViec.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

            // Tạo Task với tên ca làm việc
            String taskLabel = value.getTenCaLamViec().getCa();
            Task task = new Task(taskLabel, startDate, endDate);

            // Thêm task vào TaskSeries của mã nhân viên
            taskSerie.add(task);
        }

        // Thêm tất cả TaskSeries vào dataset
        for (TaskSeries taskSerie : employeeTaskSeriesMap.values()) {
            ds.add(taskSerie);
        }

        // Tạo biểu đồ với trục đổi chỗ
        JFreeChart chart = ChartFactory.createGanttChart("Thống Kê Giao Ca", "Nhân Viên và Tổng Tiền", "Ngày Làm Việc", ds);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 300));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    } else {
        throw new IllegalArgumentException("List Ca Lam Viec is NULL or empty");
    }
}


public void setDataToBarhart(JPanel jpItem, LocalDate selectedDate) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Lấy danh sách HoaDon và tính doanh thu theo ngày đã chọn
    double totalRevenue = 0.0;

    // Lặp qua danh sách hóa đơn và tính doanh thu
    for (HoaDon hoaDon : hoaDonDAO.getList()) {
        LocalDate hoaDonDate = hoaDon.getThoiGianLapHoaDon(); // Giả sử phương thức này trả về LocalDate

        if (hoaDonDate.equals(selectedDate)) {
            totalRevenue += hoaDon.getTongTien();
        }
    }

    // Thêm dữ liệu vào dataset
    dataset.addValue(totalRevenue, "Doanh Thu", selectedDate.toString()); // Sử dụng ngày làm nhãn

    // Tạo biểu đồ
    JFreeChart chart = ChartFactory.createBarChart(
            "Thống Kê Doanh Thu Ngày " + selectedDate.toString(), // Tựa đề biểu đồ
            "Ngày",                                                  // Tiêu đề trục X
            "Số Tiền",                                             // Tiêu đề trục Y
            dataset                                                 // Dataset
    );

    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new Dimension(700, 400));

    // Cập nhật JPanel
    jpItem.removeAll();
    jpItem.setLayout(new CardLayout());
    jpItem.add(chartPanel);
    jpItem.validate();
    jpItem.repaint();
}

public void setDataToBarhart(JPanel jpItem) { // theo ngày
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    Map<String, Double> revenueByDay = new HashMap<>();

    // Lấy danh sách HoaDon và tính doanh thu theo ngày
    hoaDonDAO.getList().forEach(hoaDon -> {
        LocalDate date = hoaDon.getThoiGianLapHoaDon(); // Convert to LocalDate
        String dayMonthYear = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)); // Format date

        double revenue = hoaDon.getTongTien();

        // Cộng dồn doanh thu theo ngày
        revenueByDay.put(dayMonthYear, revenueByDay.getOrDefault(dayMonthYear, 0.0) + revenue);
    });

    // Thêm dữ liệu vào dataset
    revenueByDay.forEach((date, totalRevenue) -> {
        dataset.addValue(totalRevenue, "Doanh Thu", date);
    });

    // Create the bar chart
    JFreeChart chart = ChartFactory.createBarChart("Thống Kê Doanh Thu", "Ngày", "Số Tiền", dataset);
    ChartPanel chartpanel = new ChartPanel(chart);
    chartpanel.setPreferredSize(new Dimension(700, 400));
    
    // Update the panel with the new chart
    jpItem.removeAll();
    jpItem.setLayout(new CardLayout());
    jpItem.add(chartpanel);
    jpItem.validate();
    jpItem.repaint();
}
//
//    public void setDataToBarhart(JPanel jpItem){ // Theo thangs
//        
//            
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        
//        Map<String, Double> revenueByMonth = new HashMap<>();
//
//        // Lấy danh sách HoaDon và tính doanh thu theo tháng
//        hoaDonDAO.getList().forEach(hoaDon -> {
//            Month month = hoaDon.getThoiGianLapHoaDon().getMonth(); // Lấy tháng từ thời gian lập hóa đơn
//            String monthYear = month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + hoaDon.getThoiGianLapHoaDon().getYear();
//            double revenue = hoaDon.getTongTien();
//
//            // Cộng dồn doanh thu theo tháng
//            revenueByMonth.put(monthYear, revenueByMonth.getOrDefault(monthYear, 0.0) + revenue);
//        });
//
//        // Thêm dữ liệu vào dataset
//        revenueByMonth.forEach((month, totalRevenue) -> {
//            dataset.addValue(totalRevenue, "Doanh Thu", month);
//        });
// 
//        HoaDon_DAO listHoaDon = new HoaDon_DAO();
//        Phong_DAO listPhong = new Phong_DAO();
//        DefaultCategoryDataset dts = dataset;
//        JFreeChart chart = ChartFactory.createBarChart("Thống Kê Doanh Thu", "Thời Gian", "Số Tiền", dts);
//        ChartPanel chartpanel = new ChartPanel(chart);
//        chartpanel.setPreferredSize(new Dimension(700, 400));
//        jpItem.removeAll();
//        jpItem.setLayout(new CardLayout());
//        jpItem.add(chartpanel);
//        jpItem.validate();
//        jpItem.repaint();
//    }
    public DefaultTableModel docDuLieuVaoBanChiTietHoaDon(){
         // Thêm tên cột vào DefaultTableModel
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Chi Tiết Hóa Đơn", "Giá Tổng", "Mã Dịch Vụ", "Thời Gian Đặt DV","Số lượng", "Mã Phòng","Số Lượng", "Thời Gian Đặt Phòng" }, 0);
        
        List<Object[]> list = listCTHD.docTuBangChiTietCaHoaDon();
           
        for (Object[] row : list) {
        dtm.addRow(row);
    }
    // Thêm dữ liệu vào DefaultTableModel
        
    
    return dtm;
    }
    public List<String> timMaCTHDTuMaHD(String maHD){
        return listCTHD.timChiTietHoaDonTheoMa(maHD).stream().map( x -> x.getMaChiTietHoaDon()).toList();
    }
    public List<String> listMaCTHD(){
        return listCTHD.getListMaCTHD();
    }
    public List<String> listMaHD(){
        return listCTHD.getListMAHD();
    }

    public DefaultTableModel docDuLieuVaoLichSuDatPhong() {
        // Create a DefaultTableModel with the specified column names
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Chi Tiết Hóa Đơn", "Mã Phòng", "Số Lượng", "Thời Gian Đặt Phòng"}, 0);

        // Assuming listDatPhong.getList() returns a List<List<Object>> or List<YourDataType>
        

        // Loop through the dataList and add each row to the DefaultTableModel
        listDatPhong.getList().forEach(item -> {
            // Assuming item has methods to get the required fields
            dtm.addRow(new Object[]{
                item.getChiTietHoaDon().getMaChiTietHoaDon(), // Replace with actual method to get "Mã Chi Tiết Hóa Đơn"
                item.getPhong().getMaPhong(), // Replace with actual method to get "Mã Phòng"
                item.getSoLuong(), // Replace with actual method to get "Số Lượng"
                item.getThoiGianDatPhong() // Replace with actual method to get "Thời Gian Đặt Phòng"
            });
        });

        return dtm; // Return the populated DefaultTableModel
    }
    public DefaultTableModel docDuLieuVaoLichSuDatPhong(String ma) {
    // Khởi tạo DefaultTableModel với các tên cột được chỉ định
    DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Chi Tiết Hóa Đơn", "Mã Dịch Vụ", "Số Lượng", "Thời Gian Đặt Dịch Vụ"}, 0);

    // Kiểm tra nếu danh sách `listCTHD.getListMaCTHD()` không rỗng
    if (listCTHD.getListMaCTHD().size() > 0) {
        // Lọc dữ liệu từ listDatDichVu dựa trên mã cung cấp
        DefaultTableModel dtm2 =  new DefaultTableModel(new String[]{"Mã Chi Tiết Hóa Đơn", "Mã Phòng", "Số Lượng", "Thời Gian Đặt Phòng"}, 0);
        listDatPhong.traVeListTuMa(ma).forEach(item -> {
           dtm2.addRow(new Object[]{
                item.getChiTietHoaDon().getMaChiTietHoaDon(), // Replace with actual method to get "Mã Chi Tiết Hóa Đơn"
                item.getPhong().getMaPhong(), // Replace with actual method to get "Mã Phòng"
                item.getSoLuong(), // Replace with actual method to get "Số Lượng"
                item.getThoiGianDatPhong() // Replace with actual method to get "Thời Gian Đặt Phòng"
            });
        });
        dtm = dtm2;
    } else {
        // Thêm tất cả dữ liệu nếu không có mã chi tiết hóa đơn phù hợp
        dtm = null;
    }

    return dtm;
}
   public DefaultTableModel docDuLieuVaoLichSuDichVu() {
    // Khởi tạo DefaultTableModel với các tên cột được chỉ định
    DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Chi Tiết Hóa Đơn", "Mã Dịch Vụ", "Số Lượng", "Thời Gian Đặt Dịch Vụ"}, 0);

    // Thêm dữ liệu vào dtm từ danh sách listDatDichVu
    listDatDichVu.getList().forEach(item -> {
        dtm.addRow(new Object[]{
            item.getChiTietHoaDon().getMaChiTietHoaDon(),
            item.getDichVu().getMaDichVu(),
            item.getSoLuong(),
            item.getThoiGianDatDichVu()
        });
    });

    return dtm;
}

public DefaultTableModel docDuLieuVaoLichSuDichVu(String ma) {
    // Khởi tạo DefaultTableModel với các tên cột được chỉ định
    DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Chi Tiết Hóa Đơn", "Mã Dịch Vụ", "Số Lượng", "Thời Gian Đặt Dịch Vụ"}, 0);

    // Kiểm tra nếu danh sách `listCTHD.getListMaCTHD()` không rỗng
    if (listCTHD.getListMaCTHD().size() > 0) {
        // Lọc dữ liệu từ listDatDichVu dựa trên mã cung cấp
        DefaultTableModel dtm2 =  new DefaultTableModel(new String[]{"Mã Chi Tiết Hóa Đơn", "Mã Dịch Vụ", "Số Lượng", "Thời Gian Đặt Dịch Vụ"}, 0);
        listDatDichVu.traVeListTheoMa(ma).forEach(item -> {
            dtm2.addRow(new Object[]{
                item.getChiTietHoaDon().getMaChiTietHoaDon(),
                item.getDichVu().getMaDichVu(),
                item.getSoLuong(),
                item.getThoiGianDatDichVu()
            });
        });
        dtm = dtm2;
    } else {
        // Thêm tất cả dữ liệu nếu không có mã chi tiết hóa đơn phù hợp
        dtm = null;
    }

    return dtm;
}

    public Double timTongTienTuMa(String ma){
        listCTHD.docTuBang();        
        return listCTHD.getTongTien(ma);
    }
   
    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        Map<String, Double> revenueByMonth = new HashMap<>();

        // Lấy danh sách HoaDon và tính doanh thu theo tháng
        hoaDonDAO.getList().forEach(hoaDon -> {
            Month month = hoaDon.getThoiGianLapHoaDon().getMonth(); // Lấy tháng từ thời gian lập hóa đơn
            String monthYear = month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + hoaDon.getThoiGianLapHoaDon().getYear();
            double revenue = hoaDon.getTongTien();

            // Cộng dồn doanh thu theo tháng
            revenueByMonth.put(monthYear, revenueByMonth.getOrDefault(monthYear, 0.0) + revenue);
        });

        // Thêm dữ liệu vào dataset
        revenueByMonth.forEach((month, totalRevenue) -> {
            dataset.addValue(totalRevenue, "Doanh Thu", month);
        });

        return dataset;
    }
    
    public DefaultPieDataset createDataSet() {
    DefaultPieDataset dataset = new DefaultPieDataset();
    
    // Đếm số lượng phòng theo trạng thái
    int trongCount = 0;
    int daDatCount = 0;
    int daCoc = 0;
    
    for (Phong phong : listPhong.getList()) {
        switch (phong.getTrangThai().getTrangThaiPhong()) {
            case "TRONG":
                trongCount++;
                break;
            case "DA_DAT":
                daDatCount++;
                break;
            case "DA_COC":
                daCoc++;
                break;
            default:
                break;
        }
    }
     
    // Thêm dữ liệu vào dataset
    dataset.setValue("Trống", trongCount);
    dataset.setValue("Đã Đặt", daDatCount);
    dataset.setValue("Đã Cọc", daCoc);
    
    return dataset;
}
         public void setDataToPie(JPanel jpnItem) {
         DefaultPieDataset dataset = createDataSet();
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Trạng Thái Phòng", // Tựa đề biểu đồ
                dataset,             // Dataset
                true,                // Có hiển thị legend không
                true,                // Có hiển thị tooltips không
                false                // Có hiển thị URLs không
        );

        // Tạo panel để chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(500, 400));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    
}
