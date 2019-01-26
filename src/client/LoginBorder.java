package client;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import client_function.Login_confirm;
import java.awt.Toolkit;

public class LoginBorder{//��¼����
	public static JFrame frame;
	public static JTextField tf1;				//�û���
	public static JTextField tf2;				//����

	/**
	 * Initialize the contents of the frame.
	 */
	public LoginBorder() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginBorder.class.getResource("/images/ktv.jpg")));
		frame.setTitle("��ӭ������KTV��¼ϵͳ");
		frame.getContentPane().setBackground(new Color(255, 255, 204));
		frame.setBounds(500, 300, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tf1 = new JTextField();
		tf1.setFont(new Font("���Ŀ���", Font.BOLD, 15));
		tf1.setBounds(156, 77, 160, 24);
		frame.getContentPane().add(tf1);
		tf1.setColumns(10);
		tf1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		tf2 = new JTextField();
		tf2.setFont(new Font("���Ŀ���", Font.BOLD, 15));
		tf2.setBounds(156, 132, 160, 24);
		tf2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(tf2);
		tf2.setColumns(10);
		
		JButton bt1 = new JButton("ע��");
		bt1.setForeground(new Color(0, 0, 0));
		bt1.setBackground(new Color(153, 255, 153));
		bt1.setFont(new Font("����", Font.BOLD, 18));
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//�������ע�����
				new RegisterBorder();
			}
		});
		bt1.setBounds(30, 180, 102, 27);
		frame.getContentPane().add(bt1);
		
		JButton bt2 = new JButton("��¼");					//��¼����
		bt2.setForeground(new Color(255, 102, 102));
		bt2.setFont(new Font("����",Font.BOLD,18));
		bt2.setBackground(new Color(255, 255, 255));
		bt2.setBounds(166, 180, 109, 27);
//		bt2.setBorder(new RoundBorder());
		bt2.addActionListener(
				new Login_confirm()
				);
		frame.getContentPane().add(bt2);
		
		JLabel label = new JLabel("�û���:");
		label.setFont(new Font("΢���ź�", Font.BOLD, 18));
		label.setBounds(77, 80, 72, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("����:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("΢���ź�", Font.BOLD, 18));
		label_1.setBounds(70, 135, 72, 18);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("��¼����");
		label_2.setFont(new Font("����", Font.BOLD, 18));
		label_2.setBounds(182, 26, 93, 18);
		frame.getContentPane().add(label_2);
		
		JButton btnNewButton = new JButton("\u4FEE\u6539\u5BC6\u7801\r\n");//�޸����빦��
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReviseBorder();
			}
		});
		btnNewButton.setFont(new Font("����", Font.BOLD, 18));
		btnNewButton.setBounds(301, 180, 117, 27);
		btnNewButton.setBackground(new Color(204, 255, 255));
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5FD8\u8BB0\u5BC6\u7801");//�������빦��
		btnNewButton_1.setBackground(new Color(255, 204, 255));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ForgetBorder();
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.BOLD, 18));
		btnNewButton_1.setBounds(156, 213, 131, 27);
		frame.getContentPane().add(btnNewButton_1);
		frame.setVisible(true);
	}
	

}

