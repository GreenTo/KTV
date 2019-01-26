package client_function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import client.MainBorder;
import util.Client_tips;
import util.JdbcUtils;

public class MainBorder_book implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String number = MainBorder.cb.getSelectedItem().toString().trim();
				String hour = MainBorder.comboBox.getSelectedItem().toString().trim();
				
				int room = 0;
				double time = 0;
				if(!"�����".equals(number) && !"ʱ��/h".equals(hour)) {	//�ж������Ƿ�Ϊ��
					room = Integer.parseInt(number);		//��÷����
					time = Double.parseDouble(hour);		//��ö���ʱ��
					double money = 0;						//������Ƿ����
					double count;							//���ݷ�����жϷ������͵Ķ����۸�
					
					Connection con = null;
					Statement st = null;
					ResultSet rs = null;
					try {
						con = JdbcUtils.getConnection();
						st = con.createStatement();
						String str = "select roomNumber from users";
						rs = st.executeQuery(str);
						
						boolean b = true;
						while(rs.next()) {							//�жϷ����Ƿ��Ѿ����˶���
							if(room == rs.getInt("roomNumber")) {
								b = false;
							}
						}
						if(b) {										//��û�˶�
							String str2 = "select money from users where name = '"+ MainBorder.name +"'";
							rs = st.executeQuery(str2);
							while(rs.next()) {						//����û����е��û����
								money = rs.getDouble("money");
							}
							if(room < 200) {
								count = 100;
							}else if(room > 300) {
								count = 300;
							}else {
								count = 200;
							}
							if(money < count*time || money <= 0) {
								//���û������򶩷�ʧ��
								new Client_tips(MainBorder.frame, "�û�����,�뼰ʱ��Ǯ!");
							}else {
//								String sql2 = "update users set roomNumber = '"+ room +"' , time = '"+ time +"', isThrough = true "
//										+ " where name = '"+ MainBorder.name +"' and roomNumber = 0";
								String sql1 = "select roomNumber from users where name = '"+ MainBorder.name +"'";
								rs = st.executeQuery(sql1);
								int r = 0;
								while(rs.next()) {
									r = rs.getInt("roomNumber");
								}
								//�жϱ����Ƿ��Ѿ�����
								if(r == 0) {
									Socket socket = new Socket("127.0.0.1", 6666);
									BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
									PrintStream ps = new PrintStream(socket.getOutputStream());
									ps.println("�û�:" + MainBorder.name + "  ����:" + number + "  ʱ��:" + hour + "/h");
									new Client_tips(MainBorder.frame, "��������δ����,��ȴ�!");
									
									//�ӷ���˻�ȡ�����������׼
									if("true".equals(br.readLine())) {
										String sql = "update users set roomNumber = '"+ room +"' , time = '"+ time +"', isThrough = true "
												+ " where name = '"+ MainBorder.name +"' and roomNumber = 0";
										st.executeUpdate(sql);
										new Client_tips(MainBorder.frame, "����������ͨ��,�����ɹ�!");
										
										//��������Ϣ¼�����ݿ��Ԥ����¼��
										String sql2 = "insert into record(name,room,money,time) values('"+ MainBorder.name +"',"
												+ "'"+ room +"','" + room/100*100*time + "','"+ time +"')";
										
										//�������ݿ�ķ�������Ϣ
										String sql3 = "update room set book = true where roomName = '"+ room +"'";
										st.executeUpdate(sql2);
										st.executeUpdate(sql3);
										MainBorder.bookedMessage.setVisible(true);
										MainBorder.book.setVisible(false);		//ʹ������ť���ɼ�,������Ϣ�ɼ�
										MainBorder.bookedMessage.setText("���Ѷ� "+ room +" �ŷ��� ");
										new MainBorder_refreshBook();		//ˢ�¶�����Ϣ
										new MainBorder_record();			//ˢ�¶�����¼
									}else {
										new Client_tips(MainBorder.frame, "��������δͨ��");
									}
									socket.close();
								}else {
									new Client_tips(MainBorder.frame, "����ʧ��,һ��ֻ��һ��!");
								}
							}
						}else {
							new Client_tips(MainBorder.frame, "�˷���������Ԥ��!");
						}
					}catch(Exception s) {
						s.printStackTrace();
					}finally {
						MainBorder.cb.setSelectedIndex(0);
						MainBorder.comboBox.setSelectedIndex(0);
						JdbcUtils.release(con, st, rs);
					}
				}else {										
					//���������ϢΪ��
					new Client_tips(MainBorder.frame, "�����뷿��źͶ���ʱ��!");
				}
			}
		}).start();
	}

}
