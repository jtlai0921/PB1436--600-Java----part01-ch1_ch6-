package com.zzk;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author �i���[
 */
@SuppressWarnings("serial")
public class PigWalkMazeFrame extends JFrame implements KeyListener, Runnable {
    Rectangle rect1, rect2, rect3, rect4;
    int gobuttonX = 0, gobuttonY = 0;
    final JButton goButton = new JButton();
    URL url = getClass().getResource("/images/pig.png");
    ImageIcon imageIcon = new ImageIcon(url);
    final JLabel label = new JLabel();
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PigWalkMazeFrame frame = new PigWalkMazeFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public PigWalkMazeFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent e) {
                goButton.requestFocus(); // �Ϥp����o�J�I
            }
        });
        getContentPane().setLayout(null);
        setBounds(100, 100, 488, 375);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("�p�ި��g�c");
        BakcgroundPanel panel = new BakcgroundPanel();
        rect1 = new Rectangle(0, 55, 190, 40);
        rect2 = new Rectangle(190, 40, 40, 240);
        rect3 = new Rectangle(190, 180, 230, 40);
        rect4 = new Rectangle(300, 180, 40, 140);
        setResizable(false);
        panel.setLayout(null);
        panel.setBounds(0, 0, 482, 341);
        getContentPane().add(panel);
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                buttonAction(e);
            }
        });
        URL url = getClass().getResource("/images/button.png");
        ImageIcon imageIcons = new ImageIcon(url);
        button.setIcon(imageIcons);
        button.setBounds(27, 100, 106, 28);
        goButton.setBounds(0, 40, imageIcon.getIconWidth(), imageIcon
                .getIconHeight());
        panel.add(goButton);
        panel.add(button);
        gobuttonX = goButton.getBounds().x; // �w�q�p�ޫ��s����y��
        gobuttonY = goButton.getBounds().y; // �w�q�p�ޫ��s���a�y��
        goButton.addKeyListener(this);
        goButton.setIcon(imageIcon);
        goButton.setContentAreaFilled(false); // ������R�ϰ�
        goButton.setBorder(null); // �������
        url = getClass().getResource("/images/exit.png");
        imageIcons = new ImageIcon(url);
        label.setIcon(imageIcons);
        label.setBounds(300, 315, imageIcons.getIconWidth(), imageIcons
                .getIconHeight());
        panel.add(label);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if ((gobuttonY == 286)) { // �p�G�p�ު��a�y�е���286
            Thread thread = new Thread(this);
            thread.start(); // �Ұʽu�{
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) { // �p�G�ϥΪ̫��U�F�V�W��
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20); // �إ�Rectangle�ﹳ
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) { // �P�_�p�ެO�_���X�F�g�c
                gobuttonY = gobuttonY - 2; // �]�w�ܼƮy��
                goButton.setLocation(gobuttonX, gobuttonY); // �]�w���s�y��
            } else { // �p�G�p�ި��X�F�g�c
                JOptionPane.showMessageDialog(this, "����F�a�I���s�}�l�a�I", "����աI",
                        JOptionPane.DEFAULT_OPTION);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) { // �P�_�ϥΪ̬O�_���V�U��
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20);
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) {
                gobuttonY = gobuttonY + 2;
                goButton.setLocation(gobuttonX, gobuttonY);
            } else {
                JOptionPane.showMessageDialog(this, "����F�a�I���s�}�l�a�I", "����աI",
                        JOptionPane.DEFAULT_OPTION);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) { // �p�G�ϥΪ̫��V����
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20);
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) {
                gobuttonX = gobuttonX - 2;
                goButton.setLocation(gobuttonX, gobuttonY);
            } else {
                JOptionPane.showMessageDialog(this, "����F�a�I���s�}�l�a�I", "����աI",
                        JOptionPane.DEFAULT_OPTION);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // �p�G�ϥΪ̫��V�k��
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20);
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) {
                gobuttonX = gobuttonX + 2;
                goButton.setLocation(gobuttonX, gobuttonY);
            } else {
                JOptionPane.showMessageDialog(this, "����F�a�I���s�}�l�a�I", "����աI",
                        JOptionPane.DEFAULT_OPTION);
            }
        }
    }
    
    public void buttonAction(ActionEvent e) {
        goButton.setIcon(imageIcon); // ���s�]�w���s����ܹϤ�
        goButton.addKeyListener(this); // �����s�W�[��Шƥ�
        goButton.setBounds(0, 40, imageIcon.getIconWidth(), imageIcon
                .getIconHeight()); // �]�w�p�ަ�m
        gobuttonX = goButton.getBounds().x; // ��o�p�ޥثe��m��X�y��
        gobuttonY = goButton.getBounds().y;// ��o�p�ޥثe��m��Y�y��
        goButton.requestFocus(); // �]�w���s��o�J�I
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void run() {
        URL out = getClass().getResource("/images/pigOut.png"); // ��o�Ϥ�URL
        ImageIcon imageout = new ImageIcon(out); // �إ߹Ϥ��ﹳ
        goButton.setIcon(imageout); // �]�w�p�ޫ��s��ܹϤ�
        goButton.setBounds(gobuttonX,
                gobuttonY - imageout.getIconHeight() + 50, imageout
                        .getIconWidth(), imageout.getIconHeight()); // ���s�]�w���s��m
        goButton.removeKeyListener(this); // ���s������Шƥ�
    }
    
}
