package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
	public static String url = "jdbc:mysql://localhost:3306/Ktv?characterEncoding=GBK&&useSSL=true"; 
	static{						//调用此类时只加载一次驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,"root","980715");
	}
	
	
	public static void release(Connection con,Statement st,ResultSet rs){
		
		if(rs!=null){
			try{
				rs.close();   //throw new 
			}catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(st!=null){
			try{
				st.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			st = null;
		}
		if(con!=null){
			try{
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void closePs(PreparedStatement ps) {
		if(ps != null) {
			try {
				ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}	
