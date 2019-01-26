package server_function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import server.ServerBorder;

public class Receive_login implements Runnable {

	BufferedReader br;
	PrintStream ps;
	
	@Override
	public void run() {
			
			try {
				ServerBorder.socket3 = ServerBorder.server3.accept();		//响应客户端的请求
				Date date = new Date();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				
				br = new BufferedReader(new InputStreamReader(ServerBorder.socket3.getInputStream()));
				String name = br.readLine();
				if("true".equals(br.readLine())) {
					ServerBorder.textArea.append("用户 "+ name + " 已登录客户端. " + df.format(date) + "\n");
				}else {
					ServerBorder.textArea.append("用户 "+ name + " 已退出客户端. " + df.format(date) + "\n");
				}
			}catch(IOException i){
				i.printStackTrace();
			}
	}

}
