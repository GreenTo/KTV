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

public class MainBorder_queryMoney implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select money from users where name = '" + MainBorder.name + "'");
			while(rs.next()) {
				new Client_tips(MainBorder.frame, "�������Ϊ:  " + rs.getDouble("money") + "Ԫ");
			}
		}catch (SQLException s) {
			s.printStackTrace();
		}
		JdbcUtils.release(con, st, rs);
	}

}

//new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		try {
//			con = JdbcUtils.getConnection();
//			st = con.createStatement();
//			rs = st.executeQuery("select money from users where name = '" + name + "'");
//			while(rs.next()) {
//				new Dialog1(frame, "�������Ϊ:  " + rs.getDouble("money") + "Ԫ");
//			}
//		}catch (SQLException s) {
//			s.printStackTrace();
//		}
//		JdbcUtils.release(con, st, rs);
//	}
//}