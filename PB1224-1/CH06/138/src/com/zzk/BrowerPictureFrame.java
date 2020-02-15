package com.zzk;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BrowerPictureFrame extends JFrame {
    private static final long serialVersionUID = 2568309165342527300L;
    private JTextField tf_path;
    private BufferedImage img=null;
    private File imgFile = null;
    private DrawImagePanel imgPanel = null;
    private String filePath = null;
    private String currentFileName = null;
    private int currentFileIndex = 0;
    private List<String> fileNameList = new ArrayList<String>();
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BrowerPictureFrame frame = new BrowerPictureFrame();
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
    public BrowerPictureFrame() {
        super();
        setTitle("圖片瀏覽器");
        setBounds(100, 100, 457, 328);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imgPanel = new DrawImagePanel();
        final JPanel panel = new JPanel();
        getContentPane().add(imgPanel, BorderLayout.CENTER);
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.NORTH);

        final JLabel label = new JLabel();
        label.setText("選擇檔案：");
        panel.add(label);

        tf_path = new JTextField();
        tf_path.setPreferredSize(new Dimension(280,25));
        panel.add(tf_path);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();// 建立檔案選擇器
                FileFilter filter = new FileNameExtensionFilter(
                        "圖形檔案（JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// 建立過濾器
                fileChooser.setFileFilter(filter);// 設定過濾器
                int flag = fileChooser.showOpenDialog(null);// 顯示開啟交談視窗
                if (flag == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // 獲得勾選圖片的File對像
                }
                if (imgFile != null) {
                    try {
                        img = ImageIO.read(imgFile);// 建構BufferedImage對像
                        filePath = imgFile.getParent();// 獲得檔案的路徑
                        tf_path.setText(filePath);// 在純文字框中顯示路徑
                        currentFileName = imgFile.getName();// 獲得選擇的檔案名，給予值給儲存目前檔案的變數
                        final String extName = currentFileName.substring(currentFileName.lastIndexOf("."));// 獲得檔案的擴充名
                        File pathFile = new File(filePath);// 建立路徑的File對像
                        String[] fileNames = pathFile.list(new FilenameFilter(){// 獲得滿足過濾條件的檔案名陣列
                            @Override
                            public boolean accept(File dir, String name) {
                                return name.endsWith(extName);// 傳回滿足擴充名的檔案名
                            }
                        });
                        for (int i=0;i<fileNames.length;i++){// 檢查陣列中的檔案名
                            if (fileNames[i].equals(currentFileName)){// 判斷是否為目前檔案
                                currentFileIndex = i;// 記憶目前檔案的索引值
                            }
                            fileNameList.add(fileNames[i]);// 將檔案增加到集合列表中
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                imgPanel.repaint();// 呼叫paint()方法
            }
        });
        button.setText("選    擇");
        panel.add(button);

        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                currentFileIndex = 0;// 第一張圖片的索引
                imgFile = new File(filePath+"/"+fileNameList.get(currentFileIndex).toString());// 建立目前索引值對應圖片的File對像
                try {
                    img = ImageIO.read(imgFile);// 建立目前圖片的圖形對像
                    imgPanel.repaint();// 呼叫paint()方法
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_1.setText("第一張");
        panel_1.add(button_1);

        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                currentFileIndex--;// 調整目前圖片的索引值
                if (currentFileIndex < 0) {
                    currentFileIndex = fileNameList.size() - 1;// 目前圖片索引為最後一張圖片的索引
                }
                imgFile = new File(filePath+"/"+fileNameList.get(currentFileIndex).toString());// 建立目前索引值對應圖片的File對像
                try {
                    img = ImageIO.read(imgFile);// 建立目前圖片的圖形對像
                    imgPanel.repaint();// 呼叫paint()方法，顯示圖片
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_2.setText("上一張");
        panel_1.add(button_2);

        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                currentFileIndex++;// 調整目前圖片的索引值
                if (currentFileIndex > fileNameList.size() - 1) {
                    currentFileIndex = 0;// 目前圖片索引為第一張圖片的索引
                }
                imgFile = new File(filePath+"/"+fileNameList.get(currentFileIndex).toString());// 建立目前索引值對應圖片的File對像
                try {
                    img = ImageIO.read(imgFile);// 建立目前圖片的圖形對像
                    imgPanel.repaint();// 呼叫paint()方法
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_3.setText("下一張");
        panel_1.add(button_3);

        final JButton button_4 = new JButton();
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                currentFileIndex = fileNameList.size() - 1;// 最後一張圖片的索引
                imgFile = new File(filePath+"/"+fileNameList.get(currentFileIndex).toString());// 建立目前索引值對應圖片的File對像
                try {
                    img = ImageIO.read(imgFile);// 建立目前圖片的圖形對像
                    imgPanel.repaint();// 呼叫paint()方法
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_4.setText("最後一張");
        panel_1.add(button_4);
    }
    // 建立面板類別
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            if (img == null){
                return;
            }
            int imgW = img.getWidth(this);
            int imgH = img.getHeight(this);
            int panelW = getWidth();
            int panelH = getHeight();
            g.clearRect(0, 0, panelW, panelH);
            g.drawImage(img, 0, 0,imgW,imgH,this); // 繪製指定的圖片
        }
    }
}
