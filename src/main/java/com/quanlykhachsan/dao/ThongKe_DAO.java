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
  CaLamViec_DAO listCaLamViec = new CaLamViec_DAO();
  HoaDon_DAO hoaDonDAO = new HoaDon_DAO();
  Phong_DAO listPhong = new Phong_DAO();

  
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


public void setDataToChartThongKeGiaoCa(JPanel jpnItem) {
  
    if (listCaLamViec != null && listCaLamViec.getList() != null && !listCaLamViec.getList().isEmpty()) {
        TaskSeriesCollection ds = new TaskSeriesCollection();

        // Iterate over each shift
        for (com.quanlykhachsan.enum_Class.CaLamViec calamviec : com.quanlykhachsan.enum_Class.CaLamViec.values()) {
            TaskSeries taskSerie = new TaskSeries(calamviec.getCa()); // Create a new TaskSeries for each shift
            
            for (CaLamViec value : listCaLamViec.getList()) {
                if (value.getTenCaLamViec().getCa().equals(calamviec.getCa())) {
                    LocalDate ngayLamViec = value.getNgayLamViec();

                    // Start date
                    Date startDate = Date.from(ngayLamViec.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    // End date: Increment by 1 day
                    LocalDate endDateLocal = ngayLamViec.plusDays(1);
                    Date endDate = Date.from(endDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    // Create a new Task
                    Task task = new Task(value.getNhanVien().getMaNhanVien() + "_" + ngayLamViec, startDate, endDate); // Add date to differentiate tasks
                    task.setDescription(value.getNhanVien().getMaNhanVien());

                    // Check if the task already exists in the task series
                    boolean taskExists = false;
                    int index = 0;
                    while (index < taskSerie.getTasks().size()) {
                        Task existingTask = (Task) taskSerie.getTasks().get(index);

                        // Check based on employee ID, date, and shift name
                        if (existingTask.getDescription().equals(task.getDescription()) 
                                && existingTask.getDuration().getStart().equals(task.getDuration().getStart())) {
                            existingTask.addSubtask(task);
                            taskExists = true;
                            break;
                        }
                        index++;
                    }

                    // If task doesn't exist, add it to the task series
                    if (!taskExists) {
                        taskSerie.add(task);
                    }
                }
            }

            // Add task series to dataset if it contains any tasks
            if (!taskSerie.getTasks().isEmpty()) {
                ds.add(taskSerie);
            }
        }

        // Create the chart with axes swapped
        JFreeChart chart = ChartFactory.createGanttChart("Thống Kê Giao Ca", "Nhân Viên", "Ca Làm Việc", ds);
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


    public void setDataToBarhart(JPanel jpItem){
        
            
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
 
        HoaDon_DAO listHoaDon = new HoaDon_DAO();
        Phong_DAO listPhong = new Phong_DAO();
        DefaultCategoryDataset dts = dataset;
        JFreeChart chart = ChartFactory.createBarChart("Thống Kê Doanh Thu", "Thời Gian", "Số Tiền", dts);
        ChartPanel chartpanel = new ChartPanel(chart);
        chartpanel.setPreferredSize(new Dimension(700, 400));
        jpItem.removeAll();
        jpItem.setLayout(new CardLayout());
        jpItem.add(chartpanel);
        jpItem.validate();
        jpItem.repaint();
    }
    public DefaultTableModel docDuLieuVaoBanHoaDon(){
         // Thêm tên cột vào DefaultTableModel
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Mã Hóa Đơn", "Ngày Lập", "Mã Nhân Viên", "Mã Voucher", "Mã Khách Hàng", "Mã Chi Tiết Hóa Đơn","Đặt Cọc", "Tiền Phạt", "Tổng Tiền" }, 0);
        
    
    // Thêm dữ liệu vào DefaultTableModel
          hoaDonDAO.getList().stream().forEach(x -> {
        dtm.addRow(new Object[]{
            x.getMaHoaDon(), x.getThoiGianLapHoaDon(), x.getNhanVien().getMaNhanVien(), x.getVoucher().getMaVoucher(), x.getKhachHang().getMaKhachHang(), x.getTienCoc(), x.getTienPhat(), x.getTongTien()
        });
    });
    
    return dtm;
    }
    public DefaultTableModel docDuLieuVaoBanHoaDon(LocalDate ngayLap) {
    // Thêm tên cột vào DefaultTableModel
    DefaultTableModel dtm = new DefaultTableModel(new String[]{
            "Mã Hóa Đơn", "Ngày Lập", "Mã Nhân Viên", "Mã Voucher", 
            "Mã Khách Hàng", "Mã Chi Tiết Hóa Đơn", "Đặt Cọc", 
            "Tiền Phạt", "Tổng Tiền"}, 0);
    
    // Lấy danh sách hóa đơn và lọc theo ngày
    List<HoaDon> hoaDons = hoaDonDAO.getList(); // Giả sử hoaDonDAO.getList() trả về danh sách hóa đơn
    hoaDons.stream()
            .filter(x -> x.getThoiGianLapHoaDon().isEqual(ngayLap)) // Lọc theo ngày
            .forEach(x -> {
                dtm.addRow(new Object[]{
                    x.getMaHoaDon(), 
                    x.getThoiGianLapHoaDon(), // Giữ nguyên LocalDate, có thể định dạng sau
                    x.getNhanVien().getMaNhanVien(), 
                    x.getVoucher() != null ? x.getVoucher().getMaVoucher() : "", // Kiểm tra null
                    x.getKhachHang() != null ? x.getKhachHang().getMaKhachHang() : "", // Kiểm tra null
                    x.getTienCoc(), 
                    x.getTienPhat(), 
                    x.getTongTien()
                });
            });
    
    return dtm;
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
    int baoTriCount = 0;
    int donDepCount = 0;
    
    for (Phong phong : listPhong.getList()) {
        switch (phong.getTrangThai().getTrangThaiPhong()) {
            case "TRONG":
                trongCount++;
                break;
            case "DA_DAT":
                daDatCount++;
                break;
            case "BAO_TRI":
                baoTriCount++;
                break;
            case "DON_DEP":
                donDepCount++;
                break;
            default:
                break;
        }
    }
     
    // Thêm dữ liệu vào dataset
    dataset.setValue("Trống", trongCount);
    dataset.setValue("Đã Đặt", daDatCount);
    dataset.setValue("Bảo Trì", baoTriCount);
    dataset.setValue("Đơn Dẹp", donDepCount);
    
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
