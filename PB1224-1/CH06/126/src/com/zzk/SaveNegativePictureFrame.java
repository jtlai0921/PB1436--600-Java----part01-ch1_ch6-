package com.zzk;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveNegativePictureFrame extends JFrame {
    private BufferedImage image;// 宣告緩衝圖形對像
    private NegativePicturePanel negativePicturePanel = null; // 宣告圖形面板對像
    private boolean negativeFlag = false;// 反向標記
    public static void main(String args[]) {
        SaveNegativePictureFrame frame = new SaveNegativePictureFrame();
        frame.setVisible(true);
    }
    
    public SaveNegativePictureFrame() {
        super();
        setTitle("反向並儲存圖片"); // 設定窗體標題
        setBounds(200, 160, 516, 458); // 設定窗體大小和位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        negativePicturePanel = new NegativePicturePanel(); // 建立圖形面板對像
        add(negativePicturePanel); // 在窗體上增加圖形面板對像
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                short[] negative = new short[256];// 建立表示顏色反向的份量陣列
                for (int i = 0; i<256;i++){
                    negative[i] = (short)(255-i);// 為陣列給予值
                }
                ShortLookupTable table = new ShortLookupTable(0,negative);// 建立尋找表對像
                LookupOp op = new LookupOp(table,null);// 建立實現從源到目標尋找操作的LookupOp對像
                image = op.filter(image, null);// 呼叫LookupOp對象的filter()方法，實現圖形反向功能
                repaint();  // 呼叫paint()方法
                negativeFlag = !negativeFlag;// 標記是否已反向
                if (negativeFlag){
                    button.setText("還原圖片");
                }else{
                    button.setText("反向圖片");
                }
            }
        });
        button.setText("反向圖片");
        panel.add(button);

        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!negativeFlag) {
                    JOptionPane.showMessageDialog(null,"還沒執行反向操作。");// 顯示提示資訊
                    return;
                }
                FileDialog dialog = new FileDialog(SaveNegativePictureFrame.this,"儲存");// 建立交談視窗
                dialog.setMode(FileDialog.SAVE);// 設定交談視窗為儲存交談視窗
                dialog.setVisible(true);// 顯示儲存交談視窗
                String path = dialog.getDirectory();// 獲得檔案的儲存路徑
                String fileName = dialog.getFile();// 獲得儲存的檔案名
                if (path == null || fileName == null){
                    return;
                }
                String fileExtName = fileName.substring(fileName.indexOf(".")+1);// 檔案擴充名,不含點
                String pathAndName = path + "\\" + fileName;// 檔案的完整路徑
                try {
                    ImageIO.write(image, fileExtName, new File(pathAndName));// 將緩衝圖形儲存到磁碟
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null,"儲存失敗\n"+e1.getMessage());// 顯示提示資訊
                }
            }
        });
        button_2.setText("儲存圖片");
        panel.add(button_2);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退    出");
        panel.add(button_1);
    }
    
    
    // 建立面板類別
    class NegativePicturePanel extends JPanel {
        public NegativePicturePanel(){
            Image img = null;// 宣告建立圖形對像
            try {
                img = ImageIO.read(new File("src/img/imag.jpg"));  // 建立圖形對像
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);// 建立緩衝圖形對像
            image.getGraphics().drawImage(img, 0, 0,  null);// 在緩衝圖形對像上繪製圖形
        }
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);// 繪製緩衝圖形對像
            }
        }
    }
}
