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

public class MainBorder_checkOut implements ActionListener {//�˷�����

	@Override
	public void actionPerformed(ActionEvent e) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int num;
		
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
			//�����ݿ��л�ȡ�û���Ķ�����Ϣ
			String sql = "select roomNumber from users where name = '"+ MainBorder.name +"'";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				if((num = rs.getInt("roomNumber")) != 0) {	
					//���û��ж���,�����ݿ����û���Ķ�����Ϣ��ղ��˷�
					String sql2 = "update users set roomNumber = 0, isThrough = false, time = 0 where name = '"+ MainBorder.name +"'";
//					�����ݿ��з����ķ�����Ϣ����
					String sql3 = "update room set book = false where roomName = '"+ num +"'";
					st.executeUpdate(sql2);
					st.executeUpdate(sql3);
					new Client_tips(MainBorder.frame, "�˷��ɹ�!");
					MainBorder.book.setVisible(true);
					new MainBorder_refreshBook();		//ˢ�¶�����Ϣ
					MainBorder.bookedMessage.setVisible(false);
				}else {
					new Client_tips(MainBorder.frame, "����û�ж���!");
				}
				break;
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
		
	}

}

//new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		int num;
//		
//		try {
//			con = JdbcUtils.getConnection();
//			st = con.createStatement();
//			//�����ݿ��л�ȡ�û���Ķ�����Ϣ
//			String sql = "select roomNumber from users where name = '"+ name +"'";
//			rs = st.executeQuery(sql);
//			while(rs.next()) {
//				if((num = rs.getInt("roomNumber")) != 0) {	
//					//���û��ж���,�����ݿ����û���Ķ�����Ϣ��ղ��˷�
//					String sql2 = "update users set roomNumber = 0, isThrough = false, time = 0 where name = '"+ name +"'";
////					�����ݿ��з����ķ�����Ϣ����
//					String sql3 = "update room set book = false where roomName = '"+ num +"'";
//					st.executeUpdate(sql2);
//					st.executeUpdate(sql3);
//					new Dialog1(frame, "�˷��ɹ�!");
//				}else {
//					new Dialog1(frame, "����û�ж���!");
//				}
//				break;
//			}
//		}catch(SQLException s) {
//			s.printStackTrace();
//		}
//		JdbcUtils.release(con, st, rs);
//	}
//}