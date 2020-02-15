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
 * �a�I�������O����
 * 
 * @author �i���[
 */
public class PrintPanel extends JPanel implements Printable {

	/**
	 * �I���Ϥ�
	 */
	private Image image;
	private PageFormat pageFormat;
	private int px;
	private int py;
	private int pwidth;
	private int pheight;

	/**
	 * �غc��k
	 */
	public PrintPanel() {
		super();
		setLayout(null);
		pageFormat = new PageFormat();// �إ߭����榡�ﹳ
		pageFormat.setOrientation(PageFormat.LANDSCAPE);// �]�w��������
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		super.paintComponent(g);
		try {
			print(g, pageFormat, 0);// �I�s�C�L��kø�s���O�ɭ�
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int print(Graphics g1, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		Graphics2D g = (Graphics2D) g1;
		px = (int) pageFormat.getImageableX();// ��o�i�C�L�ϰ쪺x�y��
		py = (int) pageFormat.getImageableY();// ��o�i�C�L�ϰ쪺y�y��
		pwidth = (int) pageFormat.getImageableWidth();// ��o�i�C�L���e��
		pheight = (int) pageFormat.getImageableHeight();// ��o�i�C�L������
		int pageWidth = (int) pageFormat.getWidth();// ��o�C�L�����e��
		int pageHeight = (int) pageFormat.getHeight();// ��o�C�L��������
		Dimension preferredSize = new Dimension(pageWidth, pageHeight);
		setPreferredSize(preferredSize);// �]�w���O�j�p
		getParent().doLayout();// ���s�w�q�G�����e��
		g.setColor(Color.WHITE);// �]�w�e���⬰�զ�
		g.fill3DRect(0, 0, pageWidth, pageHeight, true);// ø�s�P�C�L�����ۦP�j�p���x��
		if (pageIndex < 1) {// �p�G�ثe�C�L���Ƥp��1
			g.drawImage(image, px, py, pwidth, pheight, this);// ø�s�C�L���e
			return Printable.PAGE_EXISTS;// �Ǧ^�i�C�L�лx
		} else// �_�h
			return Printable.NO_SUCH_PAGE;// �Ǧ^���䴩�C�L�лx
	}

	public void pageSet(PrinterJob job) {
		pageFormat = job.pageDialog(pageFormat);
		repaint();
	}
}
