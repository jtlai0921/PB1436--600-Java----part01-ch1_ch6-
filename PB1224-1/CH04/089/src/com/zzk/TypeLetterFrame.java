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
 * @author 張振坤
 */
public class TypeLetterFrame extends JFrame {
    private JLabel lb_ok; // 顯示正確率的標籤
    private JLabel lb_speed; // 顯示打字速度的標籤
    // 建立背景面板對像
    private final BackgroundPanel backgroundPanel = new BackgroundPanel();
    
    private RandomBuildLetter buildLetter = new RandomBuildLetter();// 建立隨機產生字母的類別的對像
    private JLabel[] labels = null; // 建立標籤
    private Vector<String> vector = new Vector<String>(); // 建立儲存準備擊打字母的向量
    final JLabel label = new JLabel();
    private Date startTime = new Date();
    private int totalSeconds = 0; // 打字的總秒數
    private int totalOkLetters = 0; // 打正確的字母總數
    private int totalLetters = 0; // 出現字母總數
    private int speed = 0; // 打字速度
    
    public TypeLetterFrame() {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("打字母遊戲");
        setBounds(100, 80, 520, 400);
        
        addKeyListener(new GameListener()); // 為窗體增加按鍵監聽器
        
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
        int seed = 10; // 設定標籤之間偏移量的變數
        // 呼叫RandomBuildLetter類別的方法隨機產生8個整數並給予值給陣列，即8個A-Z之間字母的ASCII值
        int[] letters = buildLetter.getLetter(8);
        labels = new JLabel[letters.length]; // 建立顯示帶字母蘋果的標籤陣列
        // 實例化標籤陣列的每個對象
        for (int i = 0; i < letters.length; i++) {
            int value = letters[i]; // 獲得陣列letters中的值
            char c = (char) value; // 將陣列letters中的值轉為字符
            String s = String.valueOf(c); // 將字符轉為字串
            labels[i] = new JLabel(); // 實例化標籤對像
            labels[i].setToolTipText(s); // 設定標籤的提示純文字
            // 設定標籤顯示的圖片，即帶字母的蘋果
            labels[i].setIcon(SwingResourceManager.getIcon(
                    TypeLetterFrame.class, "/icon/" + s + ".png"));
            int x = (int) (Math.random() * 60) + seed; // 隨機產生標籤顯示位置的橫座標
            int y = (int) (Math.random() * 80); // 隨機產生標籤顯示位置的縱座標
            labels[i].setBounds(x, y, 100, 30); // 設定標籤的顯示位置和大小
            backgroundPanel.add(labels[i]); // 將標籤增加到背景面板中
            seed += 60; // 調整標籤之間的偏移量
            vector.add(s); // 將字串字母添到到向量對像中
            totalLetters++; // 計算出現字母的總個數
        }
    }
    
    /**
     * @author 張振坤
     *         使字母移動的線程
     */
    private class MoveLetterThread implements Runnable {
        int seed = 10; // 定義標籤之間偏移量的變數
        
