package client_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import client.MainBorder;
import util.Client_tips;
import util.JdbcUtils;

public class MainBorder_arrive implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;       
		int num;
		double time;
		
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
//			查看数据库的用户表的订房信息
			String sql = "select roomNumber, time from users where name = '"+ MainBorder.name +"'";
			rs = st.executeQuery(sql);
			while(rs.next()){
				num = rs.getInt("roomNumber");			//用户的房间号
				time = rs.getDouble("time");			//用户的订房时间
				if(num == 0) {
					new Client_tips(MainBorder.frame, "您并没有订房间!");
				}else {
//					若用户有订房,扣除订房所需的费用
					String sql2 = "update users set money = money - '"+ num/100*time*100 +"' where name = '"+ MainBorder.name +"'";
					MainBorder.second = (int)time * 3000;						//设置倒计时秒数,一个小时5秒
					MainBorder.roomNumber = num;
					st.executeUpdate(sql2);
					
					Socket socket = new Socket("127.0.0.1", 5555);
//					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintStream ps = new PrintStream(socket.getOutputStream());
					ps.println(MainBorder.second);						//向服务端发送订房时间
					ps.println(MainBorder.roomNumber);					//向服务端发送房间号
					ps.println(MainBorder.name);						//向服务端发送用户名
					socket.close();
					
					new Client_tips(MainBorder.frame, "扣费成功!现在开始计时.");
					MainBorder.bookedMessage.setVisible(false);
					MainBorder.book.setVisible(true);
				}
			}
		}catch(Exception s) {
//			s.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
	}

}

//new ActionListener() {				//确认到达功能
//	public void actionPerformed(ActionEvent e) {
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;       
//		int num;
//		double time;
//		
//		try {
//			con = JdbcUtils.getConnection();
//			st = con.createStatement();
////			查看数据库的用户表的订房信息
//			String sql = "select roomNumber, time from users where name = '"+ name +"'";
//			rs = st.executeQuery(sql);
//			while(rs.next()){
//				num = rs.getInt("roomNumber");			//用户的房间号
//				time = rs.getDouble("time");			//用户的订房时间
//				if(num == 0) {
//					new Dialog1(frame, "您并没有订房间!");
//				}else {
////					若用户有订房,扣除订房所需的费用
//					String sql2 = "update users set money = money - '"+ num/100*time*100 +"' where name = '"+ name +"'";
//					second = (int)time * 5000;						//设置倒计时秒数,一个小时5秒
//					roomNumber = num;
//					st.executeUpdate(sql2);
//					
//					Socket socket = new Socket("127.0.0.1", 5555);
////					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//					PrintStream ps = new PrintStream(socket.getOutputStream());
//					ps.println(second);						//向服务端发送订房时间
//					ps.println(roomNumber);					//向服务端发送房间号
//					ps.println(name);						//向服务端发送用户名
//					socket.close();
//					
//					new Dialog1(frame, "扣费成功!现在开始计时.");
//				}
//			}
//		}catch(Exception s) {
//			s.printStackTrace();
//		}finally {
//			JdbcUtils.release(con, st, rs);
//		}
//	}
//}