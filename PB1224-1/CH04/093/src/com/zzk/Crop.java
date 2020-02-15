package com.zzk;

import javax.swing.JLabel;
import com.swtdesigner.SwingResourceManager;

/**
 * @author 張振坤
 *
 */
public class Crop extends JLabel {

	public Crop() {
		super();
	}
	public void setIcon(String picture){
		setIcon(SwingResourceManager.getIcon(Crop.class, picture));//設定元件要顯示的圖標，用於顯示作物的狀態
	}

}
