package client_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.JdbcUtils;
import client.DeleteBorder;

public class Delete_confirm implements ActionListener{//ȷ��ɾ������
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
			new MainBorder_record();			//ˢ�¶�����¼
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
		String sql = "delete from Ԥ����¼�� where name = '"+ name +"'";
		st.executeUpdate(sql);
		new Dialog1(frame, "�ѳɹ�ɾ��Ԥ����¼!");
		
		frame.dispose();
	}catch(SQLException s) {
		s.printStackTrace();
	}
	JdbcUtils.release(con, st, rs);
}
}*/