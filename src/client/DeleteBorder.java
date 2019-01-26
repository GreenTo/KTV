package client;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import client_function.Delete_confirm;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class DeleteBorder {				//确定是否删除预订记录信息

	public static JFrame frame;

	public static String name;
	/**
	 * Create the application.
	 */
	public DeleteBorder(String name) {
		DeleteBorder.name = name;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteBorder.class.getResource("/images/ktv.jpg")));
		frame.getContentPane().setBackground(new Color(255, 204, 153));
		frame.setBounds(500, 300, 369, 228);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u60A8\u786E\u5B9A\u8981\u5220\u9664\u9884\u8BA2\u8BB0\u5F55\u5417?");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label.setBounds(14, 43, 323, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5220\u9664\u540E\u4E0D\u53EF\u6062\u590D!\u8BF7\u8C28\u614E\u64CD\u4F5C!");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_1.setBounds(65, 74, 244, 18);
		frame.getContentPane().add(label_1);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.setBackground(new Color(255, 204, 153));
		button.addActionListener(					//确定删除功能
				new Delete_confirm()
				);
		button.setFont(new Font("楷体", Font.BOLD, 18));
		button.setBounds(45, 128, 113, 27);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88\r\n");
		button_1.setBackground(new Color(255, 255, 255));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setFont(new Font("楷体", Font.BOLD, 18));
		button_1.setBounds(196, 128, 113, 27);
		frame.getContentPane().add(button_1);
	}
}
