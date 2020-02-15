package com.zzk;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.swtdesigner.SwingResourceManager;

/**
 * �C�����O
 * 
 * @author �i���[
 */
public class GamePanel extends JPanel implements MouseListener {
    private static final long serialVersionUID = -653831947783440122L;
    private Cell[] cells = new Cell[9];// �إ߳椸�Ϥ��}�C
    private Cell cellBlank = null;// �ť�
    
    // �غc��k
    public GamePanel() {
        super();
        setLayout(null);// �]�w�ŧG��
        init();// ��l��
    }
    
    // ��l�ƹC��
    public void init() {
        int num = 0;// �Ϥ��Ǹ�
        Icon icon = null;// �ϼйﹳ
        Cell cell = null;// �椸�Ϥ��ﹳ
        for (int i = 0; i < 3; i++) {// �`����
            for (int j = 0; j < 3; j++) {// �`���C
                num = i * 3 + j;// �p��Ϥ��Ǹ�
                icon = SwingResourceManager.getIcon(GamePanel.class, "/pic/"
                        + (num + 1) + ".jpg");// ��o�Ϥ�
                cell = new Cell(icon, num);// ��ҤƳ椸�Ϥ��ﹳ
                cell.setLocation(j * Cell.IMAGEWIDTH, i * Cell.IMAGEWIDTH);// �]�w�椸�Ϥ����y��
                cells[num] = cell;// �N�椸�Ϥ��x�s��椸�Ϥ��}�C��
            }
        }
        for (int i = 0; i < cells.length; i++) {
            this.add(cells[i]);// �V���O���W�[�Ҧ��椸�Ϥ�
        }
    }
    
    /**
     * ��Ϥ��i���H���Ƨ�
     */
    public void random() {
        Random rand = new Random();// ��Ҥ�Random
        int m, n, x, y;
        if (cellBlank == null) {// �P�_�ťժ��Ϥ���m�O�_����
            cellBlank = cells[cells.length - 1];// ���X�ťժ��Ϥ�
            for (int i = 0; i < cells.length; i++) {// �ˬd�Ҧ��椸�Ϥ�
                if (i != cells.length - 1) {
                    cells[i].addMouseListener(this);// ��D�ťչϤ����U���к�ť
                }
            }
        }
        for (int i = 0; i < cells.length; i++) {// �ˬd�Ҧ��椸�Ϥ�
            m = rand.nextInt(cells.length);// �����H����
            n = rand.nextInt(cells.length);// �����H����
            x = cells[m].getX();// ��ox�y��
            y = cells[m].getY();// ��oy�y��
            // ��椸�Ϥ��մ�
            cells[m].setLocation(cells[n].getX(), cells[n].getY());
            cells[n].setLocation(x, y);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Cell cell = (Cell) e.getSource();// ��oĲ�o�ɶ�����H
        int x = cellBlank.getX();// ��ox�y��
        int y = cellBlank.getY();// ��oy�y��
        if ((x - cell.getX()) == Cell.IMAGEWIDTH && cell.getY() == y) {
            cell.move(Direction.RIGHT);// �V�k����
            cellBlank.move(Direction.LEFT);
        } else if ((x - cell.getX()) == -Cell.IMAGEWIDTH && cell.getY() == y) {
            cell.move(Direction.LEFT);// �V������
            cellBlank.move(Direction.RIGHT);
        } else if (cell.getX() == x && (cell.getY() - y) == Cell.IMAGEWIDTH) {
            cell.move(Direction.UP);// �V�W����
            cellBlank.move(Direction.DOWN);
        } else if (cell.getX() == x && (cell.getY() - y) == -Cell.IMAGEWIDTH) {
            cell.move(Direction.DOWN);// �V�U����
            cellBlank.move(Direction.UP);
        }
        if (isSuccess()) {// �P�_�O�_���Ϧ��\
            int i = JOptionPane.showConfirmDialog(this, "���\�A�A�Ӥ@���H", "���Ϧ��\",
                    JOptionPane.YES_NO_OPTION);// ���ܦ��\
            if (i == JOptionPane.YES_OPTION) {
                random();// �}�l�s�@��
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
     * �P�_�O�_���Ϧ��\
     * 
     * @return ������
     */
    public boolean isSuccess() {
        for (int i = 0; i < cells.length; i++) {// �ˬd�Ҧ��椸�Ϥ�
            int x = cells[i].getX();// ��ox�y��
            int y = cells[i].getY();// ��oy�y��
            if (i != 0) {
                if (y / Cell.IMAGEWIDTH * 3 + x / Cell.IMAGEWIDTH != cells[i]
                        .getPlace()) {// �P�_�椸�Ϥ���m�O�_���T
                    return false;// �u�n���@�ӳ椸�Ϥ�����m�����T�A�N�Ǧ^false
                }
            }
        }
        return true;// �Ҧ��椸�Ϥ�����m�����T�Ǧ^true
    }
}
