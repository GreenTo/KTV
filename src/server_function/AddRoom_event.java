package server_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.AddRoomBorder;
import util.JdbcUtils;
import util.Server_tips;

public class AddRoom_event implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String type = (String) AddRoomBorder.comboBox.getSelectedItem();
		if ("��������".equals(type)) {
			new Server_tips(AddRoomBorder.frame, "��ѡ�񷿼�����");
		}else {
			int room = Integer.parseInt(AddRoomBorder.textField.getText().trim());
			if(!(room >= 101 && room <= 399)) {
				new Server_tips(AddRoomBorder.frame, "����Ź涨��101~399֮��");
			}else {
				double price;			//����۸�
				boolean b = false;
				Connection con = null;
				Statement st = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					con = JdbcUtils.getConnection();
					ps = con.prepareStatement("insert into room(roomName,roomType,price) values(?,?,?)");
					
					ps.setInt(1, room);
					ps.setString(2, type);
					if("С��".equals(type)) {
						if(room < 200) {
							price = 100;
							ps.setDouble(3, price);
							ps.executeUpdate();
							new Server_tips(AddRoomBorder.frame, "��ӳɹ�!");
							b = true;
						}else {
							new Server_tips(AddRoomBorder.frame, "�涨С���ķ������101~199֮��");
						}
					}else
					if("�з�".equals(type)) {
						if(200 < room && room < 300) {
							price = 200;
							ps.setDouble(3, price);
							ps.executeUpdate();
							b = true;
							new Server_tips(AddRoomBorder.frame, "��ӳɹ�!");
						}else {
							new Server_tips(AddRoomBorder.frame, "�涨�з��ķ������201~299֮��");
						}
					}else {
						if(room > 300) {
							price = 300;
							ps.setDouble(3, price);
							ps.executeUpdate();
							b = true;
							new Server_tips(AddRoomBorder.frame, "��ӳɹ�!");
						}else {
							new Server_tips(AddRoomBorder.frame, "�涨�󷿵ķ������301~399֮��");
						}
					}
					if(b) {											//����ӳɹ�
						new Cb_room();								//ˢ��һ�·�����Ϣ
						AddRoomBorder.UpdateRoom();
						String str = "����� " + room + " �ŷ���,�Ƿ������ͻ��˸���?";
						Socket socket = new Socket("127.0.0.1", 3333);
						PrintStream pst = new PrintStream(socket.getOutputStream());
						pst.println(str);
						AddRoomBorder.frame.dispose();
					}
				}catch(SQLException | IOException s){
					new Server_tips(AddRoomBorder.frame, "�˷����Ѵ���!");
					s.printStackTrace();
				}finally {
					AddRoomBorder.textField.setText(null);
					JdbcUtils.release(con, st, rs);
					JdbcUtils.closePs(ps);
				}
			}
		}
	}

}

//DatagramSocket socket = new DatagramSocket();
//DatagramPacket packet = new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName("127.0.0.1"), 3333);
//socket.send(packet);
//socket.close();


//new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		String type = (String) comboBox.getSelectedItem();
//		if ("��������".equals(type)) {
//			new Server_tips(frame, "��ѡ�񷿼�����");
//		}else {
//			int room = Integer.parseInt(textField.getText());
//			if(!(room >= 101 && room <= 399)) {
//				new Server_tips(frame, "����Ź涨��101~399֮��");
//			}else {
//				double price;			//����۸�
//				
//				Connection con = null;
//				Statement st = null;
//				PreparedStatement ps = null;
//				ResultSet rs = null;
//				try {
//					con = JdbcUtils.getConnection();
//					ps = con.prepareStatement("insert into room(roomName,roomType,price) values(?,?,?)");
//					
//					ps.setInt(1, room);
//					ps.setString(2, type);
//					if("С��".equals(type)) {
//						if(room < 200) {
//							price = 100;
//							ps.setDouble(3, price);
//							ps.executeUpdate();
//							new Server_tips(frame, "��ӳɹ�!");
//						}else {
//							new Server_tips(frame, "�涨С���ķ������101~199֮��");
//						}
//					}else
//					if("�з�".equals(type)) {
//						if(200 < room && room < 300) {
//							price = 200;
//							ps.setDouble(3, price);
//							new Server_tips(frame, "��ӳɹ�!");
//						}else {
//							new Server_tips(frame, "�涨�з��ķ������201~299֮��");
//						}
//					}else {
//						if(room > 300) {
//							price = 300;
//							ps.setDouble(3, price);
//							new Server_tips(frame, "��ӳɹ�!");
//						}else {
//							new Server_tips(frame, "�涨�󷿵ķ������301~399֮��");
//						}
//					}
//					new Cb_room();								//ˢ��һ�·�����Ϣ
//				}catch(SQLException s){
//					new Server_tips(frame, "�˷����Ѵ���!");
////					s.printStackTrace();
//				}finally {
//					textField.setText(null);
//					JdbcUtils.release(con, st, rs);
//					JdbcUtils.closePs(ps);
//				}
//			}
//		}
//	}
//}