        public void run() {
            while (true) {
                // 對顯示蘋果字母的標籤陣列進行操作
                for (int i = 0; i < labels.length; i++) {
                    // 設定標籤的顯示位置
                    labels[i].setLocation(labels[i].getX(), labels[i].getY()
                            + (int) (Math.random() * labels[i].getHeight()));
                    if (labels[i].getY() > 400) { // 標籤縱座標大於400
                        String oldValue = labels[i].getToolTipText(); // 獲得標籤上原來的提示純文字
                        int value = buildLetter.getRandomLetter(); // 隨機產生A-Z的ASCII值
                        char c = (char) value; // 轉換成字母字符
                        String s = String.valueOf(c); // 將字母字符轉為字串
                        labels[i].setToolTipText(s); // 設定新的提示純文字
                        // 設定圖標，即帶字母的蘋果
                        labels[i].setIcon(SwingResourceManager.getIcon(
                                TypeLetterFrame.class, "/icon/" + s + ".png"));
                        totalLetters++; // 計算出現字母的總個數
                        labels[i].setVisible(true); // 顯示標籤
                        int x = (int) (Math.random() * 60) + seed; // 隨機產生標籤顯示位置的橫座標
                        int y = (int) (Math.random() * 80); // 隨機產生標籤顯示位置的縱座標
                        labels[i].setLocation(x, y); // 設定標籤的顯示位置
                        seed += 60; // 調整標籤之間的偏移量
                        if (seed >= 490) { // 如果seed大於490
                            int m = 0; // 宣告要從向量中移除項的索引
                            for (int j = 0; j < vector.size(); j++) { // 檢查向量對像
                                String str = vector.get(j).toString(); // 獲得向量中的元素
                                if (str.equals(oldValue)) { // 如果與標籤上原來的提示純文字相同
                                    m = j; // 為索引m給予值，即要從向是中刪除項的索引
                                }
                            }
                            vector.remove(m); // 從向量中移除元素
                            seed = 10; // 重新設定標籤之間的偏移量
                        }
                        vector.add(s); // 在向量中增加新的元素
                    }
                    // 總體秒數
                    int second0 = (int) ((new Date().getTime() - startTime
                            .getTime()) / 1000);
                    int second = second0 % 60; // 時間中的秒
                    int minute0 = new Double(second0 / 60).intValue(); // 總體分鐘
                    totalSeconds = second0; // 存放總體秒數
                    int minute = minute0 % 60; // 時間中的分鐘
                    int hour = new Double(minute0 / 60).intValue(); // 總體小時數
                    // 在標籤上顯示時間
                    label.setText("用時：" + String.valueOf(hour) + ":"
                            + String.valueOf(minute) + ":"
                            + String.valueOf(second));
                    speed = (int) (totalOkLetters / (totalSeconds / 60.0f)); // 計算打字速度
                    // 在標籤上顯示打字事件
                    lb_speed.setText("速度（個/分鐘）" + String.valueOf(speed));
                    int ok = 0; // 存放正確率的變數
                    if (totalLetters != 0) {
                        ok = (totalOkLetters * 100 / totalLetters); // 計算正確率
                    }
                    lb_ok.setText("正確率（百分比）" + String.valueOf(ok)); // 在標籤上顯示正確率
                }
                try {
                    Thread.sleep(200); // 休眠200毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * @author 張振坤
     *         鍵碟按鍵監聽器類別
     */
    private class GameListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode(); // 獲得按鍵的值
            if (code >= 65 && code <= 90) { // 是大寫字母
                char c = (char) code; // 轉為字母字符
                String s = String.valueOf(c); // 轉為字串
                for (int i = 0; i < vector.size(); i++) { // 檢查向量對像
                    String str = vector.get(i).toString(); // 獲得向量中的元素
                    if (s.equals(str)) { // 按鍵字符與向量中的相同
                        for (int j = 0; j < labels.length; j++) { // 檢查標籤陣列
                            if (str.equals(labels[j].getToolTipText())) { // 按鍵字符與標籤提示純文字相同
                                labels[j].setVisible(false); // 隱藏該標籤
                                int value = buildLetter.getRandomLetter(); // 獲得新字符的ASCII值
                                char ch = (char) value; // 轉為字符
                                String ss = String.valueOf(ch); // 轉為字串
                                labels[j].setToolTipText(ss); // 重新設定標籤的提示純文字
                                // 設定標籤上顯示的字母蘋果圖片
                                labels[j].setIcon(SwingResourceManager.getIcon(
                                        TypeLetterFrame.class, "/icon/" + ss
                                                + ".png"));
                                totalLetters++; // 計算出現字母的總數
                                labels[j].setVisible(true); // 顯示標籤元件
                                int x = (int) (Math.random() * 20 + labels[j]
                                        .getX()); // 隨機產生標籤元件的橫座標
                                int y = (int) (Math.random() * -80); // 隨機產生標籤元件的縱座標
                                labels[j].setLocation(x, y); // 設定標籤的顯示位置
                                vector.add(ss); // 將新字串增加到向量中
                                break; // 結束循環
                            }
                        }
                        int x = 0; // 宣告要從向量中移除項的索引
                        for (int k = 0; k < vector.size(); k++) { // 檢查向量對像
                            String oldValue = vector.get(k).toString(); // 獲得向量中的元素
                            if (str.equals(oldValue)) { // 如果與標籤上原來的提示純文字相同
                                x = k; // 為索引x給予值，即要從向是中刪除項的索引
                            }
                        }
                        vector.remove(x); // 從向量中移除元素
                        totalOkLetters++; // 打正確的字母總數
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