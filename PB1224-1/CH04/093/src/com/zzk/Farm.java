package com.zzk;

/**
 * @author 張振坤
 */
public class Farm {
    public int state = 0; // 作物的狀態 0表示未播種；1表示播種；2表示生長；3表示開花；4表示結果
    
    /**
     * 播種
     * 
     * @param picture
     */
    public String seed(Crop crop, String picture) {
        String returnValue = ""; // 傳回值變數
        if (state == 0) { // 判斷作物的狀態是否為未播種
            crop.setIcon(picture); // 播種作物
            state = 1; // 設定狀態屬性為播種
        } else {
            returnValue = getMessage() + "，不能播種！"; // 設定提示資訊
        }
        return returnValue;
    }
    
    /**
     * 生長
     * 
     * @param crop
     * @param picture
     */
    public String grow(Crop crop, String picture) {
        String returnValue = ""; // 傳回值變數
        if (state == 1) { // 判斷作物的狀態是否為播種
            crop.setIcon(picture); // 設定作物為生長狀態
            state = 2; // 設定狀態屬性為生長
        } else {
            returnValue = getMessage() + "，不能生長！"; // 設定提示資訊
        }
        return returnValue;
    }
    
    /**
     * 開花
     * 
     * @param crop
     * @param picture
     */
    public String bloom(Crop crop, String picture) {
        String returnValue = ""; // 傳回值變數
        if (state == 2) { // 判斷作物的狀態是否為生長
            crop.setIcon(picture); // 設定作物為開花狀態
            state = 3; // 設定狀態屬性為開花
        } else {
            returnValue = getMessage() + "，不能開花！"; // 設定提示資訊
        }
        return returnValue;
    }
    
    /**
     * 結果
     * 
     * @param crop
     * @param picture
     */
    public String fruit(Crop crop, String picture) {
        String returnValue = ""; // 傳回值變數
        if (state == 3) { // 判斷作物的狀態是否為開花
            crop.setIcon(picture); // 設定作物為結果狀態
            state = 4; // 設定狀態屬性為結果
        } else {
            returnValue = getMessage() + "，不能結果！"; // 設定提示資訊
        }
        return returnValue;
    }
    
    /**
     * 收穫
     * 
     * @param crop
     * @param picture
     */
    public String harvest(Crop crop, String picture) {
        String returnValue = ""; // 傳回值變數
        if (state == 4) { // 判斷作物的狀態是否為結果
            crop.setIcon(picture); // 設定作物為未播種狀態
            state = 0; // 設定狀態屬性為未播種
        } else {
            returnValue = getMessage() + "，不能收穫！"; // 設定提示資訊
        }
        return returnValue;
    }
    
    /**
     * 根據作物的狀態屬性，確定對應的提示資訊
     * 
     * @return
     */
    public String getMessage() {
        String message = "";
        switch (state) {
            case 0:
                message = "作物還沒有播種";
                break;
            case 1:
                message = "作物剛剛播種";
                break;
            case 2:
                message = "作物正在生長";
                break;
            case 3:
                message = "作物正處於開花期";
                break;
            case 4:
                message = "作物已經結果";
                break;
        }
        return message;
    }
}
