/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class ScreenshotHelper {
    public static BufferedImage captureComponent(Component component) {
		BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		// Gọi phương thức paint của component để vẽ nội dung vào BufferedImage
		component.paint(image.getGraphics());
		return image;
	}
    
 

	public static void printImage(BufferedImage image) {
		PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(new Printable() {
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				if (pageIndex != 0) {
					return NO_SUCH_PAGE;
				}
				
				Graphics2D g2d = (Graphics2D) graphics;
				g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	
				// Tỉ lệ phóng to hình ảnh, ví dụ 1.5x
				double scaleX = 0.7;
				double scaleY = 0.7;
	
				// Tăng tỷ lệ in lên
				g2d.scale(scaleX, scaleY);
				g2d.drawImage(image, 0, 0, null);
				return PAGE_EXISTS;
			}
		});
	
		try {
			printJob.print();
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
}
