package client_function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.Socket;

import client.MainBorder;
import client.UpdateBorder;
import server.ServerBorder;

public class Update_receive implements Runnable{
	@Override
	public void run() {
		try {
			MainBorder.socket3 = MainBorder.server3.accept();
			BufferedReader br = new BufferedReader(new InputStreamReader(MainBorder.socket3.getInputStream()));
			String str = br.readLine();
			System.out.println("更新:" + str);
			new UpdateBorder(str);
			MainBorder.socket3.close();
		}catch(IOException | NullPointerException i) {
//			i.printStackTrace();
		}
		
	}
}

//try {
//	DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);//创建Packet相当于创建集装箱
//	MainBorder.socket2.receive(packet);									//接货,接收数据
//	System.out.println(111111111);
//	byte[] arr = packet.getData();							//获取数据
//	String str = new String(arr);
//	UpdateBorder.label.setText("sssssssss");
//	new UpdateBorder(str + "");
//	MainBorder.socket2.close();
//} catch (IOException e) {	
//	e.printStackTrace();
//}	