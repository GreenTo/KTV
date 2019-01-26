package client_function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import client.MainBorder;
import util.JdbcUtils;

public class MainBorder_record {

	public MainBorder_record() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
//			查看数据库的预定记录表的关于该用户的订房信息
			String sql = "select room,money,time from record where name = '" + MainBorder.name + "'";
			rs = st.executeQuery(sql);
			StringBuilder sb = new StringBuilder();
			while(rs.next()) {
				sb = sb.append(MainBorder.name + "     房间号: " + (rs.getInt("room")) + "     消费: " + (rs.getDouble("money")) + "元" +
						"    预定时间:" + (rs.getDouble("time")) + "/h\n");
			}
			MainBorder.record.setText(sb+"");
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
	}
}
