package server_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.ServerBorder;
import util.JdbcUtils;

public class QueryUser implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = (String) ServerBorder.users.getSelectedItem();
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		if(!name.equals("�û���")) {
			try {
				con = JdbcUtils.getConnection();
				st = con.createStatement();
				String sql = "select name,roomNumber,time,money from users where name = '"+ name +"'";
				rs = st.executeQuery(sql);
				while(rs.next()) {
					String str = rs.getString("name") + " �����:" + rs.getInt("roomNumber") + " ʱ��:" + rs.getDouble("time") + " ���:" + rs.getDouble("money");
					ServerBorder.userMessage.setText(str);
				}
				ServerBorder.userMessage.setVisible(true);
			}catch(SQLException s) {
				s.printStackTrace();
			}finally {
				JdbcUtils.release(con, st, rs);
			}
		}
	}
}


//new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		String name = (String) users.getSelectedItem();
//		
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		if(!name.equals("�û���")) {
//			try {
//				con = JdbcUtils.getConnection();
//				st = con.createStatement();
//				String sql = "select name,roomNumber,time,money from users where name = '"+ name +"'";
//				rs = st.executeQuery(sql);
//				while(rs.next()) {
//					String str = rs.getString("name") + " �����:" + rs.getInt("roomNumber") + " ʱ��:" + rs.getDouble("time") + " ���:" + rs.getDouble("money");
//					userMessage.setText(str);
//				}
//			}catch(SQLException s) {
//				s.printStackTrace();
//			}finally {
//				JdbcUtils.release(con, st, rs);
//			}
//		}
//	}
//}