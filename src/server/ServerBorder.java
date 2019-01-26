package server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import server_function.QueryUser;
import server_function.Cb_room;
import server_function.Intervalometer;
import server_function.QueryRoom;
import server_function.FuzzySearch;
import server_function.Rechange;


import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Toolkit;

public class ServerBorder {						//服务端界面

	public static JFrame frame;
	public static JTextField count;
	public static JTextField user;
	public static JTextArea information;
	public static JComboBox<String> bookedRoom;
	public static JComboBox<String> users;
	public static JTextField userMessage;
	public static JTextArea textArea;
	public static JTextField roomMessage;
	
	public static ServerSocket server;
	public static Socket socket;
	
	public static ServerSocket server2;
	public static Socket socket2;
	
	public static ServerSocket server3;
	public static Socket socket3;
	
	BufferedReader br;
	PrintStream ps;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerBorder window = new ServerBorder();
					ServerBorder.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerBorder() throws IOException{
		server = new ServerSocket(6666);		
		server2 = new ServerSocket(5555);
		server3 = new ServerSocket(4444);
		initialize();
		new Cb_room();
		new Intervalometer();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException{
		frame = new JFrame();
		frame.setBackground(new Color(255, 204, 255));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ServerBorder.class.getResource("/images/ktv2.jpg")));
		frame.getContentPane().setBackground(new Color(224, 255, 255));
		frame.setTitle("\u7BA1\u7406\u5458\u754C\u9762");
		frame.setBounds(500, 30, 1400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(46, 62, 1284, 240);
		frame.getContentPane().add(scrollPane_1);
		
		information = new JTextArea();
		scrollPane_1.setViewportView(information);
		information.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
		information.setEditable(false);
		
		count = new JTextField();
		count.setHorizontalAlignment(SwingConstants.CENTER);
		count.setFont(new Font("楷体", Font.BOLD, 18));
		count.setBounds(700, 421, 419, 35);
		frame.getContentPane().add(count);
		count.setColumns(10);
		
		user = new JTextField();
		user.setHorizontalAlignment(SwingConstants.CENTER);
		user.setFont(new Font("楷体", Font.BOLD, 18));
		user.setBounds(56, 398, 106, 38);
		frame.getContentPane().add(user);
		user.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(856, 514, 526, 239);
		frame.getContentPane().add(scrollPane);
		
		users = new JComboBox<>();
		users.setBackground(new Color(255, 255, 255));
		users.setForeground(new Color(0, 0, 0));
		users.setFont(new Font("楷体", Font.BOLD, 18));
		users.setBounds(364, 384, 90, 52);
		frame.getContentPane().add(users);
		users.addItem("用户名");
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("楷体", Font.BOLD, 18));
		
		JButton queryUser = new JButton("\u6A21\u7CCA\u641C\u7D22");		//查询用户功能
		queryUser.setBackground(new Color(255, 255, 255));
		queryUser.addActionListener(
				new FuzzySearch()
				);
		queryUser.setFont(new Font("微软雅黑", Font.BOLD, 18));
		queryUser.setBounds(205, 398, 113, 38);
		frame.getContentPane().add(queryUser);
		
		JButton rechange = new JButton("\u5145\u503C");			//充值功能
		rechange.setBackground(new Color(255, 255, 204));
		rechange.addActionListener(
				new Rechange()
				);
		rechange.setFont(new Font("微软雅黑", Font.BOLD, 18));
		rechange.setBounds(519, 420, 113, 36);
		frame.getContentPane().add(rechange);
		
