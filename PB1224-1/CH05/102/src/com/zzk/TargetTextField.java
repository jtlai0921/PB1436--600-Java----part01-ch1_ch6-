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
    private int mousePressX = 0;// 鼠標按下點的x座標
    private int mousePressY = 0;// 鼠標按下點的y座標
    final Border border = getBorder();// 獲得純文字框的邊框
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
        mousePressX = e.getX();// 鼠標按下點的x座標
        mousePressY = e.getY();// 鼠標按下點的y座標
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Cursor cursorD = new Cursor(Cursor.DEFAULT_CURSOR);// 預設的光標型態
        setCursor(cursorD);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        JTextField textField = (JTextField) e.getSource();
        int textW = textField.getWidth();
        int textH = textField.getHeight();
        Rectangle rect = textField.getBounds();
        if (getCursor().getType()==Cursor.W_RESIZE_CURSOR){// 調整視窗左邊框的大小和位置
            textField.setBounds((int)rect.getX() + e.getX(),(int)rect.getY(),textW - e.getX(),textH);
            textField.requestFocus();
        } else if (getCursor().getType()==Cursor.E_RESIZE_CURSOR){// 調整視窗右邊框的大小和位置
            textField.setBounds((int)rect.getX(),(int)rect.getY(),e.getX(),textH);
            textField.requestFocus();
        } else if (getCursor().getType()==Cursor.N_RESIZE_CURSOR){// 調整視窗上邊框的大小和位置
            textField.setBounds((int)rect.getX(),(int)rect.getY()+e.getY(),textW,textH - e.getY());
            textField.requestFocus();
        } else if (getCursor().getType()==Cursor.S_RESIZE_CURSOR){// 調整視窗下邊框的大小和位置
            textField.setBounds((int)rect.getX(),(int)rect.getY(),textW,e.getY());
            textField.requestFocus();
        } else {// 移動純文字框的位置
            textField.setBounds((int)rect.getX() + e.getX()-mousePressX,(int)rect.getY()+e.getY()-mousePressY,textW,textH);
            textField.requestFocus();
        }
        

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        Cursor cursorW = new Cursor(Cursor.W_RESIZE_CURSOR);// 調整視窗左邊框大小的光標型態
        Cursor cursorN = new Cursor(Cursor.N_RESIZE_CURSOR);// 調整視窗上邊框大小的光標型態
        Cursor cursorE = new Cursor(Cursor.E_RESIZE_CURSOR);// 調整視窗右邊框大小的光標型態
        Cursor cursorS = new Cursor(Cursor.S_RESIZE_CURSOR);// 調整視窗下邊框大小的光標型態
        Cursor cursorD = new Cursor(Cursor.DEFAULT_CURSOR);// 預設光標型態
        JTextField textField = (JTextField) e.getSource();
        int textW = textField.getWidth();
        int textH = textField.getHeight();
        if (e.getX() == 0){
            setCursor(cursorW);// 設定左邊框光標
        }else if (e.getY() == 0){
            setCursor(cursorN);// 設定上邊框光標
        }else if (e.getX() == textW-3){
            setCursor(cursorE);// 設定右邊框光標
        }else if (e.getY() == textH-3){
            setCursor(cursorS);// 設定下邊框光標
        }else{
            setCursor(cursorD);// 設定預設光標
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setBorder(null);
    }

}
