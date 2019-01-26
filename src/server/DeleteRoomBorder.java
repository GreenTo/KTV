package server;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;

import server_function.DeleteRoom_event;
import util.JdbcUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class DeleteRoomBorder {

	public static JFrame frame;
	public static JComboBox<String> comboBox;

	/**
	 * Create the application.
	 */
	public static void refresh() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con  = JdbcUtils.getConnection();
			st = con.createStatement();
			String sql = "select roomName from room where book = false";
			rs = st.executeQuery(sql);
			comboBox.addItem("房间号");
			comboBox.removeAllItems();
			comboBox.addItem("房间号");
			int room;
			while(rs.next()) {
				room = rs.getInt("roomName");
				comboBox.addItem(room + "");
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}
	}
	
	public DeleteRoomBorder() {
		initialize();
		refresh();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteRoomBorder.class.getResource("/images/ktv2.jpg")));
		frame.getContentPane().setBackground(new Color(255, 204, 255));
		frame.setTitle("\u5220\u9664\u623F\u95F4\u529F\u80FD");
		frame.setBounds(800, 200, 450, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("华文楷体", Font.BOLD, 20));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(151, 62, 135, 43);
		frame.getContentPane().add(comboBox);
		
		JButton button = new JButton("\u5220\u9664");//删除房间
		button.addActionListener(
				new DeleteRoom_event()
				);
		button.setFont(new Font("微软雅黑", Font.BOLD, 18));
		button.setBackground(Color.WHITE);
		button.setBounds(57, 163, 113, 43);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u8FD4\u56DE\r\n");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(259, 163, 113, 43);
		frame.getContentPane().add(button_1);
	}
}
