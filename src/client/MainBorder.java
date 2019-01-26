package client;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;


import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

import client_function.MainBorder_addRoom;
import client_function.MainBorder_arrive;
import client_function.MainBorder_book;
import client_function.MainBorder_checkOut;
import client_function.MainBorder_login;
import client_function.MainBorder_queryMoney;
import client_function.MainBorder_record;
import client_function.MainBorder_refreshBook;
import client_function.MainBorder_refreshRoom;
import client_function.Update_receive;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;

public class MainBorder {

	public static JFrame frame;
	public static JTextArea roomMessage;
	public static JTextArea textArea;
	public static JTextArea record;
	public static JComboBox<String> cb;
	public static JComboBox<String> comboBox;
	public static JButton book;
	public static JTextField bookedMessage;
	
	public static String name;
//	private String password;			//记住用户名和密码

	public static int second = 0;		//定时器秒数
	public static int roomNumber = 0;	//告诉服务人员房间号
	
//	public static ServerSocket server;
//	public static Socket socket;
	
	public static DatagramSocket socket2;
	
	public static ServerSocket server3;
	public static Socket socket3;
	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public static void timer1() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				new MainBorder_refreshRoom();
				new MainBorder_refreshBook();
				new Thread(new Update_receive()).start();
			}
		}, 0, 5000);
	}
	
	
	
	public MainBorder(String name , String password){	//从登录界面获取用户名和密码	
		MainBorder.name = name;
//		this.password = password;
		try {
			new MainBorder_login(name,true);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		initialize();
		new MainBorder_addRoom();
		new MainBorder_record();
		
		
//		try {
//			socket2 = new DatagramSocket(3333);
//		} catch (SocketException e) {
//			
//			e.printStackTrace();
//		}
//		
		try {
			server3 = new ServerSocket(3333);
		} catch (IOException e) {
			
//			e.printStackTrace();
		}
		timer1();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainBorder.class.getResource("/images/ktv.jpg")));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					new MainBorder_login(name, false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().setFont(new Font("微软雅黑", Font.BOLD, 15));
		frame.getContentPane().setBackground(new Color(255, 228, 225));
		frame.setBounds(30, 100, 1065, 692);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("欢迎 "+name+" 进入主界面~");
		frame.setVisible(true);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(117, 41, 799, 188);
		frame.getContentPane().add(scrollPane_2);
		
		roomMessage = new JTextArea();	//房间概况
		roomMessage.setEditable(false);
		scrollPane_2.setViewportView(roomMessage);
		roomMessage.setForeground(new Color(0, 0, 0));
		roomMessage.setFont(new Font("华文楷体", Font.BOLD, 18));
//		roomMessage.setText("\u5C0F\u623F(\u4EF7\u683C100\u5143/h)                      \u4E2D\u623F(\u4EF7\u683C200\u5143/h)                      \u5927\u623F(\u4EF7\u683C300\u5143/h)\r\n        101                                 201                                    301\r\n        102                                 202                                    302\r\n        103                                 203                                    303\r\n        104                                 204                                    304  \r\n        105                                 205                                    305");
		roomMessage.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(238, 242, 545, 98);
		frame.getContentPane().add(scrollPane_1);
		
		textArea = new JTextArea();	
		scrollPane_1.setViewportView(textArea);
		textArea.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textArea.setEditable(false);
		textArea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		cb = new JComboBox<>();					//房间选择框
		cb.setBackground(new Color(255, 255, 255));
		cb.setForeground(new Color(0, 0, 0));
		cb.setFont(new Font("微软雅黑", Font.BOLD, 18));
		cb.setBounds(159, 353, 125, 35);
//		cb.setBorder(new RoundBorder());
		frame.getContentPane().add(cb);
		
		comboBox = new JComboBox<>();				//订房时间选择框
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setForeground(new Color(255, 102, 153));
		comboBox.setFont(new Font("微软雅黑", Font.BOLD, 18));
		comboBox.setBounds(320, 353, 90, 35);
//		comboBox.setBorder(new RoundBorder());
		frame.getContentPane().add(comboBox);
		comboBox.addItem("时间/h");
		for(int i = 1; i <= 12; i++) {
			comboBox.addItem(i+"");
		}
		
		book = new JButton("\u8BA2\u623F");				//订房功能
		book.setBackground(new Color(255, 255, 204));
		book.addActionListener(
				new MainBorder_book()
				);
		
		bookedMessage = new JTextField();
		bookedMessage.setVisible(false);
		bookedMessage.setFont(new Font("楷体", Font.BOLD, 18));
		bookedMessage.setHorizontalAlignment(SwingConstants.CENTER);
		bookedMessage.setEditable(false);
		bookedMessage.setBounds(450, 353, 198, 35);
		frame.getContentPane().add(bookedMessage);
		bookedMessage.setColumns(10);
		book.setFont(new Font("微软雅黑", Font.BOLD, 18));
		book.setBounds(24, 353, 113, 35);
		frame.getContentPane().add(book);
		
		JButton query = new JButton("\u4F59\u989D\u67E5\u8BE2\r\n");//余额查询功能
		query.setBackground(new Color(255, 255, 255));
		query.addActionListener(
				new MainBorder_queryMoney()
				);
		query.setFont(new Font("微软雅黑", Font.BOLD, 18));
		query.setBounds(159, 426, 113, 35);
		frame.getContentPane().add(query);
		
		JButton checkOut = new JButton("\u9000\u623F");		//退房功能
		checkOut.setBackground(new Color(204, 255, 204));
		checkOut.addActionListener(
				new MainBorder_checkOut()
				);
		checkOut.setFont(new Font("微软雅黑", Font.BOLD, 18));
		checkOut.setBounds(24, 426, 113, 35);
		frame.getContentPane().add(checkOut);
		
		JLabel label_1 = new JLabel("\u9884\u8BA2\u8FC7\u7684\u623F\u95F4\u8BB0\u5F55");
		label_1.setForeground(new Color(204, 0, 102));
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label_1.setBounds(736, 379, 167, 35);
		frame.getContentPane().add(label_1);
		
		record = new JTextArea();
		record.setForeground(new Color(102, 255, 0));
		record.setFont(new Font("微软雅黑", Font.BOLD, 13));
		record.setEditable(false);
		record.setBounds(597, 289, 391, 175);
		record.setVisible(true);
		
		JButton back = new JButton("\u5DF2\u5230\u8FBE\u623F\u95F4");//退房功能
		back.setBackground(new Color(255, 204, 153));
		back.addActionListener(
				new MainBorder_arrive()
				);
		back.setFont(new Font("微软雅黑", Font.BOLD, 20));
		back.setBounds(24, 493, 148, 41);
		frame.getContentPane().add(back);
		
		JButton button_1 = new JButton("\u9000\u51FA\u767B\u5F55");//退出登录
		button_1.setBackground(new Color(255, 255, 204));
		button_1.setForeground(new Color(255, 0, 0));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginBorder();
				frame.dispose();
				try {
					new MainBorder_login(name, false);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("楷体", Font.BOLD, 22));
		button_1.setBounds(238, 569, 132, 52);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u6E05\u9664\u8BB0\u5F55");//确定删除功能
		button_2.setBackground(new Color(255, 255, 255));
		button_2.setForeground(new Color(102, 51, 153));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeleteBorder(name);
			}
		});
		button_2.setFont(new Font("微软雅黑", Font.BOLD, 18));
		button_2.setBounds(450, 493, 113, 35);
		frame.getContentPane().add(button_2);
		
		JScrollPane scrollPane = new JScrollPane(record);
		scrollPane.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(597, 426, 436, 219);
		scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane);
		
		JLabel label = new JLabel("\u7528\u6237\u754C\u9762");
		label.setForeground(Color.CYAN);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("楷体", Font.BOLD, 25));
		label.setBounds(434, -1, 113, 29);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("\u5B8C\u5584\u4E2A\u4EBA\u4FE1\u606F");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditBorder(name);
			}
		});
		button.setForeground(new Color(0, 0, 0));
		button.setFont(new Font("微软雅黑", Font.BOLD, 18));
		button.setBackground(new Color(255, 255, 255));
		button.setBounds(24, 579, 148, 35);
		frame.getContentPane().add(button);
	}
}
