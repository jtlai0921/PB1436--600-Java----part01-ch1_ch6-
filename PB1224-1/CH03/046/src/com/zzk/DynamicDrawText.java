package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.*;

import javax.swing.*;

public class DynamicDrawText extends JFrame {
    private DynamicDrawTextPanel dynamicDrawTextPanel = new DynamicDrawTextPanel();
    public static void main(String args[]) {
        DynamicDrawText frame;
        try {
            frame = new DynamicDrawText();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public DynamicDrawText() {
        setTitle("�ʺAø�s�¤�r");
        setBounds(100, 100, 262, 179);
        setDefaultCloseOperation(3);
        getContentPane().add(dynamicDrawTextPanel);
        Thread thread = new Thread(dynamicDrawTextPanel); // �إ߽u�{�ﹳ
        thread.start(); // �Ұʽu�{
    }
    
    // �إ߭��O���O
    class DynamicDrawTextPanel extends JPanel implements Runnable {
        private BufferedReader read; // �ŧi�w�Ĭy�ﹳ
        int x = 20;// �_�l�I��x�y��
        int y = 30;// �_�l�I��y�y��
        String value = "";
        public DynamicDrawTextPanel(){
            String projectPath = System.getProperty("user.dir"); // ��o�ثe����
            String filePath = projectPath + "/src/com/zzk/dyn.txt";// ��o���ؤ�loadText.java�ɮת�������|
            InputStream in = null;
            try {
                in = new FileInputStream(filePath);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // �إ߿�J�y�ﹳ
            read = new BufferedReader(new InputStreamReader(in)); // �إ߽w�Ĭy�ﹳ
        }
        public void paint(Graphics g) {
            Font font = new Font("�ؤ巢��", Font.BOLD, 20);// �إߦr��ﹳ
            g.setFont(font);// ���w�r��
            g.setColor(Color.RED);// ���w�C��
            g.drawString(value, x, y);// ø�s�¤�r
        }
        public void run() {
            int len = 0;// �x�sŪ�����r��
            try {
                while ((len = read.read()) != -1) { // Ū�����e
                    Thread.sleep(400); // �ثe�u�{��v400�@��
                    value = String.valueOf((char) len); // ��oŪ�������e
                    if (value.equals("\n") || value.equals("\r")) {// �O�T�{�δ����
                        x = 20;// �U�@��_�l�I��x�y��
                        y += 15;// �U�@��¤�r��y�y��
                    } else {// ���O�T�{�δ����
                        x += 20;// �ثe��U�@�Ӧr��x�y��
                    }
                    repaint();// �I�spaint()��k
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}