		JButton btnNewButton_1 = new JButton("\u6E05\u7A7A");
		btnNewButton_1.setBackground(new Color(204, 255, 204));
		btnNewButton_1.addActionListener(new ActionListener() {		//清除查询用户信息的结果
			public void actionPerformed(ActionEvent e) {
				userMessage.setText(null);
				userMessage.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnNewButton_1.setBounds(1200, 389, 113, 43);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel label_3 = new JLabel("\u7BA1\u7406\u5458\u754C\u9762");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.PINK);
		label_3.setFont(new Font("楷体", Font.BOLD, 25));
		label_3.setBounds(552, 13, 190, 36);
		frame.getContentPane().add(label_3);
		
		userMessage = new JTextField();
		userMessage.setVisible(false);
		userMessage.setHorizontalAlignment(SwingConstants.CENTER);
		userMessage.setEditable(false);
		userMessage.setForeground(new Color(255, 102, 0));
		userMessage.setFont(new Font("楷体", Font.BOLD, 18));
		userMessage.setBounds(700, 367, 419, 40);
		frame.getContentPane().add(userMessage);
		userMessage.setColumns(10);
		
		JButton search = new JButton("\u67E5\u8BE2");		//搜索功能
		search.setBackground(new Color(255, 204, 204));
		search.addActionListener(
				new QueryUser()
				);
		search.setFont(new Font("微软雅黑", Font.BOLD, 18));
		search.setBounds(519, 365, 113, 42);
		frame.getContentPane().add(search);
		
		bookedRoom = new JComboBox<>();
		bookedRoom.setFont(new Font("华文楷体", Font.BOLD, 18));
		bookedRoom.setBackground(new Color(255, 255, 204));
		bookedRoom.setBounds(56, 514, 106, 35);
//		bookedRoom.addItem("房间号");
		frame.getContentPane().add(bookedRoom);
		
		roomMessage = new JTextField();
		roomMessage.setEditable(false);
		roomMessage.setVisible(false);
		roomMessage.setFont(new Font("楷体", Font.BOLD, 18));
		roomMessage.setHorizontalAlignment(SwingConstants.CENTER);
		roomMessage.setBounds(364, 514, 334, 35);
		frame.getContentPane().add(roomMessage);
		roomMessage.setColumns(10);
		
		JButton queryRoom = new JButton("\u67E5\u8BE2\u623F\u95F4");
		queryRoom.setBackground(new Color(255, 204, 204));
		queryRoom.addActionListener(
				new QueryRoom()
				);
		queryRoom.setFont(new Font("微软雅黑", Font.BOLD, 18));
		queryRoom.setBounds(205, 514, 113, 35);
		frame.getContentPane().add(queryRoom);
		
		JRadioButton addRoom = new JRadioButton("\u6DFB\u52A0\u623F\u95F4");
		addRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddRoomBorder();
			}
		});
		addRoom.setBackground(new Color(204, 255, 204));
		addRoom.setFont(new Font("华文楷体", Font.BOLD, 18));
		addRoom.setBounds(56, 634, 106, 43);
		frame.getContentPane().add(addRoom);
		
		JRadioButton deleteRoom = new JRadioButton("\u5220\u9664\u623F\u95F4");
		deleteRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeleteRoomBorder();
			}
		});
		deleteRoom.setFont(new Font("华文楷体", Font.BOLD, 18));
		deleteRoom.setBackground(new Color(255, 204, 255));
		deleteRoom.setBounds(205, 634, 106, 42);
		frame.getContentPane().add(deleteRoom);
		
		JRadioButton modifyRoom = new JRadioButton("\u4FEE\u6539\u623F\u95F4\r\n");
		modifyRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModifyRoomBorder();
			}
		});
		modifyRoom.setFont(new Font("华文楷体", Font.BOLD, 18));
		modifyRoom.setBackground(new Color(255, 204, 204));
		modifyRoom.setBounds(364, 634, 106, 42);
		frame.getContentPane().add(modifyRoom);
		
		ButtonGroup group = new ButtonGroup();
		group.add(addRoom);
		group.add(deleteRoom);
		group.add(modifyRoom);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(69, 377, 72, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
		label_1.setBounds(69, 489, 72, 18);
		frame.getContentPane().add(label_1);
		
		JButton button = new JButton("\u5220\u9664\u8BB0\u5F55");//清除记录功能
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
			}
		});
		button.setFont(new Font("微软雅黑", Font.BOLD, 18));
		button.setBackground(new Color(204, 255, 204));
		button.setBounds(700, 633, 113, 43);
		frame.getContentPane().add(button);
		
	}
}
