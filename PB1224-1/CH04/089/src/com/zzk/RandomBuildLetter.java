package com.zzk;

/**
 * @author �i���[
 * ����65~90�����H����ƪ����O
 */
public class RandomBuildLetter {
    public RandomBuildLetter() {
        super();
    }
    
    public int[] getLetter(int letterCounts) {
        int[] letter = new int[letterCounts]; // �ھڰѼƫإ߾�ư}�C
        for (int i = 0; i < letterCounts; i++) {
            int a = (int) getRandomLetter(); // �I�s��k����65~90�������H����ơA�Y�j�g�r��A-Z��ASCII��
            letter[i] = a; // �N���ͪ��Ƶ����ȵ��}�C
        }
        return letter; // �Ǧ^�}�C�ﹳ
    }
    
    public int getRandomLetter() {
        int letter = (int) (Math.random() * 26) + 65; // ����65~90�������H����ơA�Y�j�g�r����ASCII��
        return letter;
    }
}
