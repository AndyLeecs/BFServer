package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**  
* 类说明   
*  
* @author Andy
* @version  
*/

public class DBUtil
{
	private static final String URL = "jdbc:mysql://localhost:3306/demo_jdbc";
	private static final String NAME = "root";
	private static final String PASSWORD = "root";
	
	private static Connection conn = null;
	
	//加载驱动，连接数据库的静态代码段
	static{
		try{
			//加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//获得数据库的连接
			conn = DriverManager.getConnection(URL, NAME, PASSWORD);
			
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//对外提供一个方法获取数据库连接
	public static Connection getConnection(){
		return conn;
	}
	
	public static void main(String args[]) throws SQLException{
		//通过数据库的连接操作数据库，实现增删改查
		Statement stmt = conn.createStatement();
		
		
	}
}
