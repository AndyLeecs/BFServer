package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**  
* 数据库，未使用
*  
* @author Andy
* @version  
*/

public class DBUtil
{
	private static final String URL = "jdbc:mysql://localhost:3306/mydb";
	private static final String NAME = "root";
	private static final String PASSWORD = "root";
	
	private static Connection conn = null;

	private static Statement stmt;
	int insertValue=-1;
	//加载驱动，连接数据库的静态代码段
	static{
		try{
			//加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//获得数据库的连接
			conn = DriverManager.getConnection(URL, NAME, PASSWORD);
			
			stmt = conn.createStatement();
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
	

	public static ResultSet executeQuery(String queryString) throws SQLException
	{
		// TODO Auto-generated method stub
        ResultSet rs=stmt.executeQuery(queryString);
        return rs;

		
	}
	
    public int insert(String sql)throws Exception{
        insertValue=stmt.executeUpdate(sql);
        return insertValue;
    }

}
