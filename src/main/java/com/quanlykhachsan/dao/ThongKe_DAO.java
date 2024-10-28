package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.CaLamViec;
import com.quanlykhachsan.model.ConnectDB;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultPieDataset;

public class ThongKe_DAO {
  CaLamViec_DAO listCaLamViec = new CaLamViec_DAO();
  public void setDataToBarChartThongKeDoanhThu(JPanel jpnItem){
      
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
public void setDataToChartThongKeCaLamViec(JPanel jpnItem ) {
    
    if (listCaLamViec != null && listCaLamViec.getList() != null && !listCaLamViec.getList().isEmpty()) {
        
        // Step 1: Count shifts per employee
        Map<String, Integer> employeeShiftCount = new HashMap<>();
        for (CaLamViec value : listCaLamViec.getList()) {
            String maNhanVien = value.getNhanVien().getMaNhanVien();
            employeeShiftCount.put(maNhanVien, employeeShiftCount.getOrDefault(maNhanVien, 0) + 1);
        }

        // Step 2: Create Pie Dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : employeeShiftCount.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        // Step 3: Create Pie Chart
        JFreeChart pieChart = ChartFactory.createPieChart("Thống Kê Số Ca Làm Việc", dataset, true, true, false);
        
        // Optional: Customize the chart
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setSimpleLabels(true);
        
        // Display the chart in the JPanel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(300, 300));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    } else {
        throw new IllegalArgumentException("List Ca Lam Viec is NULL or empty");
    }
}
public void setDataToChartThongKeCaLamViec(JPanel jpnItem, Date ngayThongKe) {

    if (listCaLamViec != null && listCaLamViec.getList() != null && !listCaLamViec.getList().isEmpty()) {
        
        // Step 1: Count shifts per employee
        Map<String, Integer> employeeShiftCount = new HashMap<>();
        for (CaLamViec value : listCaLamViec.getList()) {
            // If ngayThongKe is provided, filter by this date
            if (ngayThongKe != null) {
                LocalDate dateToCheck = ngayThongKe.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate shiftDate = value.getNgayLamViec();
                if (!shiftDate.equals(dateToCheck)) {
                    continue; // Skip if shiftDate does not match ngayThongKe
                }
            }

            // Count shifts for each employee
            String maNhanVien = value.getNhanVien().getMaNhanVien();
            employeeShiftCount.put(maNhanVien, employeeShiftCount.getOrDefault(maNhanVien, 0) + 1);
        }

        // Step 2: Create Pie Dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : employeeShiftCount.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        // Step 3: Create Pie Chart
        JFreeChart pieChart = ChartFactory.createPieChart("Thống Kê Số Ca Làm Việc", dataset, true, true, false);
        
        // Optional: Customize the chart
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setSimpleLabels(true);
        
        // Display the chart in the JPanel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(300, 300));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    } else {
        throw new IllegalArgumentException("List Ca Lam Viec is NULL or empty");
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





}
