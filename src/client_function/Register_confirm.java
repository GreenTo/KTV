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
		String user = RegisterBorder.tf1.getText().trim();	 //�û���
		String password = RegisterBorder.tf2.getText().trim(); //����
		if("".equals(user)||"".equals(password)) {
			new Client_tips(RegisterBorder.frmktv,"�������û���������!");
		}else {
			try {
				con = JdbcUtils.getConnection();
				st = con.createStatement();
				rs = st.executeQuery("select name from users");
				boolean b = true;						//��������b = false;���������ݿ�
				while(rs.next()) {						//�ж��û��Ƿ��Ѿ�����,����������������!
					if(rs.getString("name").equals(user)) {
						new Client_tips(RegisterBorder.frmktv,"�û����Ѵ���,����������!");
						b = false;
						break;
					}
				}
				if(b) {										//���û�������,��ע��
					if(Pattern.matches(".{1,5}", user)) {		//�����붼�����ֻ���ĸ
						if(Pattern.matches("^[0-9a-zA-Z]{1,8}$", password)) {	
							String sql = "insert into users (name,password) values('" + user + "','" + password + "')";
							st.executeUpdate(sql);
							new Client_tips(RegisterBorder.frmktv,"ע��ɹ�");
						}
					}else {
						new Client_tips(RegisterBorder.frmktv, "�û���С��5λ.����С��8λ,ֻ���������ֻ���ĸ");
					}
				}
			}catch(SQLException x) {
				x.printStackTrace();
			}finally {
				RegisterBorder.tf1.setText(null);
				RegisterBorder.tf2.setText(null);
				JdbcUtils.release(con, st, rs); 				//����
			}
		}	
	}
}
//new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		String user = tf1.getText().trim();		//�û���
//		String password = tf2.getText().trim(); //����
//		if("".equals(user)||"".equals(password)) {
//			new Dialog1(frmktv,"�������û���������!");
//			tf1.setText("");				//����û���������
//			tf2.setText("");
//		}else {
//			try {
//				con = JdbcUtils.getConnection();
//				st = con.createStatement();
//				rs = st.executeQuery("select name from users");
//				boolean b = true;						//��������b = false;���������ݿ�
//				while(rs.next()) {						//�ж��û��Ƿ��Ѿ�����,����������������!
//					if(rs.getString("name").equals(user)) {
//						new Dialog1(frmktv,"�û����Ѵ���,����������!");
//						b = false;
//						break;
//					}
//				}
//				if(b) {										//���û�������,��ע��
//					String sql = "insert into users (name,password) values('" + user + "','" + password + "')";
//					st.executeUpdate(sql);
//					new Dialog1(frmktv,"ע��ɹ�");
//				}
//			}catch(SQLException x) {
//				x.printStackTrace();
//			}finally {
//				tf1.setText(null);
//				tf2.setText(null);
//				JdbcUtils.release(con, st, rs); 				//����
//			}
//		}
//	}
//}