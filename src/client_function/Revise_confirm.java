package client_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.Client_tips;
import util.JdbcUtils;
import client.ReviseBorder;
public class Revise_confirm implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String name = ReviseBorder.userName.getText().trim();
		String oldWord = ReviseBorder.oldPassword.getText().trim(); 
		String newWord = ReviseBorder.newPassword.getText().trim();
		boolean b = true;
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select name,password from users");
			while(rs.next()) {
				if(name.equals(rs.getString("name"))) {
//					System.out.println("用户名:"+name);
					if(oldWord.equals(rs.getString("password"))) {
//						System.out.println("password:" + oldWord);
						if(!newWord.equals("")) {
//							若输入的用户名和原密码且新密码不为空,则修改密码
							b = false;
							String sql = "update users set password = '"+ newWord +"' where name = '"+ name +"'";
							st.executeUpdate(sql);
							new Client_tips(ReviseBorder.frame, "密码修改成功!");
							break;
						}
					}
				}
			}
			if(b) {
				new Client_tips(ReviseBorder.frame,"账号或密码错误,请重新输入!");
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			ReviseBorder.userName.setText(null);
			ReviseBorder.oldPassword.setText(null);
			ReviseBorder.newPassword.setText(null);
			JdbcUtils.release(con, st, rs);//调用此工具类,不必每次都手动关流,提高代码复用性
		}
	}

}
//new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		String name = userName.getText().trim();
//		String oldWord = oldPassword.getText().trim(); 
//		String newWord = newPassword.getText().trim();
//		boolean b = true;
//		try {
//			con = JdbcUtils.getConnection();
//			st = con.createStatement();
//			rs = st.executeQuery("select name,password from users");
//			while(rs.next()) {
//				if(name.equals(rs.getString("name"))) {
////					System.out.println("用户名:"+name);
//					if(oldWord.equals(rs.getString("password"))) {
////						System.out.println("password:" + oldWord);
//						if(!newWord.equals("")) {
////							若输入的用户名和原密码且新密码不为空,则修改密码
//							b = false;
//							String sql = "update users set password = '"+ newWord +"' where name = '"+ name +"'";
//							st.executeUpdate(sql);
//							new Dialog1(frame, "密码修改成功!");
//							oldPassword.setText(null);
//							newPassword.setText(null);
//							break;
//						}
//					}
//				}
//			}
//			if(b) {
//				new Dialog1(frame,"账号或密码错误,请重新输入!");
//				userName.setText(null);
//				oldPassword.setText(null);
//				newPassword.setText(null);
//			}
//		}catch(SQLException s) {
//			s.printStackTrace();
//		}
//		JdbcUtils.release(con, st, rs);//调用此工具类,不必每次都手动关流,提高代码复用性
//	}
//}