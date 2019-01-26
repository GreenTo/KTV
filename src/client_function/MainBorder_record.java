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
//			�鿴���ݿ��Ԥ����¼��Ĺ��ڸ��û��Ķ�����Ϣ
			String sql = "select room,money,time from record where name = '" + MainBorder.name + "'";
			rs = st.executeQuery(sql);
			StringBuilder sb = new StringBuilder();
			while(rs.next()) {
				sb = sb.append(MainBorder.name + "     �����: " + (rs.getInt("room")) + "     ����: " + (rs.getDouble("money")) + "Ԫ" +
						"    Ԥ��ʱ��:" + (rs.getDouble("time")) + "/h\n");
			}
			MainBorder.record.setText(sb+"");
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
	}
}
