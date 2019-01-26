package client;


import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;

import client_function.MainBorder_Update;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class UpdateBorder {

	public static JFrame frmKtv;
	public static JLabel label ;
	public String str;
	public static JTextField textField;
	/**
	 * Create the application.
	 */
	public UpdateBorder() {
		
	}
	public UpdateBorder(String str) {
		this.str = str;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKtv = new JFrame("尊敬的 " + MainBorder.name + ",有更新消息");
		frmKtv.setTitle("KTV\u5BA2\u6237\u7AEF\u66F4\u65B0\u754C\u9762");
		frmKtv.getContentPane().setBackground(new Color(255, 204, 102));
		frmKtv.setBounds(100, 200, 450, 300);
		frmKtv.setVisible(true);
		frmKtv.setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateBorder.class.getResource("/images/ktv.jpg")));
		frmKtv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKtv.getContentPane().setLayout(null);
		
		label = new JLabel("\u66F4\u65B0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("楷体", Font.BOLD, 18));
		label.setBounds(14, 29, 376, 24);
		label.setText(str);
		frmKtv.getContentPane().add(label);
		
		JButton btnNewButton = new JButton("\u66F4\u65B0");//更新
		btnNewButton.addActionListener(
				new MainBorder_Update()
				);
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(69, 170, 113, 43);
		frmKtv.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("\u53D6\u6D88");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmKtv.dispose();
			}
		});
		button.setFont(new Font("微软雅黑", Font.BOLD, 18));
		button.setBackground(Color.WHITE);
		button.setBounds(243, 170, 113, 43);
		frmKtv.getContentPane().add(button);
	}
}

