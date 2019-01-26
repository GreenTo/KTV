package server;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import server_function.AddRoom_event;
import util.JdbcUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Toolkit;

public class AddRoomBorder {

	public static JFrame frame;
	public static JTextField textField;
	public static JComboBox<String> comboBox;
	public static JList list;
	public static JComboBox<String> oldRoom;
	public static Vector<Integer> contents;
	
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;

	/**
	 * Create the application.
	 */
	public static void UpdateRoom() {
//		contents.add(0);
//		contents.removeAllElements();
		oldRoom.addItem("已有房间");//防止空指针异常
		oldRoom.removeAllItems();
		oldRoom.addItem("已有房间");
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
			String sql = "select roomName from room";
			rs = st.executeQuery(sql);
			while(rs.next()) {
//				contents.add(rs.getInt("roomName"));
				oldRoom.addItem(rs.getInt("roomName") + "");
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}
	}
	
	public AddRoomBorder() {
		initialize();
		UpdateRoom();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("添加房间窗口");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AddRoomBorder.class.getResource("/images/ktv2.jpg")));
		frame.getContentPane().setBackground(new Color(204, 255, 204));
		frame.setBounds(800, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		comboBox = new JComboBox<>();
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"\u623F\u95F4\u7C7B\u578B", "\u5C0F\u623F", "\u4E2D\u623F", "\u5927\u623F"}));
		comboBox.setFont(new Font("华文楷体", Font.BOLD, 18));
		comboBox.setBounds(179, 112, 101, 43);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
		textField.setBounds(309, 111, 96, 43);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0\r\n");
		btnNewButton.addActionListener(
				new AddRoom_event()
		);
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(62, 197, 113, 43);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("\u8FD4\u56DE\r\n");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button.setFont(new Font("微软雅黑", Font.BOLD, 18));
		button.setBackground(Color.WHITE);
		button.setBounds(249, 197, 113, 43);
		frame.getContentPane().add(button);
		
		label = new JLabel("\u623F\u95F4\u53F7");
		label.setForeground(new Color(204, 204, 255));
		label.setFont(new Font("楷体", Font.BOLD, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(329, 86, 63, 21);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("\u89C4\u5B9A:\u623F\u95F4\u53F7\u5728101~399\u4E4B\u95F4\r\n");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("楷体", Font.BOLD, 16));
		label_1.setBounds(100, 0, 228, 29);
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel("\u5C0F\u623F:101~199");
		label_2.setFont(new Font("楷体", Font.BOLD, 16));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(145, 24, 121, 18);
		frame.getContentPane().add(label_2);
		
		label_3 = new JLabel("\u4E2D\u623F:201~299");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("楷体", Font.BOLD, 16));
		label_3.setBounds(145, 42, 121, 18);
		frame.getContentPane().add(label_3);
		
		label_4 = new JLabel("\u5927\u623F:301~399");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("楷体", Font.BOLD, 16));
		label_4.setBounds(145, 64, 121, 18);
		frame.getContentPane().add(label_4);
		
		oldRoom = new JComboBox<String>();
		oldRoom.setFont(new Font("华文楷体", Font.BOLD, 18));
		oldRoom.setBackground(Color.WHITE);
		oldRoom.setBounds(42, 112, 101, 43);
		frame.getContentPane().add(oldRoom);
		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane.setBounds(42, 88, 87, 96);
//		frame.getContentPane().add(scrollPane);
//		contents = new Vector<>();
//		list = new JList(contents);
//		scrollPane.setViewportView(list);
	}
}
