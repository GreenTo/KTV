package client;


import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import client_function.Revise_confirm;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ReviseBorder {

	public static JFrame frame;
	public static JTextField userName;
	public static JTextField oldPassword;
	public static JTextField newPassword;
	public static JButton btnNewButton;

	public ReviseBorder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ReviseBorder.class.getResource("/images/ktv.jpg")));
		frame.setBounds(500, 300, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("欢迎来到本KTV密码修改系统!");
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D:");
		label.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label.setBounds(44, 50, 72, 18);
		frame.getContentPane().add(label);
		
		userName = new JTextField();
		userName.setFont(new Font("华文楷体", Font.BOLD, 15));
		userName.setBounds(130, 49, 206, 24);
		userName.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(userName);
		userName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u539F\u5BC6\u7801:\r\n");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lblNewLabel.setBounds(44, 104, 72, 18);
		frame.getContentPane().add(lblNewLabel);
		
		oldPassword = new JTextField();
		oldPassword.setFont(new Font("华文楷体", Font.BOLD, 15));
		oldPassword.setBounds(130, 103, 206, 24);
		oldPassword.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(oldPassword);
		oldPassword.setColumns(10);
		
		JLabel label_1 = new JLabel("\u65B0\u5BC6\u7801:");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_1.setBounds(44, 150, 72, 18);
		frame.getContentPane().add(label_1);
		
		newPassword = new JTextField();
		newPassword.setFont(new Font("华文楷体", Font.BOLD, 15));
		newPassword.setBounds(130, 149, 206, 24);
		newPassword.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(newPassword);
		newPassword.setColumns(10);
		
		JButton button = new JButton("\u786E\u5B9A\u4FEE\u6539");
		button.setBackground(new Color(255, 204, 255));
		button.addActionListener(
				new Revise_confirm()
		);
		button.setFont(new Font("楷体", Font.BOLD, 18));
		button.setBounds(75, 197, 113, 27);
//		button.setBorder(new RoundBorder());
		frame.getContentPane().add(button);
		
		btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("楷体", Font.BOLD, 18));
		btnNewButton.setBounds(244, 199, 113, 27);
		frame.getContentPane().add(btnNewButton);
	}

}
