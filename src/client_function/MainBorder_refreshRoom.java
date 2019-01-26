package client_function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import client.MainBorder;
import util.JdbcUtils;

public class MainBorder_refreshRoom {
	public MainBorder_refreshRoom() {
//		MainBorder.roomMessage
		String small = "\t\t\tС��(100Ԫ/h)" + "\n";
		String middle = "\t\t\t�з�(200Ԫ/h)" + "\n";
		String big = "\t\t\t��(300Ԫ/h)" + "\n";
		String str;
		int i = 0;
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		try {
			con = JdbcUtils.getConnection();
		    st = con.createStatement();
		    
		    String type1 = "С��";
		    MainBorder.roomMessage.setText(small);
			String sql1 = "select roomName from room where roomType = '"+ type1 +"'"; 
			rs = st.executeQuery(sql1);
			while(rs.next()) {
				i++;
				if(i % 7 == 0) {
					str = "\n";
				}else {
					str = "\t";
				}
				MainBorder.roomMessage.append(rs.getInt("roomName") + str);;
			}
			
			String type2 = "�з�";
			i = 0;
			String sql2 = "select roomName from room where roomType = '"+ type2 +"'"; 
			rs = st.executeQuery(sql2);
			MainBorder.roomMessage.append("\n\n" + middle);
			while(rs.next()) {
				i++;
				if(i % 7 == 0) {
					str = "\n";
				}else {
					str = "\t";
				}
				MainBorder.roomMessage.append(rs.getInt("roomName") + str);;
			}
			
			String type3 = "��";
			i = 0;
			String sql3 = "select roomName from room where roomType = '"+ type3 +"'"; 
			rs = st.executeQuery(sql3);
			MainBorder.roomMessage.append("\n\n" + big);
			while(rs.next()) {
				i++;
				if(i % 7 == 0) {
					str = "\n";
				}else {
					str = "\t";
				}
				MainBorder.roomMessage.append( rs.getInt("roomName") + str);;
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
	}
}