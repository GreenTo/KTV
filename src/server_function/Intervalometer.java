package server_function;

import java.util.Timer;
import java.util.TimerTask;

public class Intervalometer {					//定时器
	public Intervalometer() {					//延迟  毫秒后执行,每 毫秒执行一次
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
