package client;


import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import client_function.Forget_confirm;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ForgetBorder {

	public static JFrame frame;
	public static JTextField tf1;
	public static JTextField tf2;
	public static JTextField userName;

	public static String str1 = "张敬轩";
//	public static String str2 = "赫萝";
	
	/**
	 * Create the application.
	 */
	public ForgetBorder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ForgetBorder.class.getResource("/images/ktv.jpg")));
		frame.getContentPane().setBackground(new Color(255, 153, 255));
		frame.getContentPane().setFont(new Font("微软雅黑", Font.BOLD, 18));
		frame.setBounds(500, 300, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("欢迎来到本KTV找回密码系统");
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u95EE\u9898\u4E00:\u8C01\u662F\u88AB\u7C89\u4E1D\u79F0\u4E3A\"\u80A5\u8F69\",\"\u8F69\u53D4\"\u7684\u6B4C\u624B?\r\n");
		label.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label.setBounds(26, 13, 392, 18);
		frame.getContentPane().add(label);
		
		tf1 = new JTextField();
		tf1.setFont(new Font("华文楷体", Font.BOLD, 15));
		tf1.setBounds(99, 39, 226, 24);
		tf1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(tf1);
		tf1.setColumns(10);
		
		JLabel label_1 = new JLabel("\u95EE\u9898\u4E8C:\u8C01\u662F\u4E8C\u6B21\u5143\u91CC\u6700\u53EF\u7231\u7684\u5973\u4EBA?");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_1.setBounds(48, 76, 294, 18);
		frame.getContentPane().add(label_1);
		
		tf2 = new JTextField();
		tf2.setFont(new Font("华文楷体", Font.BOLD, 15));
		tf2.setBounds(99, 106, 226, 24);
		tf2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(tf2);
		tf2.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7528\u6237\u540D:");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_2.setBounds(26, 164, 59, 18);
		frame.getContentPane().add(label_2);
		
		userName = new JTextField();
		userName.setFont(new Font("华文楷体", Font.BOLD, 15));
		userName.setBounds(99, 158, 226, 35);
		userName.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(userName);
		userName.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");		
		btnNewButton.setBackground(new Color(255, 204, 204));
		btnNewButton.addActionListener(					//校验
				new Forget_confirm()
				);
		btnNewButton.setFont(new Font("楷体", Font.BOLD, 18));
		btnNewButton.setBounds(75, 206, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.setBackground(new Color(255, 255, 255));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button.setFont(new Font("楷体", Font.BOLD, 18));
		button.setBounds(229, 206, 113, 27);
		frame.getContentPane().add(button);
	}

}
