package server_function;

import java.io.IOException;

import server.ReceiveApplyBorder;
import server.ServerBorder;

public class ReceiveApply_event implements Runnable {
	@Override
	public void run() {
		try {
			ServerBorder.socket = ServerBorder.server.accept();		//��Ӧ�ͻ��˵�����
			new ReceiveApplyBorder(ServerBorder.socket);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
