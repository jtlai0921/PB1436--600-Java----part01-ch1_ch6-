package com.zzk;

import java.awt.print.*;

/**
 * @author �i���[
 *
 */
public class PrintDialogDemo {
    public static void main(String[] args) {
        PrinterJob job = PrinterJob.getPrinterJob(); // ��o�C�L�ﹳ
        if (!job.printDialog()) { // �}�ҦC�L��͵���
            return; // �����C�L��͵������������s�������C�L��͵��������{��������
        }
        job.setJobName("���զC�L��͵���"); // �]�w�C�L�u�@���W��
        String jobName = job.getJobName(); // ��o �C�L�u�@���W��
        System.out.println("�C�L�u�@���W�٬O�G  " + jobName);
    }
}
