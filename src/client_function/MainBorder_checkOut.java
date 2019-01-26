package client_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import client.MainBorder;
import util.Client_tips;
import util.JdbcUtils;

public class MainBorder_checkOut implements ActionListener {//退房功能

	@Override
	public void actionPerformed(ActionEvent e) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int num;
		
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
			//从数据库中获取用户表的订房信息
			String sql = "select roomNumber from users where name = '"+ MainBorder.name +"'";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				if((num = rs.getInt("roomNumber")) != 0) {	
					//若用户有订房,将数据库中用户表的订房信息清空并退房
					String sql2 = "update users set roomNumber = 0, isThrough = false, time = 0 where name = '"+ MainBorder.name +"'";
//					将数据库中房间表的房间信息更新
					String sql3 = "update room set book = false where roomName = '"+ num +"'";
					st.executeUpdate(sql2);
					st.executeUpdate(sql3);
					new Client_tips(MainBorder.frame, "退房成功!");
					MainBorder.book.setVisible(true);
					new MainBorder_refreshBook();		//刷新订房信息
					MainBorder.bookedMessage.setVisible(false);
				}else {
					new Client_tips(MainBorder.frame, "您并没有订房!");
				}
				break;
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
		
	}

}

//new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		int num;
//		
//		try {
//			con = JdbcUtils.getConnection();
//			st = con.createStatement();
//			//从数据库中获取用户表的订房信息
//			String sql = "select roomNumber from users where name = '"+ name +"'";
//			rs = st.executeQuery(sql);
//			while(rs.next()) {
//				if((num = rs.getInt("roomNumber")) != 0) {	
//					//若用户有订房,将数据库中用户表的订房信息清空并退房
//					String sql2 = "update users set roomNumber = 0, isThrough = false, time = 0 where name = '"+ name +"'";
////					将数据库中房间表的房间信息更新
//					String sql3 = "update room set book = false where roomName = '"+ num +"'";
//					st.executeUpdate(sql2);
//					st.executeUpdate(sql3);
//					new Dialog1(frame, "退房成功!");
//				}else {
//					new Dialog1(frame, "您并没有订房!");
//				}
//				break;
//			}
//		}catch(SQLException s) {
//			s.printStackTrace();
//		}
//		JdbcUtils.release(con, st, rs);
//	}
//}