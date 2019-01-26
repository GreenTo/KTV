package server_function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import client_function.MainBorder_refreshBook;
import server.ServerBorder;
import util.Server_tips;
import util.JdbcUtils;

public class Remind implements Runnable {

	BufferedReader br;
	PrintStream ps;
	
	@Override
	public void run() {
		Timer timer = new Timer();
		int r;
		try {
			ServerBorder.socket2 = ServerBorder.server2.accept();		//��Ӧ�ͻ��˵�����
			br = new BufferedReader(new InputStreamReader(ServerBorder.socket2.getInputStream()));
			ps = new PrintStream(ServerBorder.socket2.getOutputStream());
			int second = Integer.parseInt(br.readLine());		//�ӿͻ��˽��ն���ʱ��
//			System.out.println("second = " + second);
			String room  = br.readLine();						//�ӿͻ��˽��շ����
//			System.out.println("room = " + room);
			r = Integer.parseInt(room);
			String name = br.readLine();						//�ӿͻ��˽����û���
			new Server_tips(ServerBorder.frame, name + " �ѵ��� " + room + " ����!");
			if(second != 0) {
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						new Server_tips(ServerBorder.frame, room + "������û� " + name + " �Ķ���ʱ���ѵ�!");
						Connection con = null;
						Statement st = null;
						ResultSet rs = null;
						try {
							con = JdbcUtils.getConnection();
							st = con.createStatement();
							//�����û�������ݿ���Ϣ,���û��������
							String sql = "update users set roomNumber = 0, isThrough = false, time = 0 where name = '"+ name +"'";
							st.executeUpdate(sql);
							//���ķ��������ݿ���Ϣ,����Ԥ��ʱ��ķ����ڿ�
							String sql2 = "update room set book = false where roomName = '"+ r +"'";
							st.executeUpdate(sql2);
						}catch(SQLException s) {
							s.printStackTrace();
						}finally {
							JdbcUtils.release(con, st, rs);
						}
					}
				},second);
			}else {
				System.out.println("second = " + second);	//��������
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
