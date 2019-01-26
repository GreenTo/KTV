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
				if(!"房间号".equals(number) && !"时间/h".equals(hour)) {	//判断输入是否为空
					room = Integer.parseInt(number);		//获得房间号
					time = Double.parseDouble(hour);		//获得订房时间
					double money = 0;						//看余额是否充足
					double count;							//根据房间号判断房间类型的订房价格
					
					Connection con = null;
					Statement st = null;
					ResultSet rs = null;
					try {
						con = JdbcUtils.getConnection();
						st = con.createStatement();
						String str = "select roomNumber from users";
						rs = st.executeQuery(str);
						
						boolean b = true;
						while(rs.next()) {							//判断房间是否已经有人订了
							if(room == rs.getInt("roomNumber")) {
								b = false;
							}
						}
						if(b) {										//若没人订
							String str2 = "select money from users where name = '"+ MainBorder.name +"'";
							rs = st.executeQuery(str2);
							while(rs.next()) {						//获得用户表中的用户余额
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
								//若用户余额不足则订房失败
								new Client_tips(MainBorder.frame, "用户余额不足,请及时充钱!");
							}else {
//								String sql2 = "update users set roomNumber = '"+ room +"' , time = '"+ time +"', isThrough = true "
//										+ " where name = '"+ MainBorder.name +"' and roomNumber = 0";
								String sql1 = "select roomNumber from users where name = '"+ MainBorder.name +"'";
								rs = st.executeQuery(sql1);
								int r = 0;
								while(rs.next()) {
									r = rs.getInt("roomNumber");
								}
								//判断本人是否已经订房
								if(r == 0) {
									Socket socket = new Socket("127.0.0.1", 6666);
									BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
									PrintStream ps = new PrintStream(socket.getOutputStream());
									ps.println("用户:" + MainBorder.name + "  订房:" + number + "  时间:" + hour + "/h");
									new Client_tips(MainBorder.frame, "订房申请未处理,请等待!");
									
									//从服务端获取订房申请的批准
									if("true".equals(br.readLine())) {
										String sql = "update users set roomNumber = '"+ room +"' , time = '"+ time +"', isThrough = true "
												+ " where name = '"+ MainBorder.name +"' and roomNumber = 0";
										st.executeUpdate(sql);
										new Client_tips(MainBorder.frame, "订房申请已通过,订房成功!");
										
										//将订房信息录入数据库的预订记录表
										String sql2 = "insert into record(name,room,money,time) values('"+ MainBorder.name +"',"
												+ "'"+ room +"','" + room/100*100*time + "','"+ time +"')";
										
										//更新数据库的房间表的信息
										String sql3 = "update room set book = true where roomName = '"+ room +"'";
										st.executeUpdate(sql2);
										st.executeUpdate(sql3);
										MainBorder.bookedMessage.setVisible(true);
										MainBorder.book.setVisible(false);		//使订房按钮不可见,订房信息可见
										MainBorder.bookedMessage.setText("您已订 "+ room +" 号房间 ");
										new MainBorder_refreshBook();		//刷新订房信息
										new MainBorder_record();			//刷新订房记录
									}else {
										new Client_tips(MainBorder.frame, "订房申请未通过");
									}
									socket.close();
								}else {
									new Client_tips(MainBorder.frame, "订房失败,一人只许订一间!");
								}
							}
						}else {
							new Client_tips(MainBorder.frame, "此房间已有人预订!");
						}
					}catch(Exception s) {
						s.printStackTrace();
					}finally {
						MainBorder.cb.setSelectedIndex(0);
						MainBorder.comboBox.setSelectedIndex(0);
						JdbcUtils.release(con, st, rs);
					}
				}else {										
					//若输入的信息为空
					new Client_tips(MainBorder.frame, "请输入房间号和订房时间!");
				}
			}
		}).start();
	}

}
