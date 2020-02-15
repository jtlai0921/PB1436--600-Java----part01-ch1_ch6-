package com.zzk;

/**
 * @author 張振坤
 * 產生65~90之間隨機整數的類別
 */
public class RandomBuildLetter {
    public RandomBuildLetter() {
        super();
    }
    
    public int[] getLetter(int letterCounts) {
        int[] letter = new int[letterCounts]; // 根據參數建立整數陣列
        for (int i = 0; i < letterCounts; i++) {
            int a = (int) getRandomLetter(); // 呼叫方法產生65~90之間的隨機整數，即大寫字母A-Z的ASCII值
            letter[i] = a; // 將產生的數給予值給陣列
        }
        return letter; // 傳回陣列對像
    }
    
    public int getRandomLetter() {
        int letter = (int) (Math.random() * 26) + 65; // 產生65~90之間的隨機整數，即大寫字母的ASCII值
        return letter;
    }
}
