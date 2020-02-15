package com.zzk;

import java.awt.print.*;

/**
 * @author 張振坤
 *
 */
public class PrintDialogDemo {
    public static void main(String[] args) {
        PrinterJob job = PrinterJob.getPrinterJob(); // 獲得列印對像
        if (!job.printDialog()) { // 開啟列印交談視窗
            return; // 單擊列印交談視窗的取消按鈕或關閉列印交談視窗結束程式的執行
        }
        job.setJobName("測試列印交談視窗"); // 設定列印工作的名稱
        String jobName = job.getJobName(); // 獲得 列印工作的名稱
        System.out.println("列印工作的名稱是：  " + jobName);
    }
}
