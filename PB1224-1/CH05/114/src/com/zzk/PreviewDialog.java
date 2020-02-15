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
		codePanel = frame.getCodePanel();// ��o���νX���O
	}
	public PreviewDialog(PrintCodingFrame frame,boolean modal) {
		super(frame,modal);
		setTitle("�C�L�w��");
		this.frame = frame;
		codePanel = frame.getCodePanel();// ��o���νX���O
	}
	
	@Override
	public int print(Graphics graphics, PageFormat pf, int pageIndex)
			throws PrinterException {// ��{Printable���f����k
		if(pageIndex>0)// �u�C�L1��
			return Printable.NO_SUCH_PAGE;
		Graphics2D g = (Graphics2D) graphics;
		int px = (int) pf.getImageableX();// ��o�i�C�L�ϰ�
		int py = (int) pf.getImageableY();
		int pw = (int) pf.getImageableWidth();
		int ph = (int) pf.getImageableHeight();
		int width = (int) pf.getWidth();// ��o�����j�p
		int height = (int) pf.getHeight();
		setSize(new Dimension(width, height));// �]�w����P�����j�p�ۦP

		g.setColor(Color.WHITE);
		g.fill3DRect(0, 0, width, height, true);// �ϥΥզ�ø�s�ȱi�ɭ�
		g.translate(px, py);
		g.setClip(0, 0, pw, ph);
		codePanel.print(g);// ø�s���νX�ɭ�
		return Printable.PAGE_EXISTS;
	}

	@Override
	public void paint(Graphics g) {// ���sø�s�ɭ�����k
		try {
			print(g,codePanel.getFormat(),0);// �I�sprint()��k
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
	
	public static Printable getPage(){
		return new PreviewDialog(PrintCodingFrame.frame);
	}
}
