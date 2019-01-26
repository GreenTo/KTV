package server_function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import server.ServerBorder;
import util.JdbcUtils;

public class RoomMessage {

	public RoomMessage() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
			String sql = "select roomName,book from room";
			rs = st.executeQuery(sql);
			StringBuilder sb = new StringBuilder();
			String str;
			int i = 0;
			while(rs.next()) {			//每四个房间信息一行
				i++;
				if(i % 4 == 0) {
					str = "\n\n\n";
				}else {
					str = "";
				}
				sb = sb.append("房间号:  " + rs.getInt("roomName") + "     是否已预订:  " + rs.getBoolean("book") + "\t" + str);
			}
			ServerBorder.information.setText(sb + "");
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
	}
}