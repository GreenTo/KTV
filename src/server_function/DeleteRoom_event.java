package server_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.DeleteRoomBorder;
import server.ServerBorder;
import util.JdbcUtils;
import util.Server_tips;

public class DeleteRoom_event implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		int room = Integer.parseInt((String) DeleteRoomBorder.comboBox.getSelectedItem());
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
			String sql = "delete from room where roomName = '"+ room +"'";
			st.executeUpdate(sql);
			new Server_tips(DeleteRoomBorder.frame, "删除成功!");
			DeleteRoomBorder.refresh();
			
			String str = "已删除 " + room + " 号房间,是否重启客户端更新?";
			Socket socket = new Socket("127.0.0.1", 3333);
			PrintStream pst = new PrintStream(socket.getOutputStream());
			pst.println(str);
			DeleteRoomBorder.frame.dispose();
		}catch(SQLException | IOException s) {
			s.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
	}

}

//new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		int room = (int) comboBox.getSelectedItem();
//		
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//		try {
//			con = JdbcUtils.getConnection();
//			st = con.createStatement();
//			String sql = "delete roomName from room where room = '"+ room +"'";
//			st.executeUpdate(sql);
//			new Server_tips(frame, "删除成功!");
//		}catch(SQLException s) {
//			s.printStackTrace();
//		}finally {
//			JdbcUtils.release(con, st, rs);
//		}
//	}
//}