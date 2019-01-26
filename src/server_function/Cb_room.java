package server_function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.ServerBorder;
import util.JdbcUtils;

public class Cb_room{
	public Cb_room() {
		ServerBorder.bookedRoom.addItem("房间号");//防止空指针异常
		ServerBorder.bookedRoom.removeAllItems();
		ServerBorder.bookedRoom.addItem("房间号");
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
			String sql = "select roomName from room ";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ServerBorder.bookedRoom.addItem(rs.getInt("roomName")+"");
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
	}

}
