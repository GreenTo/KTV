package client_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import client.MainBorder;
import util.Client_tips;
import util.JdbcUtils;

public class MainBorder_arrive implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;       
		int num;
		double time;
		
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
//			�鿴���ݿ���û���Ķ�����Ϣ
			String sql = "select roomNumber, time from users where name = '"+ MainBorder.name +"'";
			rs = st.executeQuery(sql);
			while(rs.next()){
				num = rs.getInt("roomNumber");			//�û��ķ����
				time = rs.getDouble("time");			//�û��Ķ���ʱ��
				if(num == 0) {
					new Client_tips(MainBorder.frame, "����û�ж�����!");
				}else {
//					���û��ж���,�۳���������ķ���
					String sql2 = "update users set money = money - '"+ num/100*time*100 +"' where name = '"+ MainBorder.name +"'";
					MainBorder.second = (int)time * 3000;						//���õ���ʱ����,һ��Сʱ5��
					MainBorder.roomNumber = num;
					st.executeUpdate(sql2);
					
					Socket socket = new Socket("127.0.0.1", 5555);
//					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintStream ps = new PrintStream(socket.getOutputStream());
					ps.println(MainBorder.second);						//�����˷��Ͷ���ʱ��
					ps.println(MainBorder.roomNumber);					//�����˷��ͷ����
					ps.println(MainBorder.name);						//�����˷����û���
					socket.close();
					
					new Client_tips(MainBorder.frame, "�۷ѳɹ�!���ڿ�ʼ��ʱ.");
					MainBorder.bookedMessage.setVisible(false);
					MainBorder.book.setVisible(true);
				}
			}
		}catch(Exception s) {
//			s.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
	}

}

//new ActionListener() {				//ȷ�ϵ��﹦��
//	public void actionPerformed(ActionEvent e) {
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;       
//		int num;
//		double time;
//		
//		try {
//			con = JdbcUtils.getConnection();
//			st = con.createStatement();
////			�鿴���ݿ���û���Ķ�����Ϣ
//			String sql = "select roomNumber, time from users where name = '"+ name +"'";
//			rs = st.executeQuery(sql);
//			while(rs.next()){
//				num = rs.getInt("roomNumber");			//�û��ķ����
//				time = rs.getDouble("time");			//�û��Ķ���ʱ��
//				if(num == 0) {
//					new Dialog1(frame, "����û�ж�����!");
//				}else {
////					���û��ж���,�۳���������ķ���
//					String sql2 = "update users set money = money - '"+ num/100*time*100 +"' where name = '"+ name +"'";
//					second = (int)time * 5000;						//���õ���ʱ����,һ��Сʱ5��
//					roomNumber = num;
//					st.executeUpdate(sql2);
//					
//					Socket socket = new Socket("127.0.0.1", 5555);
////					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//					PrintStream ps = new PrintStream(socket.getOutputStream());
//					ps.println(second);						//�����˷��Ͷ���ʱ��
//					ps.println(roomNumber);					//�����˷��ͷ����
//					ps.println(name);						//�����˷����û���
//					socket.close();
//					
//					new Dialog1(frame, "�۷ѳɹ�!���ڿ�ʼ��ʱ.");
//				}
//			}
//		}catch(Exception s) {
//			s.printStackTrace();
//		}finally {
//			JdbcUtils.release(con, st, rs);
//		}
//	}
//}