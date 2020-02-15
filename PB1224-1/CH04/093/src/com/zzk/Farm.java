package com.zzk;

/**
 * @author �i���[
 */
public class Farm {
    public int state = 0; // �@�������A 0��ܥ����ءF1��ܼ��ءF2��ܥͪ��F3��ܶ}��F4��ܵ��G
    
    /**
     * ����
     * 
     * @param picture
     */
    public String seed(Crop crop, String picture) {
        String returnValue = ""; // �Ǧ^���ܼ�
        if (state == 0) { // �P�_�@�������A�O�_��������
            crop.setIcon(picture); // ���ا@��
            state = 1; // �]�w���A�ݩʬ�����
        } else {
            returnValue = getMessage() + "�A���༽�ءI"; // �]�w���ܸ�T
        }
        return returnValue;
    }
    
    /**
     * �ͪ�
     * 
     * @param crop
     * @param picture
     */
    public String grow(Crop crop, String picture) {
        String returnValue = ""; // �Ǧ^���ܼ�
        if (state == 1) { // �P�_�@�������A�O�_������
            crop.setIcon(picture); // �]�w�@�����ͪ����A
            state = 2; // �]�w���A�ݩʬ��ͪ�
        } else {
            returnValue = getMessage() + "�A����ͪ��I"; // �]�w���ܸ�T
        }
        return returnValue;
    }
    
    /**
     * �}��
     * 
     * @param crop
     * @param picture
     */
    public String bloom(Crop crop, String picture) {
        String returnValue = ""; // �Ǧ^���ܼ�
        if (state == 2) { // �P�_�@�������A�O�_���ͪ�
            crop.setIcon(picture); // �]�w�@�����}�᪬�A
            state = 3; // �]�w���A�ݩʬ��}��
        } else {
            returnValue = getMessage() + "�A����}��I"; // �]�w���ܸ�T
        }
        return returnValue;
    }
    
    /**
     * ���G
     * 
     * @param crop
     * @param picture
     */
    public String fruit(Crop crop, String picture) {
        String returnValue = ""; // �Ǧ^���ܼ�
        if (state == 3) { // �P�_�@�������A�O�_���}��
            crop.setIcon(picture); // �]�w�@�������G���A
            state = 4; // �]�w���A�ݩʬ����G
        } else {
            returnValue = getMessage() + "�A���൲�G�I"; // �]�w���ܸ�T
        }
        return returnValue;
    }
    
    /**
     * ��ì
     * 
     * @param crop
     * @param picture
     */
    public String harvest(Crop crop, String picture) {
        String returnValue = ""; // �Ǧ^���ܼ�
        if (state == 4) { // �P�_�@�������A�O�_�����G
            crop.setIcon(picture); // �]�w�@���������ت��A
            state = 0; // �]�w���A�ݩʬ�������
        } else {
            returnValue = getMessage() + "�A���বì�I"; // �]�w���ܸ�T
        }
        return returnValue;
    }
    
    /**
     * �ھڧ@�������A�ݩʡA�T�w���������ܸ�T
     * 
     * @return
     */
    public String getMessage() {
        String message = "";
        switch (state) {
            case 0:
                message = "�@���٨S������";
                break;
            case 1:
                message = "�@����輽��";
                break;
            case 2:
                message = "�@�����b�ͪ�";
                break;
            case 3:
                message = "�@�����B��}���";
                break;
            case 4:
                message = "�@���w�g���G";
                break;
        }
        return message;
    }
}
