package client_function;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import client.ForgetBorder;
import util.Client_tips;
import util.JdbcUtils;


public class Forget_confirm implements ActionListener {

	public String str1 = "�ž���";
	@Override
	public void actionPerformed(ActionEvent e) {
		String s1 = ForgetBorder.tf1.getText().trim();		//ԭ����
		String s2 = ForgetBorder.tf2.getText().trim();		
		String name = ForgetBorder.userName.getText().trim();
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		if(str1.equals(s1) && !s2.equals("")) {
			try {
				con = JdbcUtils.getConnection();
				st = con.createStatement();
				String sql = "select name,password from users ";
				rs = st.executeQuery(sql);
				boolean b = true; 
				while(rs.next()) {
					if(rs.getString("name").equals(name)) {
						new Client_tips(ForgetBorder.frame, "����������:" + rs.getString("password"));
						b = false;
					}
				}
				if(b) {
					new Client_tips(ForgetBorder.frame, "�û���������!");
				}
			}catch(SQLException s) {
				s.printStackTrace();
			}finally {
				JdbcUtils.release(con, st, rs);
			}
		}else {
			new Client_tips(ForgetBorder.frame, "�ش����,����������!");
		}
		ForgetBorder.tf1.setText(null);
		ForgetBorder.tf2.setText(null);
	}

}
//new ActionListener() {	//ȷ��
//	public void actionPerformed(ActionEvent e) {
//		String s1 = tf1.getText().trim();		//ԭ����
//		String s2 = tf2.getText().trim();		
//		String name = userName.getText().trim();
//		
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		if(str1.equals(s1) && !str2.equals("")) {
//			try {
//				con = JdbcUtils.getConnection();
//				st = con.createStatement();
//				String sql = "select name,password from users ";
//				rs = st.executeQuery(sql);
//				boolean b = true; 
//				while(rs.next()) {
//					if(rs.getString("name").equals(name)) {
//						new Dialog1(frame, "����������:" + rs.getString("password"));
//						b = false;
//					}
//				}
//				if(b) {
//					new Dialog1(frame, "�û���������!");
//				}
//			}catch(SQLException s) {
//				s.printStackTrace();
//			}finally {
//				JdbcUtils.release(con, st, rs);
//			}
//		}else {
//			new Dialog1(frame, "�ش����,����������!");
//		}
//		tf1.setText(null);
//		tf2.setText(null);
//	}
//}