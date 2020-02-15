package com.zzk;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveEdgeDetectPictureFrame extends JFrame {
    private BufferedImage image;// 宣告緩衝圖形對像
    private EdgeDetectPicturePanel edgeDetectPicturePanel = null; // 宣告圖形面板對像
    private boolean edgeDetectFlag = false;// 照亮邊緣標記
    public static void main(String args[]) {
        SaveEdgeDetectPictureFrame frame = new SaveEdgeDetectPictureFrame();
        frame.setVisible(true);
    }
    
    public SaveEdgeDetectPictureFrame() {
        super();
        setTitle("照亮邊緣並儲存"); // 設定窗體標題
        setBounds(200, 160, 371, 271); // 設定窗體大小和位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        edgeDetectPicturePanel = new EdgeDetectPicturePanel(); // 建立圖形面板對像
        this.add(edgeDetectPicturePanel); // 在窗體上增加圖形面板對像
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float[] elements = {0.0f,-1.0f,0.0f,-1.0f,4.0f,-1.0f,0.0f,-1.0f,0.0f};// 宣告表示像素份量的陣列
                convolve(elements);// 呼叫方法實現圖片照亮邊緣功能
                edgeDetectFlag = true;// 照亮邊緣圖片標記
            }
        });
        button.setText("照亮邊緣");
        panel.add(button);

        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!edgeDetectFlag) {
                    JOptionPane.showMessageDialog(null,"還沒執行照亮邊緣操作。");// 顯示提示資訊
                    return;
                }
                FileDialog dialog = new FileDialog(SaveEdgeDetectPictureFrame.this,"儲存");// 建立交談視窗
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
    
    private void convolve(float[] elements) {
        Kernel kernel = new Kernel(3, 3, elements);             // 建立 Kernel對像
        ConvolveOp op = new ConvolveOp(kernel);         // 建立ConvolveOp對像
        if (image == null) {
            return;
        }
        image = op.filter(image, null);                     // 過濾緩衝圖形對像
        repaint();                                  // 呼叫paint()方法
    }

    
    // 建立面板類別
    class EdgeDetectPicturePanel extends JPanel {
        public EdgeDetectPicturePanel(){
            Image img = null;// 宣告建立圖形對像
            try {
                img = ImageIO.read(new File("src/img/image.jpg"));  // 建立圖形對像
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