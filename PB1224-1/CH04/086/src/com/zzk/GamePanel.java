package com.zzk;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.swtdesigner.SwingResourceManager;

/**
 * 遊戲面板
 * 
 * @author 張振坤
 */
public class GamePanel extends JPanel implements MouseListener {
    private static final long serialVersionUID = -653831947783440122L;
    private Cell[] cells = new Cell[9];// 建立單元圖片陣列
    private Cell cellBlank = null;// 空白
    
    // 建構方法
    public GamePanel() {
        super();
        setLayout(null);// 設定空佈局
        init();// 初始化
    }
    
    // 初始化遊戲
    public void init() {
        int num = 0;// 圖片序號
        Icon icon = null;// 圖標對像
        Cell cell = null;// 單元圖片對像
        for (int i = 0; i < 3; i++) {// 循環行
            for (int j = 0; j < 3; j++) {// 循環列
                num = i * 3 + j;// 計算圖片序號
                icon = SwingResourceManager.getIcon(GamePanel.class, "/pic/"
                        + (num + 1) + ".jpg");// 獲得圖片
                cell = new Cell(icon, num);// 實例化單元圖片對像
                cell.setLocation(j * Cell.IMAGEWIDTH, i * Cell.IMAGEWIDTH);// 設定單元圖片的座標
                cells[num] = cell;// 將單元圖片儲存到單元圖片陣列中
            }
        }
        for (int i = 0; i < cells.length; i++) {
            this.add(cells[i]);// 向面板中增加所有單元圖片
        }
    }
    
    /**
     * 對圖片進行隨機排序
     */
    public void random() {
        Random rand = new Random();// 實例化Random
        int m, n, x, y;
        if (cellBlank == null) {// 判斷空白的圖片位置是否為空
            cellBlank = cells[cells.length - 1];// 取出空白的圖片
            for (int i = 0; i < cells.length; i++) {// 檢查所有單元圖片
                if (i != cells.length - 1) {
                    cells[i].addMouseListener(this);// 對非空白圖片註冊鼠標監聽
                }
            }
        }
        for (int i = 0; i < cells.length; i++) {// 檢查所有單元圖片
            m = rand.nextInt(cells.length);// 產生隨機數
            n = rand.nextInt(cells.length);// 產生隨機數
            x = cells[m].getX();// 獲得x座標
            y = cells[m].getY();// 獲得y座標
            // 對單元圖片調換
            cells[m].setLocation(cells[n].getX(), cells[n].getY());
            cells[n].setLocation(x, y);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Cell cell = (Cell) e.getSource();// 獲得觸發時間的對象
        int x = cellBlank.getX();// 獲得x座標
        int y = cellBlank.getY();// 獲得y座標
        if ((x - cell.getX()) == Cell.IMAGEWIDTH && cell.getY() == y) {
            cell.move(Direction.RIGHT);// 向右移動
            cellBlank.move(Direction.LEFT);
        } else if ((x - cell.getX()) == -Cell.IMAGEWIDTH && cell.getY() == y) {
            cell.move(Direction.LEFT);// 向左移動
            cellBlank.move(Direction.RIGHT);
        } else if (cell.getX() == x && (cell.getY() - y) == Cell.IMAGEWIDTH) {
            cell.move(Direction.UP);// 向上移動
            cellBlank.move(Direction.DOWN);
        } else if (cell.getX() == x && (cell.getY() - y) == -Cell.IMAGEWIDTH) {
            cell.move(Direction.DOWN);// 向下移動
            cellBlank.move(Direction.UP);
        }
        if (isSuccess()) {// 判斷是否拼圖成功
            int i = JOptionPane.showConfirmDialog(this, "成功，再來一局？", "拼圖成功",
                    JOptionPane.YES_NO_OPTION);// 提示成功
            if (i == JOptionPane.YES_OPTION) {
                random();// 開始新一局
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    /**
     * 判斷是否拼圖成功
     * 
     * @return 布爾值
     */
    public boolean isSuccess() {
        for (int i = 0; i < cells.length; i++) {// 檢查所有單元圖片
            int x = cells[i].getX();// 獲得x座標
            int y = cells[i].getY();// 獲得y座標
            if (i != 0) {
                if (y / Cell.IMAGEWIDTH * 3 + x / Cell.IMAGEWIDTH != cells[i]
                        .getPlace()) {// 判斷單元圖片位置是否正確
                    return false;// 只要有一個單元圖片的位置不正確，就傳回false
                }
            }
        }
        return true;// 所有單元圖片的位置都正確傳回true
    }
}
