package server;


import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import server_function.ModifyRoomBorder_event;
import util.JdbcUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class ModifyRoomBorder {

	public static JFrame frame;
	public static JTextField textField;
	public static JComboBox<String> comboBox;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;

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
			comboBox.addItem("·¿¼äºÅ");
			comboBox.removeAllItems();
			comboBox.addItem("·¿¼äºÅ");
			int room;
			while(rs.next()) {
				room = rs.getInt("roomName");
				comboBox.addItem(room + "");
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}
	
	}
	
	public ModifyRoomBorder() {
		initialize();
		refresh();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ModifyRoomBorder.class.getResource("/images/ktv2.jpg")));
		frame.setTitle("\u4FEE\u6539\u623F\u95F4\u754C\u9762");
		frame.getContentPane().setBackground(new Color(255, 204, 204));
		frame.setBounds(800, 200, 450, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("»ªÎÄ¿¬Ìå", Font.BOLD, 20));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(48, 119, 127, 43);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.BOLD, 16));
		textField.setColumns(10);
		textField.setBounds(261, 119, 113, 43);
		frame.getContentPane().add(textField);
		
		JButton button = new JButton("\u6539\u623F\u95F4\u53F7");
		button.addActionListener(
				new ModifyRoomBorder_event()
				);
		button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
		button.setBackground(Color.WHITE);
		button.setBounds(63, 197, 113, 43);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u8FD4\u56DE\r\n");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(261, 197, 113, 43);
		frame.getContentPane().add(button_1);
		
		label = new JLabel("\u65B0\u623F\u95F4\u53F7");
		label.setForeground(new Color(255, 255, 204));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("¿¬Ìå", Font.BOLD, 18));
		label.setBounds(277, 79, 97, 27);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("\u65E7\u623F\u95F4\u53F7");
		label_1.setForeground(new Color(255, 255, 204));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("¿¬Ìå", Font.BOLD, 18));
		label_1.setBounds(57, 79, 97, 27);
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel("\u89C4\u5B9A:\u623F\u95F4\u53F7\u5728101~399\u4E4B\u95F4");
		label_2.setFont(new Font("¿¬Ìå", Font.BOLD, 18));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(82, 24, 271, 42);
		frame.getContentPane().add(label_2);
	}

}
