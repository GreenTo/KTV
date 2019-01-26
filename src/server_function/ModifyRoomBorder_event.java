package server_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.ModifyRoomBorder;
import util.JdbcUtils;
import util.Server_tips;

public class ModifyRoomBorder_event implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = ModifyRoomBorder.textField.getText().trim();
		int Oldroom = Integer.parseInt((String) ModifyRoomBorder.comboBox.getSelectedItem());
		int Newroom;
		
		if("".equals(str)) {
			new Server_tips(ModifyRoomBorder.frame, "���������޸ĳɵķ����");
		}else {
			boolean b = true;
			Newroom = Integer.parseInt(str);
			if(Newroom > 400 && 100 < Newroom) {
				new Server_tips(ModifyRoomBorder.frame, "�涨�����Ϊ101~399");
				b = false;
			}
			if(b) {
				Connection con = null;
				Statement st = null;
				ResultSet rs = null;
				PreparedStatement ps = null;
				try {
					con = JdbcUtils.getConnection();
					st = con.createStatement();
					String sql = "update room set roomName = ?,price = ?,roomType = ? where roomName = '"+ Oldroom +"'";
					ps = con.prepareStatement(sql);
					ps.setInt(1, Newroom);
					if(Newroom < 200) {
						ps.setDouble(2, 100);
						ps.setString(3, "С��");
					}else if(Newroom > 300) {
						ps.setDouble(2, 300);
						ps.setString(3, "��");
					}else {
						ps.setDouble(2, 200);
						ps.setString(3, "�з�");
					}
					ps.executeUpdate();
					new Server_tips(ModifyRoomBorder.frame, "�޸ĳɹ�!");
					
					String string = "���޸� " + Oldroom + " �ŷ���,�Ƿ������ͻ��˸���?";
					Socket socket = new Socket("127.0.0.1", 3333);
					PrintStream pst = new PrintStream(socket.getOutputStream());
					pst.println(string);
					ModifyRoomBorder.refresh();
					ModifyRoomBorder.frame.dispose();
				}catch(SQLException | IOException s) {
//					s.printStackTrace();
					new Server_tips(ModifyRoomBorder.frame, "�˷��������û�Ԥ��");
				}finally {
					JdbcUtils.release(con, st, rs);
					JdbcUtils.closePs(ps);
				}
			}
			ModifyRoomBorder.textField.setText(null);
		}
	}

}

//new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		String str = textField.getText();
//		int Oldroom = Integer.parseInt((String) comboBox.getSelectedItem());
//		int Newroom;
//		
//		if("".equals(str)) {
//			new Server_tips(frame, "���������޸ĳɵķ����");
//		}else {
//			boolean b = true;
//			Newroom = Integer.parseInt(str);
//			if(Newroom > 400 && 100 < Newroom) {
//				new Server_tips(frame, "�涨�����Ϊ101~399");
//				b = false;
//			}
//			if(b) {
//				Connection con = null;
//				Statement st = null;
//				ResultSet rs = null;
//				PreparedStatement ps = null;
//				try {
//					con = JdbcUtils.getConnection();
//					st = con.createStatement();
//					String sql = "update room set roomName = ?,price = ?,roomType = ? where roomName = '"+ Oldroom +"'";
//					ps = con.prepareStatement(sql);
//					ps.setInt(1, Newroom);
//					if(Newroom < 200) {
//						ps.setDouble(2, 100);
//						ps.setString(3, "С��");
//					}else if(Newroom > 300) {
//						ps.setDouble(2, 300);
//						ps.setString(3, "��");
//					}else {
//						ps.setDouble(2, 200);
//						ps.setString(3, "�з�");
//					}
//					ps.executeUpdate();
//					new Server_tips(frame, "�޸ĳɹ�!");
//					ModifyRoomBorder.refresh();
//				}catch(SQLException s) {
////					s.printStackTrace();
//					new Server_tips(frame, "�˷��������û�Ԥ��");
//				}finally {
//					JdbcUtils.release(con, st, rs);
//					JdbcUtils.closePs(ps);
//				}
//			}
//			textField.setText(null);
//		}
//	}
//}