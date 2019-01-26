package server_function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import client_function.MainBorder_refreshBook;
import server.ServerBorder;
import util.Server_tips;
import util.JdbcUtils;

public class Remind implements Runnable {

	BufferedReader br;
	PrintStream ps;
	
	@Override
	public void run() {
		Timer timer = new Timer();
		int r;
		try {
			ServerBorder.socket2 = ServerBorder.server2.accept();		//响应客户端的请求
			br = new BufferedReader(new InputStreamReader(ServerBorder.socket2.getInputStream()));
			ps = new PrintStream(ServerBorder.socket2.getOutputStream());
			int second = Integer.parseInt(br.readLine());		//从客户端接收订房时间
//			System.out.println("second = " + second);
			String room  = br.readLine();						//从客户端接收房间号
//			System.out.println("room = " + room);
			r = Integer.parseInt(room);
			String name = br.readLine();						//从客户端接收用户名
			new Server_tips(ServerBorder.frame, name + " 已到达 " + room + " 房间!");
			if(second != 0) {
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						new Server_tips(ServerBorder.frame, room + "房间的用户 " + name + " 的订房时间已到!");
						Connection con = null;
						Statement st = null;
						ResultSet rs = null;
						try {
							con = JdbcUtils.getConnection();
							st = con.createStatement();
							//更改用户表的数据库信息,将用户请出房间
							String sql = "update users set roomNumber = 0, isThrough = false, time = 0 where name = '"+ name +"'";
							st.executeUpdate(sql);
							//更改房间表的数据库信息,将到预定时间的房间腾空
							String sql2 = "update room set book = false where roomName = '"+ r +"'";
							st.executeUpdate(sql2);
						}catch(SQLException s) {
							s.printStackTrace();
						}finally {
							JdbcUtils.release(con, st, rs);
						}
					}
				},second);
			}else {
				System.out.println("second = " + second);	//测试秒数
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
