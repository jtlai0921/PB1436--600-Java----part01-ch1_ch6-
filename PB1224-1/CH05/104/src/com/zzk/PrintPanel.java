package com.zzk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JPanel;

/**
 * 帶背景的面板元件
 * 
 * @author 張振坤
 */
public class PrintPanel extends JPanel implements Printable {

	/**
	 * 背景圖片
	 */
	private Image image;
	private PageFormat pageFormat;
	private int px;
	private int py;
	private int pwidth;
	private int pheight;

	/**
	 * 建構方法
	 */
	public PrintPanel() {
		super();
		setLayout(null);
		pageFormat = new PageFormat();// 建立頁面格式對像
		pageFormat.setOrientation(PageFormat.LANDSCAPE);// 設定水平頁面
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		super.paintComponent(g);
		try {
			print(g, pageFormat, 0);// 呼叫列印方法繪製面板界面
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int print(Graphics g1, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		Graphics2D g = (Graphics2D) g1;
		px = (int) pageFormat.getImageableX();// 獲得可列印區域的x座標
		py = (int) pageFormat.getImageableY();// 獲得可列印區域的y座標
		pwidth = (int) pageFormat.getImageableWidth();// 獲得可列印的寬度
		pheight = (int) pageFormat.getImageableHeight();// 獲得可列印的高度
		int pageWidth = (int) pageFormat.getWidth();// 獲得列印頁面寬度
		int pageHeight = (int) pageFormat.getHeight();// 獲得列印頁面高度
		Dimension preferredSize = new Dimension(pageWidth, pageHeight);
		setPreferredSize(preferredSize);// 設定面板大小
		getParent().doLayout();// 重新定義佈局父容器
		g.setColor(Color.WHITE);// 設定前景色為白色
		g.fill3DRect(0, 0, pageWidth, pageHeight, true);// 繪製與列印頁面相同大小的矩形
		if (pageIndex < 1) {// 如果目前列印頁數小於1
			g.drawImage(image, px, py, pwidth, pheight, this);// 繪製列印內容
			return Printable.PAGE_EXISTS;// 傳回可列印標誌
		} else// 否則
			return Printable.NO_SUCH_PAGE;// 傳回不支援列印標誌
	}

	public void pageSet(PrinterJob job) {
		pageFormat = job.pageDialog(pageFormat);
		repaint();
	}
}
