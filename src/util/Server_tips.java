package util;


import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Server_tips extends JDialog {

	public Server_tips(JFrame frame,String str) {
		super(frame, str, true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Server_tips.class.getResource("/images/ktv2.jpg")));
		setBounds(800, 200, 450, 38);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);
	}

}
