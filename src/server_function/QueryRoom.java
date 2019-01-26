package server_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import server.ServerBorder;
import util.JdbcUtils;

public class QueryRoom implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		int room = Integer.parseInt((String) ServerBorder.bookedRoom.getSelectedItem());
		ArrayList<Integer> list = new ArrayList<>();
		boolean b = true;
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
			String sql1 = "select roomName from room where book = true";
			rs = st.executeQuery(sql1);
			while(rs.next()) {
				list.add(rs.getInt("roomName"));
			}
			for (Integer integer : list) {
				if(integer == room) {
					b = false;
				}
			}
			if(b) {
				ServerBorder.roomMessage.setText("此房间无人预订");
			}else {
				String sql2 = "select name,money,time from users where roomNumber = '"+ room +"'";
				rs = st.executeQuery(sql2);
				while(rs.next()) {
					String str = rs.getString("name")  + "  时间:" + rs.getDouble("time") + "/h  余额:" + rs.getDouble("money") + "元";
					ServerBorder.roomMessage.setText(str);
				}
			}
			ServerBorder.roomMessage.setVisible(true);
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			ServerBorder.bookedRoom.setSelectedIndex(0);
			JdbcUtils.release(con, st, rs);
		}
	}

}

//new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		int room = Integer.parseInt((String) bookedRoom.getSelectedItem());
//		ArrayList<Integer> list = new ArrayList<>();
//		boolean b = true;
//		
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		try {
//			con = JdbcUtils.getConnection();
//			st = con.createStatement();
//			String sql1 = "select roomName from room where book = true";
//			rs = st.executeQuery(sql1);
//			while(rs.next()) {
//				list.add(rs.getInt("roomName"));
//			}
//			for (Integer integer : list) {
//				if(integer == room) {
//					b = false;
//				}
//			}
//			if(b) {
//				roomMessage.setText("此房间无人预订");
//			}else {
//				String sql2 = "select name,money,time from users where roomNumber = '"+ room +"'";
//				rs = st.executeQuery(sql2);
//				while(rs.next()) {
//					String str = rs.getString("name")  + "  时间:" + rs.getDouble("time") + "  余额:" + rs.getDouble("money");
//					roomMessage.setText(str);
//				}
//			}
//		}catch(SQLException s) {
//			s.printStackTrace();
//		}finally {
//			bookedRoom.setSelectedIndex(0);
//			JdbcUtils.release(con, st, rs);
//		}
//	}
//}