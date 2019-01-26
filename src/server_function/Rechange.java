package server_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.ServerBorder;
import util.JdbcUtils;
import util.Server_tips;

public class Rechange implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = (String) ServerBorder.users.getSelectedItem();
		String money = ServerBorder.count.getText().trim(); 
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		if(!name.equals("房间号")) {
			try {
				con = JdbcUtils.getConnection();
				st = con.createStatement();
				String sql = "update users set money = money +'" + money + "' where name = '"+ name +"'";
				if(st.executeUpdate(sql) != 0) {
					new Server_tips(ServerBorder.frame, "充值成功!");
				}else {
					new Server_tips(ServerBorder.frame, "充值失败!请确认用户名正确和充值金额正确.");
				}
			}catch(SQLException s) {
				s.printStackTrace();
			}finally {
				ServerBorder.count.setText(null);
				ServerBorder.users.setSelectedIndex(0);
				JdbcUtils.release(con, st, rs);
			}
		}
	}
}

//new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		String name = (String) users.getSelectedItem();
//		String money = count.getText(); 
//		
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		if(!name.equals("房间号")) {
//			try {
//				con = JdbcUtils.getConnection();
//				st = con.createStatement();
//				String sql = "update users set money = money +'" + money + "' where name = '"+ name +"'";
//				if(st.executeUpdate(sql) != 0) {
//					new Server_tips(frame, "充值成功!");
//				}else {
//					new Server_tips(frame, "充值失败!请确认用户名正确和充值金额正确.");
//				}
//				count.setText(null);
//			}catch(SQLException s) {
//				s.printStackTrace();
//			}finally {
//				users.setSelectedIndex(0);
//				JdbcUtils.release(con, st, rs);
//			}
//		}
//	}
//}