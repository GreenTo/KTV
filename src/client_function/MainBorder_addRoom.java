package client_function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.JdbcUtils;
import client.MainBorder;

public class MainBorder_addRoom {		//往下拉表框添加房间

	public MainBorder_addRoom() {
		MainBorder.cb.removeAllItems();
		MainBorder.cb.addItem("房间号");
//		查询数据库的room表来获得房间号
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();		
			st = con.createStatement();
			String sql = "select roomName from room";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				MainBorder.cb.addItem(rs.getInt("roomName")+"");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
	}
}
