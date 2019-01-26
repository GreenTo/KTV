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

	public String str1 = "张敬轩";
	@Override
	public void actionPerformed(ActionEvent e) {
		String s1 = ForgetBorder.tf1.getText().trim();		//原密码
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
						new Client_tips(ForgetBorder.frame, "您的密码是:" + rs.getString("password"));
						b = false;
					}
				}
				if(b) {
					new Client_tips(ForgetBorder.frame, "用户名不存在!");
				}
			}catch(SQLException s) {
				s.printStackTrace();
			}finally {
				JdbcUtils.release(con, st, rs);
			}
		}else {
			new Client_tips(ForgetBorder.frame, "回答错误,请重新输入!");
		}
		ForgetBorder.tf1.setText(null);
		ForgetBorder.tf2.setText(null);
	}

}
//new ActionListener() {	//确定
//	public void actionPerformed(ActionEvent e) {
//		String s1 = tf1.getText().trim();		//原密码
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
//						new Dialog1(frame, "您的密码是:" + rs.getString("password"));
//						b = false;
//					}
//				}
//				if(b) {
//					new Dialog1(frame, "用户名不存在!");
//				}
//			}catch(SQLException s) {
//				s.printStackTrace();
//			}finally {
//				JdbcUtils.release(con, st, rs);
//			}
//		}else {
//			new Dialog1(frame, "回答错误,请重新输入!");
//		}
//		tf1.setText(null);
//		tf2.setText(null);
//	}
//}