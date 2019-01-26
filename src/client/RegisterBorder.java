package client;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import client_function.Register_confirm;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class RegisterBorder {	//注册界面

	public static JFrame frmktv;
	public static JTextField tf1;
	public static JTextField tf2;
	public JButton bt2;
	private JLabel label;

	public RegisterBorder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmktv = new JFrame();
		frmktv.setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterBorder.class.getResource("/images/ktv.jpg")));
		frmktv.getContentPane().setBackground(new Color(204, 255, 255));;
		frmktv.setVisible(true);
		frmktv.setTitle("欢迎来到KTV注册界面");
		frmktv.setBounds(500, 300, 450, 300);
		frmktv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmktv.getContentPane().setLayout(null);
		
		tf1 = new JTextField();
		tf1.setFont(new Font("华文楷体", Font.BOLD, 15));
		tf1.setBounds(150, 63, 169, 24);
		tf1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frmktv.getContentPane().add(tf1);
		tf1.setColumns(10);
		
		tf2 = new JTextField();
		tf2.setFont(new Font("华文楷体", Font.BOLD, 15));
		tf2.setBounds(150, 117, 169, 24);
		tf2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frmktv.getContentPane().add(tf2);
		tf2.setColumns(10);
		
		JLabel jl1 = new JLabel("用户名");
		jl1.setHorizontalAlignment(SwingConstants.CENTER);
		jl1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		jl1.setBounds(64, 64, 72, 18);
		frmktv.getContentPane().add(jl1);
		
		JLabel jl2 = new JLabel("密码");
		jl2.setFont(new Font("微软雅黑", Font.BOLD, 18));
		jl2.setHorizontalAlignment(SwingConstants.CENTER);
		jl2.setBounds(64, 118, 72, 18);
		frmktv.getContentPane().add(jl2);
		
		JButton bt1 = new JButton("确定");			//确定功能
		bt1.setBackground(new Color(255, 255, 204));
		bt1.setForeground(new Color(255, 102, 153));
		bt1.setFont(new Font("楷体", Font.BOLD, 18));
		bt1.addActionListener(
				new Register_confirm()
				);
		bt1.setBounds(75, 171, 122, 27);
//		bt1.setBorder(new RoundBorder());
		frmktv.getContentPane().add(bt1);
		
		bt2 = new JButton("返回");
		bt2.setBackground(new Color(255, 255, 255));
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmktv.dispose();
			}
		});
		bt2.setFont(new Font("楷体", Font.BOLD, 18));
		bt2.setBounds(267, 171, 113, 27);
		frmktv.getContentPane().add(bt2);
		
		label = new JLabel("\u6CE8\u518C\u754C\u9762");
		label.setFont(new Font("楷体", Font.BOLD, 20));
		label.setBounds(177, 13, 84, 37);
		frmktv.getContentPane().add(label);
	}
}
