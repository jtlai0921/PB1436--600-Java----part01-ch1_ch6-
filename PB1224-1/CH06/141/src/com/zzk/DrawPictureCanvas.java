package com.zzk;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

/**
 * @author �i���[
 *         �إߵe�����O
 */
public class DrawPictureCanvas extends Canvas {
    private Image image = null; // �w�qImage��H���Ѧ�
    public DrawPictureCanvas() {
        super();
    }
    public void setImage(Image image) {
        this.image = image; // �������ܼƵ�����
    }
    /*
     * ���s�w�qpaint()��k,�b�e���Wø�s�ϧ�
     */
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null); // �b�e���Wø�s�ϧ�
    }
    /*
     * ���s�w�qupdate()��k�A�o�˥i�H�ѨM�̹��{ģ�����D
     */
    public void update(Graphics g) {
        paint(g); // �I�spaint��k
    }
}
