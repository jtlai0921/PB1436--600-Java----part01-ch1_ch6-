package com.zzk;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.zzk.panel.BackgroundPanel;

@SuppressWarnings("serial")
public class ExpressPrintFrame extends JFrame {
    private URL url = null;// �ŧi�Ϥ���URL
    private Image image = null;// �ŧi�ϧιﹳ
    private BackgroundPanel backPanel = null;// �ŧi�۩w�q�I�����O�ﹳ
    private Robot robot = null; // �ŧiRobot�ﹳ
    private BufferedImage buffImage = null; // �ŧi�w�Ĺϧιﹳ
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExpressPrintFrame frame = new ExpressPrintFrame();
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
    public ExpressPrintFrame() {
        super();
        setTitle("�C�L�ֻ���");
        setBounds(0, 0, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        url = ExpressPrintFrame.class.getResource("/image/express.jpg"); // ��o�Ϥ���URL
        image = new ImageIcon(url).getImage(); // �إ߹ϧιﹳ
        backPanel = new BackgroundPanel(image);
        backPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {// �������Хk��
                    int x = e.getX();// ��o���Ц�m��X�y��
                    int y = e.getY();// ��o���Ц�m��Y�y��
                    TargetTextField tf = new TargetTextField();// �إߦ۩w�q�¤�r�ت����
                    tf.addMouseListener(tf);// �W�[���к�ť��
                    tf.addMouseMotionListener(tf);// �W�[���к�ť��
                    tf.addActionListener(tf);// �W�[�ʧ@��ť��
                    tf.setBounds(x, y, 147, 22);// ���w�¤�r�ت���m�M�j�p
                    backPanel.add(tf);// �W�[��I�����O�W
                    tf.requestFocus();// �ϯ¤�r����o�J�I
                }
            }
        });
        backPanel.setLayout(null);
        getContentPane().add(backPanel);
        try {
            robot = new Robot();
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    final PrinterJob job = PrinterJob.getPrinterJob(); // ��o�C�L�ﹳ
                    if (!job.printDialog()) // �}�ҦC�L��͵���
                        return;// �����C�L��͵������������s�������C�L��͵��������{��������
                    job.setPrintable(new Printable() {
                        // ��{print()��k�Aø�s�C�L���e
                        public int print(Graphics graphics,
                                PageFormat pf, int pageIndex)
                                throws PrinterException {
                            if (pageIndex > 0)
                                return Printable.NO_SUCH_PAGE;
                            Graphics2D g2 = (Graphics2D) graphics; // ��o�ϧΤW�U��ﹳ
                            int x = (int)(ExpressPrintFrame.this.getBounds().getX())+8;// �I�����O�b�̹��W��X�y��
                            int y = (int)(ExpressPrintFrame.this.getBounds().getY())+30;// �I�����O�b�̹��W��Y�y��
                            int w = (int)backPanel.getBounds().getWidth();// �I�����O���e��
                            int h = (int)backPanel.getBounds().getHeight();// �I�����O������
                            Rectangle rect = new Rectangle(x, y, w, h);// �إ�Rectangle�ﹳ
                            buffImage = robot.createScreenCapture(rect);// ��o�w�Ĺϧιﹳ
                            int imgWidth = buffImage.getWidth();// �ϧΪ��e��
                            int imgHeight = buffImage.getHeight();// �ϧΪ�����
                            float wh = imgWidth / imgHeight;// �ϧμe����
                            int printX = (int) pf.getImageableX();// ��o�i�C�L�ϰ쪺x�y��
                            int printY = (int) pf.getImageableY();// ��o�i�C�L�ϰ쪺y�y��
                            int width = (int) pf.getImageableWidth();// ��o�i�C�L�ϰ쪺�e��
                            int height = (int) pf.getImageableHeight();// ��o�i�C�L�ϰ쪺����
                            if (imgWidth > width) { // �p�G�e�j��i�C�L�ϰ�
                                imgWidth = width;
                                imgHeight = (int)(imgHeight * wh);
                            }
                            if (imgHeight > height) { // �p�G���j��i�C�L�ϰ�
                                imgHeight = height;
                                imgWidth = (int)(imgWidth * wh);
                            }
                            g2.drawImage(buffImage, printX, printY, imgWidth, imgHeight, ExpressPrintFrame.this);// �N�w�Ĺϧ�ø�s��C�L��
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("�C�L�ֻ���"); // �]�w�C�L�u�@���W��
                    job.print(); // �I�sprint()��k�}�l�C�L
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("�C�L�ֻ���");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("�h    �X");
        panel.add(button_1);
    }
}
