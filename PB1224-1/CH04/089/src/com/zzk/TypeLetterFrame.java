package com.zzk;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

/**
 * @author �i���[
 */
public class TypeLetterFrame extends JFrame {
    private JLabel lb_ok; // ��ܥ��T�v������
    private JLabel lb_speed; // ��ܥ��r�t�ת�����
    // �إ߭I�����O�ﹳ
    private final BackgroundPanel backgroundPanel = new BackgroundPanel();
    
    private RandomBuildLetter buildLetter = new RandomBuildLetter();// �إ��H�����ͦr�������O���ﹳ
    private JLabel[] labels = null; // �إ߼���
    private Vector<String> vector = new Vector<String>(); // �إ��x�s�ǳ������r�����V�q
    final JLabel label = new JLabel();
    private Date startTime = new Date();
    private int totalSeconds = 0; // ���r���`���
    private int totalOkLetters = 0; // �����T���r���`��
    private int totalLetters = 0; // �X�{�r���`��
    private int speed = 0; // ���r�t��
    
    public TypeLetterFrame() {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("���r���C��");
        setBounds(100, 80, 520, 400);
        
        addKeyListener(new GameListener()); // ������W�[�����ť��
        
        backgroundPanel.setImage(SwingResourceManager.getImage(
                TypeLetterFrame.class, "/image/background.jpg"));
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);
        
        label.setBounds(10, 10, 157, 18);
        backgroundPanel.add(label);
        
        lb_speed = new JLabel();
        lb_speed.setBounds(173, 10, 157, 18);
        backgroundPanel.add(lb_speed);
        
