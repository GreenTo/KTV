package client_function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainBorder_login {
	public Socket socket;
	
	public MainBorder_login(String name, boolean b) throws IOException {
		socket = new Socket("127.0.0.1", 4444);
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream ps = new PrintStream(socket.getOutputStream());
		
		ps.println(name);
		ps.println(b);
		socket.close();
	}
}
