package util;


import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Client_tips extends JDialog {

	public Client_tips(JFrame frame,String str) {
		super(frame, str, true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Client_tips.class.getResource("/images/ktv.jpg")));
		setBounds(500, 300, 450, 38);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);
	}

}
