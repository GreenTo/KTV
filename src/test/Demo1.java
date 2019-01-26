package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

public class Demo1 {

	public static void main(String[] args) throws SQLException,ClassNotFoundException{
		/*
		 * DriverManager:�����������࣬getConnection��url,���ݿ��¼��,���룩��������ӵķ���
		   jdbc:mysql://localhost:3306/mydb�ĸ�ʽ����
		   jdbc:mysql://������ַ���˿ں�/���ݿ�����
		      �������ݿ����������ģ�url���Ϊ��
		   jdbc:mysql://127.0.0.1:3306/mydb?characterEncoding=GBK
		 */
		
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        try {
        // 1.�������ݷ�������
           Class.forName("com.mysql.jdbc.Driver");
        //2.���ӵ�����"��"��ȥ
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ktv?characterEncoding=GBK&&useSSL=true", "root", "980715");
        if(!conn.isClosed())
        	System.out.println("Succeeded connecting to the Database!");

        //3.����SQL����
        state = conn.createStatement();
        //�����б�
        /*String s="insert into users values('����',11,100,90,1)";
        state.executeUpdate(s);*/
        rs = state.executeQuery("select * from users");
        while(rs.next()) {
        	System.out.println(rs.getString("name"));
        	System.out.println(rs.getInt("roomNumber"));
        	System.out.println(rs.getDouble("money"));
        	System.out.println(rs.getInt("time"));
        	System.out.println(rs.getBoolean("isThrough"));
        }
        }catch (SQLException e) {
        	e.printStackTrace();
        }finally {
        	if(rs != null) {
        		try {
        			rs.close();
        		}catch(SQLException e) {
        			e.printStackTrace();
        		}finally {
        			rs = null;
        		}
        	}
        	if(state != null) {
        		try {
        			state.close();
        		}catch(SQLException e) {
        			e.printStackTrace();
        		}finally {
        			state = null;
        		}
        	}
        	if(conn != null) {
        		try {
        			conn.close();
        		}catch(SQLException e) {
        			e.printStackTrace();
        		}finally {
        			conn = null;
        		}
        	}
        }
        
       /* rs.close();
        state.close();
        conn.close();
        
        }catch(ClassNotFoundException e) {   
        	     //���ݿ��������쳣����
          System.out.println("Sorry,can`t find the Driver!");   
          e.printStackTrace();   
         } catch(SQLException e) {
             //���ݿ�����ʧ���쳣����
             e.printStackTrace();  
         }catch (Exception e) {
        	 e.printStackTrace();
         }finally{
        	 System.out.println("���ݿ����ݳɹ���ȡ����");
         }
*/
        /*
         * Javaʹ��mysql-jdbc����MySQL�������¾��棺
����		   Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
����		       ��mysql�����ַ���url�м���ssl=true����false���ɣ�������ʾ��
����		   url=jdbc:mysql://127.0.0.1:3306/framework?characterEncoding=utf8&useSSL=true
         */
	}

}
