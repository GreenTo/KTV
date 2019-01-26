package server_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.ServerBorder;
import util.JdbcUtils;

public class FuzzySearch implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {		//搜索用户功能
		String name = ServerBorder.user.getText().trim();
		ServerBorder.user.setText("");
		ServerBorder.users.removeAllItems();
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
//			匹配任何带'name'的用户
			String sql = "select name,money,roomNumber from users where name like '" + "%" + name + "%"+ "'";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ServerBorder.users.addItem(rs.getString("name"));		//将搜索用户的结果返回给下拉表框
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}
		JdbcUtils.release(con, st, rs);
	}

}

//new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		String name = user.getText().trim();
//		
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		try {
//			con = JdbcUtils.getConnection();
//			st = con.createStatement();
////			匹配任何带'name'的用户
//			String sql = "select name,money,roomNumber from users where name like '" + "%" + name + "%"+ "'";
//			rs = st.executeQuery(sql);
//			while(rs.next()) {
//				users.addItem(rs.getString("name"));		//将查询用户的结果返回给下拉表框
//			}
//		}catch(SQLException s) {
//			s.printStackTrace();
//		}
//		JdbcUtils.release(con, st, rs);
//	}
//}