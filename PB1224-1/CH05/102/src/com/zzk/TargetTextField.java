package com.zzk;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class TargetTextField extends JTextField implements MouseListener,
MouseMotionListener, ActionListener {
    private int mousePressX = 0;// ���Ы��U�I��x�y��
    private int mousePressY = 0;// ���Ы��U�I��y�y��
    final Border border = getBorder();// ��o�¤�r�ت����
    public TargetTextField() {
        super();
        setBorder(border);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setBorder(border);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressX = e.getX();// ���Ы��U�I��x�y��
        mousePressY = e.getY();// ���Ы��U�I��y�y��
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Cursor cursorD = new Cursor(Cursor.DEFAULT_CURSOR);// �w�]�����Ы��A
        setCursor(cursorD);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        JTextField textField = (JTextField) e.getSource();
        int textW = textField.getWidth();
        int textH = textField.getHeight();
        Rectangle rect = textField.getBounds();
        if (getCursor().getType()==Cursor.W_RESIZE_CURSOR){// �վ��������ت��j�p�M��m
            textField.setBounds((int)rect.getX() + e.getX(),(int)rect.getY(),textW - e.getX(),textH);
            textField.requestFocus();
        } else if (getCursor().getType()==Cursor.E_RESIZE_CURSOR){// �վ�����k��ت��j�p�M��m
            textField.setBounds((int)rect.getX(),(int)rect.getY(),e.getX(),textH);
            textField.requestFocus();
        } else if (getCursor().getType()==Cursor.N_RESIZE_CURSOR){// �վ�����W��ت��j�p�M��m
            textField.setBounds((int)rect.getX(),(int)rect.getY()+e.getY(),textW,textH - e.getY());
            textField.requestFocus();
        } else if (getCursor().getType()==Cursor.S_RESIZE_CURSOR){// �վ�����U��ت��j�p�M��m
            textField.setBounds((int)rect.getX(),(int)rect.getY(),textW,e.getY());
            textField.requestFocus();
        } else {// ���ʯ¤�r�ت���m
            textField.setBounds((int)rect.getX() + e.getX()-mousePressX,(int)rect.getY()+e.getY()-mousePressY,textW,textH);
            textField.requestFocus();
        }
        

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        Cursor cursorW = new Cursor(Cursor.W_RESIZE_CURSOR);// �վ��������ؤj�p�����Ы��A
        Cursor cursorN = new Cursor(Cursor.N_RESIZE_CURSOR);// �վ�����W��ؤj�p�����Ы��A
        Cursor cursorE = new Cursor(Cursor.E_RESIZE_CURSOR);// �վ�����k��ؤj�p�����Ы��A
        Cursor cursorS = new Cursor(Cursor.S_RESIZE_CURSOR);// �վ�����U��ؤj�p�����Ы��A
        Cursor cursorD = new Cursor(Cursor.DEFAULT_CURSOR);// �w�]���Ы��A
        JTextField textField = (JTextField) e.getSource();
        int textW = textField.getWidth();
        int textH = textField.getHeight();
        if (e.getX() == 0){
            setCursor(cursorW);// �]�w����إ���
        }else if (e.getY() == 0){
            setCursor(cursorN);// �]�w�W��إ���
        }else if (e.getX() == textW-3){
            setCursor(cursorE);// �]�w�k��إ���
        }else if (e.getY() == textH-3){
            setCursor(cursorS);// �]�w�U��إ���
        }else{
            setCursor(cursorD);// �]�w�w�]����
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setBorder(null);
    }

}
