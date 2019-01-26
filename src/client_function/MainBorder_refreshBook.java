package client_function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import client.MainBorder;
import util.JdbcUtils;

public class MainBorder_refreshBook {
	public MainBorder_refreshBook() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
			String sql = "select roomName from room where book = true";
			rs = st.executeQuery(sql);
			String str = new String();
			int i = 0;
			StringBuilder sb = new StringBuilder();
			
			while(rs.next()) {
				i++;
				if(i%5 == 0) {
					str = "\n";
				}else {
					str = "";
				}
				sb.append(rs.getInt("roomName") + "\t" + str);
			}
			MainBorder.textArea.setText("已订房间如下:\n" + sb);
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
	}
}
