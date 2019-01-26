package client_function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.JdbcUtils;
import client.MainBorder;

public class MainBorder_addRoom {		//�����������ӷ���

	public MainBorder_addRoom() {
		MainBorder.cb.removeAllItems();
		MainBorder.cb.addItem("�����");
//		��ѯ���ݿ��room������÷����
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
