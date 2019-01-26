package server_function;

import java.util.Timer;
import java.util.TimerTask;

public class Intervalometer {					//��ʱ��
	public Intervalometer() {					//�ӳ�  �����ִ��,ÿ ����ִ��һ��
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				new Thread(new ReceiveApply_event()).start();
				new Thread(new Remind()).start();
				new Thread(new Receive_login()).start();
				new RoomMessage();
			}
		}, 0, 10000);
	}
}