        lb_ok = new JLabel();
        lb_ok.setBounds(345, 10, 157, 18);
        backgroundPanel.add(lb_ok);
        addLetter();
        Thread th = new Thread(new MoveLetterThread());
        th.start();
    }
    
    private void addLetter() {
        int seed = 10; // �]�w���Ҥ��������q���ܼ�
        // �I�sRandomBuildLetter���O����k�H������8�Ӿ�ƨõ����ȵ��}�C�A�Y8��A-Z�����r����ASCII��
        int[] letters = buildLetter.getLetter(8);
        labels = new JLabel[letters.length]; // �إ���ܱa�r��ī�G�����Ұ}�C
        // ��ҤƼ��Ұ}�C���C�ӹ�H
        for (int i = 0; i < letters.length; i++) {
            int value = letters[i]; // ��o�}�Cletters������
            char c = (char) value; // �N�}�Cletters�������ର�r��
            String s = String.valueOf(c); // �N�r���ର�r��
            labels[i] = new JLabel(); // ��ҤƼ��ҹﹳ
            labels[i].setToolTipText(s); // �]�w���Ҫ����ܯ¤�r
            // �]�w������ܪ��Ϥ��A�Y�a�r����ī�G
            labels[i].setIcon(SwingResourceManager.getIcon(
                    TypeLetterFrame.class, "/icon/" + s + ".png"));
            int x = (int) (Math.random() * 60) + seed; // �H�����ͼ�����ܦ�m����y��
            int y = (int) (Math.random() * 80); // �H�����ͼ�����ܦ�m���a�y��
            labels[i].setBounds(x, y, 100, 30); // �]�w���Ҫ���ܦ�m�M�j�p
            backgroundPanel.add(labels[i]); // �N���ҼW�[��I�����O��
            seed += 60; // �վ���Ҥ����������q
            vector.add(s); // �N�r��r���K���V�q�ﹳ��
            totalLetters++; // �p��X�{�r�����`�Ӽ�
        }
    }
    
    /**
     * @author �i���[
     *         �Ϧr�����ʪ��u�{
     */
    private class MoveLetterThread implements Runnable {
        int seed = 10; // �w�q���Ҥ��������q���ܼ�
        
        public void run() {
            while (true) {
                // �����ī�G�r�������Ұ}�C�i��ާ@
                for (int i = 0; i < labels.length; i++) {
                    // �]�w���Ҫ���ܦ�m
                    labels[i].setLocation(labels[i].getX(), labels[i].getY()
                            + (int) (Math.random() * labels[i].getHeight()));
                    if (labels[i].getY() > 400) { // �����a�y�Фj��400
                        String oldValue = labels[i].getToolTipText(); // ��o���ҤW��Ӫ����ܯ¤�r
                        int value = buildLetter.getRandomLetter(); // �H������A-Z��ASCII��
                        char c = (char) value; // �ഫ���r���r��
                        String s = String.valueOf(c); // �N�r���r���ର�r��
                        labels[i].setToolTipText(s); // �]�w�s�����ܯ¤�r
                        // �]�w�ϼСA�Y�a�r����ī�G
                        labels[i].setIcon(SwingResourceManager.getIcon(
                                TypeLetterFrame.class, "/icon/" + s + ".png"));
                        totalLetters++; // �p��X�{�r�����`�Ӽ�
                        labels[i].setVisible(true); // ��ܼ���
                        int x = (int) (Math.random() * 60) + seed; // �H�����ͼ�����ܦ�m����y��
                        int y = (int) (Math.random() * 80); // �H�����ͼ�����ܦ�m���a�y��
                        labels[i].setLocation(x, y); // �]�w���Ҫ���ܦ�m
                        seed += 60; // �վ���Ҥ����������q
                        if (seed >= 490) { // �p�Gseed�j��490
                            int m = 0; // �ŧi�n�q�V�q��������������
                            for (int j = 0; j < vector.size(); j++) { // �ˬd�V�q�ﹳ
                                String str = vector.get(j).toString(); // ��o�V�q��������
                                if (str.equals(oldValue)) { // �p�G�P���ҤW��Ӫ����ܯ¤�r�ۦP
                                    m = j; // ������m�����ȡA�Y�n�q�V�O���R����������
                                }
                            }
                            vector.remove(m); // �q�V�q����������
                            seed = 10; // ���s�]�w���Ҥ����������q
                        }
                        vector.add(s); // �b�V�q���W�[�s������
                    }
                    // �`����
                    int second0 = (int) ((new Date().getTime() - startTime
                            .getTime()) / 1000);
                    int second = second0 % 60; // �ɶ�������
                    int minute0 = new Double(second0 / 60).intValue(); // �`�����
                    totalSeconds = second0; // �s���`����
                    int minute = minute0 % 60; // �ɶ���������
                    int hour = new Double(minute0 / 60).intValue(); // �`��p�ɼ�
                    // �b���ҤW��ܮɶ�
                    label.setText("�ήɡG" + String.valueOf(hour) + ":"
                            + String.valueOf(minute) + ":"
                            + String.valueOf(second));
                    speed = (int) (totalOkLetters / (totalSeconds / 60.0f)); // �p�⥴�r�t��
                    // �b���ҤW��ܥ��r�ƥ�
                    lb_speed.setText("�t�ס]��/�����^" + String.valueOf(speed));
                    int ok = 0; // �s�񥿽T�v���ܼ�
                    if (totalLetters != 0) {
                        ok = (totalOkLetters * 100 / totalLetters); // �p�⥿�T�v
                    }
                    lb_ok.setText("���T�v�]�ʤ���^" + String.valueOf(ok)); // �b���ҤW��ܥ��T�v
                }
                try {
                    Thread.sleep(200); // ��v200�@��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * @author �i���[
     *         ��Ы����ť�����O
     */
    private class GameListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode(); // ��o���䪺��
            if (code >= 65 && code <= 90) { // �O�j�g�r��
                char c = (char) code; // �ର�r���r��
                String s = String.valueOf(c); // �ର�r��
                for (int i = 0; i < vector.size(); i++) { // �ˬd�V�q�ﹳ
                    String str = vector.get(i).toString(); // ��o�V�q��������
                    if (s.equals(str)) { // ����r�ŻP�V�q�����ۦP
                        for (int j = 0; j < labels.length; j++) { // �ˬd���Ұ}�C
                            if (str.equals(labels[j].getToolTipText())) { // ����r�ŻP���Ҵ��ܯ¤�r�ۦP
                                labels[j].setVisible(false); // ���øӼ���
                                int value = buildLetter.getRandomLetter(); // ��o�s�r�Ū�ASCII��
                                char ch = (char) value; // �ର�r��
                                String ss = String.valueOf(ch); // �ର�r��
                                labels[j].setToolTipText(ss); // ���s�]�w���Ҫ����ܯ¤�r
                                // �]�w���ҤW��ܪ��r��ī�G�Ϥ�
                                labels[j].setIcon(SwingResourceManager.getIcon(
                                        TypeLetterFrame.class, "/icon/" + ss
                                                + ".png"));
                                totalLetters++; // �p��X�{�r�����`��
                                labels[j].setVisible(true); // ��ܼ��Ҥ���
                                int x = (int) (Math.random() * 20 + labels[j]
                                        .getX()); // �H�����ͼ��Ҥ��󪺾�y��
                                int y = (int) (Math.random() * -80); // �H�����ͼ��Ҥ����a�y��
                                labels[j].setLocation(x, y); // �]�w���Ҫ���ܦ�m
                                vector.add(ss); // �N�s�r��W�[��V�q��
                                break; // �����`��
                            }
                        }
                        int x = 0; // �ŧi�n�q�V�q��������������
                        for (int k = 0; k < vector.size(); k++) { // �ˬd�V�q�ﹳ
                            String oldValue = vector.get(k).toString(); // ��o�V�q��������
                            if (str.equals(oldValue)) { // �p�G�P���ҤW��Ӫ����ܯ¤�r�ۦP
                                x = k; // ������x�����ȡA�Y�n�q�V�O���R����������
                            }
                        }
                        vector.remove(x); // �q�V�q����������
                        totalOkLetters++; // �����T���r���`��
                        break;
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        TypeLetterFrame frame = new TypeLetterFrame();
        frame.setVisible(true);
    }
    
}
