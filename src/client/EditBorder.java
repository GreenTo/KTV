package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import client_function.EditBorder_confirm;
import util.JdbcUtils;

import java.awt.Button;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class EditBorder {

	public static JFrame frame;
	public static JLabel userName;
	public static String name;
	public static JTextField telephone;
	public static JTextField idCard;
	public static JTextField userAddress;

	public void message() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			st = con.createStatement();
			String sql = "select tele,Id,address from users where name = '"+ EditBorder.name +"'";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				EditBorder.telephone.setText( rs.getString("tele")); 
				EditBorder.idCard.setText(rs.getString("Id")); 
				EditBorder.userAddress.setText(rs.getString("address"));
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}finally {
			JdbcUtils.release(con, st, rs);
		}
	}

	/**
	 * Create the application.
	 */
	public EditBorder(String name) {
		this.name = name;
		initialize();
		message();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EditBorder.class.getResource("/images/ktv.jpg")));
		frame.getContentPane().setBackground(new Color(204, 204, 255));
		frame.setBounds(200, 300, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		userName = new JLabel(name);
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		userName.setFont(new Font("楷体", Font.BOLD, 16));
		userName.setBounds(28, 13, 152, 34);
		frame.getContentPane().add(userName);
		
		JRadioButton radioButton = new JRadioButton("\u7537");
		radioButton.setBackground(new Color(255, 255, 204));
		radioButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		radioButton.setBounds(252, 17, 52, 27);
		frame.getContentPane().add(radioButton);
		
		JRadioButton rdbtnNv = new JRadioButton("\u5973");
		rdbtnNv.setBackground(new Color(255, 255, 204));
		rdbtnNv.setFont(new Font("微软雅黑", Font.BOLD, 15));
		rdbtnNv.setBounds(310, 17, 52, 27);
		frame.getContentPane().add(rdbtnNv);
		
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton);
		group.add(rdbtnNv);
		
		telephone = new JTextField();
		telephone.setFont(new Font("华文楷体", Font.BOLD, 15));
		telephone.setColumns(10);
		telephone.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		telephone.setBounds(123, 62, 239, 24);
		frame.getContentPane().add(telephone);
		
		JLabel label = new JLabel("\u7535\u8BDD\u53F7\u7801:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("楷体", Font.BOLD, 16));
		label.setBounds(28, 64, 81, 19);
		frame.getContentPane().add(label);
		
		idCard = new JTextField();
		idCard.setFont(new Font("华文楷体", Font.BOLD, 15));
		idCard.setColumns(10);
		idCard.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		idCard.setBounds(123, 111, 239, 24);
		frame.getContentPane().add(idCard);
		
		JLabel label_1 = new JLabel("\u8EAB\u4EFD\u8BC1:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("楷体", Font.BOLD, 16));
		label_1.setBounds(28, 114, 81, 19);
		frame.getContentPane().add(label_1);
		
		userAddress = new JTextField();
		userAddress.setFont(new Font("华文楷体", Font.BOLD, 15));
		userAddress.setColumns(10);
		userAddress.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		userAddress.setBounds(123, 160, 239, 24);
		frame.getContentPane().add(userAddress);
		
		JLabel label_2 = new JLabel("\u5BB6\u5EAD\u5730\u5740:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("楷体", Font.BOLD, 16));
		label_2.setBounds(28, 163, 81, 19);
		frame.getContentPane().add(label_2);
		
		JButton button = new JButton("\u7F16\u8F91");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("bbbbbbbbbbbbbb");
				new EditBorder_confirm();
			}
		});
		button.setForeground(Color.BLACK);
		button.setFont(new Font("楷体", Font.BOLD, 18));
		button.setBackground(new Color(153, 255, 153));
		button.setBounds(63, 213, 102, 27);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("楷体", Font.BOLD, 18));
		button_1.setBackground(new Color(153, 255, 153));
		button_1.setBounds(260, 215, 102, 27);
		frame.getContentPane().add(button_1);
	}
}
