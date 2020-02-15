package com.zzk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.JDialog;

public class PreviewDialog extends JDialog implements Printable {

	private static PrintCodingFrame frame;
	private CodePanel codePanel;

	/**
	 * Create the panel
	 */
	private PreviewDialog() {
		super();
	}

	public PreviewDialog(PrintCodingFrame frame) {
		super(frame);
		this.frame = frame;
		codePanel = frame.getCodePanel();// 獲得條形碼面板
	}
	public PreviewDialog(PrintCodingFrame frame,boolean modal) {
		super(frame,modal);
		setTitle("列印預覽");
		this.frame = frame;
		codePanel = frame.getCodePanel();// 獲得條形碼面板
	}
	
	@Override
	public int print(Graphics graphics, PageFormat pf, int pageIndex)
			throws PrinterException {// 實現Printable接口的方法
		if(pageIndex>0)// 只列印1頁
			return Printable.NO_SUCH_PAGE;
		Graphics2D g = (Graphics2D) graphics;
		int px = (int) pf.getImageableX();// 獲得可列印區域
		int py = (int) pf.getImageableY();
		int pw = (int) pf.getImageableWidth();
		int ph = (int) pf.getImageableHeight();
		int width = (int) pf.getWidth();// 獲得頁面大小
		int height = (int) pf.getHeight();
		setSize(new Dimension(width, height));// 設定元件與頁面大小相同

		g.setColor(Color.WHITE);
		g.fill3DRect(0, 0, width, height, true);// 使用白色繪製紙張界面
		g.translate(px, py);
		g.setClip(0, 0, pw, ph);
		codePanel.print(g);// 繪製條形碼界面
		return Printable.PAGE_EXISTS;
	}

	@Override
	public void paint(Graphics g) {// 重新繪製界面的方法
		try {
			print(g,codePanel.getFormat(),0);// 呼叫print()方法
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
	
	public static Printable getPage(){
		return new PreviewDialog(PrintCodingFrame.frame);
	}
}
