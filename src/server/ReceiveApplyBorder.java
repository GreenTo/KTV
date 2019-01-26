package server;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class ReceiveApplyBorder {			//接收客户端的订房申请后返回成功订房信息

	private JFrame frame;
	BufferedReader br;
	PrintStream ps;
	private JLabel label;
	
	public ReceiveApplyBorder(Socket socket) {
		initialize(socket);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Socket socket) {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ReceiveApplyBorder.class.getResource("/images/ktv2.jpg")));
		frame.getContentPane().setBackground(new Color(255, 255, 204));
		frame.setTitle("\u9884\u8BA2\u623F\u95F4\u7533\u8BF7\r\n");
		frame.setBounds(800, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JButton bt = new JButton("\u63A5\u53D7");
		JButton button = new JButton("\u62D2\u7EDD");
		
		label = new JLabel("");
		label.setFont(new Font("楷体", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(14, 43, 404, 43);
		frame.getContentPane().add(label);
		
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ps = new PrintStream(socket.getOutputStream());
			
			label.setText(br.readLine());
//			tf.setText(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		bt.setBackground(new Color(255, 255, 255));
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//接受申请
				ps.println("true");
				frame.dispose();
			}
		});
		bt.setFont(new Font("微软雅黑", Font.BOLD, 18));
		bt.setBounds(71, 156, 113, 27);
		frame.getContentPane().add(bt);
		
		button = new JButton("\u62D2\u7EDD");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//拒绝申请
				ps.println("false");
				frame.dispose();
			}
		});
		button.setFont(new Font("微软雅黑", Font.BOLD, 18));
		button.setBackground(Color.WHITE);
		button.setBounds(247, 156, 113, 27);
		frame.getContentPane().add(button);
		
	}
}
