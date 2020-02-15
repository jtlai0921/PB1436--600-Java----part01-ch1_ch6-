package com.zzk;

import java.awt.Rectangle;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * �椸�Ϥ�
 * 
 * @author �i���[
 */
public class Cell extends JButton {
    private static final long serialVersionUID = 718623114650657819L;
    public static final int IMAGEWIDTH = 117;// �Ϥ��e��
    private int place;// �Ϥ���m
    
    public Cell(Icon icon, int place) {
        this.setSize(IMAGEWIDTH, IMAGEWIDTH);// �椸�Ϥ����j�p
        this.setIcon(icon);// �椸�Ϥ����ϼ�
        this.place = place;// �椸�Ϥ�����m
    }
    
    public void move(Direction dir) {// ���ʳ椸�Ϥ�����k
        Rectangle rec = this.getBounds();// ��o�Ϥ���Rectangle�ﹳ
        switch (dir) {// �P�_��V
            case UP:// �V�W����
                this.setLocation(rec.x, rec.y - IMAGEWIDTH);
                break;
            case DOWN:// �V�U����
                this.setLocation(rec.x, rec.y + IMAGEWIDTH);
                break;
            case LEFT:// �V������
                this.setLocation(rec.x - IMAGEWIDTH, rec.y);
                break;
            case RIGHT:// �V�k����
                this.setLocation(rec.x + IMAGEWIDTH, rec.y);
                break;
        }
    }
    
    public int getX() {
        return this.getBounds().x;// ��o�椸�Ϥ���x�y��
    }
    
    public int getY() {
        return this.getBounds().y;// ��o�椸�Ϥ���y�y��
    }
    
    public int getPlace() {
        return place;// ��o�椸�Ϥ�����m
    }
}
