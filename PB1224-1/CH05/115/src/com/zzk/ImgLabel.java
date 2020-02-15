package com.zzk;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImgLabel extends JLabel {
    public ImgLabel() {
        super();
    }
    @Override
    public void paint(Graphics g) {
        try {
            int width = this.getWidth(); // ��o�Ϥ��e��
            int height = this.getHeight(); // ��o�Ϥ�����
            ImageIcon icon = (ImageIcon) getIcon(); // ��oImageIcon�ﹳ
            if (icon != null) {// �Ϥ�������
                g.drawImage(icon.getImage(), 0, 0, width, height, this); // ø�s�Ϥ�
            }
        } catch (Exception e) {
            e.printStackTrace(); // ��X�ҥ~��T
        }
    }
}
