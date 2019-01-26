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
		if ("房间类型".equals(type)) {
			new Server_tips(AddRoomBorder.frame, "请选择房间类型");
		}else {
			int room = Integer.parseInt(AddRoomBorder.textField.getText().trim());
			if(!(room >= 101 && room <= 399)) {
				new Server_tips(AddRoomBorder.frame, "房间号规定在101~399之间");
			}else {
				double price;			//房间价格
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
					if("小房".equals(type)) {
						if(room < 200) {
							price = 100;
							ps.setDouble(3, price);
							ps.executeUpdate();
							new Server_tips(AddRoomBorder.frame, "添加成功!");
							b = true;
						}else {
							new Server_tips(AddRoomBorder.frame, "规定小房的房间号在101~199之间");
						}
					}else
					if("中房".equals(type)) {
						if(200 < room && room < 300) {
							price = 200;
							ps.setDouble(3, price);
							ps.executeUpdate();
							b = true;
							new Server_tips(AddRoomBorder.frame, "添加成功!");
						}else {
							new Server_tips(AddRoomBorder.frame, "规定中房的房间号在201~299之间");
						}
					}else {
						if(room > 300) {
							price = 300;
							ps.setDouble(3, price);
							ps.executeUpdate();
							b = true;
							new Server_tips(AddRoomBorder.frame, "添加成功!");
						}else {
							new Server_tips(AddRoomBorder.frame, "规定大房的房间号在301~399之间");
						}
					}
					if(b) {											//若添加成功
						new Cb_room();								//刷新一下房间信息
						AddRoomBorder.UpdateRoom();
						String str = "已添加 " + room + " 号房间,是否重启客户端更新?";
						Socket socket = new Socket("127.0.0.1", 3333);
						PrintStream pst = new PrintStream(socket.getOutputStream());
						pst.println(str);
						AddRoomBorder.frame.dispose();
					}
				}catch(SQLException | IOException s){
					new Server_tips(AddRoomBorder.frame, "此房间已存在!");
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
//		if ("房间类型".equals(type)) {
//			new Server_tips(frame, "请选择房间类型");
//		}else {
//			int room = Integer.parseInt(textField.getText());
//			if(!(room >= 101 && room <= 399)) {
//				new Server_tips(frame, "房间号规定在101~399之间");
//			}else {
//				double price;			//房间价格
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
//					if("小房".equals(type)) {
//						if(room < 200) {
//							price = 100;
//							ps.setDouble(3, price);
//							ps.executeUpdate();
//							new Server_tips(frame, "添加成功!");
//						}else {
//							new Server_tips(frame, "规定小房的房间号在101~199之间");
//						}
//					}else
//					if("中房".equals(type)) {
//						if(200 < room && room < 300) {
//							price = 200;
//							ps.setDouble(3, price);
//							new Server_tips(frame, "添加成功!");
//						}else {
//							new Server_tips(frame, "规定中房的房间号在201~299之间");
//						}
//					}else {
//						if(room > 300) {
//							price = 300;
//							ps.setDouble(3, price);
//							new Server_tips(frame, "添加成功!");
//						}else {
//							new Server_tips(frame, "规定大房的房间号在301~399之间");
//						}
//					}
//					new Cb_room();								//刷新一下房间信息
//				}catch(SQLException s){
//					new Server_tips(frame, "此房间已存在!");
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