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
    
    public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);

		JButton button = new JButton("Click Me");
		frame.getContentPane().add(button, BorderLayout.SOUTH);

		JLabel label = new JLabel("Hello, this is a test label.");
		frame.getContentPane().add(label, BorderLayout.CENTER);

		frame.setVisible(true);

		// Đợi frame hiển thị trước khi chụp
		SwingUtilities.invokeLater(() -> {
			BufferedImage capturedImage = captureComponent(frame.getContentPane());
			ImageIcon imageIcon = new ImageIcon(capturedImage);
			// Hiển thị ảnh trong một JOptionPane để xem trước
			JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(capturedImage)));
		});
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
