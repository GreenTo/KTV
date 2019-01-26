package client_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import client.EditBorder;
import util.Client_tips;
import util.JdbcUtils;

public class EditBorder_confirm implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("dddddddddddddddddddd");
		String s1 = EditBorder.telephone.getText().trim();
		String s2 = EditBorder.idCard.getText().trim();
		String s3 = EditBorder.userAddress.getText().trim();
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
			System.out.println("sssssssssssssssssssss");
			String sql = "update users set tele = '"+ s1 +"',Id = '"+ s2 +"',address = '"+ s3 +"' where userName = '"+ EditBorder.name +"'";
			st.executeUpdate(sql);
			new Client_tips(EditBorder.frame, "±à¼­³É¹¦");
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
	}

}
