package com.zzk;

import javax.swing.JLabel;
import com.swtdesigner.SwingResourceManager;

/**
 * @author �i���[
 *
 */
public class Crop extends JLabel {

	public Crop() {
		super();
	}
	public void setIcon(String picture){
		setIcon(SwingResourceManager.getIcon(Crop.class, picture));//�]�w����n��ܪ��ϼСA�Ω���ܧ@�������A
	}

}
