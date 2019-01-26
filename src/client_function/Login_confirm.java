package client_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import client.LoginBorder;
import client.MainBorder;
import util.Client_tips;
import util.JdbcUtils;

public class Login_confirm implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String name = LoginBorder.tf1.getText().trim();
		String password = LoginBorder.tf2.getText().trim(); 
		boolean b = true;
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select name,password from users");
			while(rs.next()) {
				if(name.equals(rs.getString("name"))) {
					if(password.equals(rs.getString("password"))) {
//								若用户名和密码都正确,进入KTV主界面
						b = false;
						new MainBorder(name, password);
						LoginBorder.frame.dispose();
						break;
					}
				}
			}
			if(b) {		
				new Client_tips(LoginBorder.frame,"账号或密码错误,请重新输入!");
			}
			LoginBorder.tf1.setText("");
			LoginBorder.tf2.setText("");
		}catch(SQLException s) {
			s.printStackTrace();
		}
		JdbcUtils.release(con, st, rs);//调用此工具类,不必每次都手动关流,提高代码复用性
	}

}
/*new ActionListener() {
public void actionPerformed(ActionEvent e) {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	String name = tf1.getText().trim();
	String password = tf2.getText().trim(); 
	boolean b = true;
	try {
		con = JdbcUtils.getConnection();
		st = con.createStatement();
		rs = st.executeQuery("select name,password from users");
		while(rs.next()) {
			if(name.equals(rs.getString("name"))) {
				if(password.equals(rs.getString("password"))) {
//					若用户名和密码都正确,进入KTV主界面
					b = false;
					new MainBorder(name , password);
					break;
				}
			}
		}
		if(b) {		
			new Dialog1(frame,"账号或密码错误,请重新输入!");
			tf1.setText("");
			tf2.setText("");
		}
	}catch(SQLException s) {
		s.printStackTrace();
	}
	JdbcUtils.release(con, st, rs);//调用此工具类,不必每次都手动关流,提高代码复用性
}
}*/