package client_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import client.MainBorder;
import client.UpdateBorder;

public class MainBorder_Update implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = MainBorder.name;
		MainBorder.frame.dispose();
		UpdateBorder.frmKtv.dispose();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				new MainBorder(name, "");
			}
		},3000);
	}

}

//new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		String name = MainBorder.name;
//		MainBorder.frame.dispose();
//		frmKtv.dispose();
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//			public void run() {
//				new MainBorder(name, "");
//			}
//		},3000);
//	}
//}