package client_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import util.Client_tips;
import util.JdbcUtils;
import client.LoginBorder;
import client.RegisterBorder;
public class Register_confirm implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String user = RegisterBorder.tf1.getText().trim();	 //用户名
		String password = RegisterBorder.tf2.getText().trim(); //密码
		if("".equals(user)||"".equals(password)) {
			new Client_tips(RegisterBorder.frmktv,"请输入用户名和密码!");
		}else {
			try {
				con = JdbcUtils.getConnection();
				st = con.createStatement();
				rs = st.executeQuery("select name from users");
				boolean b = true;						//若存在则b = false;不存入数据库
				while(rs.next()) {						//判断用户是否已经存在,若存在则重新输入!
					if(rs.getString("name").equals(user)) {
						new Client_tips(RegisterBorder.frmktv,"用户名已存在,请重新输入!");
						b = false;
						break;
					}
				}
				if(b) {										//若用户不存在,则注册
					if(Pattern.matches(".{1,5}", user)) {		//若密码都是数字或字母
						if(Pattern.matches("^[0-9a-zA-Z]{1,8}$", password)) {	
							String sql = "insert into users (name,password) values('" + user + "','" + password + "')";
							st.executeUpdate(sql);
							new Client_tips(RegisterBorder.frmktv,"注册成功");
						}
					}else {
						new Client_tips(RegisterBorder.frmktv, "用户名小于5位.密码小于8位,只能输入数字或字母");
					}
				}
			}catch(SQLException x) {
				x.printStackTrace();
			}finally {
				RegisterBorder.tf1.setText(null);
				RegisterBorder.tf2.setText(null);
				JdbcUtils.release(con, st, rs); 				//关流
			}
		}	
	}
}
//new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		String user = tf1.getText().trim();		//用户名
//		String password = tf2.getText().trim(); //密码
//		if("".equals(user)||"".equals(password)) {
//			new Dialog1(frmktv,"请输入用户名和密码!");
//			tf1.setText("");				//清空用户名和密码
//			tf2.setText("");
//		}else {
//			try {
//				con = JdbcUtils.getConnection();
//				st = con.createStatement();
//				rs = st.executeQuery("select name from users");
//				boolean b = true;						//若存在则b = false;不存入数据库
//				while(rs.next()) {						//判断用户是否已经存在,若存在则重新输入!
//					if(rs.getString("name").equals(user)) {
//						new Dialog1(frmktv,"用户名已存在,请重新输入!");
//						b = false;
//						break;
//					}
//				}
//				if(b) {										//若用户不存在,则注册
//					String sql = "insert into users (name,password) values('" + user + "','" + password + "')";
//					st.executeUpdate(sql);
//					new Dialog1(frmktv,"注册成功");
//				}
//			}catch(SQLException x) {
//				x.printStackTrace();
//			}finally {
//				tf1.setText(null);
//				tf2.setText(null);
//				JdbcUtils.release(con, st, rs); 				//关流
//			}
//		}
//	}
//}