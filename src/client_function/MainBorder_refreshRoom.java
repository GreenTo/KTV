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
		String small = "\t\t\t小房(100元/h)" + "\n";
		String middle = "\t\t\t中房(200元/h)" + "\n";
		String big = "\t\t\t大房(300元/h)" + "\n";
		String str;
		int i = 0;
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		try {
			con = JdbcUtils.getConnection();
		    st = con.createStatement();
		    
		    String type1 = "小房";
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
			
			String type2 = "中房";
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
			
			String type3 = "大房";
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