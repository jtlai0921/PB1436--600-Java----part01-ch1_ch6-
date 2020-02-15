package com.zzk;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import jbarcodebean.JBarcodeBean;

public class PrintCodingFrame extends JFrame {
    
    private JTextField textField;
    private CodePanel codePanel;
    public static PrintCodingFrame frame;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        frame = new PrintCodingFrame();
        frame.setVisible(true);
    }
    
    /**
     * Create the frame
     */
    public PrintCodingFrame() {
        super();
        setTitle("批次列印條形碼");
        setBounds(100, 100, 760, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane);
        
        final JPanel panel = new JPanel();
        scrollPane.setViewportView(panel);
        
        codePanel = new CodePanel();
        panel.add(codePanel);
        
        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);
        
        final JLabel label = new JLabel();
        label.setText("輸入13位以內的編號：");
        panel_1.add(label);
        
        textField = new JTextField();
        textField.addCaretListener(new CaretListener() {
            public void caretUpdate(final CaretEvent arg0) {
                String text = textField.getText();// 獲得純文字框內容
                Component[] codes = codePanel.getComponents();// 獲得面板所有元件
                for (Component component : codes) {// 檢查元件陣列
                    if (component instanceof JBarcodeBean) {// 如果元件是條形碼元件
                        JBarcodeBean bean = (JBarcodeBean) component;// 強制轉為條形碼對像
                        bean.setCode(text);// 設定元件的寫程式
                    }
                }
                codePanel.repaint();
            }
        });
        textField.setColumns(15);
        panel_1.add(textField);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // 建立列印預覽交談視窗
                PreviewDialog dialog = new PreviewDialog(PrintCodingFrame.this, true);
                dialog.setSize(600, 400);// 設定交談視窗大小
                dialog.setVisible(true);// 顯示交談視窗
            }
        });
        button_1.setText("列印預覽");
        panel_1.add(button_1);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                codePanel.pageSetup();// 呼叫頁面設定方法
            }
        });
        button.setText("頁面設定");
        panel_1.add(button);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                codePanel.doPrint();// 呼叫列印方法
            }
        });
        button_2.setText("列印");
        panel_1.add(button_2);
        //
    }
    
    public CodePanel getCodePanel() {
        return codePanel;
    }
    
}
