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
//					System.out.println("�û���:"+name);
					if(oldWord.equals(rs.getString("password"))) {
//						System.out.println("password:" + oldWord);
						if(!newWord.equals("")) {
//							��������û�����ԭ�����������벻Ϊ��,���޸�����
							b = false;
							String sql = "update users set password = '"+ newWord +"' where name = '"+ name +"'";
							st.executeUpdate(sql);
							new Client_tips(ReviseBorder.frame, "�����޸ĳɹ�!");
							break;
						}
					}
				}
			}
			if(b) {
				new Client_tips(ReviseBorder.frame,"�˺Ż��������,����������!");
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			ReviseBorder.userName.setText(null);
			ReviseBorder.oldPassword.setText(null);
			ReviseBorder.newPassword.setText(null);
			JdbcUtils.release(con, st, rs);//���ô˹�����,����ÿ�ζ��ֶ�����,��ߴ��븴����
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
////					System.out.println("�û���:"+name);
//					if(oldWord.equals(rs.getString("password"))) {
////						System.out.println("password:" + oldWord);
//						if(!newWord.equals("")) {
////							��������û�����ԭ�����������벻Ϊ��,���޸�����
//							b = false;
//							String sql = "update users set password = '"+ newWord +"' where name = '"+ name +"'";
//							st.executeUpdate(sql);
//							new Dialog1(frame, "�����޸ĳɹ�!");
//							oldPassword.setText(null);
//							newPassword.setText(null);
//							break;
//						}
//					}
//				}
//			}
//			if(b) {
//				new Dialog1(frame,"�˺Ż��������,����������!");
//				userName.setText(null);
//				oldPassword.setText(null);
//				newPassword.setText(null);
//			}
//		}catch(SQLException s) {
//			s.printStackTrace();
//		}
//		JdbcUtils.release(con, st, rs);//���ô˹�����,����ÿ�ζ��ֶ�����,��ߴ��븴����
//	}
//}