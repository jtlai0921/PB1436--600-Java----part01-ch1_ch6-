package com.zzk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * �a�I�������O����
 * 
 * @author �i���[
 */
public class PrintPanel extends JPanel implements Printable {
    
    /**
     * �I���Ϥ�
     */
    private PageFormat pageFormat;// �C�L�榡�ﹳ
    private JTextArea textArea;// �¤�r�줸��
    private int px;// �i�C�L�ϰ쪺�����y��
    private int py;// �i�C�L�ϰ쪺�����y��
    private int pwidth;// �i�C�L�ϰ쪺�e��
    private int pheight;// �i�C�L�ϰ쪺����
    private boolean reverse = false;// �O�_����
    
    /**
     * �غc��k
     */
    public PrintPanel() {
        super();
        setLayout(null);
        pageFormat = new PageFormat();// �إ߭����榡�ﹳ
        pageFormat.setOrientation(PageFormat.LANDSCAPE);// �]�w��������
        textArea = new JTextArea();// �إ߯¤�r�줸��
        textArea.setBackground(new Color(250, 250, 250));
        textArea.setBounds(200, 5, 100, 54);
        textArea.setLineWrap(true);// �۰ʴ���
        textArea.setOpaque(false);
        // �]�w���ѯ¤�r
        textArea.setText("�Цb�o�̿�J�n�C�L���¤�r�A�����譱�ĪG���s�i�H�ݮĪG�A�C�L���s�N�H�ثe�ĪG�C�L�C");
        add(textArea);
        setVisible(true);// ��ܵ���
    }
    
    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g);// ø�s�즳���󤺮e
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
        textArea.setBounds(px, py, pwidth, pheight);// �]�w�¤�r�줸��j�p
        int pageWidth = (int) pageFormat.getWidth();// ��o�C�L�����e��
        int pageHeight = (int) pageFormat.getHeight();// ��o�C�L��������
        Dimension preferredSize = new Dimension(pageWidth, pageHeight);
        setPreferredSize(preferredSize);// �]�w���O�j�p
        getParent().doLayout();// ���s�w�q�G�����e��
        g.setColor(Color.WHITE);// �]�w�e���⬰�զ�
        g.fill3DRect(0, 0, pageWidth, pageHeight, true);// ø�s�P�C�L�����ۦP�j�p���x��
        if (pageIndex < 1 && textArea != null && reverse) {// �p�G�ثe�C�L���Ƥp��1�åB�}���譱�ĪG
            BufferedImage image = new BufferedImage(pwidth - px, pheight - py,
                    BufferedImage.TYPE_INT_RGB);// �إ߽w�Ĺϧιﹳ
            Graphics2D graphics = image.createGraphics();// ��o�Ϥ���H��ø�ϤW�U��
            graphics.setColor(Color.WHITE);// �]�w�e���⬰�զ�
            graphics.fillRect(0, 0, image.getWidth(), image.getHeight());// �ϥΥզ��R�ɭ�
            graphics.setColor(Color.BLACK);// �]�w�e���⬰�¦�
            Font font = textArea.getFont();// ��o�¤�r�줸�󪺦r��ﹳ
            graphics.setFont(font);// ��r��ﹳ�]�w���Ϥ���ø�ϤW�U��
            textArea.print(graphics);// ��¤�r��ɭ�ø�s��w�Ĺϧιﹳ�W
            image.flush();// ��s�Ϥ�ø�Ͻw�İ�
            g.drawImage(image, px, py, pwidth, pheight, image.getWidth(), 0, 0,
                    image.getHeight(), this);// �ϦVø�s�C�L���e�A��{�譱�ĪG
            return Printable.PAGE_EXISTS;// �Ǧ^�i�C�L�лx
        } else {// �_�h
            return Printable.NO_SUCH_PAGE;// �Ǧ^���䴩�C�L�лx
        }
    }
    
    public void setReverse(boolean reverse) {
        this.reverse = reverse;
        if (reverse) {
            textArea.setVisible(false);
            textArea.setEditable(false);
        } else {
            textArea.setVisible(true);
            textArea.setEditable(true);
        }
    }
    
    public void pageSet(PrinterJob job) {
        pageFormat = job.pageDialog(pageFormat);// �}�ҭ����]�w��͵���
        repaint();// ���sø�s�ɭ�
    }
    
    public boolean isReverse() {
        return reverse;
    }
}
