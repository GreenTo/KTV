package client_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.JdbcUtils;
import client.DeleteBorder;

public class Delete_confirm implements ActionListener{//确认删除功能
	@Override
	public void actionPerformed(ActionEvent e) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
			String sql = "delete from record where name = '"+ DeleteBorder.name +"'";
			st.executeUpdate(sql);
			new MainBorder_record();			//刷新订房记录
			DeleteBorder.frame.dispose();
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
	}	
}

/*new ActionListener() {
public void actionPerformed(ActionEvent e) {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	try {
		con = JdbcUtils.getConnection();
		st = con.createStatement();
		String sql = "delete from 预订记录表 where name = '"+ name +"'";
		st.executeUpdate(sql);
		new Dialog1(frame, "已成功删除预订记录!");
		
		frame.dispose();
	}catch(SQLException s) {
		s.printStackTrace();
	}
	JdbcUtils.release(con, st, rs);
}
}*/